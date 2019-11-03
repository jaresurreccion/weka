package com.tfg.wekaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.AlgoritmosRepository;
import com.tfg.wekaWeb.dto.Algoritmos;

import weka.attributeSelection.ASSearch;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.filters.Filter;

@Service
public class AlgoritmosService {

	@Autowired
	private AlgoritmosRepository algoritmosRepository;
	
	public Algoritmos findById(int idAlgoritmo) {
		return algoritmosRepository.findById(idAlgoritmo).get();
	}
	
	public Instances bestFirst(Instances data) throws Exception {
		weka.filters.supervised.attribute.AttributeSelection filter = new weka.filters.supervised.attribute.AttributeSelection();
	    CfsSubsetEval eval = new CfsSubsetEval();
	    BestFirst search = new BestFirst();
	    filter.setEvaluator(eval);
	    filter.setSearch(search);
	    filter.setInputFormat(data);
	    Instances newData = Filter.useFilter(data, filter);
	    return newData;
	}
	
	public String NaiveBayes(Instances data) throws Exception {
		Classifier naives = new weka.classifiers.bayes.NaiveBayes();
		data.setClassIndex(data.numAttributes()-1);
		naives.buildClassifier(data);
		return naives.toString();
	}
	

}
