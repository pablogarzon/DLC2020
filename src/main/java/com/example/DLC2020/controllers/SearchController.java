package com.example.DLC2020.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.services.SearchService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

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
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Documento> search(@QueryParam("q") String query) {
		List<Documento> docs = new ArrayList<>();
		docs = service.search(query);
		return docs;
	}

	@POST
	@Path("{document}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response download(Documento documento) {
		File file = new File(documento.getUrl());
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"" + documento.getNombre() + "\"");
		return response.build();
	}
}
