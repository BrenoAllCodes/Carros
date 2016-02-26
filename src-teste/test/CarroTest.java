package test;

import java.util.List;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	
	private CarroService carroService = new CarroService();
	
	public void testListCarros() {
		List<Carro> carros = carroService.getCarros();
		
		assertNotNull(carros);
		
		// Valida se encontrou algo
		assertTrue(carros.size() > 0);
		
		// Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948",	tucker.getNome());
		
		// Valida se encontrou a Ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		
		// Valida se encontrou o Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
		
	}
	
	public void testSalvarDeletarCarro() {
		Carro carro = new Carro();
		
		carro.setNome("Teste");
		carro.setDesc("Teste desc");
		carro.setUrlFoto("Teste url foto");
		carro.setUrlVideo("Teste url video");
		carro.setLatitude("Teste latitude");
		carro.setLongitude("teste longitude");
		carro.setTipo("tipo");
		carroService.save(carro);
		
		// id do carro salvo
		Long id = carro.getId();
		assertNotNull(id);
		
		// Busca no banco de dados para confirmar que o carro foi salvo
		carro = carroService.getCarro(id);
		assertEquals("Teste", carro.getNome());
		assertEquals("Teste desc", carro.getDesc());
		assertEquals("Teste url foto", carro.getUrlFoto());
		assertEquals("Teste url video", carro.getUrlVideo());
		assertEquals("Teste latitude", carro.getLatitude());
		assertEquals("teste longitude", carro.getLongitude());
		assertEquals("tipo", carro.getTipo());
		
		// Atualiza o carro
		carro.setNome("Teste UPDATE");
		carroService.save(carro);
		
		// Busca carro novamente (vai estar atualizado)
		carro = carroService.getCarro(id);
		assertEquals("Teste UPDATE", carro.getNome());
		
		// Deleta o carro
		carroService.delete(id);
		
		// Busca o carro novamente
		carro = carroService.getCarro(id);
		
		// Desta vez o carro não existe novamente 
		assertNull(carro);
	}

}
