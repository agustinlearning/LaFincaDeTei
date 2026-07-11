package logica;

import java.time.LocalDate;

public class Becerrito extends Bovino {
	private String numero;
	private Vaca madre;
	private Toro padre;
	private String genero;
	public Becerrito(String id, String nombre, LocalDate fechaNac, String raza, String procedencia) {
		super(id, nombre, fechaNac, raza, procedencia);
		
	}

}
