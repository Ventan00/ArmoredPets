package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyMath;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandZalozPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(MainArmoredPets.getInstance().playerHasPet(player))
                return false;
            float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
            Location loc = player.getLocation().add(parametryToAdd[0],0.5,parametryToAdd[1]);
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item==null)
            {
                return false;
            }
            String ID =null;
            String EXP=null;
            String LVL=null;
            String Luck=null;
            String Attack=null;
            String Defence=null;
            String Drop=null;
            String updates=null;
            String Type = item.getItemMeta().getDisplayName().substring(2);
            switch (item.getI18NDisplayName()){
                case "§eKURCZAK":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Luck = parameters.get(6);
                    updates = parameters.get(7);
                    break;
                }
                case "§dPSZCZOLKA":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Luck = parameters.get(6);
                    updates = parameters.get(7);
                    break;
                }
                case "§9PTASZEK":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Attack = parameters.get(6);
                    Defence = parameters.get(7);
                    updates = parameters.get(8);
                    break;
                }
                case "§cLIS":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Luck = parameters.get(6);
                    Attack = parameters.get(7);
                    Defence = parameters.get(8);
                    updates = parameters.get(9);
                    break;
                }
                case "§6MROWKA":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Luck = parameters.get(6);
                    Attack = parameters.get(7);
                    Defence = parameters.get(8);
                    Drop = parameters.get(9);
                    updates = parameters.get(10);
                    break;
                }
                case "§5SLIMACZEK":
                {
                    List<String> parameters = item.getLore();
                    ID = parameters.get(0);
                    EXP = parameters.get(3);
                    LVL = parameters.get(4);
                    Attack = parameters.get(6);
                    Defence = parameters.get(7);
                    Luck = parameters.get(8);
                    Drop = parameters.get(9);
                    updates = parameters.get(10);
                    break;
                }
                default:
                    return false;
            }
            ArmorStand armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
            BukkitTask task =  new BukkitRunnable(){
                final float maxval=0.3f;
                final float minval=-0.3f;
                float val=0f;
                long timestamp=System.currentTimeMillis()+40*1000;
                float toAdd=0.05f;

                @Override
                public void run() {
                    val+=toAdd;
                    if(val>=maxval)
                        toAdd=-0.05f;
                    if(val<=minval)
                        toAdd=0.05f;
                    float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                    armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                    if(timestamp<=System.currentTimeMillis()){
                        timestamp=System.currentTimeMillis()+40*1000;
                        player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,0.764f);
                    }
                }
            }.runTaskTimer(MainArmoredPets.getInstance(),20,1); //animating moving
            MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(
                    eID(ID), Type, eEXP(EXP), Integer.parseInt(LVL.substring(9)),stf(Luck),stf(Attack),stf(Defence),stf(Drop),eUpdates(updates),armorStand,task
            ));
            ItemStack head = new ItemStack(skullCreator.getSkull(MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getTexture()));
            armorStand.setHelmet(head);
            armorStand.setGravity(false);
            armorStand.setCanMove(true);
            armorStand.setCanPickupItems(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);
            armorStand.setCollidable(false);
            armorStand.setSmall(true);
            switch (Type.toLowerCase()){
                case "kurczak":
                    armorStand.setCustomName(ChatColor.YELLOW+"Kurczak"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
                case "pszczolka":
                    armorStand.setCustomName(ChatColor.LIGHT_PURPLE+"Pszczolka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
                case "slimaczek":
                    armorStand.setCustomName(ChatColor.DARK_PURPLE+"Slimaczek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
                case "lis":
                    armorStand.setCustomName(ChatColor.RED+"Lis"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
                case "mrowka":
                    armorStand.setCustomName(ChatColor.GOLD+"Mrowka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
                case "ptaszek":
                    armorStand.setCustomName(ChatColor.BLUE+"Ptaszek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                    break;
            }
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            FileManager.savePlayer(player);
        }
        else {
            commandSender.sendMessage("Idioto, jak chcesz założyć peta konsoli. Puknij że ty się w łeb. Najlepiej czołgiem");
        }
        return true;
    }

    private float[] calculateComplexParameters(float yaw) {
        float[] result = new float[2];
        yaw+=180;
        result[0]= 1.5f*(float) MyMath.getTable().getCos((int) yaw);
        result[1]= 1.5f*(float) MyMath.getTable().getSine((int) yaw);
        return result;
    }

    //string to float
    public float stf(String input){
        float output=0;
        if(input==null){
            return 0.0f;
        }
        Pattern p = Pattern.compile("([-]?\\d+\\.\\d+)");
        Matcher m = p.matcher(input);
        m.find();
        output=Float.parseFloat(m.group(1));
        return output;
    }
    //extract ID
    public int eID(String ID){
        return Integer.parseInt(ID.substring(6));
    }
    //extract EXP
    public int eEXP(String EXP){
        Pattern p = Pattern.compile("(§fExp: §a)(\\d+)([k]*)");
        Matcher m = p.matcher(EXP);
        m.find();
        if(!m.group(3).isEmpty()){
            return (int)(Integer.parseInt(m.group(2)) * Math.pow(1000,m.group(3).length()));
        }
        else {
            return Integer.parseInt(m.group(2));
        }
    }
    //extractUpdates
    public int eUpdates(String in){
        return in.charAt(14)-'0';
    }

}
