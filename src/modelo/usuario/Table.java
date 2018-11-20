package modelo.usuario;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.Menu;

public class Table extends JFrame {
	final JTable table;

    public Table(LinkedList<InformacionJugador> ListaDePuntajes,int CantidadAMostar) {
        super(" Ranking ");
        DefaultTableModel modelo = new DefaultTableModel(); 
         table= new JTable(modelo);
        table.setEnabled(false);
        table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));  
        modelo.addColumn("Posicion");
        modelo.addColumn("Jugador");
        modelo.addColumn("Puntaje");
        modelo.addColumn("Tiempo");
        for (Iterator<InformacionJugador> i = ListaDePuntajes.iterator(); i.hasNext();) {
			InformacionJugador infoTabla=i.next();
			Object[] filas = {infoTabla.posicionRank,infoTabla.nombreRank,infoTabla.puntajeRank,infoTabla.tiempoJugado};
			CantidadAMostar--;
			if(CantidadAMostar <= 0)
				modelo.addRow(filas);
    	}
        
        JScrollPane scrollPane = new JScrollPane(table);
        setContentPane(scrollPane);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	table.setVisible(false);
            	//Menu.VentanaMenu.show();
                //System.exit(0);
            	
            	
            }
              
        });
    }
}