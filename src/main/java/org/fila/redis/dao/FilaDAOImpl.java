package org.fila.redis.dao;

import java.util.List;

import javax.ejb.Stateless;
import org.fila.redis.modelo.Evento;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

/**
 * DAO que gerencia uma stream de eventos no redis.
 * 
 * @author guto
 *
 */
@Stateless
public class FilaDAOImpl{

	
	private static redis.clients.jedis.JedisPool jedisPool = new redis.clients.jedis.JedisPool(new JedisPoolConfig(),"localhost",32768);

	
	public Evento pop() {
		try (Jedis jedis = jedisPool.getResource()) {
			String id = jedis.get(RedisSchema.headKey());
			// Caso a key não exista inicializa ela com o inicio da stream.
			id = RedisSchema.getNextID(id);
			// Busca na stream uma lista de registros de tamanho máximo igual a 1 a partir
			// do último id lido.
			// Fiz desta forma pois xread é muito complicado desnecessariamente
			List<StreamEntry> entries = jedis.xrange(RedisSchema.getFilaKey(), new StreamEntryID(id), null, 1);
			StreamEntry entry = entries.size() > 0 ? entries.get(0) : null;

			// Se não encontrar registros retorna null e não atualiza o head.
			if (entry == null) {
				return null;
			}
			// Atualiza o head e retorna o evento;
			jedis.set(RedisSchema.headKey(), entry.getID().toString());
			return new Evento(entry.getFields());
		}
	}

	
	public void push(Evento evento) {
		try (Jedis jedis = jedisPool.getResource()) {
			jedis.xadd(RedisSchema.getFilaKey(), StreamEntryID.NEW_ENTRY, evento.toMap());
		}
	}
	
	
	// Limpa registros ja processados.
	public void clean() {
		try (Jedis jedis = jedisPool.getResource()) {
			String id = jedis.get(RedisSchema.headKey());
			if (id == null) {
				return;
			}
			// Lista os até 20000 registros do inicio do stream até a head.
			List<StreamEntry> entries = jedis.xrange(RedisSchema.getFilaKey(), new StreamEntryID("0-0"), new StreamEntryID(id), 20000);
			for(StreamEntry entry: entries) {
				jedis.xdel(RedisSchema.getFilaKey(), entry.getID());
			}
			
		}
	}

}
