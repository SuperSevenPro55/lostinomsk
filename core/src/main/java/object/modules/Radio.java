package object.modules;

import ru.pe.lostinomsk.utils.Signal;

public class Radio {
    Signal signal=new Signal();
    private int frequencyRadio;
    private int longitudeRadio;
    public void turnOn(){
        signal.newSignal();
        frequencyRadio=(int) (Math.random() * 101);
        longitudeRadio=(int) (Math.random() * 101);
    }
    public void checkTrue(){
        if(signal.checkSignal(frequencyRadio,longitudeRadio)){

        }
    }
    public void turnOff(){

    }
    public void checkFrequency(){
        if(signal.getFrequencySignal()==frequencyRadio){

        }

    }
    public void checkLongitude(){
        if(signal.getFrequencySignal()==longitudeRadio){

        }
    }
}
