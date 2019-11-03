package com.tfg.wekaWeb.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.wekaWeb.dao.FiltrosRepository;
import com.tfg.wekaWeb.dto.Ficheros;
import com.tfg.wekaWeb.dto.Filtros;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.supervised.instance.Resample;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;

@Service
public class FiltrosService {
	
	@Autowired
	private FiltrosRepository FiltrosRepository;
	
	@Autowired
	private FicherosService ficheroService;
	
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
	
	public Instances aplicarfiltros(Filtros filtro) throws Exception {
		
		Optional<Ficheros> f = ficheroService.getFichero(filtro.getIdFichero());
		DataSource source = new DataSource(f.get().getRuta());
		Instances data = source.getDataSet();
		int numClass = data.numAttributes();
		
		
		if(filtro.getAtributosRemove().length() > 0 ) {
			Remove rm = new Remove();
			rm.setAttributeIndices(filtro.getAtributosRemove());
			rm.setInputFormat(data); 
			data =  Filter.useFilter(data, rm);
			 numClass = data.numAttributes();
		}
		data.setClassIndex(numClass-1);
		
		switch(Integer.parseInt(filtro.getTipo())) {
		
		case 1: //Discretize
			  Discretize disTransform = new Discretize();
			  disTransform.setUseBetterEncoding(true);
			  disTransform.setInputFormat(data);
			  data = Filter.useFilter(data, disTransform);
			  break;
		
		case 2: //AttributeSelection
			InfoGainAttributeEval eval = new InfoGainAttributeEval();
			Ranker search = new Ranker();
			search.setOptions(new String[] { "-T", "0.001" });	// information gain threshold
			AttributeSelection attSelect = new AttributeSelection();
			attSelect.setEvaluator(eval);
			attSelect.setSearch(search);
			// apply attribute selection
			attSelect.SelectAttributes(data);
			// remove the attributes not selected in the last run
			data = attSelect.reduceDimensionality(data);
			break;
			
		case 3: //Resample
			Resample resample = new Resample();
			String Fliteroptions="-B 1.0";
			resample.setOptions(weka.core.Utils.splitOptions(Fliteroptions));
			resample.setRandomSeed((int)System.currentTimeMillis());
			data = Resample.useFilter(data, resample);
			break;
			
		case 4: //Normalize
			Normalize filterNorm = new Normalize();
	        filterNorm.setInputFormat(data);
	        data = Filter.useFilter(data, filterNorm);
			break;
		case 5: //Discretize
			  weka.filters.unsupervised.attribute.Discretize disTransformUn = new weka.filters.unsupervised.attribute.Discretize();
			  disTransformUn.setInputFormat(data);
			  data = Filter.useFilter(data, disTransformUn);
			break;
		case 6: //Resample
			weka.filters.unsupervised.instance.Resample r = new weka.filters.unsupervised.instance.Resample();
			r.setNoReplacement(true);
			r.setInputFormat(data);
			data = Filter.useFilter(data, r); 
			break;
		}
		
		return data;
	}
	

}
