package PruebasTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import Decorator.Composite;
import Modelo.Encrypter;
import Modelo.Facade;
import Modelo.Proxy;
import Modelo.Usuario;

public class PruebasMalNe {
	

	
	@Test
	 public void crearUsuario() {
	        try {

	           Usuario user = new Usuario("admin@sabana.com", "1234");

	            Modelo.Proxy proxy = Proxy.getInstance();

	            proxy.add(user);

	            String prime = proxy.accederSistema(user);

	            Encrypter crypto = Encrypter.getInstance();

	            String key = crypto.encript();

	            Modelo.Facade facade = Facade.getInstance();
	            facade.agregarUs(null, key);
	            assertTrue(true);
	            
	        } catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	
@Test
 public void ConsultarUsuario()  {
	 try {
	 Usuario user = new Usuario("admin@sabana.com", "1234");
  Modelo.Proxy proxy = Proxy.getInstance();
  proxy.add(user);
  String prime = proxy.accederSistema(user);
  Encrypter crypto = Encrypter.getInstance();
  String key = crypto.encript();
  Modelo.Facade facade= Facade.getInstance();
  ArrayList<Usuario>users = null;
  users=facade.transMap(null);
  assertTrue(true);	 
	 }
	 catch(Exception e) {
		 fail(e.getMessage());
	 }
}	

@Test
public void editarUs() {
	 try {
	 Usuario user = new Usuario("admin@sabana.com", "1234");
  Modelo.Proxy proxy = Proxy.getInstance();
  proxy.add(user);
  Usuario user2=new Usuario("lolazo@sabana.com","1234");
  String prime = proxy.accederSistema(user);
  Encrypter crypto = Encrypter.getInstance();
  String key = crypto.encript();
  Modelo.Facade facade= Facade.getInstance();
  facade.editarUs(null,"1", key);
  assertTrue(true);	
	 }
	 catch(Exception e){
		 fail(e.getMessage()); 
	 }
}


	


@Test
public void EliminarUs() {
	 try {
		 Usuario user = new Usuario("admin@sabana.com", "1234");
	     Modelo.Proxy proxy = Proxy.getInstance();
	     proxy.add(user);
	     String prime = proxy.accederSistema(user);
	     Encrypter crypto = Encrypter.getInstance();
	     String key = crypto.encript();
	     Modelo.Facade facade= Facade.getInstance();
	     facade.borrarUs(null, key);
	     assertTrue(true);	
	 }
	 catch(Exception e) {
		 fail(e.getMessage()); 	 
	 }
}
}
