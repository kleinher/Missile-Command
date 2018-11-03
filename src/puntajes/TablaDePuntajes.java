package puntajes;


import java.util.Map;
import java.util.TreeMap;

public class TablaDePuntajes {	 
	 private static Map<String, Integer> tabla=new TreeMap<String,Integer>();
	 
	/*Guarda En un TreeMap los puntajes de los jugadores una vez finalizada la partida*/
	public static void actualizarTablaDePuntajes(Integer score, String nombre) {
		tabla.put(nombre,score);
		tabla.toString();
	}
	public void agregarPuntaje(String nombre, Integer puntaje) {
		TablaDePuntajes.tabla.put(nombre, puntaje);
		
	}
}
