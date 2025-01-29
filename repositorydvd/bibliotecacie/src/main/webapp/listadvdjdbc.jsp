<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.cie.negocio.Dvd"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">    
	<title>Lista DvdJDBC</title>
</head>
<body>
    <div class="container mt-4">
        <h1 class="text-center mb-4">Lista de DVDs</h1>

        <%
        List<Dvd> lista = (List<Dvd>) request.getAttribute("lista");
        %>

        <table class="table table-striped table-bordered table-hover">
            <thead class="table-light">
                <tr>
                    <th><a href="?orden=iddvd" class="text-decoration-none">Id</a></th>
                    <th><a href="?orden=titulo" class="text-decoration-none">Título</a></th>
                    <th><a href="?orden=anio" class="text-decoration-none">Año</a></th>
                    <th><a href="?orden=disponible" class="text-decoration-none">Disponible</a></th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Dvd d : lista) { %>
                    <tr>
                        <td><%= d.getIddvd() %></td>
                        <td><%= d.getTitulo() %></td>
                        <td><%= d.getAnio() %></td>
                        <td>
                            <span class="badge <%= d.getDisponible() ? "bg-success" : "bg-danger" %>">
                                <%= d.getDisponible() ? "Disponible" : "No disponible" %>
                            </span>
                        </td>
                        <td>
                            <a href="?comando=borrardvd&iddvd=<%= d.getIddvd() %>" class="btn btn-danger btn-sm">Borrar</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-end">
            <a href="?comando=dvdformulario" class="btn btn-primary">Insertar Nuevo DVD</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-qGTKNF4Bnt60vJTZ11Lz0mMgD9WcD3hvO8iNf93ykMvCWo8j4gfW8IWREHEE1Uw+" crossorigin="anonymous"></script>
</body>
</html>
