package object.modules;

public class Radio {
    private Signal signal=new Signal();
    private int frequencyRadio;
    private int longitudeRadio;
    private Computer computer;
    Radio(Computer computerAdd){
        computer=computerAdd;
    }
    public void turnOn(){
        signal.newSignal();
        frequencyRadio=(int) (Math.random() * 83);
        longitudeRadio=(int) (Math.random() * 83);
    }
    public void checkTrue(){
        if(signal.checkSignal(frequencyRadio,longitudeRadio)){
            computer.getSignal();
        }
    }
    public void turnOff(){

    }
    public void checkFrequency(){
        if(signal.getFrequencySignal()==frequencyRadio){

        }
    }
    public void checkLongitude(){
        if(signal.getLongitudeSignal()==longitudeRadio){

        }
    }
}
