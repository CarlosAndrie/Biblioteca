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
import biblioteca.daos.LivroDAO;
import biblioteca.models.Aluno;
import biblioteca.models.Livro;

@WebServlet("/Livros")
public class AdicionaLivro extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editora = request.getParameter("editora");
		String anoPublicacaoEmTexto = request.getParameter("anoDePublicacao");
		Calendar anoPublicacao = null;
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(anoPublicacaoEmTexto);
			anoPublicacao = Calendar.getInstance();
			anoPublicacao.setTime(date);
		} catch (ParseException e) {
			
		}
		

		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setEditora(editora);
		livro.setAnoDePublicacao(anoPublicacao);

		LivroDAO dao = new LivroDAO();
		dao.inserir(livro);

		RequestDispatcher rd = request.getRequestDispatcher("/livro-cadatrado.jsp");
		rd.forward(request,response);
	}
}
