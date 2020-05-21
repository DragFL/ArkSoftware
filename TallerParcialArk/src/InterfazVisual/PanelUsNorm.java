package InterfazVisual;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Controlador.Controler;
import Modelo.Usuario;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelUsNorm extends JPanel {

    private static PanelUsNorm instanciaUnica =null;
    private static Display displei = Display.getInstance();
	private Controler controla = Controler.getInstance();
	private JButton btnBuscarNorm ;
	private JTable tablaNorm;
	ArrayList <Usuario> lista;

	public static PanelUsNorm getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new PanelUsNorm();
	        return instanciaUnica;
	}
	
	
	
	public PanelUsNorm() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Usuarios", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		btnBuscarNorm.setBounds(121, 346, 126, 35);
		add(btnBuscarNorm);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("Button.disabledText"), 2, true));
		panel.setBounds(6, 19, 358, 313);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tablaNorm = new JTable();
		tablaNorm.setModel(new javax.swing.table.DefaultTableModel(
				new Object [] [] {
					{null,null},
					{null,null},
					{null,null},
				},
				new String [] {
					"Correo","Contraseña"			
				}
				));
		tablaNorm.setRowHeight(30);
		scrollPane.setViewportView(tablaNorm);
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
		
		JButton btnRutas = new JButton("Rutas");
		btnRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRutas.setBounds(258, 349, 106, 29);
		add(btnRutas);
		
	}
	
	
	public void updateTable() {
		setLista();
		
		String matriz [][] = new String[lista.size()][2];
		for(int i=0; i<lista.size();i++) {
			matriz [i][0] = lista.get(i).getMail();
			matriz [i][1] = lista.get(i).getPsw();	
		}
		tablaNorm.setModel(new javax.swing.table.DefaultTableModel(
				matriz,
				new String [] {
				"Correo","Contraseña"			
				}
				));
	}
	
	public void setLista() {
		this.lista = controla.obtnUs();
	}
}
