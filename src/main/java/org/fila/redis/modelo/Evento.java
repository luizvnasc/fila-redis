package org.fila.redis.modelo;

import java.util.HashMap;
import java.util.Map;

public class Evento {

	private String chaveAcesso;
	private String xml;
	
	public Evento() {}
	
	public Evento(Map<String, String> map) {
		chaveAcesso = map.get("chaveAcesso");
		xml = map.get("xml");
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String,String>();
		map.put("chaveAcesso",chaveAcesso);
		map.put("xml", xml);
		return map;
	}
}
