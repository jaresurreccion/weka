package com.tfg.wekaWeb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;

@Controller
public class fileController {

	HttpSession sesion;
	private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	SesionTrabajoService SesionTrabajo;

	@Autowired
	private FicherosService ficherosService;

	// Folder to upload File

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, String comentario, HttpServletRequest request)
			throws NumberFormatException, Exception {

		 
		sesion = request.getSession(false);
		Integer idSessionActual = Integer.parseInt(sesion.getAttribute("sesionActivaIdSesion").toString());

		ApplicationHome app = new ApplicationHome();
		File home = app.getDir();
		File datasets = new File(home, "datasets");
		if (!datasets.exists()) {
			datasets.mkdir();
		}
		byte[] bytes = null;
		bytes = file.getBytes();
		String ContentType = file.getContentType();
		String OriginalName= file.getOriginalFilename();
		Path path = Paths.get(datasets.getAbsolutePath(), file.getOriginalFilename());
		Files.write(path, bytes);
		Ficheros f = ficherosService.guardarFichero(ContentType,OriginalName, idSessionActual, comentario);
		System.out.println(f.toString());
		SesionTrabajo.actualizarFileSesion(idSessionActual, f.getIdFichero());

		return "redirect:/ficheros";

	}

	@GetMapping("/deleteFile/{fileId}")
	public String deleteFile(@PathVariable String fileId, HttpServletRequest request) {
		sesion = request.getSession(false);
		Integer idSessionActual = Integer.parseInt(sesion.getAttribute("sesionActivaIdSesion").toString());
		ficherosService.deleteFicherosById(Integer.parseInt(fileId));
		SesionTrabajo.actualizarFileSesion(idSessionActual, null);
		return "redirect:/ficheros";
	}

	@GetMapping("/downloadFile/{idFile}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String idFile, HttpServletRequest request) {
		// Load file as Resource
		Optional<Ficheros> f = ficherosService.getFichero(Integer.parseInt(idFile));

		InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(f.get().getRuta()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
