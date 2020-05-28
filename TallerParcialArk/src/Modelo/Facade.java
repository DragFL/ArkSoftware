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
	
		 Transporte ruta1 = new Conductor(" Conductor Roberto Carlos",new Caracteristicas(" Modelo 2017",new Cupos(4,new Placa("JKA877 ",new ID("Wheels ",new TransDetalle())))));
		 Transporte ruta2 = new Conductor(" Conductor Enrique Segoviano",new Caracteristicas(" Modelo 2000",new Cupos(6,new Placa("WFI923 ",new ID("Wheels ",new TransDetalle())))));
		 Transporte ruta3 = new Conductor(" Conductor Romeo Santo",new Caracteristicas(" Modelo 1995",new Cupos(9,new Placa("HGB322 ",new ID("Bus ",new TransDetalle())))));
		 Transporte ruta4 = new Conductor(" Conductor Alan Brito",new Caracteristicas(" Modelo 2012",new Cupos(2,new Placa("LSD420 ",new ID("Wheels ",new TransDetalle())))));
		 Transporte ruta5 = new Conductor(" Conductor Armando Casas",new Caracteristicas(" Modelo 2020",new Cupos(8,new Placa("ANF670 ",new ID("Bus ",new TransDetalle())))));
		 Transporte ruta6 = new Conductor(" Conductor Ismael Calvo",new Caracteristicas(" Modelo 2019",new Cupos(4,new Placa("FKM234 ",new ID("Wheels ",new TransDetalle())))));
		 Transporte ruta7 = new Conductor(" Conductor Hurtado Honesto",new Caracteristicas(" Modelo 2013",new Cupos(7,new Placa("FGG034 ",new ID("Bus ",new TransDetalle())))));
		 Transporte ruta8 = new Conductor(" Conductor Richard Nixon",new Caracteristicas(" Modelo 2009",new Cupos(9,new Placa("PÑE024 ",new ID("Bus ",new TransDetalle())))));
		 Transporte ruta9 = new Conductor(" Conductor Casimiro Buenavista",new Caracteristicas(" Modelo 2003",new Cupos(1,new Placa("KIL421 ",new ID("Wheels ",new TransDetalle())))));
		 Transporte ruta0 = new Conductor(" Conductor Elna Vajas",new Caracteristicas(" Modelo 2015",new Cupos(5,new Placa("QTN145 ",new ID("Bus ",new TransDetalle())))));
	                              
 		this.trans.put("JKA877", ruta1);
 		this.trans.put("WFI923", ruta2);
 		this.trans.put("HGB322", ruta3);
 		this.trans.put("LSD420", ruta4);
 		this.trans.put("ANF670", ruta5);
 		this.trans.put("FKM234", ruta6);
 		this.trans.put("FGG034", ruta7);
 		this.trans.put("PÑE024", ruta8);
 		this.trans.put("KIL421", ruta9);
 		this.trans.put("QTN145", ruta0);
 		
	 }  
	 

	    
}
