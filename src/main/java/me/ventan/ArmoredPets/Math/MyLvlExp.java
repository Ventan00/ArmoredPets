package me.ventan.ArmoredPets.Math;

public class MyLvlExp {
    long[] lvlPdBariers = new long[105];
    public static MyLvlExp instance = new MyLvlExp();
    private MyLvlExp(){
        lvlPdBariers[0]=1000;
        for(int i=1;i<105;i++){
            lvlPdBariers[i]= (long) (2000*Math.pow(i,7/3)+lvlPdBariers[i-1]);
        }
    }

    public long getPd(int lvl) {
        return lvlPdBariers[lvl-1];
    }
}
