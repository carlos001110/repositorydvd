package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.cie.negocio.Dvd;
import es.cie.repositories.DvdRepository;
import es.cie.repositories.jdbc.DvdRepositoryJdbc;

@WebServlet("/DvdServlet")
public class DvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DvdRepository repo = new DvdRepositoryJdbc();
	List<Dvd> lista = repo.buscartodos();

	// el metodo que se ejecuta cuando pido el servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("comando") == null) {

			if (request.getParameter("orden") != null) {
				lista = repo.buscarTodosOrdenados(request.getParameter("orden"));
			} else {
				// datos de los dvd
				lista = repo.buscartodos();
			}
			request.setAttribute("lista", lista);
			RequestDispatcher despachador = request.getRequestDispatcher("listadvdjdbc.jsp");
			despachador.forward(request, response);
		} else {
			// leo el comando
			String comando = request.getParameter("comando");
			if (comando.equals("dvdformulario")) {
				RequestDispatcher despachador = request.getRequestDispatcher("dvdformulario.html");
				despachador.forward(request, response);
				//salvar dvd
			}else if(comando.equals("salvardvd")) {

				String titulo = request.getParameter("titulo");
				int anio = Integer.parseInt(request.getParameter("anio"));
				boolean disponible = Boolean.parseBoolean(request.getParameter("disponible"));

				Dvd d = new Dvd(0, titulo, anio, disponible);
				repo.insertar(d);
				lista = repo.buscartodos();
				// vuelve al listado de dvds
				request.setAttribute("lista", lista);
				RequestDispatcher despachador = request.getRequestDispatcher("listadvdjdbc.jsp");
				despachador.forward(request, response);
			}else if(comando.equals("borrardvd")) {
				int iddvd = Integer.parseInt(request.getParameter("iddvd"));
				System.out.println("funciona");

			

				Dvd d = new Dvd(iddvd);
				repo.borrar(d);

				lista = repo.buscartodos();
				request.setAttribute("lista", lista);
				RequestDispatcher despachador = request.getRequestDispatcher("listadvdjdbc.jsp");
				despachador.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
