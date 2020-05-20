package com.example.DLC2020.controllers;

import org.glassfish.jersey.server.mvc.Viewable;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class IndexerController {

	@GET
	public Viewable test() {
		return new Viewable("/search.ftl", "World");
	}
}
