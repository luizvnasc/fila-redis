package org.fila.redis.dao;

import javax.ejb.EJB;
import org.fila.redis.modelo.Evento;
import org.junit.jupiter.api.Test;

class FilaDAOTests {
	
	@EJB
	FilaDAO dao;
	
	@Test
	void TestPush() {
		Evento e = new Evento();
		e.setXml("<nome>Guto</nome>");
		e.setChaveAcesso("123");
		dao.push(e);
		
	}

}
