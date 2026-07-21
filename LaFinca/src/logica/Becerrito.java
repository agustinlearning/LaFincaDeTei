package logica;

import java.time.LocalDate;

public class Becerrito extends Bovino {
	private String numero;
	private Vaca madre;
	private Toro padre;
	private String genero;
	public Becerrito(String nombre,String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super(nombre,urlImagen, fechaNac, raza, procedencia);
		
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Vaca getMadre() {
		return madre;
	}
	public void setMadre(Vaca madre) {
		this.madre = madre;
	}
	public Toro getPadre() {
		return padre;
	}
	public void setPadre(Toro padre) {
		this.padre = padre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

}
