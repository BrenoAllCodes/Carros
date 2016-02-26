package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class CarroDAO extends BaseDAO {
	
	// * MÉTODOS DA CLASSE PODEM SER ESTATICOS, POIS A CLASSE NÃO ULTILIZA VARIAVES DE INSTANCIA
	// * PODE SER CRIADO UM MÉTODO ESPECIALIZADO PARA BLOCOS DE CODIGO REPETIDO
	
	public Carro getCarroById(Long id) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Carro carro = createCarro(rs);
				rs.close();
				return carro;
			}

		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public List<Carro> findByName(String name) throws SQLException {

		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase());
			;
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro carro = createCarro(rs);
				carros.add(carro);
			}

			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}

		return carros;
	}

	public List<Carro> findByTipo(String tipo) throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro carro = createCarro(rs);
				carros.add(carro);
			}

			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
		
		return carros;
	}
	
	public List<Carro> getCarros() throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro carro = createCarro(rs);
				carros.add(carro);
			}

			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
		
		return carros;
	}
	
	public Carro createCarro(ResultSet rs) throws SQLException {
		
		Carro carro = new Carro();
		
		carro.setId(rs.getLong("id"));
		carro.setNome(rs.getString("nome"));
		carro.setDesc(rs.getString("descricao"));
		carro.setUrlFoto(rs.getString("url_foto"));
		carro.setUrlVideo(rs.getString("url_video"));
		carro.setLatitude(rs.getString("latitude"));
		carro.setLongitude(rs.getString("longitude"));
		carro.setTipo(rs.getString("tipo"));
		
		return carro;
	}
	
	public void save(Carro carro) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			
			if(carro.getId() == null) {
				stmt = conn.prepareStatement("insert into carro(nome, descricao, url_foto,"
						+ "url_video, latitude, longitude, tipo) VALUES(?,?,?,?,?,?,?) ", 
						Statement.RETURN_GENERATED_KEYS);
				
			} else {
				stmt = conn.prepareStatement("update carro set nome=?, descricao=?, url_foto=?, url_video=?, "
						+ "latitude=?, longitude=?, tipo=? where id=?");
			}
			
			stmt.setString(1, carro.getNome());
			stmt.setString(2, carro.getDesc());
			stmt.setString(3, carro.getUrlFoto());
			stmt.setString(4, carro.getUrlVideo());
			stmt.setString(5, carro.getLatitude());
			stmt.setString(6, carro.getLongitude());
			stmt.setString(7, carro.getTipo());
			
			// Caso de update
			if(carro.getId() != null) {
				stmt.setLong(8, carro.getId());
			}
			
			int count = stmt.executeUpdate();
			
			if(count == 0) {
				throw new SQLException("Erro ao inseri um carro");
			}
			
			// Se inseriu, ler o id auto incremento
			if(carro.getId() == null) {
				Long id = getGeneratedId(stmt);
				carro.setId(id);
			}
			
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public static Long getGeneratedId(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		
		if(rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}
	
	public boolean delete(Long id) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from carro where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			
			return ok;

		} finally  {
			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	}
}
