package com.tfg.wekaWeb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.SesionTrabajoRepository;
import com.tfg.wekaWeb.dto.SesionTrabajo;

@Service
public class SesionTrabajoService {

	   private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	   
	   @Autowired
	   SesionTrabajoRepository trabajo;
	   
	   public List<SesionTrabajo> buscarSesiones (Integer idUsuario){
			return trabajo.findByidUsuario(idUsuario); 
		}
	   
	   public SesionTrabajo nuevaSesionTrabajo(Integer idUsuario,String nombre) {
		   SesionTrabajo nuevoTrabajo = new SesionTrabajo(idUsuario,nombre,null,null,null,new Date(), new Date(),null,null,null);
		  return trabajo.save(nuevoTrabajo);
	   }
	   
	   public void actualizarFileSesion(Integer idSesion,Integer idFile, String nombreFile) {
		   Optional<SesionTrabajo> updateFile = trabajo.findById(idSesion);
		   if(updateFile.isPresent()) {
			   updateFile.get().setIdFile(idFile);
			   updateFile.get().setNombreFile(nombreFile);
			   updateFile.get().setFechaActualizacion(new Date());
			  trabajo.save(updateFile.get());
		   }
	   }
	   
	   public void actualizarAlgoritmoSesion(Integer idSesion,Integer idAlgoritmo, String nombreAlg) {
		   Optional<SesionTrabajo> updateFile = trabajo.findById(idSesion);
		   if(updateFile.isPresent()) {
			   updateFile.get().setIdAlgoritmo(idAlgoritmo);
			   updateFile.get().setNombreAlg(nombreAlg);
			   updateFile.get().setFechaActualizacion(new Date());
			   trabajo.save(updateFile.get());
		   }
	   }
	   
	   public void actualizarFiltroSesion(Integer idSesion,Integer idFiltro, String nombreFiltros) {
		   Optional<SesionTrabajo> updateFile = trabajo.findById(idSesion);
		   if(updateFile.isPresent()) {
			   updateFile.get().setIdFiltros(idFiltro);
			   updateFile.get().setNombreFiltros(nombreFiltros);
			   updateFile.get().setFechaActualizacion(new Date());
			   trabajo.save(updateFile.get());
		   }
	   }
	   
	   public SesionTrabajo buscarXid(Integer idSession) {
		   return trabajo.findById(idSession).get();
	   }
	   
	   public void deleteSession(Integer idSession) {
		   trabajo.deleteById(idSession);
	   }

}
