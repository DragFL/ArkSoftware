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

@SuppressWarnings("serial")
public class Consultp extends JPanel {
	private static Consultp instanciaUnica = null;
	private static Display displei = Display.getInstance();
	private Controler controla = Controler.getInstance();
	private JTextField plcs;
	private JTable tablaPred;
	ArrayList<String> list ;
	private String oldPlc;
	private JTextField cpos;
	private JTextField drvr;
	public String cat;
	public String mod;
	
	public static Consultp getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new Consultp();
	        return instanciaUnica;
	}
	
	public Consultp() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Reservaci\u00F3n de cupos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PIde cupo para tu ruta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 23, 242, 22);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(16, 57, 419, 224);
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
			 cat= tablaPred.getValueAt(i, 0).toString();
			 plcs.setText(tablaPred.getValueAt(i, 1).toString());
			 cpos.setText(tablaPred.getValueAt(i, 2).toString());
			 mod =tablaPred.getValueAt(i, 3).toString();
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
		btnLoad.setBounds(90, 327, 117, 29);
		add(btnLoad);
		
		plcs = new JTextField();
		plcs.setHorizontalAlignment(SwingConstants.CENTER);
		plcs.setBounds(81, 289, 79, 26);
		add(plcs);
		plcs.setColumns(10);
		
		JButton btnEdit = new JButton("Reservar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cupos = convert(cpos.getText());
					String rute = cat+" "+plcs.getText()+" "+cupos+" Modelo "+mod+" Conductor"+drvr.getText();
					controla.editRout(rute, oldPlc);
					plcs.setText("");
					cpos.setText("");
					drvr.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(238, 327, 117, 29);
		add(btnEdit);
		
		JLabel lblNewLabel_1 = new JLabel("Placas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(16, 294, 64, 16);
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
		
		cpos = new JTextField();
		cpos.setHorizontalAlignment(SwingConstants.CENTER);
		cpos.setColumns(10);
		cpos.setBounds(215, 289, 42, 26);
		add(cpos);
		
		JLabel lblCupos = new JLabel("Cupos");
		lblCupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCupos.setBounds(155, 294, 64, 16);
		add(lblCupos);
		
		drvr = new JTextField();
		drvr.setHorizontalAlignment(SwingConstants.CENTER);
		drvr.setColumns(10);
		drvr.setBounds(346, 289, 79, 26);
		add(drvr);
		
		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConductor.setBounds(265, 294, 79, 16);
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
	public String convert(String cup) {
		int i = Integer.parseInt(cup);
		i -=1;
		String conv = String.valueOf(i);
		return conv;
	}
}
