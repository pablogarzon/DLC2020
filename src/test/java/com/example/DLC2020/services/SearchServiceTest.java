package com.example.DLC2020.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class SearchServiceTest {

	private SearchService service;

	private Map<String, Vocabulario> v;

	private String title1 = "hello world";
	private String title2 = "hello world program";
	private String title3 = "super mario world";
	private String title4 = "dinosaurs";
	private String title5 = "Cadillacs and Dinosaurs";
	private String title6 = "Cadillacs and Dinosaurs: The Second Cataclysm";
	private String title7 = "Turok";

	@Before
	public void setUp() {
		v = new HashMap<String, Vocabulario>();

		Documento doc1 = new Documento(1, title1, "url1");
		Documento doc2 = new Documento(2, title2, "url2");
		Documento doc3 = new Documento(3, title3, "url3");
		Documento doc4 = new Documento(4, title4, "url4");
		Documento doc5 = new Documento(5, title5, "url5");
		Documento doc6 = new Documento(6, title6, "url6");
		Documento doc7 = new Documento(7, title7, "url7");

		fillV("hello", new HashMap() {
			{
				put(doc1, 1);
				put(doc2, 1);
			}
		}); // Arrays.asList(, doc2, doc3));
		fillV("world", new HashMap() {
			{
				put(doc1, 1);
				put(doc2, 1);
				put(doc3, 1);
			}
		});
		fillV("program", new HashMap() {
			{
				put(doc2, 2);
			}
		});
		fillV("super", new HashMap() {
			{
				put(doc3, 3);
			}
		});
		fillV("mario", new HashMap() {
			{
				put(doc3, 3);
			}
		});
		fillV("dinosaurs", new HashMap() {
			{
				put(doc4, 5);
				put(doc5, 3);
				put(doc6, 3);
				put(doc7, 2);
			}
		});
		fillV("cadillacs", new HashMap() {
			{
				put(doc5, 3);
				put(doc6, 3);
			}
		});
		fillV("cataclysm", new HashMap() {
			{
				put(doc6, 2);
			}
		});
	}

	private void fillV(String word, Map<Documento, Integer> docs) {
		if (!v.containsKey(word)) {
			Vocabulario vocabulario = new Vocabulario(word, new ArrayList<>());

			for (Entry<Documento, Integer> doc : docs.entrySet()) {
				Posteo p = new Posteo();
				p.setVocabulario(vocabulario);
				p.setDocumentos(doc.getKey());
				p.setTf(doc.getValue());
				vocabulario.addPosteo(p);
			}

			v.put(word, vocabulario);
		}
	}

	@Test
	public void testSearch() {
		service = new SearchService(v, null);
		List<Documento> result = service.search("cadillacs dinosaurs cataclysm");
		assertTrue(result.get(0).getNombre().equals(title6));
		assertTrue(result.get(1).getNombre().equals(title5));
		assertTrue(result.get(2).getNombre().equals(title4));
		assertTrue(result.get(3).getNombre().equals(title7));
	}
}
