package com.tfg.wekaWeb.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.tfg.wekaWeb.dao.FicherosRepository;
import com.tfg.wekaWeb.dto.Ficheros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

@Service
public class FicherosService {

	@Autowired
	private FicherosRepository ficherosRepository;

	static ApplicationHome app = new ApplicationHome();
	static File home = app.getDir();
	static File datasets = new File(home, "datasets");
	private static String UPLOADED_FOLDER = datasets.getAbsolutePath();
	
	public Ficheros guardarFichero(String ContentType, String OriginalName, Integer idSessionActual,String comentario) throws Exception {
		
		File path = new File(UPLOADED_FOLDER, OriginalName);
		String filename = OriginalName;
		Ficheros fichero = null;
		
		if(filename.contains(".arff")) {
			//String path = UPLOADED_FOLDER + file.getOriginalFilename();
			String [] datosDataset = datosDataset(path.getAbsolutePath());
			 fichero = new Ficheros(ContentType, filename, path.getAbsolutePath(), idSessionActual, new Date(),
					new Date(),comentario,null,null,null);
			 ficherosRepository.save(fichero);
		}
		else {
			
		String[] fileNameArff = filename.split("\\.");
		filename = fileNameArff[0] + ".arff";
		try {
			if (filename.contains("..")) {
				throw new FileSystemException("El fichero contiene carácterés inválidos");
			}
			String pathCSV = convertirCSVtoARFF(path.getAbsolutePath());
			
			 fichero = new Ficheros(ContentType, filename, pathCSV, idSessionActual, new Date(),
					new Date(),comentario,null,null,null);
			 ficherosRepository.save(fichero);
		
		} catch (IOException ex) {
			throw new FileSystemException("No se pudo guardar");
		}
		}
		
		String [] datosDataset = datosDataset(fichero.getRuta());
		String clase = datosDataset[1];
		String nAtributos = datosDataset[2];
		String nInstancias = datosDataset[0];
		fichero.setClase(clase);
		fichero.setnAtributos(nAtributos);
		fichero.setNumInstancias(nInstancias);
		fichero =  ficherosRepository.save(fichero);
		return fichero;
	}


	public Optional<Ficheros> getFichero(int idFichero) {
		return ficherosRepository.findById(idFichero);
	}
	
	public List<Ficheros> getFicherosByIdSession(int idSession){
		return ficherosRepository.findAllByIdSession(idSession);
	} 
	
	public void deleteFicherosById(int idFichero) {
		 ficherosRepository.deleteById(idFichero);
	}
	
	public String convertirCSVtoARFF(String sourcepath) throws Exception
    {
        // load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(sourcepath));
        Instances dataSet = loader.getDataSet();
        String[] destpath = sourcepath.split("\\.");
        String sourceDest =destpath[0]+".arff";
        // save ARFF
        BufferedWriter writer = new BufferedWriter(new FileWriter(sourceDest));
        writer.write(dataSet.toString());
        writer.flush();
        writer.close(); 
        return sourceDest;
	}
	
	public String[] datosDataset(String path) throws IOException{
		/* Cargamos el fichero con los datos */
		BufferedReader datafile = new BufferedReader(new FileReader(path));
		Instances data = new Instances(datafile);
		String[] datos = new String[3];
		/* Seleccionamos la columna de los datos a estimar */
		data.setClassIndex(data.numAttributes() - 1);
		List<String> list = new ArrayList<>();
		Enumeration<Attribute> e = data.enumerateAttributes();
		while(e.hasMoreElements()){
			list.add(e.nextElement().toString());
		}
		
		datos[0]= String.valueOf(list.size());
		String classAtribute = data.classAttribute().toString(); 
		if(classAtribute.contains(Pattern.quote("{"))){
			String[] clase = classAtribute.split(Pattern.quote("{"));
			clase = clase[1].split(Pattern.quote("}"));
			datos[1]=clase[0];
		}else{
			datos[1]="No tiene";
		}
		
		datos[2]= String.valueOf(data.numInstances());
		return datos;
	}

}
