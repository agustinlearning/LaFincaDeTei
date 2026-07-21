package logica;

import java.time.LocalDate;

public abstract class Bovino {
	protected String id;
	protected String nombre;
	protected LocalDate fechaNac;
	protected String urlImagen;
	protected String raza;
	protected String procedencia;
	protected Boolean vivo = true;
	
	public Bovino(String nombre,String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super();
		GestionFinca.getInstancia();
		this.id = "B-" + GestionFinca.idBovino;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.raza = raza;
		this.procedencia = procedencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public Boolean getVivo() {
		return vivo;
	}

	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}

	public String getId() {
		return id;
	}
	
	

}
