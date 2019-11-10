package org.fila.redis.rest.ws;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fila.redis.business.FilaBusiness;
import org.fila.redis.modelo.Evento;

@Path("/fila")

public class Fila {

	@Inject
	FilaBusiness filaBusiness;
	
	@Path("/push")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String versao() {
		return filaBusiness.push();
	}
	
	@Path("/pop")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Evento pop() {
		return filaBusiness.pop();
	}
	
	@Path("/clean")
	@GET
	public void clean() {
		filaBusiness.clean();
	}
}
