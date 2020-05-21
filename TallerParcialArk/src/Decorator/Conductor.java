
package Decorator;

public class Conductor extends TransDecorator {
    private String Caracteristicas="";
    
    public Conductor(String Caracteristica, Transporte nuevoTrans) {
        super(nuevoTrans);
        this.Caracteristicas=Caracteristica;
    }
    
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas()+ this.Caracteristicas;
        
    }

    
    
}
