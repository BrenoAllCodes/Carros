package br.com.livro.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String nome = (req.getParameter("nome") == null? "Servlet" : req.getParameter("nome"));
		String sobrenome = (req.getParameter("sobrenome") == null? "" : req.getParameter("sobrenome"));
		
		HashMap dados = new HashMap();
		dados.put("NOME", nome);
		dados.put("SOBRENOME", sobrenome);
		System.out.println("Tesste requisicao "+nome);
		escreverMensagemBoasVindas(resp, dados);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String nome = (req.getParameter("nome") == null? "Servlet" : req.getParameter("nome"));
		String sobrenome = (req.getParameter("sobrenome") == null? "" : req.getParameter("sobrenome"));
		
		HashMap dados = new HashMap();
		dados.put("NOME", nome);
		dados.put("SOBRENOME", sobrenome);
		
		escreverMensagemBoasVindas(resp, dados);
	}
	
	private void escreverMensagemBoasVindas(HttpServletResponse resp, HashMap dados) throws ServletException, IOException{
		
		resp.getWriter().print("Ola mundo "+dados.get("NOME")+" "+dados.get("SOBRENOME"));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.getWriter().print("Ola DELETE");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.getWriter().print("Olá PUT");
	}
	
}
