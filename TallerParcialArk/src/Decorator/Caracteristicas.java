
package Decorator;

public class Caracteristicas extends TransDecorator {
    private String Caracteristicas="";
    
    public Caracteristicas(String Caracteristicas,Transporte nuevoTrans) {
        super(nuevoTrans);
        this.Caracteristicas=Caracteristicas;
    }
    
    public String getCarac() {
    	return this.Caracteristicas;
    }
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas()+ this.Caracteristicas;
        
    }
}