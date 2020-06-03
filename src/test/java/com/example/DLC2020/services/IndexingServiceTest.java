package com.example.DLC2020.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.example.DLC2020.config.EntityManagerFactory;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;

public class IndexingServiceTest {
	
	private IndexingService service;
	private VocabularioDao vocabularioDao;
	private DocumentoDao documentoDao;
	
	@Before
	public void setUp() {
		EntityManager entityManager = new EntityManagerFactory().provide();
		vocabularioDao = new VocabularioDao(entityManager);
		documentoDao = new DocumentoDao(entityManager);
		service = new IndexingService(documentoDao, vocabularioDao);
	}
	
	@Test
	public void testIndexing() throws Exception {
		String path = "./testFiles";
		service.indexing(path);
		assertEquals(5, documentoDao.count());
		System.out.println(vocabularioDao.count());
		assertTrue(vocabularioDao.count() > 0);
	}
	
}
