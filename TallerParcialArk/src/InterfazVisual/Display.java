package InterfazVisual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controlador.Controler;
import Modelo.Usuario;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Display extends JFrame{
    
	private static Display instanciaUnica=null;
	private JScrollPane Contenido;
	private JPanel contentPane;
    private Controler controla = Controler.getInstance();
	private static LoginPanel panelLog;
	private static PanelUsPred panel1;
	private static PanelUsNorm panel2;
    
    
    public static Display getInstance() {
		   if(instanciaUnica==null)
	            instanciaUnica=new Display();
	        return instanciaUnica;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	    Contenido = new JScrollPane();
		contentPane.add(Contenido, BorderLayout.CENTER);
		
		
		
	}
	
	public void ponerLogin(LoginPanel login) {		
		Contenido.setViewportView(login);
	}
	public void ponerPred(PanelUsPred pred) {
		Contenido.setViewportView(pred);
	}
	public void ponerNorm(PanelUsNorm norm) {
		Contenido.setViewportView(norm);;
	}
	
	 public void instanciarVista() {
		    Display.setLoginPan(LoginPanel.getInstance());
			Display.setPanelUsNor(PanelUsNorm.getInstance());
			Display.setPanelUsPre(PanelUsPred.getInstance());
			
			
			Contenido.setViewportView(panelLog);
		 }	
	 
	  public void login(Usuario user) throws Exception {	
			 
			 String msj = controla.logi().accederSistema(user);
			 
			 System.out.println("Acceso concedido su llave es: "+ msj);
			 if(user.getMail().equals("elma@malon")) {
			  Contenido.setViewportView(panel1);
			 }
			 else {
				 Contenido.setViewportView(panel2);
			 }
		  }
		  
		  public void desconectar() {
			  Contenido.setViewportView(panelLog);
			  System.out.println("Cerrando sesion");
		  }

	  public static void setLoginPan(LoginPanel intance) {
		  panelLog = intance;
	  }
	  public static void setPanelUsPre(PanelUsPred intance) {
		  panel1 = intance;
	  }
	  public static void setPanelUsNor(PanelUsNorm intance) {
		  panel2 = intance;
	  }
}
