package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vaca extends Bovino {
	
	ArrayList<Embarazo> misEmbarazos;
	ArrayList<Becerrito> misHijos;
	
	public Vaca(String id, String nombre,String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super(id, nombre,urlImagen, fechaNac, raza, procedencia);
	}
		
	public Embarazo getUltimoEmbarazo()
	{
		int index = misEmbarazos.size();
		return misEmbarazos.get(index - 1);
	}

}
