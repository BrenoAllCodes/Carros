package br.com.livro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.domain.Response;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/carros/*")
public class CarroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarroService carroService = new CarroService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if(id != null) {
			// Informou id
			Carro carro = carroService.getCarro(id);
			
			if(carro != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(resp, json);
			} else {
				resp.sendError(404, "Carro não encontrado..."); // Caso não encontre o carro retorna um erro 404
			}
		} else {
			// Obtem a lista de carros 
			List<Carro> carros = carroService.getCarros();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			// Gera JSON com API do Google
			String gJSON = gson.toJson(carros);
			
			// Grava na saida do browser a lista de carro em formato JSON
			ServletUtil.writeJSON(resp, gJSON);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Cria um carro
		Carro carro = getCarroFromRequest(req);
		
		// Salva o carro
		carroService.save(carro);
		
		// Escreve o JSON do novo carro salvo
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(carro);
		
		ServletUtil.writeJSON(resp, json);
	}
	
	// Lê os parâmetros da request e cria o objeto Carro.
	private Carro getCarroFromRequest(HttpServletRequest request) {
		Carro carro = new Carro();
		
		String id = request.getParameter("id");
		
		if(id != null) {
			// Se informou o id, busca o objeto do banco de dados
			carro = carroService.getCarro(Long.parseLong(id));
		}
		
		carro.setNome(request.getParameter("nome"));
		carro.setDesc(request.getParameter("descricao"));
		carro.setUrlFoto(request.getParameter("url_foto"));
		carro.setUrlVideo(request.getParameter("url_video"));
		carro.setLatitude(request.getParameter("latitude"));
		carro.setLongitude(request.getParameter("longitude"));
		carro.setTipo(request.getParameter("tipo"));
		
		return carro;
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if(id != null) {
			carroService.delete(id);
			
			Response retorno = Response.Ok("Carro excluído com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(retorno);
			ServletUtil.writeJSON(resp, json);
			
		} else {
			// URL invalida
			resp.sendError(404, "URL inválida");
		}
	}
	

}
