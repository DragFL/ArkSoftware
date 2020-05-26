package Decorator;
import java.util.ArrayList;

public class Composite implements Transporte {
    String name;
    ArrayList<Transporte> rutas  = new ArrayList<Transporte>();
    ArrayList<String> placas = new ArrayList<String>();
    
    public Composite(String name) {
        this.name = name;
    }

    public void addTransporte(Transporte ruta, String placa){
        this.placas.add(placa);
    	this.rutas.add(ruta);
    }
    
    public void deleteTrans(Transporte o, String placa) {
    	int it = rutas.indexOf(o);
    	int ip =placas.indexOf(placa);
    	this.rutas.remove(it);
    	this.placas.remove(ip);
    }

	@Override
	public String getCaracteristicas() {
		return null;
	}
	
	public ArrayList<String> rutasMun(){
		
		ArrayList<String> ruts = new ArrayList<String>();
		for(Transporte i: this.rutas) {
    		String ruta =i.getCaracteristicas();
    		ruts.add(ruta);
    		}
    	return ruts;
	}

	public void showUsers() {
        System.out.println(name);
        for(Transporte t : rutas)
        {
           System.out.println(t.getCaracteristicas());
        }
    }
 
}
