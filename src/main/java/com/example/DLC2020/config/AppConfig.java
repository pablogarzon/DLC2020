package com.example.DLC2020.config;

import javax.persistence.EntityManager;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.PosteoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.services.IndexingService;
import com.example.DLC2020.services.SearchService;

public class AppConfig extends ResourceConfig {
	
	public AppConfig() {
		packages("com.example.DLC2020");
		register(DocumentoDao.class);
		register(VocabularioDao.class);
		register(PosteoDao.class);
		register(SearchService.class);
		register(IndexingService.class);
		
		register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				bindAsContract(DocumentoDao.class);
				bindAsContract(VocabularioDao.class);
				bindAsContract(PosteoDao.class);
				bindAsContract(SearchService.class);
				bindAsContract(IndexingService.class);
				bindFactory(EntityManagerFactory.class).to(EntityManager.class);
			}
		});
		
		property(FreemarkerMvcFeature.TEMPLATE_BASE_PATH, "views");
		register(FreemarkerMvcFeature.class);
	}

}
