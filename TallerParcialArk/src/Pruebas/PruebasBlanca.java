package PruebasTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Decorator.Composite;
import Modelo.Encrypter;
import Modelo.Facade;
import Modelo.Proxy;
import Modelo.Usuario;

public class PruebasBlanca {


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
	
}
