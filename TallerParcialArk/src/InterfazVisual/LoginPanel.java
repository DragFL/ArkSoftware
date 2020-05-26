package InterfazVisual;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Modelo.Usuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	private static LoginPanel instanciaUnica =null;
	private static Display displei = Display.getInstance();
	private JButton btnLogin;
	private JTextField mailUs;
	private JTextField pswUs;

	
	public static LoginPanel getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new LoginPanel();
	        return instanciaUnica;
	}
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setBorder(new TitledBorder(null, "Arquitectura de sfotware", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel guelcom = new JLabel("Bienvenido");
		guelcom.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		guelcom.setHorizontalAlignment(SwingConstants.CENTER);
		guelcom.setBounds(147, 43, 164, 40);
		add(guelcom);
		
		mailUs = new JTextField();
		mailUs.setHorizontalAlignment(SwingConstants.CENTER);
		mailUs.setForeground(UIManager.getColor("Button.disabledText"));
		mailUs.setText("Correo");
		mailUs.addMouseListener(new MouseAdapter(){	
			  public void mouseClicked(MouseEvent e) {
				 mailUs.setText("");
			  }});
		mailUs.setColumns(10);
		mailUs.setBounds(120, 123, 218, 40);
		add(mailUs);
		
		
		pswUs = new JTextField();
		pswUs.setText("Contraseña");
		pswUs.setHorizontalAlignment(SwingConstants.CENTER);
		pswUs.setForeground(Color.GRAY);
		pswUs.addMouseListener(new MouseAdapter(){	
			  public void mouseClicked(MouseEvent e) {
				 pswUs.setText("");
			  }});
		pswUs.setColumns(10);
		pswUs.setBounds(120, 199, 218, 40);
		add(pswUs);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario(mailUs.getText(),pswUs.getText());
				try {
					displei.login(user);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(166, 281, 133, 40);
		add(btnLogin);
		

	}

	
}
