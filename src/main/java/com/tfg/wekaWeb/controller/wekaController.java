package com.tfg.wekaWeb.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;

@Controller
public class wekaController {

	private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	private FicherosService ficherosService;
	
	@Autowired
	private AlgoritmosRepository algoritmoRepository;
	
	@Autowired
	private SesionTrabajoService sessionService;
	
	HttpSession session;
	
	
	@GetMapping("/weka/{idFile}")
    public String  wekaFile(@PathVariable String idFile,@CookieValue(value = "userId", defaultValue = "Attat") String userId,
    		RedirectAttributes model) {
      
      Optional <Ficheros> f = ficherosService.getFichero(Integer.parseInt(idFile));
      try {
          /* Cargamos el fichero con los datos */
          BufferedReader datafile = new BufferedReader(new FileReader(f.get().getRuta()));
          Instances data = new Instances(datafile);
           
          /* Seleccionamos la columna de los datos a estimar */
          data.setClassIndex(data.numAttributes() - 1);
          List<String> list = new ArrayList<>();
          Enumeration<Attribute> e = data.enumerateAttributes();
          while(e.hasMoreElements()){
        	  list.add(e.nextElement().toString());
          }
          model.addFlashAttribute("lista",list);
          model.addFlashAttribute("clase",data.classAttribute().toString());
          /* Creamos dos grupos uno de entreno y otro de test */
          int numFolds=14;
          Instances[] trainingSplits=new Instances[numFolds];
          Instances[] testingSplits= new Instances[numFolds];
            
          for (int i = 0; i < numFolds; i++) {
              trainingSplits[i] = data.trainCV(numFolds, i);
              testingSplits[i] = data.testCV(numFolds, i);
              }
    
          /* Ejecutamos cada diferentes modelos */
          model.addFlashAttribute("J48",ejecutarModelo( new J48(), trainingSplits, testingSplits)); // a decision tree
          model.addFlashAttribute("PART",ejecutarModelo( new PART(), trainingSplits, testingSplits));
          model.addFlashAttribute("TABLE",ejecutarModelo( new DecisionTable(), trainingSplits, testingSplits));//decision table majority classifier
          model.addFlashAttribute("DECISION",ejecutarModelo( new DecisionStump(), trainingSplits, testingSplits)); //one-level decision tree
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
			session = request.getSession(true);
			Integer idSession = Integer.parseInt(session.getAttribute("sesionActivaIdSesion").toString());
			Integer idAlgoritmo = Integer.parseInt(optradio);
			sessionService.actualizarAlgoritmoSesion(idSession, idAlgoritmo);
			return "/algoritmo";
	}
    
}


