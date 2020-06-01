package com.example.DLC2020.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Vocabulario;

public class SearchService {
	
	public final static int R = 10;
	private final VocabularioDao vocabularioDao;
	private final DocumentoDao documentoDao;
	
	@Inject
	public SearchService(VocabularioDao vocabularioDao, DocumentoDao documentoDao) {
		this.vocabularioDao = vocabularioDao;
		this.documentoDao = documentoDao;
	}

	public List<Documento> search(String q) {
		System.out.println("search starts at " + System.currentTimeMillis());
		// < di, ir>
		Map<Documento, Double> ld = new TreeMap<>();
		
		long N = 7;
		
		//filtrar y ordenar elementos
		List<Vocabulario> v = getV(q);
		
		v.forEach(tk -> {
			int i = 0;
			tk.getPosteoCollection().forEach(pk -> {
				if (i < R) {
					double prevVal = ld.getOrDefault(pk.getDocumentos(), 0.0);
					double val = prevVal + Math.log(pk.getTf() * (N / tk.getNr()));
					ld.put(pk.getDocumentos(), val);
				} else {
					return;
				}
			});
		});
		
		List res = ld.entrySet().stream()
			.sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
			.map(Map.Entry::getKey)
			.collect(Collectors.toList());
		
		System.out.println("search end at " + System.currentTimeMillis());
		
		return res;
	}

	private List<Vocabulario> getV(String q) {
		List<String> terms = Arrays.asList(q.split(" "));
		
		List<Vocabulario> words = vocabularioDao.findAll();
		
		return words.stream()
			.filter(w -> terms.contains(w.getPalabra()))
			.sorted((o1, o2) -> Integer.compare(o2.getNr(), o1.getNr()))
			.collect(Collectors.toList());
	}
}
