package com.tfg.wekaWeb.controller;

import java.io.BufferedReader;
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

import weka.attributeSelection.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;

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
	
	@Autowired AlgoritmosService algoritmosService;
	
	HttpSession session;
	private UtilsService utils = new UtilsService();
	
	
	@GetMapping("/weka")
    public String  wekaFile(HttpServletRequest request,int test, int train, RedirectAttributes model) {
		session = utils.isValidSession(request);
		if(session == null) return "redirect:/sessionCaducada";
		
      Optional <Ficheros> f = ficherosService.getFichero(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
      try {
          /* Cargamos el fichero con los datos */
          BufferedReader datafile = new BufferedReader(new FileReader(f.get().getRuta()));
          Instances data = new Instances(datafile);
           
          /* Seleccionamos la columna de los datos a estimar */
          String removeData = session.getAttribute("filtroActivoRemove").toString();
          String[] val = removeData.split(";");
          
          if(val.length > 0) {
        	  for(int i = 0;i < val.length; i++) {
        		  data.remove(Integer.parseInt(val[i]));
        	  }
          }
          data.setClassIndex(data.numAttributes() - 1);
          /* Creamos dos grupos uno de entreno y otro de test */
          int numFolds=14;
          Instances[] trainingSplits=new Instances[train];
          Instances[] testingSplits= new Instances[test];
            
          for (int i = 0; i < train; i++) {
              trainingSplits[i] = data.trainCV(train, i);
              }
          
          for (int i = 0; i < test; i++) {
              testingSplits[i] = data.testCV(test, i);
              }
          	int algoritmo = Integer.parseInt(session.getAttribute("algoritmoActivoId").toString());
    
          	switch(algoritmo) {
          	
          	case 1:
          		model.addFlashAttribute("BestFirst",algoritmosService.bestFirst(data));
          	}
    
          return "redirect:/algoritmos";
          
      } catch (Exception ex) { ex.printStackTrace(); }
      	
      	model.addFlashAttribute("error","true");
      	return "redirect:/algoritmos";
    }
	
	public String ejecutarModelo(Classifier model, Instances[] trainingSplits, Instances[] testingSplits) throws Exception {
        ArrayList<Prediction> predictions = new ArrayList<Prediction>();
 
        /* Entrenamos el modelo */
        for (int i = 0; i < trainingSplits.length; i++) {
            model.buildClassifier(trainingSplits[i]);
            }
         
        /* Testenamos el modelo */
        for (int i = 0; i < testingSplits.length; i++) {
            Evaluation evaluation = new Evaluation(testingSplits[i]);
            evaluation.evaluateModel(model, testingSplits[i]);
            predictions.addAll( evaluation.predictions() );
            }
         
        /* Calculamos la exactitud del modelo */
        double accuracy = calculateAccuracy(predictions);
        return  "Exactitud del modelo " + model.getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy);
        }
     
    public double calculateAccuracy(ArrayList<Prediction> predictions) {
        double correct = 0;
  
        for (Prediction np:predictions) {
            if (np.predicted() == np.actual())
                correct++;
            }
  
        return 100 * correct / predictions.size();
        } 
    
    
    @RequestMapping(value="/saveAlgoritmo", method = RequestMethod.POST)
	public String saveAlgoritmo(String optradio,ModelMap model,HttpServletRequest request) throws Exception  {
			session = utils.isValidSession(request);
			if(session == null) return "redirect:/sessionCaducada";
			Integer idSession = Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString());
			Integer idAlgoritmo = Integer.parseInt(optradio);
			sessionService.actualizarAlgoritmoSesion(idSession, idAlgoritmo);
			Optional<Algoritmos> alg = algoritmoRepository.findById(idAlgoritmo);
			session.setAttribute("algoritmoActivo", true);
			session.setAttribute("algoritmoActivoId", idAlgoritmo);
			session.setAttribute("algoritmoActivoNombre", alg.get().getNombreAlg());
			return "redirect:/algoritmos";
    }
    
    @RequestMapping(value="/saveFilters", method = RequestMethod.POST)
	public String saveFiltro(String params,String filtro,String optsArea,ModelMap model,HttpServletRequest request){
            
    	session = utils.isValidSession(request);
    	if(session == null) return "redirect:/sessionCaducada";
    	Filtros filter = FiltrosService.guardarFiltro(Integer.parseInt(session.getAttribute("filtroActivoId").toString()),
    			filtro, params,optsArea,
    			Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString()),
    			Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
    	sessionService.actualizarFiltroSesion(Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString()), filter.getIdFiltros());
            int filtroSelecionado = Integer.parseInt(filtro);
            session = request.getSession(false);
            session.setAttribute("filtroActivo", true);
            session.setAttribute("filtroActivoId",filter.getIdFiltros());
            session.setAttribute("filtroActivoRemoveName", optsArea);
            session.setAttribute("filtroActivoTipo", filtroSelecionado == 1 ? "Supervisado" : "No supervisado");
			return "redirect:/filtro";
	}
    
    @RequestMapping(value="/seleccionAtributos", method = RequestMethod.GET)
    public String selectAtributos(String optradio,HttpServletRequest request) throws IOException {
    	session = utils.isValidSession(request);
    	if(session == null) return "redirect:/sessionCaducada";
    	File file = ficherosService.getFileByFichero(Integer.parseInt(session.getAttribute("sesionActivaIdFile").toString()));
    	BufferedReader datafile = new BufferedReader(new FileReader(file));
        Instances data = new Instances(datafile);
    	AttributeSelection attSelection = new AttributeSelection();
        CfsSubsetEval eval = new CfsSubsetEval();

    	if(optradio.equals("1")) {
    		BestFirst search = new BestFirst();
    		attSelection.setSearch(search);
    	}else if (optradio.equals("2")) {
    		GreedyStepwise search = new GreedyStepwise();
    		// ScatterSearch search = new ScatterSearch();
    		attSelection.setSearch(search);
    	}else if (optradio.equals("3")) {
    		Ranker search = new Ranker();
    		attSelection.setSearch(search);
    	}
    	
    	attSelection.setEvaluator(eval);
    	try {
    		attSelection.SelectAttributes(data);
    		int[] attIndex = attSelection.selectedAttributes();
    		List <String> listaAtributos = new ArrayList<String>();
    		
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
    
    
}


