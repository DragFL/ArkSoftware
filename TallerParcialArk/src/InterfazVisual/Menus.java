package InterfazVisual;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.applet.Applet;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Menus extends Applet {

	private static Menus instanciaUnica = null;
	private static Display displei = Display.getInstance();
	
	public static Menus getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new Menus();
	        return instanciaUnica;
	}
	
	public Menus() {
		//setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Menu del Administrador", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JLabel lblNewLabel = new JLabel("Bienvenido de nuevo Jefe");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setBounds(114, 38, 246, 32);
		add(lblNewLabel);
		btnUsuarios.setBounds(137, 93, 179, 45);
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
		btnRutas.setBounds(137, 158, 179, 45);
		add(btnRutas);
		
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.asigPane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAsignar.setBounds(137, 220, 179, 45);
		add(btnAsignar);
		
		JButton btnConsulta = new JButton("Consultas");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displei.srchPane();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConsulta.setBounds(137, 288, 179, 45);
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
