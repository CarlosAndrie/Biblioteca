package biblioteca.models;

import java.util.Calendar;

public class Aluno {
		private Long id;
		private String nome;
		private String matricula;
		private String cpf;
		private Calendar data;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public String getCPF() {
			return cpf;
		}

		public void setCPF(String cpf) {
			this.cpf = cpf;
		}

		public Calendar getDataNascimento() {
			return data;
		}

		public void setDataNascimento(Calendar data) {
			this.data = data;
		}

	}


