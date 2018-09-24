package biblioteca.models;

import java.util.Calendar;

public class Emprestimo {
	private Long id;
	private String aluno;
	private String livro;
	private Calendar dataDeEmprestimo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public String getLivro() {
		return livro;
	}
	public void setLivro(String livro) {
		this.livro = livro;
	}
	public Calendar getDataDeEmprestimo() {
		return dataDeEmprestimo;
	}
	public void setDataDeEmprestimo(Calendar dataDeEmprestimo) {
		this.dataDeEmprestimo = dataDeEmprestimo;
	}
	
	
	
}
