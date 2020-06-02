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

import com.example.DLC2020.commons.TestUtils;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class SearchServiceTest {

	private SearchService service;

	private VocabularioDao vocabularioDao;
	private DocumentoDao documentoDao;

	@Before
	public void setUp() {
		vocabularioDao = Mockito.mock(VocabularioDao.class);
		documentoDao = Mockito.mock(DocumentoDao.class);

		when(vocabularioDao.findByWord(TestUtils.STR_HELLO))
				.thenReturn(createVocabulario(TestUtils.STR_HELLO, new HashMap() {
					{
						put(TestUtils.DOC_HW, 1);
						put(TestUtils.DOC_HWP, 1);
					}
				}));


		when(vocabularioDao.findByWord(TestUtils.STR_WORLD))
				.thenReturn(createVocabulario(TestUtils.STR_WORLD, new HashMap() {
			{
				put(TestUtils.DOC_HW, 1);
				put(TestUtils.DOC_HWP, 1);
				put(TestUtils.DOC_MARIO, 1);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_PROGRAM))
				.thenReturn(createVocabulario(TestUtils.STR_PROGRAM, new HashMap() {
			{
				put(TestUtils.DOC_HWP, 2);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_SUPER))
				.thenReturn(createVocabulario(TestUtils.STR_SUPER, new HashMap() {
			{
				put(TestUtils.DOC_MARIO, 3);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_MARIO))
				.thenReturn(createVocabulario(TestUtils.STR_MARIO, new HashMap() {
			{
				put(TestUtils.DOC_MARIO, 3);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_DINOSAURS))
				.thenReturn(createVocabulario(TestUtils.STR_DINOSAURS, new HashMap() {
			{
				put(TestUtils.DOC_DINOS, 5);
				put(TestUtils.DOC_CADILLACS, 3);
				put(TestUtils.DOC_CADILLACS2, 3);
				put(TestUtils.DOC_TUROK, 2);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_CADILLACS))
				.thenReturn(createVocabulario(TestUtils.STR_CADILLACS, new HashMap() {
			{
				put(TestUtils.DOC_CADILLACS, 3);
				put(TestUtils.DOC_CADILLACS2, 3);
			}
		}));
		when(vocabularioDao.findByWord(TestUtils.STR_CATACLYSM))
				.thenReturn(createVocabulario(TestUtils.STR_CATACLYSM, new HashMap() {
			{
				put(TestUtils.DOC_CADILLACS2, 2);
			}
		}));

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
		List<Documento> result = service
				.search(TestUtils.STR_CADILLACS + " " + TestUtils.STR_DINOSAURS + " " + TestUtils.STR_CATACLYSM);
		assertTrue(result.get(0).equals(TestUtils.DOC_CADILLACS2));
		assertTrue(result.get(1).equals(TestUtils.DOC_CADILLACS));
		assertTrue(result.get(2).equals(TestUtils.DOC_DINOS));
		assertTrue(result.get(3).equals(TestUtils.DOC_TUROK));
	}
}
