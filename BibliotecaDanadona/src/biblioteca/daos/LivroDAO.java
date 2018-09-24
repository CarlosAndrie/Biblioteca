package biblioteca.daos;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.List;

import biblioteca.models.Livro;

	public class LivroDAO {

		private Connection connection;

		public LivroDAO() {
			connection = ConnectionFactory.getConnection();
		}

		public boolean inserir(Livro livro) {

			String sql = "insert into livro (titulo , autor , editora , anoDePublicacao ) values (?, ?, ?, ?);";

			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setString(1, livro.getTitulo());
				stmt.setString(2, livro.getAutor());
				stmt.setString(3, livro.getEditora());

				stmt.setDate(4, new java.sql.Date(livro.getAnoDePublicacao().getTimeInMillis()));

				stmt.execute();
				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			return true;
		}

		public List<Livro> getLista() {
			List<Livro> result = new ArrayList<>();

			try {
				PreparedStatement stmt = this.connection.prepareStatement("select * from livros;");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					// criando o objeto Livro
					Livro livro = new Livro();
					livro.setId(rs.getLong("id"));
					livro.setTitulo(rs.getString("titulo"));
					livro.setAutor(rs.getString("autor"));
					livro.setEditora(rs.getString("editora"));

					// montando a data atraves do Calendar
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("anoDePublicacao"));
					livro.setAnoDePublicacao(data);

					// adicionando o objeto � lista
					result.add(livro);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;
		}

		public boolean alterar(Livro livro) {
			String sql = "update livros set titulo=?, autor=?, editora=?, anoDePublicacao=? where id=?;";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, livro.getTitulo());
				stmt.setString(2, livro.getAutor());
				stmt.setString(3, livro.getEditora());
				stmt.setDate(4, new java.sql.Date(livro.getAnoDePublicacao().getTimeInMillis()));
				stmt.setLong(5, livro.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		public boolean remover(Livro livro) {
			try {
				PreparedStatement stmt = connection.prepareStatement("delete from livros where id=?;");
				stmt.setLong(1, livro.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		public Livro getById(Long id) {
			Livro result = null;

			try {
				PreparedStatement stmt = this.connection.prepareStatement("select * from livros where id = ?;");
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					// criando o objeto Livro
					result = new Livro();
					result.setId(rs.getLong("id"));
					result.setTitulo(rs.getString("titulo"));
					result.setAutor(rs.getString("autor"));
					result.setEditora(rs.getString("editora"));

					// montando a data atraves do Calendar
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("anoDePublicacao"));
					result.setAnoDePublicacao(data);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;
		}

		public void adiciona(Livro livro) {
			// TODO Auto-generated method stub
			
		}

		

	}

