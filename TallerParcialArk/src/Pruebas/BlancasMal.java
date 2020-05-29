package Pruebas;



import static org.junit.Assert.*;

import org.junit.Test;

import Modelo.Encrypter;
import Modelo.Facade;
import Modelo.Proxy;
import Modelo.Usuario;

public class BlancasMal {

	@Test
	 public void ingresaralSistema() {
       try {
           Usuario user = new Usuario("oilo@gmail.com", "1234");
           Modelo.Proxy proxy = Proxy.getInstance();
           proxy.add(user);
           String prime = proxy.accederSistema(null);
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
	            facade.agregarUs(null, key);
	            assertTrue(true);
	        } catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	
}
