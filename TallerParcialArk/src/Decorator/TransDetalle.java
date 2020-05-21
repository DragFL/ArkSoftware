
package Decorator;

public class TransDetalle implements Transporte{
    
	private String Caracteristicas="";

    @Override
    public String getCaracteristicas() {
        return this.Caracteristicas;
    }
    
}
