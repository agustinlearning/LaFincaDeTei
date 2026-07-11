package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vaca extends Bovino {
	
	ArrayList<Embarazo> misEmbarazos;
	ArrayList<Becerrito> misHijos;
	
	
		
	public Embarazo getUltimoEmbarazo()
	{
		int index = misEmbarazos.size();
		return misEmbarazos.get(index - 1);
	}
	
	public Vaca(String nombre, String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super(nombre, urlImagen, fechaNac, raza, procedencia);
	}

}
