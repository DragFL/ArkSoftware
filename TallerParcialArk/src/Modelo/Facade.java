package Modelo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import Decorator.Caracteristicas;
import Decorator.Composite;
import Decorator.Conductor;
import Decorator.Cupos;
import Decorator.ID;
import Decorator.Placa;
import Decorator.TransDetalle;
import Decorator.Transporte;


public class Facade {
	 private static Facade instanciaUnica= null;
	   
	    private HashMap<String,Transporte> trans;
	    private ArrayList<Composite> munici;
	   
	    public static Facade getInstance(){
	        if(instanciaUnica==null)
	            instanciaUnica=new Facade();
	        return instanciaUnica;
	    }
	    
		 public String dinamicDecrip(String crpted) {
			 try {
				 Encrypter objEnc = Encrypter.getInstance();
				 Method encrip=objEnc.getClass().getDeclaredMethod("decript", String.class);
				 String decripted = (String) encrip.invoke(objEnc, crpted);
				  return decripted;
			 }catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
				 return null;
			 }
		 }
	    
	    
	    
	    // CRUD Account 
	    public void agregarUs(Usuario user, String key) {
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {
	    	Proxy prox = Proxy.getInstance();
	    	prox.add(user);
	    	}
	    	else {
	    		System.out.println("llave incorrecta");	
	    	}
	    }
	    
	    public void borrarUs(Usuario user, String key) {
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {
	    	Proxy prox = Proxy.getInstance();
	    	prox.deleteUser(user.getMail());
	    	}
	    	else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	    public void editarUs(Usuario user, String olId, String key) {
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {
	    	Proxy prox = Proxy.getInstance();
	    	prox.updateUser(olId, user);
	    	}
	    	else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	    public ArrayList<Usuario> transMap(String key){
	    	ArrayList<Usuario>users = new ArrayList<Usuario>();
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {	
	    	   Proxy prox = Proxy.getInstance();
	    	   HashMap<String,Usuario> hash = prox.todosUs();
	    	     for (Usuario i : hash.values()) { users.add(i);}
	    	   return users;
	       }else {
	    	 System.out.println("llave incorrecta");
	    	 return users;
	     }
	    }
	    
	    //Add/Delete route from local
	    
	    @SuppressWarnings("unlikely-arg-type")
		public void addRutaLoc(String plc, String muni,String key) {
	    	String clave = dinamicDecrip(key);
	    	String placa = dinamicDecrip(plc);
	    	String munic = dinamicDecrip(muni);
	    	if(clave.equals("ejecutar")) {
	    	int i= this.munici.indexOf(munic);
	    	Composite municipio =munici.get(i);
	    	municipio.addTransporte(trans.get(placa));
	    	munici.remove(i);
	    	munici.add(municipio);
	    	}else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	    @SuppressWarnings("unlikely-arg-type")
		public void deleteRutaLoc(String plc, String muni, String key) {
	    	String clave = dinamicDecrip(key);
	    	String placa = dinamicDecrip(plc);
	    	String munic = dinamicDecrip(muni);
	    	if(clave.equals("ejecutar")) {
	 	    	int i= this.munici.indexOf(munic);
	 	    	Composite municipio =munici.get(i);
	 	    	municipio.deleteTrans(trans.get(placa));
	 	    	}else {
	 	    		System.out.println("llave incorrecta");
	 	    	}
	    }
	       
	    //CRUD Routes
	    
	    public void elimRuta(String plc, String key){
	    	String clave = dinamicDecrip(key);
	    	String placa = dinamicDecrip(plc);
	    	if(clave.equals("ejecutar")) {
	    	this.trans.remove(placa);
	    	} else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	    
	    public ArrayList<String> buscRutas(String key) {
	    	ArrayList<String> rutas = new ArrayList<String>();
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {
	    	for(Transporte i: this.trans.values()) {
	    		String ruta =i.getCaracteristicas();
	    		rutas.add(ruta);
	    		}
	    	return rutas;
	    	}else {
		    	 System.out.println("llave incorrecta");
		    	 return rutas;
		     }
	    	
	    }
	    
	    
	    public void crearRuta(String paq,String key) {
	    	String cat,plc,mod,cond;
	    	int cup;
	    	String clave = dinamicDecrip(key);
	    	String paque = dinamicDecrip(paq);
	    	if(clave.equals("ejecutar")) {
	    	 if(paque.startsWith("Bus")) {
	    		
	    		cat ="Bus ";
	    		plc = paque.substring(4, 10);
	    		cup = Integer.parseInt(paque.substring(11, 12));
	    		mod = "Modelo "+paque.substring(13, 17);
	    		cond = " Condutor "+paque.substring(18); 
	    		Transporte ruta = new Conductor(cond,new Caracteristicas(mod,new Cupos(cup,new Placa(plc+" ",new ID(cat,new TransDetalle())))));
	    		 
	    		if(trans.containsKey(plc)) return;
	    	        this.trans.put(plc, ruta);
	    		
	    	 }else {
	    		
	    		cat ="Wheels ";
	    		plc = paque.substring(7, 13);
	    		cup = Integer.parseInt(paque.substring(14, 15));
	    		mod = "Modelo "+paque.substring(16, 20);
	    		cond = " Condutor "+paque.substring(21); 
	    		Transporte ruta = new Conductor(cond,new Caracteristicas(mod,new Cupos(cup,new Placa(plc+" ",new ID(cat,new TransDetalle())))));
	    		 
	    		if(trans.containsKey(plc)) return;
	    	     this.trans.put(plc, ruta);	
	    	 } 
	    	}else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	    public void editarRuta(String oldPlc, String paq, String key) {
	    	String cat,plc,mod,cond;
	    	int cup;
	    	String clave = dinamicDecrip(key);
	    	String paque = dinamicDecrip(paq);
	    	String placa = dinamicDecrip(oldPlc);
	    	if(clave.equals("ejecutar")) {	
	    		this.trans.remove(placa);	 
	    		if(paque.startsWith("Bus")) {
		    		
	    		    cat ="Bus ";
		    		plc = paque.substring(4, 10);
		    		cup = Integer.parseInt(paque.substring(11, 12));
		    		mod = "Modelo "+paque.substring(13, 17);
		    		cond = " Condutor "+paque.substring(18); 
		    		Transporte ruta = new Conductor(cond,new Caracteristicas(mod,new Cupos(cup,new Placa(plc+" ",new ID(cat,new TransDetalle())))));
		            
		    		this.trans.put(plc, ruta);
		    		
		    	}else {
		    		
		    		cat ="Wheels ";
		    		plc = paque.substring(7, 13);
		    		cup = Integer.parseInt(paque.substring(14, 15));
		    		mod = "Modelo "+paque.substring(16, 20);
		    		cond = " Condutor "+paque.substring(21); 
		    		Transporte ruta = new Conductor(cond,new Caracteristicas(mod,new Cupos(cup,new Placa(plc+" ",new ID(cat,new TransDetalle())))));
		    	    
		    		this.trans.put(plc, ruta);	
		    	} 	
	    	}else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
}
