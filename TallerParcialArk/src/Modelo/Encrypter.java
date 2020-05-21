package Modelo;

import java.math.BigInteger;

public class Encrypter {
	 private static Encrypter instanciaUnica= null;
	 private BigInteger primo;
	 
	 
	 
	 public static Encrypter getInstance(){
	        if(instanciaUnica==null)
	            instanciaUnica=new Encrypter();
	        return instanciaUnica;
	    }
	
    public String encript() {	
    	int cripto =Math.round(primo.intValue()/10000000);
    	char clave[] = "ejecutar".toCharArray();
    	for(int i=0;i<clave.length;i++) {
    		clave[i]= (char)(clave[i]+cripto);
    	}
    	String cripted = String.valueOf(clave);
    	return cripted;
    }
    
    public String encriptData(String data) {	
    	int cripto =Math.round(primo.intValue()/10000000);
    	char clave[] = data.toCharArray();
    	for(int i=0;i<clave.length;i++) {
    		clave[i]= (char)(clave[i]+cripto);
    	}
    	String cripted = String.valueOf(clave);
    	return cripted;
    }
    
    public String decript(String criptado) {
    	int cripto =Math.round(primo.intValue()/10000000);
    	char clave[] = criptado.toCharArray();
    	for(int i=0;i<clave.length;i++) {
    		clave[i]= (char)(clave[i]-cripto);
    	}
    	String decripted = String.valueOf(clave);
    	return decripted;
    }
     
    public void registrarSesion(BigInteger primo){
        System.out.println("Sesión registrada");
        this.primo=primo;

    }
}
