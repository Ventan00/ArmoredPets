package me.ventan.ArmoredPets.Math;

public class MyMath {
    double[] cos = new double[361];
    double[] sin = new double[361];
    private static final MyMath instance = new MyMath();

    private MyMath() {
        for (int i = 0; i <= 360; i++) {
            cos[i] = Math.cos(Math.toRadians(i-180));
            sin[i] = Math.sin(Math.toRadians(i-180));
        }
    }

    private double getSine(int angle) {
        int angleCircle = (angle+180)% 360;
        return sin[angleCircle];
    }

    private double getCos(int angle) {
        int angleCircle = (angle+180)% 360;
        return cos[angleCircle];
    }
    public static MyMath getInstance() {
        return instance;
    }

    public float[] calculateComplexParameters(float yaw) {
        float[] result = new float[2];
        yaw+=180;
        result[0]= 1.5f*(float) instance.getCos((int) yaw);
        result[1]= 1.5f*(float) instance.getSine((int) yaw);
        return result;
    }
}
