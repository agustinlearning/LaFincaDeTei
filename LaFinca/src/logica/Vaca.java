package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vaca extends Bovino {
	
	ArrayList<Embarazo> misEmbarazos;
	ArrayList<Becerrito> misHijos;
	
	
		
	public Embarazo getUltimoEmbarazo()
	{
		if(misEmbarazos.size() == 0)
			return null;
		else
		{
			return misEmbarazos.get(misEmbarazos.size()-1);
		}
	}



	public Vaca(String nombre, String urlImagen, LocalDate fechaNac, String raza, String procedencia) {
		super(nombre, urlImagen, fechaNac, raza, procedencia);
		this.misEmbarazos = new ArrayList<>();
		this.misHijos = new ArrayList<>();
	}



	public ArrayList<Embarazo> getMisEmbarazos() {
		return misEmbarazos;
	}



	



	public ArrayList<Becerrito> getMisHijos() {
		return misHijos;
	}
	
	
	
	

}
