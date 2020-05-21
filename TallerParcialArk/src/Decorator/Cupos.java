
package Decorator;

public class Cupos extends TransDecorator {
    private int cupos= 0;
    
    public Cupos(int cupos, Transporte nuevoTrans) {
        super(nuevoTrans);
        this.cupos=cupos;
    }
    
    public int getCupos() {
    	return this.cupos;
    }
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas()+this.cupos;
    }


}