package com.tfg.wekaWeb.controller;

import java.nio.file.FileSystemException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.service.FicherosService;

@RestController
public class fileController {

    private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	private FicherosService ficherosService;
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, @CookieValue(value = "userId", defaultValue = "Attat") String userId,String comentario) {
		
		Ficheros f;
		try {
			f = ficherosService.guardarFichero(file, Integer.parseInt(userId),comentario);
			
			return "redirect:/ficheros";
		} catch (FileSystemException e) {
			
			e.printStackTrace();
			return "error";
		}
		
	}
	
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Optional<Ficheros> dbFile = ficherosService.getFichero(Integer.parseInt(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.get().getcontentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.get().getNombreFichero() + "\"")
                .body(new ByteArrayResource(dbFile.get().getDatos()));
    }
	
	@GetMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable String fileId) {
      ficherosService.deleteFicherosById(Integer.parseInt(fileId));
      
        return "redirect:/ficheros";
    }
	
}
