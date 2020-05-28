package Controlador;

import java.applet.Applet;

import InterfazVisual.Display;
import Modelo.Facade;
import Modelo.Proxy;
import Modelo.Usuario;

public class Principal extends Applet{

	public static void main(String[] args) {
		poblar();
		Display ctrl = Display.getInstance();
		ctrl.setVisible(true);
		ctrl.instanciarVista();
	
	}

	
	private static void poblar() {
		Proxy pro = Proxy.getInstance();
	    Facade fac = Facade.getInstance();
	    
	    
		pro.add(new Usuario("elma@malon","ici")); //This is the only administrator
		pro.add(new Usuario("vadim@urod","blyat"));
		pro.add(new Usuario("frederico@mercurio","eeoo"));
		pro.add(new Usuario("bakuritsu@girl","megumin"));
		pro.add(new Usuario("aqua@godess","useless"));
		pro.add(new Usuario("doom@slayer","rip&tear"));
		pro.add(new Usuario("maicol@yakson","heehee"));
		
		fac.addMuni("Mosquera");
		fac.addMuni("Funza");
		fac.addMuni("Cota");
		
		fac.rutaprueba();
		
		
		
		
	}
	

}
