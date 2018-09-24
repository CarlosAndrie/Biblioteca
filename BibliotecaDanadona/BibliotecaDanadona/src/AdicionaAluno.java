
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import biblioteca.daos.AlunoDAO;
import biblioteca.models.Aluno;


@WebServlet("/Alunos")
public class AdicionaAluno extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String cpf = request.getParameter("cpf");
		String dataDeNascimento = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeNascimento);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			
		}
		

		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		aluno.setCPF(cpf);
		aluno.setDataNascimento(dataNascimento);

		AlunoDAO dao = new AlunoDAO();
		dao.inserir(aluno);

		RequestDispatcher rd = request
				.getRequestDispatcher("/Aluno-Cadastrado.jsp");
				rd.forward(request,response);
	}


	}

