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
	
	public Ficheros guardarFichero(MultipartFile file, int userId,String comentario) throws Exception {
		
		File path = new File(UPLOADED_FOLDER, file.getOriginalFilename());
		String filename = file.getOriginalFilename();
		
		if(filename.contains(".arff")) {
			//String path = UPLOADED_FOLDER + file.getOriginalFilename();
			String [] datosDataset = datosDataset(path.getAbsolutePath());
			Ficheros fichero = new Ficheros(file.getContentType(), filename, path.getAbsolutePath(), userId, new Date(),
					new Date(),comentario,datosDataset[1],datosDataset[0],datosDataset[2]);
			return ficherosRepository.save(fichero);
		}
		else {
			
		String[] fileNameArff = filename.split("\\.");
		filename = fileNameArff[0] + ".arff";
		try {
			if (filename.contains("..")) {
				throw new FileSystemException("El fichero contiene carácterés inválidos");
			}
			String pathCSV = convertirCSVtoARFF(path.getAbsolutePath());
			String [] datosDataset = datosDataset(pathCSV);
			Ficheros fichero = new Ficheros(file.getContentType(), filename, pathCSV, userId, new Date(),
					new Date(),comentario,datosDataset[1],datosDataset[0],datosDataset[2]);
			return ficherosRepository.save(fichero);
		
		} catch (IOException ex) {
			throw new FileSystemException("No se pudo guardar");
		}
		}
	}

	public Ficheros getFicheroByIduser(int id) {
		return ficherosRepository.findByidUsuario(id);
	}

	public Optional<Ficheros> getFichero(int idFichero) {
		return ficherosRepository.findById(idFichero);
	}
	
	public List<Ficheros> getFicherosByIdUser(int idUser){
		return ficherosRepository.findAllByIdUser(idUser);
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
		String[] clase = classAtribute.split(Pattern.quote("{"));
		clase = clase[1].split(Pattern.quote("}"));
		datos[1]=clase[0];
		datos[2]= String.valueOf(data.numInstances());
		return datos;
	}

}
