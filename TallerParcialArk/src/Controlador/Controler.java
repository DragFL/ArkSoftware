package Controlador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import Modelo.Encrypter;
import Modelo.Facade;
import Modelo.Proxy;
import Modelo.Usuario;

public class Controler {
	private static Controler instanciaUnica=null; 
    
    
	 public String dinamicCrip() {
		 try {
			 Encrypter objEnc = Encrypter.getInstance();
			 Method encrip=objEnc.getClass().getDeclaredMethod("encript", null);
			 String encriptao = (String) encrip.invoke(objEnc, null);
			  return encriptao;
		 }catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			 return null;
		 }
	 }
	 
	 public String dinamicDataCrip(String data) {
		 try {
			 Encrypter objEnc = Encrypter.getInstance();
			 Method encrip=objEnc.getClass().getDeclaredMethod("encriptData", String.class);
			 String encriptao = (String) encrip.invoke(objEnc, data);
			  return encriptao;
		 }catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			 return null;
		 }
	 }	

	 
    public static Controler getInstance() {
	  if(instanciaUnica==null)
	    instanciaUnica=new Controler();
	      return instanciaUnica;
	}
	
	//login
	public Proxy logi() {
		Proxy prox = Proxy.getInstance();
		return prox;
	}
  
  //CRUD Account
  public ArrayList<Usuario> obtnUs(){
	Facade fac = Facade.getInstance();
	ArrayList<Usuario> trans =fac.transMap(dinamicCrip()) ;
	return trans;	
  }
  
  public void agregarUser(Usuario use) {
	 Facade fac = Facade.getInstance();
	 fac.agregarUs(use, dinamicCrip()); 
  }
  
  public void deleteUser(Usuario use) {
	 Facade fac = Facade.getInstance();
	 fac.borrarUs(use, dinamicCrip());
  }
  
  public void editeUser(Usuario use, String olId) {
	 Facade fac = Facade.getInstance();
	 fac.editarUs(use, olId, dinamicCrip());
  }
  

  //CRUD Routes
  
  public ArrayList<String>  obtRout() {
		Facade fac = Facade.getInstance();
		ArrayList<String> rutas =fac.buscRutas(dinamicCrip()) ;
	  return rutas;
  }
  
  public void agregarRout(String paq) {
	  Facade fac = Facade.getInstance();
	  String paque = dinamicDataCrip(paq);
	  fac.crearRuta(paque, dinamicCrip());
  }
  
  public void deleteRout(String plc) {
	  Facade fac = Facade.getInstance();
	  String placa = dinamicDataCrip(plc);
	  fac.elimRuta(placa, dinamicCrip());
  }
  
  public void editRout(String paq, String oldPlc) {
	  Facade fac = Facade.getInstance();
	  String paque = dinamicDataCrip(paq);
	  String oldePla= dinamicDataCrip(oldPlc);
	  fac.editarRuta(oldePla, paque, dinamicCrip());
  }
 
  
 //Add/delete a route from local
  public void rutaLocalpls(String plc,String muni) {
	  Facade fac = Facade.getInstance();
	  String placa = dinamicDataCrip(plc);
	  String munic = dinamicDataCrip(muni);
	  fac.addRutaLoc(placa, munic, dinamicCrip());
  }
  
  public void rutaLocalmns(String plc,String muni) {
	  Facade fac = Facade.getInstance();
	  String placa = dinamicDataCrip(plc);
	  String munic = dinamicDataCrip(muni);
	  fac.deleteRutaLoc(placa, munic, dinamicCrip());
  }
  
  
}
