package org.fila.redis.dao;

import javax.ejb.Local;

import org.fila.redis.modelo.Evento;

@Local
public interface FilaDAO {
	
	Evento pop();
	void push(Evento evento);
}
