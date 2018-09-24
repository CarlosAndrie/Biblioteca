package biblioteca.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import biblioteca.models.Aluno;
import biblioteca.models.Emprestimo;

public class EmprestimoDAO {
	private Connection connection;

	public EmprestimoDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public boolean inserir(Emprestimo emprestimo) {

		String sql = "insert into emprestimo (id, aluno , livro , dataDeEmprestimo ) values (?, ?, ?, ?);";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, emprestimo.getAluno());
			stmt.setString(2, emprestimo.getLivro());
			stmt.setDate(3, new java.sql.Date(emprestimo.getDataDeEmprestimo().getTimeInMillis()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<Emprestimo> getLista() {
		List<Emprestimo> result = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimo;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setId(rs.getLong("id"));
				emprestimo.setAluno(rs.getString("aluno"));
				emprestimo.setLivro(rs.getString("livro"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataDeEmprestimo"));
				emprestimo.setDataDeEmprestimo(data);

				// adicionando o objeto � lista
				result.add(emprestimo);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean alterar(Emprestimo emprestimo) {
		String sql = "update contatos set emprestimo=?, livro=?, dataDeEmprestimo=? where id=?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, emprestimo.getAluno());
			stmt.setString(2, emprestimo.getLivro());
			stmt.setDate(3, new java.sql.Date(emprestimo.getDataDeEmprestimo().getTimeInMillis()));
			stmt.setLong(4, emprestimo.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean remover(Emprestimo emprestimo) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from emprestimo where id=?;");
			stmt.setLong(1, emprestimo.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Emprestimo getById(Long id) {
		Emprestimo result = null;

		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from emprestimo where id = ?;");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				
				result = new Emprestimo();
				result.setId(rs.getLong("id"));
				result.setAluno(rs.getString("aluno"));
				result.setLivro(rs.getString("livro"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataDeEmprestimo"));
				result.setDataDeEmprestimo(data);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void adiciona(Emprestimo emprestimo) {
		
		
	}
}
