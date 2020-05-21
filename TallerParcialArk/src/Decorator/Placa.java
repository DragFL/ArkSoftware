
package Decorator;

public class Placa extends TransDecorator {
    private String Caracteristicas="";
    
    public Placa(String Caracteristicas,Transporte nuevoTrans) {
        super(nuevoTrans);
        this.Caracteristicas=Caracteristicas;
    }
    
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas()+this.Caracteristicas;
        
    }

}