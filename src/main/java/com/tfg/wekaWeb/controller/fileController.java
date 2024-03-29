package com.tfg.wekaWeb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;
import com.tfg.wekaWeb.dto.Algoritmos;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.Filtros;
import com.tfg.wekaWeb.service.AlgoritmosService;
import com.tfg.wekaWeb.service.FicherosService;
import com.tfg.wekaWeb.service.FiltrosService;
import com.tfg.wekaWeb.service.SesionTrabajoService;
import com.tfg.wekaWeb.service.UtilsService;

@Controller
public class fileController {

	HttpSession sesion;
	private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	SesionTrabajoService SesionTrabajo;

	@Autowired
	private FicherosService ficherosService;
	
	@Autowired
	private FiltrosService filtrosService;
	
	@Autowired
	private AlgoritmosService algoritmoService;

	private UtilsService utils = new UtilsService();
	// Folder to upload File

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, String comentario, HttpServletRequest request)
			throws NumberFormatException, Exception {

		 
		sesion = utils.isValidSession(request);
		if(sesion == null) return "redirect:/sessionCaducada";
		Integer idSessionActual = Integer.parseInt(sesion.getAttribute("sesionActivaIdSesion").toString());
		ApplicationHome app = new ApplicationHome();
		File home = app.getDir();
		File datasets = new File("C:\\tmpFiles","datasets");
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
		SesionTrabajo.actualizarFileSesion(idSessionActual, f.getIdFichero(),OriginalName);
		sesion.setAttribute("sesionActivaIdFile", f.getIdFichero());

		return "redirect:/ficheros";

	}

	@GetMapping("/deleteFile/{fileId}")
	public String deleteFile(@PathVariable String fileId, HttpServletRequest request) {
		sesion = utils.isValidSession(request);
		if(sesion == null) return "redirect:/sessionCaducada";
		Integer idSessionActual = Integer.parseInt(sesion.getAttribute("sesionActivaIdSesion").toString());
		ficherosService.deleteFicherosById(Integer.parseInt(fileId));
		SesionTrabajo.actualizarFileSesion(idSessionActual, null,null);
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
		contentType = request.getServletContext().getMimeType(f.get().getContentType());

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.get().getNombreFichero() + "\"")
				.body(resource);
	}

	@GetMapping("/opendata")
	public String openData(HttpServletRequest request, ModelMap model) {
		sesion = utils.isValidSession(request);
		if(sesion == null) return "redirect:/sessionCaducada";
		List<Ficheros> f = ficherosService.getFiles();
		if(f.size() >= 1) {
			model.addAttribute("listaFicheros",f);	
		}
		return "opendata";
	}
	
	@PostMapping("/generatePDF")
	public ResponseEntity<Resource> generatePDF(String resultadosHidden, HttpServletRequest request, ModelMap model) throws IOException, DocumentException {
		sesion = utils.isValidSession(request);
		String resultado = resultadosHidden;
		Ficheros file = ficherosService.getFichero(Integer.parseInt(sesion.getAttribute("sesionActivaIdFile").toString())).get();
		Filtros filtros = filtrosService.findByIdFiltros(Integer.parseInt(sesion.getAttribute("sesionActivaIdFiltros").toString()));
		Algoritmos algoritmos = algoritmoService.findById(Integer.parseInt(sesion.getAttribute("sesionActivaIdAlgoritmo").toString()));
		InputStreamResource resource = new InputStreamResource(new FileInputStream(ficherosService.generatePDF(resultado,file,filtros,algoritmos)));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" resultados.pdf\"")
				.body(resource);
	}
	
}
