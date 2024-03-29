package com.tfg.wekaWeb.controller;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.wekaWeb.dao.AlgoritmosRepository;
import com.tfg.wekaWeb.dao.SesionTrabajoRepository;
import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.Filtros;
import com.tfg.wekaWeb.service.AlgoritmosService;
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.FiltrosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UtilsService;
import com.tfg.wekaWeb.service.wekaService;

import weka.attributeSelection.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.pmml.jaxbbindings.SupportVectorMachine;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

@Controller
public class wekaController {

	private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	private FicherosService ficherosService;

	@Autowired
	private AlgoritmosRepository algoritmoRepository;

	@Autowired
	private SesionTrabajoService sessionService;

	@Autowired
	private FiltrosService FiltrosService;

	@Autowired
	AlgoritmosService algoritmosService;
	
	@Autowired
	wekaService  wekaService;

	HttpSession session;
	private UtilsService utils = new UtilsService();

	@GetMapping("/weka")
	public String wekaFile(HttpServletRequest request, int test, int train, RedirectAttributes model) {
		session = utils.isValidSession(request);
		if (session == null)
			return "redirect:/sessionCaducada";

		Optional<Ficheros> f = ficherosService
				.getFichero(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
		try {
			/* Cargamos el fichero con los datos */
			BufferedReader datafile = new BufferedReader(new FileReader(f.get().getRuta()));
			Instances data = new Instances(datafile);
			Instances dataClus = data;
			/* Seleccionamos la columna de los datos a estimar */
			String removeData = session.getAttribute("filtroActivoRemove").toString();
			String[] val = removeData.split(",");
			
			if (val.length > 0 && val != null && removeData != "" && removeData.length() > 0) {
				for (int i = 0; i < val.length; i++) {
					data.remove(Integer.parseInt(val[i]));
					dataClus.remove(Integer.parseInt(val[i]));
				}
			}
			data.setClassIndex(data.numAttributes() - 1);
			
			
			/* Creamos dos grupos uno de entreno y otro de test */

			Instances[] trainingSplits = new Instances[train];
			Instances[] testingSplits = new Instances[test];

			for (int i = 0; i < train; i++) {
				trainingSplits[i] = data.trainCV(train, i);
			}

			for (int i = 0; i < test; i++) {
				testingSplits[i] = data.testCV(test, i);
			}
			int algoritmo = Integer.parseInt(session.getAttribute("algoritmoActivoId").toString());

			switch (algoritmo) {

			case 1: //BestFirst
				model.addFlashAttribute("resultado", ejecutarModelo(new BestFirst(), trainingSplits, testingSplits));
				break;
			case 2: //ScatterSearch
				model.addFlashAttribute("resultado", ejecutarModelo(new weka.attributeSelection.ScatterSearchV1(), trainingSplits, testingSplits));
				break;
			case 3: //RankSearch
				model.addFlashAttribute("resultado", ejecutarModelo(new Ranker(), trainingSplits, testingSplits));
				break;
			case 4: //simpleKmeans
				SimpleKMeans kMeans = new SimpleKMeans();
				kMeans.setSeed(10);
				kMeans.setPreserveInstancesOrder(true);
		        kMeans.setNumClusters(3);
				model.addFlashAttribute("resultado", ejecutarModeloClust(kMeans,dataClus));
				break;
			case 5: //Clustering Conceptual (COBWEB)
				model.addFlashAttribute("resultado", ejecutarModeloClust(new Cobweb(),dataClus));
				break;
			case 6: //Clustering Probabilistico (EM)
				model.addFlashAttribute("resultado", ejecutarModeloClust(new EM(),dataClus));
				break;
			case 7: //NaivesBayes
				model.addFlashAttribute("resultado", ejecutarModeloClasi(new NaiveBayes(), trainingSplits, testingSplits));
				break;
			case 8: //J48
				model.addFlashAttribute("resultado", ejecutarModeloClasi(new J48(), trainingSplits, testingSplits));
				break;
			case 9: //SVM
				model.addFlashAttribute("resultado", ejecutarModeloClasi(new weka.classifiers.rules.PART(), trainingSplits, testingSplits));
				break;
			}
			
			
			
			session.setAttribute("resutadosBool", "true");
			return "redirect:/resultados";

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		model.addFlashAttribute("error", "true");
		return "redirect:/resultados";
	}

	public String ejecutarModelo(ASSearch search, Instances[] trainingSplits, Instances[] testingSplits)
			throws Exception {
		ArrayList<Prediction> predictions = new ArrayList<Prediction>();
		weka.classifiers.meta.AttributeSelectedClassifier model =  new AttributeSelectedClassifier();
		model.setSearch(search);
		/* Entrenamos el modelo */
		for (int i = 0; i < trainingSplits.length; i++) {
			model.buildClassifier(trainingSplits[i]);
		}

		/* Testenamos el modelo */
		for (int i = 0; i < testingSplits.length; i++) {
			Evaluation evaluation = new Evaluation(testingSplits[i]);
			evaluation.evaluateModel(model, testingSplits[i]);
			predictions.addAll(evaluation.predictions());
		}

		/* Calculamos la exactitud del modelo */
		double accuracy = calculateAccuracy(predictions);
		String s = "Exactitud del modelo " + model.getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy) + search.toString();
		return s;
	}
	
	public String ejecutarModeloClasi(Classifier search, Instances[] trainingSplits, Instances[] testingSplits)
			throws Exception {
		ArrayList<Prediction> predictions = new ArrayList<Prediction>();
		/* Entrenamos el modelo */
		for (int i = 0; i < trainingSplits.length; i++) {
			search.buildClassifier(trainingSplits[i]);
		}

		/* Testenamos el modelo */
		for (int i = 0; i < testingSplits.length; i++) {
			Evaluation evaluation = new Evaluation(testingSplits[i]);
			evaluation.evaluateModel(search, testingSplits[i]);
			predictions.addAll(evaluation.predictions());
		}

		/* Calculamos la exactitud del modelo */
		double accuracy = calculateAccuracy(predictions);
		String s = "Exactitud del modelo " + search.getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy) +"\n" + search.toString();
		System.out.println(s); 
		return s;
	}
	
	public String ejecutarModeloClust(Clusterer search, Instances data)
			throws Exception {
		Remove remove = new Remove();
		String x = String.valueOf(data.numAttributes());
		System.out.println("data.numAttributes()"+ data.numAttributes());
		String[] options = new String[2];
        options[0] = "-R";                                    // "range"
        options[1] = x;
        remove.setOptions(options);                           // set options
        remove.setInputFormat(data);
        Instances newData = Filter.useFilter(data, remove);	
		search.buildClusterer(newData);
		

		ClusterEvaluation eval = new ClusterEvaluation();
		eval.setClusterer(search); 
		/* Testenamos el modelo */
			 eval.evaluateClusterer(newData);

		/* Calculamos la exactitud del modelo */
		String s = "# of clusters: " + eval.getNumClusters() + "-"+ eval.clusterResultsToString();
		System.out.println(s);
		return s;
	}

	public double calculateAccuracy(ArrayList<Prediction> predictions) {
		double correct = 0;

		for (Prediction np : predictions) {
			if (np.predicted() == np.actual())
				correct++;
		}

		return 100 * correct / predictions.size();
	}

	@RequestMapping(value = "/saveAlgoritmo", method = RequestMethod.POST)
	public String saveAlgoritmo(String optradio, ModelMap model, HttpServletRequest request) throws Exception {
		session = utils.isValidSession(request);
		if (session == null)
			return "redirect:/sessionCaducada";
		Integer idSession = Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString());
		Integer idAlgoritmo = Integer.parseInt(optradio);
		Optional<Algoritmos> alg = algoritmoRepository.findById(idAlgoritmo);
		sessionService.actualizarAlgoritmoSesion(idSession, idAlgoritmo,alg.get().getNombreAlg());		
		session.setAttribute("algoritmoActivo", true);
		session.setAttribute("sesionActivaIdAlgoritmo", true);
		session.setAttribute("algoritmoActivoId", idAlgoritmo);
		session.setAttribute("algoritmoActivoNombre", alg.get().getNombreAlg());
		return "redirect:/algoritmos";
	}

	@RequestMapping(value = "/saveFilters", method = RequestMethod.POST)
	public String saveFiltro(String params, String filtro, String optsArea, RedirectAttributes model,
			HttpServletRequest request) {

		Instances data = null;
		session = utils.isValidSession(request);
		if (session == null)
			return "redirect:/sessionCaducada";
		String filtroActivoid = session.getAttribute("filtroActivoId") != null ? session.getAttribute("filtroActivoId").toString() : null;
		int filtroid = 0;
		if( filtroActivoid != null ) {
			filtroid = Integer.parseInt(filtroActivoid);
		}
		
		Filtros filter = FiltrosService.guardarFiltro(filtroid, filtro, params, optsArea,
				Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString()),
				Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
		
		int filtroSelecionado = Integer.parseInt(filtro);
		String filtroActivoString = "";
		if (filtroSelecionado > 0 | filtroSelecionado <= 3) {
			filtroActivoString = "Supervisado";
		} else {
			filtroActivoString = "No Supervisado";
		}
		try {
			data = FiltrosService.aplicarfiltros(filter);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		sessionService.actualizarFiltroSesion(Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString()),
				filter.getIdFiltros(),filtroActivoString);
		//Gestionar filtro -> filter
		session.setAttribute("filtroActivo", true);
		session.setAttribute("filtroActivoId", filter.getIdFiltros());
		session.setAttribute("filtroActivoRemove",params);
		session.setAttribute("filtroActivoRemoveName", optsArea);
		session.setAttribute("filtroActivoTipo", filtroActivoString);
		System.out.println(data.toSummaryString());
		String dataSumary = data.toSummaryString();
		System.out.println(dataSumary);
		model.addFlashAttribute("filtroResultado", dataSumary);
		return "redirect:/filtro";
	}

	@RequestMapping(value = "/seleccionAtributos", method = RequestMethod.GET)
	public String selectAtributos(String optradio, HttpServletRequest request) throws IOException {
		session = utils.isValidSession(request);
		if (session == null)
			return "redirect:/sessionCaducada";
		File file = ficherosService
				.getFileByFichero(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
		BufferedReader datafile = new BufferedReader(new FileReader(file));
		Instances data = new Instances(datafile);
		AttributeSelection attSelection = new AttributeSelection();
		CfsSubsetEval eval = new CfsSubsetEval();

		if (optradio.equals("1")) {
			BestFirst search = new BestFirst();
			attSelection.setSearch(search);
		} else if (optradio.equals("2")) {
			GreedyStepwise search = new GreedyStepwise();
			// ScatterSearch search = new ScatterSearch();
			attSelection.setSearch(search);
		} else if (optradio.equals("3")) {
			Ranker search = new Ranker();
			attSelection.setSearch(search);
		}

		attSelection.setEvaluator(eval);
		try {
			attSelection.SelectAttributes(data);
			int[] attIndex = attSelection.selectedAttributes();
			List<String> listaAtributos = new ArrayList<String>();

			for (int i = 0; i < attIndex.length; i++) {
				data.get(attIndex[i]);
				listaAtributos.add(data.attribute(attIndex[i]).name());
			}
			session.setAttribute("listaAtributosFiltrosBol", true);
			session.setAttribute("listaAtributosFiltros", listaAtributos);
		} catch (Exception e) {
		}

		return "redirect:/filtro";
	}
	
	@RequestMapping(value = "/wekaLine", method = RequestMethod.GET)
	public String commandLine(HttpServletRequest request, String idFichero,String params,RedirectAttributes model) throws NumberFormatException, IOException {
		String resultado = wekaService.gestionarCommandLine(Integer.parseInt(idFichero), params);
		model.addFlashAttribute("resultCommand", resultado);
		return "redirect:/wekaCommand";
	}
	
	@RequestMapping(value = "/wekaCommand", method = RequestMethod.GET)
	public String returnWekaCommand(HttpServletRequest request,ModelMap model) {
		session = utils.isValidSession(request);
		if (session == null)
			return "redirect:/sessionCaducada";
		List<Ficheros> f = ficherosService.getFiles();
		if(f.size() >= 1) {
			model.addAttribute("listaFicheros",f);	
		}
		return "/wekaCommand";
	}


}
