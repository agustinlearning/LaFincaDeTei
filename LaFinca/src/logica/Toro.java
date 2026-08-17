package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Toro extends Bovino {
	ArrayList<Becerrito> misHijos;
	public Toro(String id,String nombre,String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super(id, nombre, urlImagen, fechaNac, raza, procedencia);
		this.misHijos = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Becerrito> getMisHijos() {
		return misHijos;
	}

}
