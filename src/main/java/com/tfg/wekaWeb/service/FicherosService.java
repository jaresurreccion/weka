package com.tfg.wekaWeb.service;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Date;
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

	public Ficheros guardarFichero(MultipartFile file, int userId) throws FileSystemException {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (filename.contains("..")) {
				throw new FileSystemException("El fichero contiene carácterés inválidos");
			}

			Ficheros fichero = new Ficheros(file.getContentType(), filename, file.getBytes(), userId, new Date(),
					new Date());
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

}
