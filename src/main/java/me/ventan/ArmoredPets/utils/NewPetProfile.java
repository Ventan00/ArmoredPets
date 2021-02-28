package me.ventan.ArmoredPets.utils;

//import com.destroystokyo.paper.ParticleBuilder;
import com.google.gson.JsonObject;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.Math.MyMath;
import org.bukkit.ChatColor;
import org.bukkit.Location;
//import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;


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
    private static final float expModifier = 0.07f;

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
        LVL=0;
        updates=0;
    }

    //on reload and put on constructor
    public NewPetProfile(int ID, String type, long exp, float luck,float attack,float defence,float drop, int updates){
        this.ID = ID;
        this.type = PetType.typeOrNull(type);
        this.exp = exp;
        this.luck = luck;
        this.attack = attack;
        this.defence = defence;
        this.drop = drop;
        this.updates = updates;
        while(MyLvlExp.instance.getPd(LVL+1)<exp)
            LVL++;
    }

    public boolean spawn(Player player){
        if(MainArmoredPets.getInstance().playerHasPet(player))
            return false;
        float[] params = MyMath.getInstance().calculateComplexParameters(player.getLocation().getYaw());
        Location loc = player.getLocation().add(params[0],0.5,params[1]);
        armorstand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
        armorstand.setCustomName(type.nickColor+type.toString().substring(0, 1).toUpperCase()+type.toString().substring(1)  +ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+LVL+ChatColor.WHITE+")");
        armorstand.setGravity(false);
        //armorstand.setCanMove(true);
        armorstand.setCustomNameVisible(true);
        armorstand.setVisible(false);
        armorstand.setCanPickupItems(false);
        //armorstand.setCollidable(false);
        armorstand.setSmall(true);
        ItemStack head = new ItemStack(skullCreator.getSkull(type.texture));
        armorstand.setHelmet(head);
        animation= new BukkitRunnable() {
            final float maxval=0.3f;
            final float minval=-0.3f;
            float val=0f;
            float toAdd=0.05f;
            long timestamp=System.currentTimeMillis()+40*1000;

            @Override
            public void run() {
                val+=toAdd;
                if(val>=maxval)
                    toAdd=-0.05f;
                if(val<=minval)
                    toAdd=0.05f;
                float[] parametryToAdd = MyMath.getInstance().calculateComplexParameters(player.getLocation().getYaw());
                armorstand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                if(timestamp<=System.currentTimeMillis()){
                    timestamp=System.currentTimeMillis()+40*1000;
                    player.getWorld().playSound(armorstand.getLocation(), Sound.BAT_LOOP, 3.0f,1.094f);
                }
                /*ParticleBuilder particleBuilder = new ParticleBuilder(Particle.END_ROD);
                particleBuilder.location(armorstand.getLocation());
                particleBuilder.count(1);
                particleBuilder.receivers(10);
                particleBuilder.spawn();*/
            }
        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
        MainArmoredPets.getInstance().addPetToPlayer(player,this);
        FileManager.savePlayer(player);
        return true;
    }

    public boolean despawn(){
        if(!MainArmoredPets.getInstance().petIsSpanwed(this))
            return false;
        animation.cancel();
        armorstand.remove();
        MainArmoredPets.getInstance().removePetFromPlayer(MainArmoredPets.getInstance().getPetOwner(this));
        return true;
    }

    public ItemStack getItem(){
        ItemStack head = new ItemStack(skullCreator.getSkull(type.texture));
        ItemMeta itemMeta = head.getItemMeta();
        itemMeta.setDisplayName(type.nickColor + type.toString());
        String ID = ChatColor.DARK_GRAY+"ID: " + this.ID;
        String rarity = ChatColor.WHITE + "Rzadkosc: " + type.rarity.color+type.rarity.toString();
        String minlvl = ChatColor.WHITE + "Wymagany poziom: " +ChatColor.GREEN+ type.minLvl;
        String Lvl = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+LVL;
        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(exp)+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(LVL);
        String Luck = ChatColor.GREEN+"Szczescie: "+luck;
        String Attack = ChatColor.RED+"Atak: "+attack;
        String Obrona = ChatColor.BLUE+"Atak: "+defence;
        String Drop = ChatColor.GOLD+"Drop: "+drop;
        String updts= ChatColor.DARK_GRAY+"Ulepszenia: "+updates+"/3";
        itemMeta.setLore(Arrays.asList(ID,rarity,minlvl,EXP,Lvl,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,Drop,updts));
        head.setItemMeta(itemMeta);
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

    public ArmorStand getArmorstand(){
        return armorstand;
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

    public void addExp(long amount){
        exp+=amount;
        if(MyLvlExp.instance.getPd(LVL+1)<exp){
            while(MyLvlExp.instance.getPd(LVL+1)<exp) {
                LVL += 1;
                attack += attack * expModifier;
                defence += defence * expModifier;
                luck += luck * expModifier;
                drop += drop * expModifier;
            }
            MainArmoredPets.getInstance().getPetOwner(this).sendMessage(ChatColor.AQUA+"Twój pet awansował na "+ChatColor.GREEN+ChatColor.BOLD+LVL+ChatColor.AQUA+" poziom!!!");
            //change display name
            armorstand.setCustomName(type.nickColor+type.toString().substring(0, 1).toUpperCase()+type.toString().substring(1)  +ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+LVL+ChatColor.WHITE+")");
        }
    }


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

    public static String generateMaxEXPForLevel(int LVL){
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
    public static String generateExp(long EXP){
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
