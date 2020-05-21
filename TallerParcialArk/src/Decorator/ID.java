
package Decorator;

public class ID extends TransDecorator {
    private String CaracteristicasId="";
    
    public ID(String Id,Transporte nuevoTrans) {
        super(nuevoTrans);
        this.CaracteristicasId=Id;
        
    }
    
    @Override
    public String getCaracteristicas(){
        return tempTrans.getCaracteristicas()+ this.CaracteristicasId;
       
                
    }

}
