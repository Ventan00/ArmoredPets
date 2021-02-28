package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
*   Spawns pet that follows player
*
 */
public class CommandSpawnPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            if(PetType.typeOrNull(args[0])!=null && args.length>0){
                Player player = (Player)commandSender;
                NewPetProfile profile = new NewPetProfile(PetType.typeOrNull(args[0]));
                if(!profile.spawn(player)){
                    player.getInventory().addItem(profile.getItem());
                }
            }else {
                commandSender.sendMessage(ChatColor.RED+"Poprawne użycie: /spawnpet <typ moba>");
                commandSender.sendMessage(ChatColor.RED+"Typy mobów: kurczak, pszczolka, slimaczek, lis, mrowka, ptaszek");
            }

        }
        return true;
    }
}
