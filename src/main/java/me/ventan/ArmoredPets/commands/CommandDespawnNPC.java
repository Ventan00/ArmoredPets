package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.NPCs.API.NPCsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDespawnNPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length>0){
            if(MainArmoredPets.getInstance().getNPCs().contains(NPCsAPI.getFromID(Integer.valueOf(args[0])))){
                NPCsAPI.removeNPC(Integer.valueOf(args[0]));
            }
            else
                commandSender.sendMessage(ChatColor.RED+"Nie ma takiego NPC w bazie ArmoredPets!");
        }else{
            commandSender.sendMessage(ChatColor.RED+"Poprawne użycie: /removePetNpc <ID>");
        }
        return false;
    }
}
