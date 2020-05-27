package InterfazVisual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import Controlador.Controler;


@SuppressWarnings("serial")
public class RutasB extends JPanel {

	    private static RutasB instanciaUnica =null;
	    private static Display displei = Display.getInstance();
		private Controler controla = Controler.getInstance();
		private JButton btnBuscarNorm ;
		private JTable tablaPred;
		ArrayList <String> list;
	
	public static RutasB getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new RutasB();
	        return instanciaUnica;
	}
	
	public RutasB() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Rutas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		
	
		btnBuscarNorm = new JButton("Consultar ");
		btnBuscarNorm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscarNorm.setBounds(166, 346, 126, 35);
		add(btnBuscarNorm);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("Button.disabledText"), 2, true));
		panel.setBounds(6, 19, 438, 313);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tablaPred = new JTable();
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				new Object [] [] {
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
				},
				new String [] {
					"Categoria","Placas","Cupos","Modelo","Conductor"			
				}
				));
		tablaPred.setRowHeight(30);
		
		scrollPane.setViewportView(tablaPred);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JButton btnCutoff = new JButton("Desconectar");
		btnCutoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.desconectar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCutoff.setBounds(6, 349, 106, 29);
		add(btnCutoff);
		
		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.goback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(338, 349, 106, 29);
		add(btnBack);
		
	}
	
	public void updateTable() {
		setLista();
		String cat,plc,mod,cond,cup;
    	
		String matriz [][] = new String[list.size()][5];
		
		for(int i=0; i<list.size();i++) {
			
			String paque = list.get(i);
          
			if(paque.startsWith("Bus")) {
	    		
	    		cat ="Bus";
	    		plc = paque.substring(4, 10);
	    		cup = paque.substring(11, 12);
	    		mod = paque.substring(20, 24);
	    		cond = paque.substring(35); 
	    		
	    		
	    	 }else {
	    		
	    		cat ="Wheels";
	    		plc = paque.substring(7, 13);
	    		cup = paque.substring(14, 15);
	    		mod = paque.substring(23, 27);
	    		cond = paque.substring(37); 
	    		
	    	 } 
			
			
			matriz [i][0] = cat;
			matriz [i][1] = plc;
			matriz [i][2] = cup;
			matriz [i][3] = mod;
			matriz [i][4] = cond;
		}
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				matriz,
				new String [] {
				 "Categoria","Placas","Cupos","Modelo","Conductor"			
				}
				));
	}
	public void setLista() {
		this.list = controla.obtRout();
	}
	
	
}
