package object.modules;

public class Signal {
    private int frequencySignal;
    private int longitudeSignal;

    public void newSignal(){
        frequencySignal= (int) (Math.random() * 83);
        longitudeSignal=(int) (Math.random() * 83);

    }
    public boolean checkSignal(int frequency,int longitude){
        if(frequencySignal==frequency && longitudeSignal==longitude){
            return true;
        }else{
            return false;
        }
    }
    public int getFrequencySignal(){
        return  frequencySignal;
    }
    public int getLongitudeSignal(){
        return longitudeSignal;
    }
}
