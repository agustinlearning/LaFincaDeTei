package logica;

import java.time.LocalDate;

public abstract class Bovino {
	protected String id;
	protected String nombre;
	protected LocalDate fechaNac;
	protected String raza;
	protected String procedencia;
	
	public Bovino(String id, String nombre, LocalDate fechaNac, String raza, String procedencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.raza = raza;
		this.procedencia = procedencia;
	}
	
	

}
