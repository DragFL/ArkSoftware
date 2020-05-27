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
	    private HashMap<String,Composite> munici;
	   
	    public static Facade getInstance(){
	        if(instanciaUnica==null)
	            instanciaUnica=new Facade();
	        return instanciaUnica;
	    }
	    
	    public Facade () {
	    	this.trans = new HashMap<>();
	    	this.munici = new HashMap<>();		
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
	    
	    public void addRutaLoc(String plc, String muni,String key) {
	    	String clave = dinamicDecrip(key);
	    	String placa = dinamicDecrip(plc);
	    	String munic = dinamicDecrip(muni);
	    	if(clave.equals("ejecutar")) {
	    	 Composite municipio =munici.get(munic);
	    	 municipio.addTransporte(trans.get(placa), placa);
	    	 munici.remove(munic);
	    	 munici.put(munic,municipio);
	    	 System.out.println("se agrego la ruta a la localidad");
	    	}else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	       
	    public void deleteRutaLoc(String plc, String muni, String key) {
	    	String clave = dinamicDecrip(key);
	    	String placa = dinamicDecrip(plc);
	    	String munic = dinamicDecrip(muni);
	    	if(clave.equals("ejecutar")) {
	    		Composite municipio =munici.get(munic);
	 	    	municipio.deleteTrans(trans.get(placa), placa);
	 	   	    munici.remove(munic);
	    	    munici.put(munic,municipio);
	 	    	}else {
	 	    		System.out.println("llave incorrecta");
	 	    	}
	    }
	    
	    public ArrayList<Composite> buscarLoc(String key) {
	    	ArrayList<Composite> locs = new ArrayList<Composite>();
	    	String clave = dinamicDecrip(key);
	    	if(clave.equals("ejecutar")) {
	    		for (Composite i : this.munici.values()) { locs.add(i);}
	    
	    	return locs;	
	    	} else {
 	    		System.out.println("llave incorrecta");
 	    		return locs;
 	    	}
	    }
	    
	    public Composite espcLoc(String muni, String key) {
	      String clave = dinamicDecrip(key);	
	      String munic = dinamicDecrip(muni);
	      Composite municipio = null;
	      if(clave.equals("ejecutar")) {
	    	    municipio =munici.get(munic);
	 	   	    return municipio;
	 	    	}else {
	 	    		System.out.println("llave incorrecta");
	 	    	return municipio;
	 	    	}
	    	
	    }

	    public void addMuni(String muni) {
	    	Composite local = new Composite(muni);
	    	this.munici.put(local.getName(),local);	
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
	    		
	    		cat ="Bus";
	    		plc = paque.substring(4, 10);
	    		cup = Integer.parseInt(paque.substring(11, 12));
	    		mod = "Modelo "+paque.substring(20, 24);
	    		cond = "Conductor "+paque.substring(35); 
	    		Transporte ruta = new Conductor(" "+cond,new Caracteristicas(" "+mod,new Cupos(cup,new Placa(plc+" ",new ID(cat+" ",new TransDetalle())))));
	    		 
	    		if(trans.containsKey(plc)) return;
	    	        this.trans.put(plc, ruta);
	    		
	    	 }else {
	    		
	    		cat ="Wheels";
	    		plc = paque.substring(7, 13);
	    		cup = Integer.parseInt(paque.substring(14, 15));
	    		mod = "Modelo "+paque.substring(23, 27);
	    		cond = "Conductor "+paque.substring(37); 
	    		Transporte ruta = new Conductor(" "+cond,new Caracteristicas(" "+mod,new Cupos(cup,new Placa(plc+" ",new ID(cat+" ",new TransDetalle())))));
	    		 
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
		    		
	    			cat ="Bus";
		    		plc = paque.substring(4, 10);
		    		cup = Integer.parseInt(paque.substring(11, 12));
		    		mod = "Modelo "+paque.substring(20, 24);
		    		cond = "Conductor "+paque.substring(35); 
		    		Transporte ruta = new Conductor(" "+cond,new Caracteristicas(" "+mod,new Cupos(cup,new Placa(plc+" ",new ID(cat+" ",new TransDetalle())))));
		            
		    		this.trans.put(plc, ruta);
		    		
		    	}else {
		    		cat ="Wheels";
		    		plc = paque.substring(7, 13);
		    		cup = Integer.parseInt(paque.substring(14, 15));
		    		mod = "Modelo "+paque.substring(23, 27);
		    		cond = "Conductor "+paque.substring(37); 
		    		Transporte ruta = new Conductor(" "+cond,new Caracteristicas(" "+mod,new Cupos(cup,new Placa(plc+" ",new ID(cat+" ",new TransDetalle())))));
		    		 
		    		
		    		this.trans.put(plc, ruta);	
		    	} 	
	    	}else {
	    		System.out.println("llave incorrecta");
	    	}
	    }
	    
	 public void rutaprueba() {
		 String cat,plc,mod,cond;
	    int cup;
	    cat ="Wheels";
		plc = "JKA877";
		cup = 4;
		mod = "Modelo "+"2017";
		cond = "Conductor "+"El Jajas";
		 Transporte ruta = new Conductor(" "+cond,new Caracteristicas(" "+mod,new Cupos(cup,new Placa(plc+" ",new ID(cat+" ",new TransDetalle())))));
         
 		this.trans.put(plc, ruta);
 		
	 }  
	 
	 public void pruebarut() {
		/*
		 String cat,plc,mod,cond;
		 int cup;
		 */
		 
		 Transporte i = this.trans.get("JKA877");
		 String paque =i.getCaracteristicas();
		 System.out.println(paque);
		/*
		    cat ="Wheels";
    		plc = paque.substring(7, 13);
    		cup = Integer.parseInt(paque.substring(14, 15));
    		mod = "Modelo "+paque.substring(23, 27);
    		cond = "Conductor "+paque.substring(37); 
		 
		 
		 System.out.println(cat);
		 System.out.println(plc);
		 System.out.println(cup);
		 System.out.println(mod);
		 System.out.println(cond);
		 */
	 }
	    
}
