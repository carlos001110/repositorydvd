package es.cie.negocio;

public class Dvd {
	private int iddvd;
	private String titulo;
	private int anio;
	private boolean disponible;
	public int getIddvd() {
		return iddvd;
	}
	public void setIddvd(int iddvd) {
		this.iddvd = iddvd;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Dvd() {
		super();
	}
	public Dvd(int iddvd, String titulo, int anio, boolean disponible) {
		super();
		this.iddvd = iddvd;
		this.titulo = titulo;
		this.anio = anio;
		this.disponible = disponible;
	}
	public Dvd(int iddvd) {
		super();
		this.iddvd = iddvd;
	}

}