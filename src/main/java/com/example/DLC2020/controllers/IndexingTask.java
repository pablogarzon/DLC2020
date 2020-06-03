package com.example.DLC2020.controllers;

import javax.inject.Inject;

import com.example.DLC2020.services.IndexingService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/indexing")
public class IndexingTask {

	
	private final IndexingService indexingService;
	
	@Inject
	public IndexingTask(IndexingService indexingService) {
		this.indexingService = indexingService;
		start();
	}
	
	@GET
	public String start() {
		System.out.println("inicio del indexando archivos " + System.currentTimeMillis() );
		try {
			indexingService.indexing("./testFiles");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fin del indexando archivos " + System.currentTimeMillis() );
		return "OK";
	}
}
