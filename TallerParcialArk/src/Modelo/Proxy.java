package Modelo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;


public class Proxy {
    private static Proxy instanciaUnica= null;
    private HashMap<String, Usuario> usuarios;
    Encrypter cripto;
    Proxy prox; 
    
    public Proxy () {
        this.usuarios=new HashMap<>();;

    }
    public static Proxy getInstance(){
        if(instanciaUnica==null)
            instanciaUnica=new Proxy();
        return instanciaUnica;
    }


    
    public String accederSistema(Usuario user) throws Exception{
        
    	String msg;
    	BigInteger primo;
        
    	if(!usuarios.containsKey(user.getMail())) throw new Exception ("Correo incorrecto");
       
    	Usuario psw = usuarios.get(user.getMail());
        
        if(!psw.getPsw().equals(user.getPsw())) throw new Exception ("contraseña incorrecta");
          
           primo = BigInteger.probablePrime(30, new SecureRandom());
           cripto = Encrypter.getInstance();
           cripto.registrarSesion(primo);
           msg=cripto.encript();
           
        return msg;
    }
    
    
    public void add(Usuario usuario){
        if(usuarios.containsKey(usuario.getMail())) return;
        this.usuarios.put(usuario.getMail(), usuario);
    }
    public Usuario buscarUser(String Id) {
        return this.usuarios.get(Id);
    }
    public void deleteUser(String Id) {
        this.usuarios.remove(Id);
    }
    public void updateUser(String OldId, Usuario newUser){
        this.usuarios.remove(OldId);
        this.usuarios.put(newUser.getMail(), newUser);
    }
    
    public HashMap <String, Usuario> todosUs() {
     return this.usuarios;
    }
    
}
