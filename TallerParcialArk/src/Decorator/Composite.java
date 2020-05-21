package Decorator;
import java.util.ArrayList;

public class Composite implements Transporte {
    String name;
    ArrayList<Transporte> rutas  = new ArrayList<Transporte>();

    public Composite(String name) {
        this.name = name;
    }

    public void addTransporte(Transporte ruta){
        this.rutas.add(ruta);
    }
    
    public void deleteTrans(Transporte o) {
    	int i = rutas.indexOf(o);
    	this.rutas.remove(i);
    }

	@Override
	public String getCaracteristicas() {
		return null;
	}
	
	public void showUsers() {
        System.out.println(name);
        for(Transporte t : rutas)
        {
           System.out.println(t.getCaracteristicas());
        }
    }
 
}
