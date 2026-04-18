package object.modules;

public class Computer {
    private String text;

    Computer (String text) {
        this.text = text;
    }

    public boolean check (String x) {
        if ((x.toUpperCase()).equals((this.text).toUpperCase())) {
            return true;
        }
        return false;
    }
}