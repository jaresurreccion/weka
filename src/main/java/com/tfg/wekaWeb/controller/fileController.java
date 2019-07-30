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

@Controller
public class fileController {

    private static final Logger logger = LoggerFactory.getLogger(fileController.class);

	@Autowired
	private FicherosService ficherosService;
	
	//Folder to upload File

	
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, @CookieValue(value = "userId", defaultValue = "Attat") String userId,String comentario
			, RedirectAttributes redirectAttributes) throws NumberFormatException, Exception {
		
		Ficheros f;
		ApplicationHome app = new ApplicationHome();
		File home = app.getDir();
		File datasets = new File(home, "datasets");
		if(!datasets.exists()) {
			datasets.mkdir();
			}
		
		try {
			
			byte[] bytes = null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			Path path = Paths.get(datasets.getAbsolutePath(),file.getOriginalFilename());
            try {
				Files.write(path, bytes);
				f = ficherosService.guardarFichero(file, Integer.parseInt(userId),comentario);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/ficheros";
		} catch (FileSystemException e) {
			
			e.printStackTrace();
			return "error";
		}
		
		
		
	}
	
	
	@GetMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable String fileId) {
      ficherosService.deleteFicherosById(Integer.parseInt(fileId));
      
        return "redirect:/ficheros";
    }
	
	 @GetMapping("/downloadFile/{idFile}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String idFile, HttpServletRequest request) {
	        // Load file as Resource
		 Optional <Ficheros> f = ficherosService.getFichero(Integer.parseInt(idFile));
		 
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
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	
}
