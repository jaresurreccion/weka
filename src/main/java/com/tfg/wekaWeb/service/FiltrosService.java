package com.tfg.wekaWeb.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.FiltrosRepository;
import com.tfg.wekaWeb.dto.Filtros;

@Service
public class FiltrosService {
	
	@Autowired
	private FiltrosRepository FiltrosRepository;
	
	public Filtros guardarFiltro(int filtro,String tipo,String atributosRemove,String atributosRemoveName, int idSesion, int idFichero) {
		Filtros nuevoFiltro;
		if(filtro != 0) {
			nuevoFiltro = actualizarFiltro(filtro, tipo, atributosRemove, atributosRemoveName);
		}else {
			nuevoFiltro = new Filtros(tipo,atributosRemove,atributosRemoveName,idSesion,idFichero,new Date());	
			FiltrosRepository.save(nuevoFiltro);
		}
		return nuevoFiltro;
			
	}
	
	public Filtros findByIdSession(int idSession) {
		return FiltrosRepository.findByidSession(idSession);
	}
	
	public Filtros findByIdFiltros(int IdFiltros) {
		return FiltrosRepository.findByidFiltros(IdFiltros);
	}
	
	public Filtros actualizarFiltro(int filtro,String tipo,String atributosRemove,String atributosRemoveName) {
		Filtros nuevoFiltro = findByIdFiltros(filtro);
		if(nuevoFiltro.getTipo() != tipo) nuevoFiltro.setTipo(tipo);
		if(nuevoFiltro.getAtributosRemove() != atributosRemove) nuevoFiltro.setAtributosRemove(atributosRemove);
		if(nuevoFiltro.getAtributosRemoveName() != atributosRemoveName) nuevoFiltro.setAtributosRemoveName(atributosRemoveName);
		return FiltrosRepository.save(nuevoFiltro);
	}
	

}
