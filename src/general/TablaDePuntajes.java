package general;


import java.util.Map;
import java.util.TreeMap;

public class TablaDePuntajes {	 
	 private static Map<String, Integer> tabla=new TreeMap<String,Integer>();
	 
	
	public static void actualizarTablaDePuntajes() {
	
	}
	public void agregarPuntaje(String nombre, Integer puntaje) {
		TablaDePuntajes.tabla.put(nombre, puntaje);
		
	}
}
