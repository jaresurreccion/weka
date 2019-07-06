package com.tfg.wekaWeb.service;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.tfg.wekaWeb.dao.FicherosRepository;
import com.tfg.wekaWeb.dto.Ficheros;

@Service
public class FicherosService {

	@Autowired
	private FicherosRepository ficherosRepository;

	private static String UPLOADED_FOLDER = "C:\\Users\\Jose\\Documents\\NAS\\datasets\\";
	
	public Ficheros guardarFichero(MultipartFile file, int userId,String comentario) throws FileSystemException {

		String filename = file.getOriginalFilename();

		try {
			if (filename.contains("..")) {
				throw new FileSystemException("El fichero contiene carácterés inválidos");
			}
			String path = UPLOADED_FOLDER + file.getOriginalFilename();
			Ficheros fichero = new Ficheros(file.getContentType(), filename, path, userId, new Date(),
					new Date(),comentario);
			return ficherosRepository.save(fichero);
		} catch (IOException ex) {
			throw new FileSystemException("No se pudo guardar");
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
	
	

}
