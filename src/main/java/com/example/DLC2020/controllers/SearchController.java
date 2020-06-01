package com.example.DLC2020.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.services.SearchService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class SearchController {
	
	private final SearchService service;
	
	@Inject
	public SearchController(SearchService service) {
		this.service = service;
	}

	@GET
	public Viewable init() {
		return new Viewable("/search.ftl", "World");
	}
	
	@GET
	@Path("{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Documento> search(@PathParam("query") String query) {
		List<Documento> docs = new ArrayList<>();
		service.search(query);
		return docs;
	}
}
