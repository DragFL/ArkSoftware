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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controler;
import Decorator.Caracteristicas;
import Decorator.Conductor;
import Decorator.Cupos;
import Decorator.ID;
import Decorator.Placa;
import Decorator.TransDetalle;
import Decorator.Transporte;
import Modelo.Usuario;
import javax.swing.SwingConstants;

public class RutasA extends JPanel {

	private static RutasA instanciaUnica = null;
	private static Display displei = Display.getInstance();
	private Controler controla = Controler.getInstance();
	private JTextField plcs;
	private JTable tablaPred;
	ArrayList<String> list ;
	private String oldPlc;
	private JTextField catego;
	private JTextField cpos;
	private JTextField modl;
	private JTextField drvr;
	
	public static RutasA getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new RutasA();
	        return instanciaUnica;
	}
	public RutasA() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Panel de Administrador", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administración de rutas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 23, 242, 22);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(16, 86, 419, 164);
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
		tablaPred.addMouseListener(new MouseAdapter(){	
		  public void mouseClicked(MouseEvent e) {
			int i = tablaPred.getSelectedRow();
			 catego.setText(tablaPred.getValueAt(i, 0).toString());
			 plcs.setText(tablaPred.getValueAt(i, 1).toString());
			 cpos.setText(tablaPred.getValueAt(i, 2).toString());
			 modl.setText(tablaPred.getValueAt(i, 3).toString());
			 drvr.setText(tablaPred.getValueAt(i, 4).toString());
			 oldPlc = tablaPred.getValueAt(i,1).toString();
		  }});
		
		scrollPane.setViewportView(tablaPred);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JButton btnLoad = new JButton("Cargar");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(318, 52, 117, 29);
		add(btnLoad);
		
		plcs = new JTextField();
		plcs.setHorizontalAlignment(SwingConstants.CENTER);
		plcs.setBounds(110, 289, 79, 26);
		add(plcs);
		plcs.setColumns(10);
		
		JButton btnAdd = new JButton("Agregar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rute = catego.getText()+" "+plcs.getText()+" "+cpos.getText()+" Modelo "+modl.getText()+" Conductor"+drvr.getText();
				try {
					System.out.println(rute);
					controla.agregarRout(rute);
					catego.setText("");
					plcs.setText("");
					cpos.setText("");
					modl.setText("");
					drvr.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(38, 327, 117, 29);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rute = catego.getText()+" "+plcs.getText()+" "+cpos.getText()+" Modelo "+modl.getText()+" Conductor"+drvr.getText();
				try {
					controla.editRout(rute, oldPlc);
					catego.setText("");
					plcs.setText("");
					cpos.setText("");
					modl.setText("");
					drvr.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(167, 327, 117, 29);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Eliminiar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controla.deleteRout(plcs.getText());
					catego.setText("");
					plcs.setText("");
					cpos.setText("");
					modl.setText("");
					drvr.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(296, 327, 117, 29);
		add(btnDelete);
		
		JLabel lblNewLabel_1 = new JLabel("Placas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(110, 265, 79, 16);
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
		
		JLabel label = new JLabel("Categoria ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(16, 265, 79, 16);
		add(label);
		
		catego = new JTextField();
		catego.setHorizontalAlignment(SwingConstants.CENTER);
		catego.setColumns(10);
		catego.setBounds(16, 289, 79, 26);
		add(catego);
		
		cpos = new JTextField();
		cpos.setHorizontalAlignment(SwingConstants.CENTER);
		cpos.setColumns(10);
		cpos.setBounds(201, 289, 42, 26);
		add(cpos);
		
		JLabel lblCupos = new JLabel("Cupos");
		lblCupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCupos.setBounds(184, 265, 79, 16);
		add(lblCupos);
		
		modl = new JTextField();
		modl.setHorizontalAlignment(SwingConstants.CENTER);
		modl.setColumns(10);
		modl.setBounds(255, 289, 79, 26);
		add(modl);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setBounds(255, 265, 79, 16);
		add(lblModelo);
		
		drvr = new JTextField();
		drvr.setHorizontalAlignment(SwingConstants.CENTER);
		drvr.setColumns(10);
		drvr.setBounds(346, 289, 79, 26);
		add(drvr);
		
		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConductor.setBounds(346, 265, 79, 16);
		add(lblConductor);
		

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
