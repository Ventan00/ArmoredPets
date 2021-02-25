package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class CommandDespawnPet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            MainArmoredPets main = MainArmoredPets.getInstance();
            if(main.playerHasPet(player)){
                PetProfile pet = main.getProfileOfPlayersPet(player);
                pet.interupt();
                pet.getInstance().remove(); //despawning armorstand
                ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));//giving item to player hand
                switch (pet.getType()){
                    case KURCZAK: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.YELLOW + pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE + "Rzadkosc: " + ChatColor.GRAY + " pospolity";
                        String WymaganyPoziom = ChatColor.WHITE + "Wymagany poziom: " + ChatColor.GREEN + "10";
                        String LVL = ChatColor.WHITE + "Lvl: " + ChatColor.GREEN + pet.getLVL();
                        String EXP = ChatColor.WHITE + "Exp: " + ChatColor.GREEN + generateExp(pet.getExp()) + ChatColor.WHITE + "/" + ChatColor.GREEN + generateMaxEXPForLevel(pet.getLVL());
                        String Luck = ChatColor.GREEN + "Szczescie: " + pet.getLuck()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc, WymaganyPoziom, EXP, LVL, ChatColor.WHITE + "Bonusy:", Luck,updates));
                        break;
                    }
                    case PSZCZOLKA: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE+pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.DARK_PURPLE +" rzadki";
                        String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"25";
                        String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
                        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
                        String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,updates));
                        break;
                    }
                    case PTASZEK: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.BLUE+pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.LIGHT_PURPLE +" Nietypowy";
                        String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"30";
                        String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
                        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
                        String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
                        String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Attack,Obrona,updates));
                        break;
                    }
                    case LIS: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.RED+pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.BLUE +" Mityczny";
                        String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"50";
                        String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
                        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
                        String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
                        String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
                        String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,updates));
                        break;
                    }
                    case MROWKA: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.GOLD+pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.GOLD +" Boski";
                        String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"0";
                        String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
                        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
                        String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
                        String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
                        String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
                        String DropChance = ChatColor.GOLD+"Drop skrzyni: "+pet.getDrop()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates));
                        break;
                    }
                    case SLIMACZEK: {
                        ItemMeta itemMeta = head.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.DARK_PURPLE+pet.getType().toString());
                        head.setItemMeta(itemMeta);
                        String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
                        String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.YELLOW +" Legendarny";
                        String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"0";
                        String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
                        String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
                        String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
                        String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
                        String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
                        String DropChance = ChatColor.GOLD+"Drop skrzyni: "+pet.getDrop()+"%";
                        String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
                        head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates));
                        break;
                    }
                }
                player.getInventory().addItem(head);
                main.removePetFromPlayer(player);
                FileManager.deletePlayer(player);

            }
            else {
                player.sendMessage(ChatColor.RED+"Nie masz peta!");
                return true;
            }
        }
        else{
            commandSender.sendMessage("Później zaimplementuję tę funkcję dla konsoli ;)");
        }
        return true;
    }
    public String generateMaxEXPForLevel(int LVL){
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
    public String generateExp(long EXP){
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
