package es.cie.repositories;

import java.util.List;

import es.cie.negocio.Dvd;

public interface DvdRepository {
	List<Dvd> buscartodos();
	void insertar(Dvd dvd);
	void borrar(Dvd dvd);
	List<Dvd> buscarTodosOrdenados(String orden);
}
