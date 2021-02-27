package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandDespawnPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            MainArmoredPets main = MainArmoredPets.getInstance();
            if(main.playerHasPet(player)){
                player.getInventory().addItem(main.getProfileOfPlayersPet(player).getItem());
                main.removePetFromPlayer(player);
                FileManager.deletePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.RED+"Nie masz peta!");
                return true;
            }
        }
        else{
            //// TODO: 27.02.2021 usuwanie peta w konosoli dla gracza
            if(strings.length>0){
                MainArmoredPets.getInstance().getServer().getOnlinePlayers()
                        .stream()
                        .filter(player -> player.getName().compareTo(strings[0])==0);
            }
            commandSender.sendMessage("Później zaimplementuję tę funkcję dla konsoli ;)");
        }
        return true;
    }
}
