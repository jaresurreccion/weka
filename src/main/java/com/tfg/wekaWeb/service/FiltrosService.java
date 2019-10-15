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
	
	public Filtros guardarFiltro(String tipo,String atributosRemove,String atributosRemoveName, int idSesion, int idFichero) {
		Filtros nuevoFiltro = new Filtros(tipo,atributosRemove,atributosRemoveName,idSesion,idFichero,new Date());
		return FiltrosRepository.save(nuevoFiltro);	
	}
	
	public Filtros findByIdSession(int idSession) {
		return FiltrosRepository.findByidSession(idSession);
	}
	
	public Filtros findByIdFiltros(int IdFiltros) {
		return FiltrosRepository.findByidFiltros(IdFiltros);
	}
	

}
