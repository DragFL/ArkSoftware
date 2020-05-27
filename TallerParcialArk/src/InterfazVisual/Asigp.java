package InterfazVisual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controler;
import Decorator.Composite;

@SuppressWarnings("serial")
public class Asigp extends JPanel {
	private static Asigp instanciaUnica = null;
	private static Display displei = Display.getInstance();
	private Controler controla = Controler.getInstance();
	private JTextField plcs;
	private JTable tablaPred1;
	private JTable tablaPred;
	ArrayList<Composite> locs;
	ArrayList<String> list ;
	private JTextField munic;
	
	public static Asigp getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new Asigp();
	        return instanciaUnica;
	}
	
	public Asigp() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Rutas en localidades", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar rutas por localidad");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 23, 242, 22);
		add(lblNewLabel);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel1.setBounds(162, 67, 273, 233);
		add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane1 = new JScrollPane();
		tablaPred1 = new JTable();
		tablaPred1.setModel(new javax.swing.table.DefaultTableModel(
				new Object [] [] {
					{null,null,null},
					{null,null,null},
					{null,null,null},
				},
				new String [] {
					"Categoria","Placas","Conductor"			
				}
				));
		tablaPred1.setRowHeight(30);
		tablaPred1.addMouseListener(new MouseAdapter(){	
		  public void mouseClicked(MouseEvent e) {
			int i = tablaPred1.getSelectedRow();
			 plcs.setText(tablaPred1.getValueAt(i, 1).toString());
			
		  }});
		
		scrollPane1.setViewportView(tablaPred1);
		panel1.add(scrollPane1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(16, 67, 134, 233);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tablaPred = new JTable();
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				new Object [] [] {
					{null},
					{null},
					{null},
				},
				new String [] {
					"Municipio"			
				}
				));
		tablaPred.setRowHeight(30);
		tablaPred.addMouseListener(new MouseAdapter(){	
		  public void mouseClicked(MouseEvent e) {
			int i = tablaPred.getSelectedRow();
			 munic.setText(tablaPred.getValueAt(i, 0).toString());
			
		  }});
		
		scrollPane.setViewportView(tablaPred);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JButton btnLoad = new JButton("Cargar");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateTable2();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(16, 36, 117, 29);
		add(btnLoad);
		
		plcs = new JTextField();
		plcs.setHorizontalAlignment(SwingConstants.CENTER);
		plcs.setBounds(318, 312, 79, 26);
		add(plcs);
		plcs.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Placas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(246, 317, 79, 16);
		add(lblNewLabel_1);
		
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
		btnCutoff.setBounds(327, 368, 117, 29);
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
		btnBack.setBounds(6, 368, 117, 29);
		add(btnBack);
		
		munic = new JTextField();
		munic.setHorizontalAlignment(SwingConstants.CENTER);
		munic.setColumns(10);
		munic.setBounds(105, 312, 94, 26);
		add(munic);
		
		JLabel lblConductor = new JLabel("Municipio");
		lblConductor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConductor.setBounds(26, 317, 79, 16);
		add(lblConductor);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String muni = munic.getText();
					setLista1(controla.esLoc(muni));
					
					updateTable1();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(152, 345, 144, 38);
		add(btnBuscar);
	}
	public void updateTable1() {
		
		String cat,plc,cond;
    	
		String matriz [][] = new String[list.size()][3];
		
		for(int i=0; i<list.size();i++) {
			
			String paque = list.get(i);
          
			if(paque.startsWith("Bus")) {
	    		
	    		cat ="Bus";
	    		plc = paque.substring(4, 10);
	    		cond = paque.substring(35); 
	    		
	    		
	    	 }else {
	    		
	    		cat ="Wheels";
	    		plc = paque.substring(7, 13);
	    		cond = paque.substring(37); 
	    		
	    	 } 
			
			
			matriz [i][0] = cat;
			matriz [i][1] = plc;
			matriz [i][2] = cond;
			
		}
		tablaPred1.setModel(new javax.swing.table.DefaultTableModel(
				matriz,
				new String [] {
				 "Categoria","Placas","Conductor"			
				}
				));
	}
	
	public void setLista1(Composite esploc) {
		this.list = esploc.rutasMun();
		
	}
	
	
	
	public void updateTable2() {
	setLista2();
		
		String matriz [][] = new String[locs.size()][1];
		for(int i=0; i<locs.size();i++) {
			matriz [i][0] = locs.get(i).getName();
				
		}
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				matriz,
				new String [] {
				"Municipio"			
				}
				));
	}
	
	public void setLista2() {
		this.locs = controla.obtnLoc();
	}
	
	
	
}
