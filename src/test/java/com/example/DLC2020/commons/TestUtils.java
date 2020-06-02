package com.example.DLC2020.commons;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class TestUtils {
	
	public static String STR_HELLO = "hello";
	public static String STR_WORLD = "world";
	public static String STR_PROGRAM = "program";
	public static String STR_SUPER = "super";
	public static String STR_MARIO = "mario";
	public static String STR_DINOSAURS = "dinosaurs";
	public static String STR_CADILLACS = "cadillacs";
	public static String STR_CATACLYSM = "cataclysm";
	
	public static Documento DOC_HW = new Documento(1, "hello world", "url1");
	public static Documento DOC_HWP = new Documento(2, "hello world program", "url2");
	public static Documento DOC_MARIO = new Documento(3, "super mario world", "url3");
	public static Documento DOC_DINOS = new Documento(4, "dinosaurs", "url4");
	public static Documento DOC_CADILLACS = new Documento(5, "Cadillacs and Dinosaurs", "url5");
	public static Documento DOC_CADILLACS2 = new Documento(6, "Cadillacs and Dinosaurs: The Second Cataclysm", "url6");
	public static Documento DOC_TUROK = new Documento(7, "Turok", "url7");
	
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