package br.com.livro.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

/*
 * Nesse c�digo, o m�todo getSigletons() � reponsavel por adicionar funcionalidades
 * (features) ao Jersey. Nesse caso, JettisonFeature faz configura��o para permitir
 * ao Jersey retorne dados em JSON.
 * 
 * O m�todo getProperties() configura a propriedade jersey.config.server.provider.packages que 
 * indica o Jersey em qual pacote est�o as classe dos web services. Isto � utilizado para fazer 
 * o escaneamento (scan) das anota��es. Desta forma, podemos utilizar anota��es @Path, @GET,
 * @POST ext., Jersey vai entend�-las (desde que a classe estejam no pacote ou em algum subpacote 
 * do que foi configurado). Como estamos utilizando o pactoe br.com.livro como principal do projeto,
 * configurei este pacote para o Jersey fazer o scan.
 * 
 * Pr�ximo passo � configurar o o servlet do Jersey no arquivo web.xml. vide pag. 109 
 * */

import org.glassfish.jersey.jettison.JettisonFeature;

public class MyApplication extends Application {
	
	/*@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		// Driver do Jettison para gerar JSON.
		singletons.add(new JettisonFeature());
		return singletons;
	}*/

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anota��es REST.
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
	}
}
