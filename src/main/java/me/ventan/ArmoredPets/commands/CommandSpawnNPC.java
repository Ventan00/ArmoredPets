package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.NPCs.API.NPCsAPI;
import me.ventan.NPCs.utils.NPCProfile;
import net.minecraft.server.v1_12_R1.Item;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandSpawnNPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            if(validate(args))
            {
                Player player = (Player) commandSender;
                NPCProfile profile = NPCsAPI.getNPC(args[0],args[1],args[2],args[3],player.getLocation());
                MainArmoredPets.getInstance().addNPCManager(profile);
                FileManager.saveNPCs();
            }else{
                commandSender.sendMessage(ChatColor.RED+"poprawne użycie: /spawnpetnpc <kolorowy nick (spacja to _)> <nick skina> <hex color (bez #)> <id itemu>");
            }
        }
        return true;
    }

    private boolean validate(String[] args) {
        if(args.length==4){
            Pattern pattern = Pattern.compile("[a-fA-F0-9]{6}");
            Matcher matcher = pattern.matcher(args[2]);
            if(matcher.matches()){
                if(Item.getById(Integer.parseInt(args[3]))!=null){
                    return true;
                }
            }
        }
        return false;
    }
}
