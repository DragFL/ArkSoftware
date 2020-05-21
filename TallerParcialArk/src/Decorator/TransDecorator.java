
package Decorator;

abstract class TransDecorator implements Transporte {

    protected Transporte tempTrans;

    public TransDecorator(Transporte nuevoTrans){ 
        
        tempTrans = nuevoTrans;
    }
    
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas();
    }
   
}
