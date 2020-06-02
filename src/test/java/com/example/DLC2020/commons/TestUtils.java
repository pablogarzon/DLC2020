package com.example.DLC2020.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class TestUtils {
	
	public static String title1 = "hello world";
	public static String title2 = "hello world program";
	public static String title3 = "super mario world";
	public static String title4 = "dinosaurs";
	public static String title5 = "Cadillacs and Dinosaurs";
	public static String title6 = "Cadillacs and Dinosaurs: The Second Cataclysm";
	public static String title7 = "Turok";
	
	public static Vocabulario createVocabulario(String word, Map<Documento, Integer> docs) {
		Vocabulario vocabulario = new Vocabulario(word, new ArrayList<>());

		for (Entry<Documento, Integer> doc : docs.entrySet()) {
			Posteo p = new Posteo();
			p.setVocabulario(vocabulario);
			p.setDocumento(doc.getKey());
			p.setTf(doc.getValue());
			vocabulario.addPosteo(p);
		}
		
		return vocabulario;
	}
}