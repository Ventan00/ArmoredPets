package me.ventan.ArmoredPets.Math;

public class MyMath {
    double[] cos = new double[361];
    double[] sin = new double[361];
    private static MyMath table = new MyMath();

    private MyMath() {
        for (int i = 0; i <= 360; i++) {
            cos[i] = Math.cos(Math.toRadians(i-180));
            sin[i] = Math.sin(Math.toRadians(i-180));
        }
    }

    public double getSine(int angle) {
        int angleCircle = (angle+180)% 360;
        return sin[angleCircle];
    }

    public double getCos(int angle) {
        int angleCircle = (angle+180)% 360;
        return cos[angleCircle];
    }
    public static MyMath getTable() {
        return table;
    }
}
