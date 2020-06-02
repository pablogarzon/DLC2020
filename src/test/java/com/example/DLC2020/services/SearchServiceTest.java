package com.example.DLC2020.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class SearchServiceTest {

	private SearchService service;

	private VocabularioDao vocabularioDao;
	private DocumentoDao documentoDao;

	private String title1 = "hello world";
	private String title2 = "hello world program";
	private String title3 = "super mario world";
	private String title4 = "dinosaurs";
	private String title5 = "Cadillacs and Dinosaurs";
	private String title6 = "Cadillacs and Dinosaurs: The Second Cataclysm";
	private String title7 = "Turok";

	@Before
	public void setUp() {
		vocabularioDao = Mockito.mock(VocabularioDao.class);
		documentoDao = Mockito.mock(DocumentoDao.class);

		Documento doc1 = new Documento(1, title1, "url1");
		Documento doc2 = new Documento(2, title2, "url2");
		Documento doc3 = new Documento(3, title3, "url3");
		Documento doc4 = new Documento(4, title4, "url4");
		Documento doc5 = new Documento(5, title5, "url5");
		Documento doc6 = new Documento(6, title6, "url6");
		Documento doc7 = new Documento(7, title7, "url7");
		
		List<Vocabulario> words = new ArrayList<>();

		words.add(createVocabulario("hello", new HashMap() {
			{
				put(doc1, 1);
				put(doc2, 1);
			}
		})); // Arrays.asList(, doc2, doc3));
		words.add(createVocabulario("world", new HashMap() {
			{
				put(doc1, 1);
				put(doc2, 1);
				put(doc3, 1);
			}
		}));
		words.add(createVocabulario("program", new HashMap() {
			{
				put(doc2, 2);
			}
		}));
		words.add(createVocabulario("super", new HashMap() {
			{
				put(doc3, 3);
			}
		}));
		words.add(createVocabulario("mario", new HashMap() {
			{
				put(doc3, 3);
			}
		}));
		words.add(createVocabulario("dinosaurs", new HashMap() {
			{
				put(doc4, 5);
				put(doc5, 3);
				put(doc6, 3);
				put(doc7, 2);
			}
		}));
		words.add(createVocabulario("cadillacs", new HashMap() {
			{
				put(doc5, 3);
				put(doc6, 3);
			}
		}));
		words.add(createVocabulario("cataclysm", new HashMap() {
			{
				put(doc6, 2);
			}
		}));
		
		when(vocabularioDao.findAll()).thenReturn(words);
		when(documentoDao.count()).thenReturn(7L);
	}

	private Vocabulario createVocabulario(String word, Map<Documento, Integer> docs) {
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

	@Test
	public void testSearch() {
		service = new SearchService(vocabularioDao, documentoDao);
		List<Documento> result = service.search("cadillacs dinosaurs cataclysm");
		assertTrue(result.get(0).getNombre().equals(title6));
		assertTrue(result.get(1).getNombre().equals(title5));
		assertTrue(result.get(2).getNombre().equals(title4));
		assertTrue(result.get(3).getNombre().equals(title7));
	}
}
