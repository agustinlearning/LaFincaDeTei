package logica;

import java.util.ArrayList;
import java.io.*;

public class GestionFinca {
	private ArrayList<Toro> losToros;
	private ArrayList<Vaca> lasVacas;
	private ArrayList<Becerrito> losBecerritos;
	public static GestionFinca finca = null;
	public static int idBovino=1;
	
	public GestionFinca() {
		losToros = new ArrayList<>();
		lasVacas = new ArrayList<>();
		losBecerritos = new ArrayList<>();

		GestorBaseDatos.inicializarEstructura();
	}
	
	public void agregarVaca(Vaca V1)
	{
		GestorBaseDatos.insertarVaca(V1);
		lasVacas.add(V1);
	}
	
	public void agregarToro(Toro T1)
	{
		//GestorBaseDatos.insertarToro(T1);
		losToros.add(T1);
	}
	public void agregarBecerrito(Becerrito B1)
	{
		//GestorBaseDatos.insertarBecerrito(B1);
		losBecerritos.add(B1);
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

	public Vaca buscarVacaPorId(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void eliminarVacaPorIndex(Vaca selected) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Toro> getLosToros() {
		return losToros;
	}

	public void setLosToros(ArrayList<Toro> losToros) {
		this.losToros = losToros;
	}

	public ArrayList<Vaca> getLasVacas() {
		return lasVacas;
	}

	public void setLasVacas(ArrayList<Vaca> lasVacas) {
		this.lasVacas = lasVacas;
	}

	public ArrayList<Becerrito> getLosBecerritos() {
		return losBecerritos;
	}

	public void setLosBecerritos(ArrayList<Becerrito> losBecerritos) {
		this.losBecerritos = losBecerritos;
	}

	public static int getIdBovino() {
		return idBovino;
	}
	
	
}
