package br.com.livro.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.livro.domain.Response;

/*
 * A anota��o @Consumes define o tipo do conte�do (content-type) que este m�todo 
 * consome.
 * 
 * A anota��o @Produces define o tipo de conte�do que o m�todo retorna.
 * 
 * */

@Path("/hello-jersey")
public class HelloResources {
	
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
	public String helloHTML() {
		return "<br>Ol� mundo HTML!</b>";
	}
	
	@GET
	public String helloTextPlain() {
		return "Ol� mundo Texto!";
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response helloXML() {
		return Response.Ok("Ol� mundo XML!");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloJSON() {
		return Response.Ok("Ol� mundo JSON!");
	}
}
