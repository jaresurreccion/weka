package com.tfg.wekaWeb.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.FicherosRepository;
import com.tfg.wekaWeb.dto.Ficheros;

import weka.core.Attribute;
import weka.core.Instances;

@Service
public class wekaService {
	
	@Autowired
	private FicherosRepository ficherosService;
	
	
	
	public Ficheros getFichero(Integer idFichero) {
		Ficheros fichero = ficherosService.findById(idFichero).get();
		return fichero;
	}
	
	public List<String []> getAtributos(Integer idFichero) throws IOException {
		
		 BufferedReader datafile = new BufferedReader(new FileReader(getFichero(idFichero).getRuta()));
         Instances data = new Instances(datafile);
         
         /* Seleccionamos la columna de los datsos a estimar */
         List<String []> list = new ArrayList<>();
         Enumeration<Attribute> e = data.enumerateAttributes();
         while(e.hasMoreElements()){
        	 String[] s = new String[2]; 
         String[] p = (e.nextElement().toString()).split("\\s+");
        
         s[0]=p[1];
         s[1] = p[2];
       	 list.add(s);
         }
         
		return list;
	}
	
	public String gestionarCommandLine(int idFichero, String params) throws IOException {
		String command = '"'+"C:\\Users\\Jose\\Documents\\repositorio\\src\\main\\resources\\static\\vendor\\weka.jar"+'"';
		 Process p;
		 Ficheros f = ficherosService.findById(idFichero).get();
		 String file = '"'+f.getRuta()+'"';
		// String config = "weka.classifiers.bayes.BayesNet -t "+file+" -D -Q weka.classifiers.bayes.net.search.local.K2 -- -P 1 -S BAYES -E weka.classifiers.bayes.net.estimate.SimpleEstimator -- -A 0.5";
		String split = params.substring(0,params.indexOf(' '));
		String config = split+" -t "+file+" "+ params.substring(params.indexOf(' ')+1,params.length());;
		 p = Runtime.getRuntime().exec("java -cp "+command+ " "+ config);
       
       String NEWLINE = System.getProperty("line.separator");
       StringBuilder result = new StringBuilder(80);
       try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream())))
       {
           while (true)
           {
               String lines = in.readLine();
               if (lines == null)
                   break;
               result.append(lines).append(NEWLINE);
           }
       }
       return result.toString(); 
	}

	}
	
	

