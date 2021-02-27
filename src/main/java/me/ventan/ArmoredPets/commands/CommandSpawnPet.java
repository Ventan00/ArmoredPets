package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyMath;
import me.ventan.ArmoredPets.utils.*;
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

/*
*   Spawns pet that follows player
*
 */
public class CommandSpawnPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            try{
                PetType.valueOf(args[0].toUpperCase());
                if(args.length>0){
                    Player player = (Player)commandSender;
                    NewPetProfile profile = new NewPetProfile(PetType.valueOf(args[0].toUpperCase()));
                    if(!profile.spawn(player)){
                        player.getInventory().addItem(profile.getItem());
                    }
                }else {
                    commandSender.sendMessage(ChatColor.RED+"Poprawne użycie: /spawnpet <typ moba>");
                    commandSender.sendMessage(ChatColor.RED+"Typy mobów: kurczak, pszczolka, slimaczek, lis, mrowka, ptaszek");
                }
            }catch (Exception e){
                commandSender.sendMessage(ChatColor.RED+"Poprawne użycie: /spawnpet <typ moba>");
                commandSender.sendMessage(ChatColor.RED+"Typy mobów: kurczak, pszczolka, slimaczek, lis, mrowka, ptaszek");
            }


        }
        return true;
    }}
