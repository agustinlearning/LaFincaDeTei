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

}
