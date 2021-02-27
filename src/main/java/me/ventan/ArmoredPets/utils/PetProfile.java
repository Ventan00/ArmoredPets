package me.ventan.ArmoredPets.utils;
/*
import com.google.gson.JsonObject;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitTask;

public class PetProfile {
    public static int LastID=0;

    //texture parameters
    private final int ID;
    private final petType type;
    private final String texture;
    private final ArmorStand instance;
    private final BukkitTask runnableInstance;

    //lvl parameters
    private long exp;
    private int LVL;

    //abilities
    private float luck;
    private float attack;
    private float defence;
    private float drop;

    private int updates;

    //constructor to loadonjoin and on Zaloz
    public PetProfile(int ID, String type, long exp, int LVL, float luck,float attack,float defence,float drop, int updates, ArmorStand instance, BukkitTask task ) {
        this.ID = ID;
        this.exp = exp;
        this.attack=attack;
        this.defence=defence;
        this.LVL=LVL;
        this.instance=instance;
        this.runnableInstance=task;
        this.luck=luck;
        this.drop=drop;
        this.updates=updates;
        switch (type){
            case "KURCZAK":
                this.type=petType.KURCZAK;
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZmZWEyMGY4NTI1MDFkYzJkYzk4ODc3YjhkODY5ODBiZDE2YTliY2I2ZGYzNTgzYjNhMmIzMjU0YTgzNWY1YiJ9fX0=";
                break;
            case "PSZCZOLKA":
                this.type = petType.PSZCZOLKA;
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmEwNjYxZTIwZDI4Yzc0MzIzYjliYTY5MjY4YTYyYjNkMjM2YjY5OThjMTUzNjQzYjRkNjFlNTNjMmNhNTlmMyJ9fX0=";
                break;
            case "SLIMACZEK":
                this.type = petType.SLIMACZEK;
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM3M2ZiNTQ4MjNlYWMzMzBhZjAzMzcwMWIwNjk0YTNlY2U5ZjEzMjJkODJiNjhhY2UyN2NhYzhlYTk3MzQxOSJ9fX0=";
                break;
            case "LIS":
                this.type=petType.LIS;
                texture="ewogICJ0aW1lc3RhbXAiIDogMTU5NzI3NjY4MTMwNCwKICAicHJvZmlsZUlkIiA6ICI4NjMyODFlOTBiN2M0ZDIzODAxYmQxZTUyYjk3YzZlMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJHcjBwTWFTdDNyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYxMzc1ODFjMWExYTQ5ZGNhYmUyNzg4MDkyMjVlYzZkN2ZmMTEzOTNkMGE5MTRkNWUxYTcwZDdiOTY2YTcxNjgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
                break;
            case "MROWKA":
                this.type=petType.MROWKA;
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJmYTk5NzkyYWE2OTZiNzgwYzFmZGUwYTk2ODE2ZjdlZDc3NWY2NWE4MWM1ZmNlZGVmZWQ2NDQwOWZmZjlhNyJ9fX0=";
                break;
            case "PTASZEK":
                this.type=petType.PTASZEK;
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZiMjE5MGFmOWM5ZDAxMWU3OTYyZThhNTFmNDMwNThjMTIwYWQ2ZWY4ZmYwNzkwMjcxOWIyMjJkNjU5MDNlMiJ9fX0=";
                break;
            default:
                this.type=null;
                this.texture=null;
        }
    }

    //constructor to create new
    public PetProfile(petType type, ArmorStand instance, BukkitTask runnableInstance) {
        this.instance = instance;
        this.runnableInstance = runnableInstance;
        this.ID = LastID+1;
        LastID++;
        FileManager.SaveLastID(ID);
        this.type = type;
        exp=0;
        LVL=1;
        updates=0;
        switch(type){
            case LIS:
                texture="ewogICJ0aW1lc3RhbXAiIDogMTU5NzI3NjY4MTMwNCwKICAicHJvZmlsZUlkIiA6ICI4NjMyODFlOTBiN2M0ZDIzODAxYmQxZTUyYjk3YzZlMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJHcjBwTWFTdDNyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYxMzc1ODFjMWExYTQ5ZGNhYmUyNzg4MDkyMjVlYzZkN2ZmMTEzOTNkMGE5MTRkNWUxYTcwZDdiOTY2YTcxNjgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
                luck=0.5f;
                attack=1f;
                defence=1f;
                break;
            case KURCZAK:
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZmZWEyMGY4NTI1MDFkYzJkYzk4ODc3YjhkODY5ODBiZDE2YTliY2I2ZGYzNTgzYjNhMmIzMjU0YTgzNWY1YiJ9fX0=";
                luck=0.5f;
                break;
            case MROWKA:
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJmYTk5NzkyYWE2OTZiNzgwYzFmZGUwYTk2ODE2ZjdlZDc3NWY2NWE4MWM1ZmNlZGVmZWQ2NDQwOWZmZjlhNyJ9fX0=";
                luck=2.5f;
                attack=0.5f;
                defence=2f;
                drop=0.1f;
                break;
            case PTASZEK:
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZiMjE5MGFmOWM5ZDAxMWU3OTYyZThhNTFmNDMwNThjMTIwYWQ2ZWY4ZmYwNzkwMjcxOWIyMjJkNjU5MDNlMiJ9fX0=";
                attack=1f;
                defence=1f;
                break;
            case PSZCZOLKA:
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmEwNjYxZTIwZDI4Yzc0MzIzYjliYTY5MjY4YTYyYjNkMjM2YjY5OThjMTUzNjQzYjRkNjFlNTNjMmNhNTlmMyJ9fX0=";
                luck=1f;
                break;
            case SLIMACZEK:
                texture="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM3M2ZiNTQ4MjNlYWMzMzBhZjAzMzcwMWIwNjk0YTNlY2U5ZjEzMjJkODJiNjhhY2UyN2NhYzhlYTk3MzQxOSJ9fX0=";
                luck=5f;
                attack=2f;
                defence=0.5f;
                drop=0.2f;
                break;
            default:
                texture="ERROR";
        }
    }

    //getters and adders
    public long getExp(){
        return exp;
    }
    public int getLVL(){
        return LVL;
    }
    public float getLuck() {
        return luck;
    }
    public float getAttack() {
        return attack;
    }
    public float getDefence() {
        return defence;
    }
    public float getDrop() {
        return drop;
    }
    public int getID() {
        return ID;
    }
    public petType getType() {
        return type;
    }
    public String getTexture() {
        return texture;
    }
    public void interupt(){
        runnableInstance.cancel();
    }
    public int getUpdates(){return updates;}

    public ArmorStand getInstance() {
        return instance;
    }

    //dopisać system zwiększania lvl i komunikatów do gracza
    public void addExp(long amount){
        exp+=amount;
    }
    public void addAttack(float amount) {
        attack =+ amount;
    }
    public void addLuck(float amount){
        luck+=amount;
    }
    public void addDefence(float amount){
        defence+=amount;
    }
    public void addDrop(float amount){
        drop+=amount;
    }
    public void addLvl(){
        LVL+=1;
        attack+=attack*0.1f;
        defence+=defence*0.1f;
        luck+=luck*0.1f;
        drop+=drop*0.1f;
    }
    public boolean addUpdates(){
        if (updates<3) {
            updates++;
            return true;
        }
        else
            return false;
    }
    public enum petType{
        KURCZAK,PSZCZOLKA,SLIMACZEK,LIS,MROWKA,PTASZEK
    }
    public JsonObject toJsonObject(){
        JsonObject me = new JsonObject();
        me.addProperty("id",String.valueOf(ID));
        me.addProperty("type",type.toString());
        me.addProperty("texture",texture);
        me.addProperty("exp",String.valueOf(exp));
        me.addProperty("lvl",String.valueOf(LVL));
        me.addProperty("luck",String.valueOf(luck));
        me.addProperty("attack",String.valueOf(attack));
        me.addProperty("defence",String.valueOf(defence));
        me.addProperty("drop",String.valueOf(drop));
        me.addProperty("updates",String.valueOf(updates));
        return me;
    }
}
*
 */
