package com.tfg.wekaWeb.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public List<String> getAtributos(Integer idFichero) throws IOException {
		
		 BufferedReader datafile = new BufferedReader(new FileReader(getFichero(idFichero).getRuta()));
         Instances data = new Instances(datafile);
          
         /* Seleccionamos la columna de los datos a estimar */
         List<String> list = new ArrayList<>();
         Enumeration<Attribute> e = data.enumerateAttributes();
         while(e.hasMoreElements()){
       	  list.add(e.nextElement().toString());
         }
		return list;
	}
	
	
	
}
