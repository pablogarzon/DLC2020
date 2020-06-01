package com.example.DLC2020.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;

public class EntityManagerFactory implements Factory<EntityManager>{
	
	private final String PERSISTENCE_UNIT_NAME = "com.example_DLC2020_jar_0.0.1-SNAPSHOTPU";
	
	@Override
	public EntityManager provide() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	@Override
	public void dispose(EntityManager instance) {
		instance.close();
	}
}
