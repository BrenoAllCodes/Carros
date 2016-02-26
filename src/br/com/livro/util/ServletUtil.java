package br.com.livro.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	
	public static void writeXML(HttpServletResponse response, String xml) throws IOException {
		if(xml != null) {
			PrintWriter writer = response.getWriter(); // Pega a saida da resposta 
			response.setContentType("application/xml;charset=UTF8"); // grava na resposata o tipo da mensagem retornada
			writer.write(xml); // grava na saida da resposta os dados em xml 
			writer.close(); // Fecha o fluxo da saida 
		}
	}
	
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		if(json != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF8");
			writer.write(json);
			writer.close();
		}
	}
}
