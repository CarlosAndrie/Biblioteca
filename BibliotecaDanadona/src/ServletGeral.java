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

@WebServlet("/SistemaGeral")
public class ServletGeral extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("logica");
		AlunoDAO dao = new AlunoDAO();
		if (acao.equals("AdicionaAluno")) {
			Aluno aluno = new Aluno();
			aluno.setNome(request.getParameter("nome"));
			aluno.setMatricula(request.getParameter("matricula"));
			aluno.setCPF(request.getParameter("cpf"));
			dao.adiciona(aluno);
			
		} else if (acao.equals("ListaAlunos")) {

		} else if (acao.equals("RemoveAlunoo")) {

		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/aluno-adicionado.jsp");
		rd.forward(request, response);
	}
}
     