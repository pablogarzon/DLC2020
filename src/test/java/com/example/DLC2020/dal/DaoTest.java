package com.example.DLC2020.dal;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.example.DLC2020.commons.TestUtils;
import com.example.DLC2020.config.EntityManagerFactory;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Vocabulario;

public class DaoTest {

	private VocabularioDao vocabularioDao;
	private DocumentoDao documentoDao;

	@Test
	public void CreateTest() {
		EntityManager entityManager = new EntityManagerFactory().provide();
		vocabularioDao = new VocabularioDao(entityManager);
		documentoDao = new DocumentoDao(entityManager);
		
		Documento doc1 = new Documento();
		doc1.setNombre("title");
		doc1.setUrl("url");
		documentoDao.create(doc1);
		doc1.setUrl("ur2");
		documentoDao.update(doc1);
		Vocabulario vocabulario = TestUtils.createVocabulario(TestUtils.STR_HELLO, new HashMap() {{ put(doc1, 1);}});
		Vocabulario v1 = vocabularioDao.create(vocabulario);
		assertTrue("object is not null", v1 != null);
		
		List<Vocabulario> words = vocabularioDao.findAll();
		assertTrue("list is not empty", words != null && words.size() > 0);
		
		Vocabulario find = vocabularioDao.findByWord(TestUtils.STR_HELLO);
		assertTrue("findbyWord is ok", find != null);
	}
}
