package org.fila.redis.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.fila.redis.dao.FilaDAOImpl;
import org.fila.redis.modelo.Evento;

@Stateless
public class FilaBusiness {

	@Inject
	private FilaDAOImpl filaDAO;
	
	public String push() {
		Evento e = new Evento();
		e.setChaveAcesso("123");
		e.setXml("<nome>Guto</nome>");
		filaDAO.push(e);
		return "0.0.1"; 
	}
	
	public Evento pop() {
		Evento e = filaDAO.pop();
		return e; 
	}
	
	public void clean() {
		filaDAO.clean();
	}
}
