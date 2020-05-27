package InterfazVisual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Menup extends JPanel {


	private static Menup instanciaUnica = null;
	private static Display displei = Display.getInstance();
	
	public static Menup getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new Menup();
	        return instanciaUnica;
	}
	
	public Menup() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Menu de selecci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(null);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.userPane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Bienvenido de nuevo ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setBounds(136, 35, 246, 32);
		add(lblNewLabel);
		btnUsuarios.setBounds(136, 222, 179, 45);
		add(btnUsuarios);
		
		JButton btnRutas = new JButton("Rutas");
		btnRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.rutePane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRutas.setBounds(136, 287, 179, 45);
		add(btnRutas);
		
		JButton btnAsignar = new JButton("Cupos");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.asigPane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAsignar.setBounds(136, 165, 179, 45);
		add(btnAsignar);
		
		JButton btnConsulta = new JButton("Localidades");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.srchPane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConsulta.setBounds(136, 108, 179, 45);
		add(btnConsulta);
		
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
		btnCutoff.setBounds(155, 365, 147, 29);
		add(btnCutoff);

	}
}

