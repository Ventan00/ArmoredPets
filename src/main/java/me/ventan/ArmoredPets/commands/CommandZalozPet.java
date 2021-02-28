package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyMath;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.NewPetProfile;
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
            ItemStack item = player.getInventory().getItemInHand();
            if(item==null || item.getItemMeta()==null || item.getItemMeta().getLore()==null)
            {
                return false;
            }
            List<String> parameters = item.getItemMeta().getLore();
            String ID =parameters.get(0);
            String EXP=parameters.get(3);
            String Luck = parameters.get(6);
            String Attack = parameters.get(7);
            String Defence = parameters.get(8);
            String Drop = parameters.get(9);
            String updates = parameters.get(10);
            String Type = item.getItemMeta().getDisplayName().substring(2);
            NewPetProfile profile = new NewPetProfile(eID(ID),Type,eEXP(EXP),stf(Luck),stf(Attack),stf(Defence),stf(Drop),eUpdates(updates));
            profile.spawn(player);
            player.getInventory().setItemInHand(new ItemStack(Material.AIR));
            FileManager.savePlayer(player);
        }
        else {
            commandSender.sendMessage("Idioto, jak chcesz założyć peta konsoli. Puknij że ty się w łeb. Najlepiej czołgiem");
        }
        return true;
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
    public long eEXP(String EXP){
        Pattern p = Pattern.compile("(§fExp: §a)(\\d+)([kmbt]*)");
        Matcher m = p.matcher(EXP);
        m.find();
        if(!m.group(3).isEmpty()){
            long multiplier;
            switch (m.group(3)){
                case "k":
                    multiplier=1000L;
                    break;
                case "m":
                    multiplier=1000000L;
                    break;
                case "b":
                    multiplier=1000000L;
                    break;
                case "t":
                    multiplier=1000000000L;
                    break;
                default:
                    multiplier=1L;
            }
            return Integer.parseInt(m.group(2)) * multiplier;
        }
        else {
            return Long.parseLong(m.group(2));
        }
    }
    //extractUpdates
    public int eUpdates(String in){
        return in.charAt(14)-'0';
    }

}
