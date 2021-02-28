package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.NewPetProfile;
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
                NewPetProfile pet = main.getProfileOfPlayersPet(player);
                pet.despawn();
                player.getInventory().addItem(pet.getItem());
                FileManager.deletePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.RED+"Nie masz peta!");
                return true;
            }
        }
        else{
            if(strings.length>0){
                Player player = MainArmoredPets.getInstance().getServer().getOnlinePlayers()
                        .stream()
                        .filter(p -> p.getName().compareTo(strings[0])==0).findFirst().get();
                if(MainArmoredPets.getInstance().playerHasPet(player)){
                    NewPetProfile profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
                    profile.despawn();
                }
            }
            commandSender.sendMessage("Później zaimplementuję tę funkcję dla konsoli ;)");
        }
        return true;
    }
}
