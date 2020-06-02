package com.example.DLC2020.dal;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.example.DLC2020.commons.TestUtils;
import com.example.DLC2020.config.EntityManagerFactory;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Vocabulario;

public class DbTest {

	private VocabularioDao vocabularioDao;
	private DocumentoDao documentoDao;

	@Test
	public void CreateTest() {
		EntityManager entityManager = new EntityManagerFactory().provide();
		vocabularioDao = new VocabularioDao(entityManager);
		documentoDao = new DocumentoDao(entityManager);
		
		Documento doc1 = new Documento();
		doc1.setNombre(TestUtils.title1);
		doc1.setUrl("url");
		documentoDao.create(doc1);
		
		Vocabulario vocabulario = TestUtils.createVocabulario("drama", new HashMap() {{ put(doc1, 1);}});
		
		Vocabulario v1 = (Vocabulario) vocabularioDao.create(vocabulario);
		assertTrue("object is not null", v1 != null);

	}
}
