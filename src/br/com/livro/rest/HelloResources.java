package br.com.livro.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.livro.domain.Response;

/*
 * A anotação @Consumes define o tipo do conteúdo (content-type) que este método 
 * consome.
 * 
 * A anotação @Produces define o tipo de conteúdo que o método retorna.
 * 
 * */

@Path("/hello-jersey")
public class HelloResources {
	
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
	public String helloHTML() {
		return "<br>Olá mundo HTML!</b>";
	}
	
	@GET
	public String helloTextPlain() {
		return "Olá mundo Texto!";
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response helloXML() {
		return Response.Ok("Olá mundo XML!");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloJSON() {
		return Response.Ok("Olá mundo JSON!");
	}
}
