package object.modules;

public class Computer {
    private boolean signal=false;
    private int stage=1;
    private String message;


    void getSignal(){
        signal=true;
    }
    void turnOn(){
        if(signal){
            checkStatusSignal();
        }
    }
    void turnOff(){
        if(signal){
            checkStatusSignal();
        }
    }
    void checkStatusSignal(){
        switch (stage){
            case 1:
                message="вайбкодер";
                break;
            case 2:
                message="Рома гений дизайна";
                break;
            case 3:
                message="Выход из Омска нет!";
        }
    }

}
