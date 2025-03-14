package com.example.DLC2020.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.example.DLC2020.commons.Constants;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class SearchService {

	private final VocabularioDao vocabularioDao;
	private final DocumentoDao documentoDao;

	public final static int R = 10;
	public long N;

	@Inject
	public SearchService(VocabularioDao vocabularioDao, DocumentoDao documentoDao) {
		this.vocabularioDao = vocabularioDao;
		this.documentoDao = documentoDao;
	}

	public List<Documento> search(String q) {
		Map<Documento, Double> ld = new TreeMap<>();

		N = documentoDao.count();

		// filtrar y ordenar elementos
		List<Vocabulario> v = getV(q);

		for (Vocabulario tk: v) {
			int i = 0;
			for (Posteo pk : tk.getPosteos()) {
				if (i < R) {
					try {
						// obtener el peso anterior
						double prevVal = ld.getOrDefault(pk.getDocumento(), 0.0);
						// recalcula el peso
						double val = prevVal + Math.log(pk.getTf() * (N / tk.getNr()));
						ld.put(pk.getDocumento(), val);
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					break;
				}
			}
		}
		//ordenar el map por el peso y convertir a lista
		List result = ld.entrySet().stream().sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
				.map(Map.Entry::getKey).collect(Collectors.toList());

		return result;
	}

	private List<Vocabulario> getV(String q) {

		List<String> terms = Arrays.asList(q.toLowerCase().split(Constants.DELIMS));
		List<Vocabulario> words = new ArrayList<Vocabulario>();

		for (String w : terms) {
			Vocabulario v = vocabularioDao.findByWord(w);
			if (v != null) {
				words.add(v);
			}
		}

		return words.stream().sorted((o1, o2) -> Integer.compare(o2.getNr(), o1.getNr())).collect(Collectors.toList());
	}
}
