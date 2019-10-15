package com.tfg.wekaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.AlgoritmosRepository;
import com.tfg.wekaWeb.dto.Algoritmos;

@Service
public class AlgoritmosService {

	@Autowired
	private AlgoritmosRepository algoritmosRepository;
	
	public Algoritmos findById(int idAlgoritmo) {
		return algoritmosRepository.findById(idAlgoritmo).get();
	}
}
