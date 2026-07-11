package logica;

import java.time.LocalDate;

public class Embarazo {
	private String id;
	private Toro padre;
	private LocalDate fechaEmbarazo;
	private LocalDate fechaParto;
	private Boolean finalizado;
	
	public Embarazo(LocalDate fechaEmbarazo) {
		super();
		this.fechaEmbarazo = fechaEmbarazo;
	}
	
	public void terminarEmbarazo(String nombre, LocalDate nacimiento)
	{
		this.setPadre(GestionFinca.getInstancia().buscarToroPorNombre(nombre));
		this.setFechaParto(nacimiento);
		this.setFinalizado(true);
	}

	public Toro getPadre() {
		return padre;
	}

	public void setPadre(Toro padre) {
		this.padre = padre;
	}

	public LocalDate getFechaEmbarazo() {
		return fechaEmbarazo;
	}

	public void setFechaEmbarazo(LocalDate fechaEmbarazo) {
		this.fechaEmbarazo = fechaEmbarazo;
	}

	public LocalDate getFechaParto() {
		return fechaParto;
	}

	public void setFechaParto(LocalDate fechaParto) {
		this.fechaParto = fechaParto;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public String getId() {
		return id;
	}
	
	
	
	
	
	
	
	
	
	
}
