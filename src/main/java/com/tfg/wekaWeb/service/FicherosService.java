package com.tfg.wekaWeb.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.tfg.wekaWeb.dao.FicherosRepository;
import com.tfg.wekaWeb.dto.Ficheros;

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
			Ficheros fichero = new Ficheros(file.getContentType(), filename, path.getAbsolutePath(), userId, new Date(),
					new Date(),comentario);
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
			Ficheros fichero = new Ficheros(file.getContentType(), filename, pathCSV, userId, new Date(),
					new Date(),comentario);
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

}
