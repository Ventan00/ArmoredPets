package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyMath;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static me.ventan.ArmoredPets.utils.PetProfile.petType.*;
/*
*   Spawns pet that follows player
*
 */
public class CommandSpawnPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            if(args.length>0){
                Player player = (Player)commandSender;
                if(MainArmoredPets.getInstance().playerHasPet(player))
                {
                    player.sendMessage(ChatColor.RED+"Masz już założonego peta!");
                    return false;

                }
                float[] params = calculateComplexParameters(player.getLocation().getYaw());
                Location loc = player.getLocation().add(params[0],0.5,params[1]);
                ArmorStand armorStand;
                BukkitTask movingComponent;
                switch (args[0].toLowerCase()){
                    case "kurczak":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
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
                                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                                if(timestamp<=System.currentTimeMillis()){
                                    timestamp=System.currentTimeMillis()+40*1000;
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1); //animating moving
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(KURCZAK, armorStand, movingComponent));
                        break;
                    case "pszczolka":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
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
                                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                                if(timestamp<=System.currentTimeMillis()){
                                    timestamp=System.currentTimeMillis()+40*1000;
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(PSZCZOLKA, armorStand, movingComponent));
                        break;
                    case "slimaczek":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
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
                                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                                if(timestamp<=System.currentTimeMillis()){
                                    timestamp=System.currentTimeMillis()+40*1000;
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(SLIMACZEK, armorStand, movingComponent));
                        break;
                    case "lis":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
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
                                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                                if(timestamp<=System.currentTimeMillis()){
                                    timestamp=System.currentTimeMillis()+40*1000;
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(LIS, armorStand, movingComponent));
                        break;
                    case "mrowka":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
                            final float maxval=0.3f;
                            final float minval=-0.3f;
                            long timestamp=System.currentTimeMillis()+40*1000;
                            float val=0f;
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
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(MROWKA, armorStand, movingComponent));
                        break;
                    case "ptaszek":
                        armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
                        movingComponent = new BukkitRunnable(){
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
                                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                                if(timestamp<=System.currentTimeMillis()){
                                    timestamp=System.currentTimeMillis()+40*1000;
                                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                                }
                            }
                        }.runTaskTimer(MainArmoredPets.getInstance(),20,1);
                        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(PTASZEK, armorStand, movingComponent));
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Nie poprawny typ moba!");
                        return false;
                }
                armorStand.setGravity(false);
                armorStand.setCanMove(true);
                armorStand.setCustomNameVisible(true);
                armorStand.setVisible(false);
                armorStand.setCanPickupItems(false);
                armorStand.setCollidable(false);
                armorStand.setSmall(true);
                ItemStack head = new ItemStack(skullCreator.getSkull(MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getTexture()));
                armorStand.setHelmet(head);

                switch (args[0].toLowerCase()){
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
                FileManager.savePlayer(player);
            }else {
                commandSender.sendMessage(ChatColor.RED+"Poprawne użycie: /spawnpet <typ moba>");
                commandSender.sendMessage(ChatColor.RED+"Typy mobów: kurczak, pszczolka, slimaczek, lis, mrowka, ptaszek");
            }

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
}
