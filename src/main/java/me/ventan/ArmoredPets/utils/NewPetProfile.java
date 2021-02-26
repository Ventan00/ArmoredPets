package me.ventan.ArmoredPets.utils;

import com.google.gson.JsonObject;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;
import java.util.List;

public class NewPetProfile {
    public static int LastID=0;

    //texture parameters
    private final int ID;
    private final PetType type;
    private ArmorStand armorstand;
    private BukkitTask animation;

    //lvl parameters
    private long exp;
    private int LVL=0;

    //abilities
    private float luck;
    private float attack;
    private float defence;
    private float drop;

    private int updates;

    //standard constructor
    public NewPetProfile(PetType type){
        this.ID = LastID+1;
        LastID++;
        this.type=type;
        this.luck=type.baseLuck;
        this.attack=type.baseAttack;
        this.defence=type.baseDefence;
        this.drop=type.baseDrop;
        exp=0;
        LVL=1;
        updates=0;
    }

    //on reload constructor
    public NewPetProfile(int ID, String type, long exp, float luck,float attack,float defence,float drop, int updates){
        this.ID = ID;
        this.type = PetType.valueOf(type.toUpperCase());
        this.exp = exp;
        this.luck = luck;
        this.attack = attack;
        this.defence = defence;
        this.drop = drop;
        this.updates = updates;
    }

    public void spawn(Player player){

    }

    public void despawn(){

    }

    public ItemStack getItem(){
        ItemStack head = new ItemStack(skullCreator.getSkull(type.texture));
        ItemMeta itemMeta = head.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + type.toString());
        head.setItemMeta(itemMeta);
        String ID = ChatColor.DARK_GRAY+"ID: " + this.ID;
        String rarity = ChatColor.WHITE + "Rzadkosc: " + type.rarity.color+type.rarity.toString();
        String minlvl = ChatColor.WHITE + "Wymagany poziom: " +ChatColor.GREEN+ type.minLvl;
        String Lvl = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+LVL;
        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(exp)+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(LVL);
        String Luck = ChatColor.GREEN+"Szczescie: "+luck+"%";
        String updts= ChatColor.DARK_GRAY+"Ulepszenia: "+updates+"/3";
        head.setLore(Arrays.asList(ID,rarity,minlvl,EXP,Lvl,ChatColor.WHITE+"Bonusy:",Luck,updts));
        return head;
    }

    public int getID() {
        return ID;
    }

    public PetType getType() {
        return type;
    }

    public long getExp() {
        return exp;
    }

    public int getLVL() {
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

    public int getUpdates() {
        return updates;
    }

    public boolean addUpdates(){
        if (updates<3) {
            updates++;
            return true;
        }
        else
            return false;
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

    // TODO: 26.02.2021  dopisać system zwiększania lvl i komunikatów do gracza

    public JsonObject toJsonObject(){
        JsonObject me = new JsonObject();
        me.addProperty("id",String.valueOf(ID));
        me.addProperty("type",type.toString());
        me.addProperty("exp",String.valueOf(exp));
        me.addProperty("luck",String.valueOf(luck));
        me.addProperty("attack",String.valueOf(attack));
        me.addProperty("defence",String.valueOf(defence));
        me.addProperty("drop",String.valueOf(drop));
        me.addProperty("updates",String.valueOf(updates));
        return me;
    }

    public String generateMaxEXPForLevel(int LVL){
        StringBuilder output = new StringBuilder();
        String longL= Long.toString(MyLvlExp.instance.getPd(LVL));
        int length = longL.length();
        if(length>5) {
            if ((length + 1) % 3 == 1) {
                output.append(longL.charAt(0));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else if ((length + 1) % 3 == 2) {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                output.append(longL.charAt(2));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            }
            output.append('k');
            return output.toString();
        }
        else{
            return longL;
        }
    }
    public String generateExp(long EXP){
        StringBuilder output = new StringBuilder();
        String longL = String.valueOf(EXP);
        int length = longL.length();
        if(length>5) {
            if ((length + 1) % 3 == 1) {
                output.append(longL.charAt(0));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else if ((length + 1) % 3 == 2) {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                output.append(longL.charAt(2));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            }
            output.append('k');
            return output.toString();
        }
        else{
            return longL;
        }
    }
}
