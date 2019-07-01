package com.tfg.wekaWeb.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tfg.wekaWeb.dto.Algoritmos;

@Transactional
public interface AlgoritmosRepository extends CrudRepository<Algoritmos, Integer> {

}
