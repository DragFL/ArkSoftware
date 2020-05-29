package PruebasTest;
import static org.junit.Assert.*;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.SecretKey;
import org.junit.Test;

import Decorator.Composite;
import Decorator.Transporte;
import Modelo.Encrypter;
import Modelo.Proxy;
import Modelo.Usuario;


import junit.framework.Assert;

public class PruebasNe {
	private Modelo.Facade Facade;
	
	@Test

	 public void ingresaralSistema() {
	        try {
	            Usuario user = new Usuario("oilo@gmail.com", "1234");
	            Modelo.Proxy proxy = Proxy.getInstance();
	            proxy.add(user);
	            String prime = proxy.accederSistema(user);
	            assertTrue(true);
	        }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	
	
	@Test
	 public void crearUsuario() {
	        try {
	           Usuario user = new Usuario("admin@sabana.com", "1234");
	            Encrypter crypto = Encrypter.getInstance();
	            String key = crypto.encript();
	            Modelo.Facade facade = Facade.getInstance();
	            facade.agregarUs(user, key);
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
     ArrayList<Usuario>users = new ArrayList<Usuario>();
     users=facade.transMap(key);
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
     facade.editarUs(user2,"1", key);
     assertTrue(true);	
	 }
	 catch(Exception e){
		 fail(e.getMessage()); 
	 }
 }
 
 @Test
 public void addRuta() {
	 try {
		 Usuario user = new Usuario("admin@sabana.com", "1234");
	     Modelo.Proxy proxy = Proxy.getInstance();
	     proxy.add(user);
	     String prime = proxy.accederSistema(user);
	     Encrypter crypto = Encrypter.getInstance();
	     String key = crypto.encript();
	     Modelo.Facade facade= Facade.getInstance();
	     HashMap<String,Composite> munici;
	     String munic = "chia";
	     Composite compo=new Composite("user");
	     assertTrue(true);	
	 }
	 catch(Exception e) {
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
	     facade.borrarUs(user, key);
	     assertTrue(true);	
	 }
	 catch(Exception e) {
		 fail(e.getMessage()); 	 
	 }
 }
 	
 
 
}


	
	