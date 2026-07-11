package logica;

import java.util.ArrayList;

public class GestionFinca {
	private ArrayList<Toro> losToros;
	private ArrayList<Vaca> lasVacas;
	private ArrayList<Becerrito> losBecerritos;
	public static GestionFinca finca = null;
	public GestionFinca() {
		
	}
	
	public static GestionFinca getInstancia()
	{
		if(finca == null)
		{
			finca = new GestionFinca();
		}
		return finca;
	}
	
	public Toro buscarToroPorNombre(String nombre)
	{
		for (Toro toro : losToros) {
			if(toro.getNombre().equalsIgnoreCase(nombre))
			{
				return toro;
			}
		}
		return null;
	}
	
	
}
