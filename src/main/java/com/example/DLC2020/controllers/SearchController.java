package com.example.DLC2020.controllers;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.server.mvc.Viewable;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class SearchController {

	@GET
	public Viewable init() {
		return new Viewable("/search.ftl", "World");
	}
	
	@GET
	@Path("{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<String> titles(@PathParam("query") String query) {
		List<String> results = new ArrayList<>();
		results.add("prueba1");
		results.add("prueba2");
		results.add("prueba3");		
		return results;
	}
}
