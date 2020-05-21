package InterfazVisual;

import javax.swing.JPanel;

import Controlador.Controler;
import Modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelUsPred extends JPanel {
	private static PanelUsPred instanciaUnica = null;
	private static Display displei = Display.getInstance();
	private Controler controla = Controler.getInstance();
	private JTextField mailUs;
	private JTextField pswUs;
	private JTable tablaPred;
	ArrayList<Usuario> list ;
	private String oldID;
	
	public static PanelUsPred getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new PanelUsPred();
	        return instanciaUnica;
	}
	public PanelUsPred() {
		setBorder(new TitledBorder(null, "Panel de administrador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido de nuevo jefe");
		lblNewLabel.setBounds(102, 23, 161, 22);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(149, 77, 215, 306);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tablaPred = new JTable();
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				new Object [] [] {
					{null,null},
					{null,null},
					{null,null},
				},
				new String [] {
					"Correo","Contraseña"			
				}
				));
		tablaPred.setRowHeight(30);
		tablaPred.addMouseListener(new MouseAdapter(){	
		  public void mouseClicked(MouseEvent e) {
			int i = tablaPred.getSelectedRow();
			 mailUs.setText(tablaPred.getValueAt(i, 0).toString());
			 pswUs.setText(tablaPred.getValueAt(i, 1).toString());
			 oldID = tablaPred.getValueAt(i,0).toString();
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
		btnLoad.setBounds(6, 77, 117, 29);
		add(btnLoad);
		
		mailUs = new JTextField();
		mailUs.setBounds(6, 146, 130, 26);
		add(mailUs);
		mailUs.setColumns(10);
		
		pswUs = new JTextField();
		pswUs.setBounds(6, 205, 130, 26);
		add(pswUs);
		pswUs.setColumns(10);
		
		JButton btnAdd = new JButton("Agregar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario(mailUs.getText(),pswUs.getText());
				try {
					controla.agregarUser(user);
					mailUs.setText("");
					pswUs.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(6, 243, 117, 29);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario(mailUs.getText(),pswUs.getText());
				try {
					controla.editeUser(user, oldID);
					mailUs.setText("");
					pswUs.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(6, 284, 117, 29);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Eliminiar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario(mailUs.getText(),pswUs.getText());
				try {
					controla.deleteUser(user);;
					mailUs.setText("");
					pswUs.setText("");
					updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(6, 325, 117, 29);
		add(btnDelete);
		
		JLabel lblNewLabel_1 = new JLabel("Correo :");
		lblNewLabel_1.setBounds(6, 118, 61, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña :");
		lblNewLabel_2.setBounds(6, 177, 79, 16);
		add(lblNewLabel_2);
		
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
		btnCutoff.setBounds(6, 365, 117, 29);
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
		btnRutas.setBounds(247, 46, 117, 29);
		add(btnRutas);
		

	}
	
	public void updateTable() {
		setLista();
		
		String matriz [][] = new String[list.size()][2];
		for(int i=0; i<list.size();i++) {
			matriz [i][0] = list.get(i).getMail();
			matriz [i][1] = list.get(i).getPsw();	
		}
		tablaPred.setModel(new javax.swing.table.DefaultTableModel(
				matriz,
				new String [] {
				"Correo","Contraseña"			
				}
				));
	}
	public void setLista() {
		this.list = controla.obtnUs();
	}
}
