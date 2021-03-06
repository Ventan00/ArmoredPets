package me.ventan.ArmoredPets.API;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.NewPetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PetPlaceholders extends PlaceholderExpansion {
    MainArmoredPets plugin = MainArmoredPets.getInstance();
    @Override
    public boolean canRegister(){
        return plugin.getServer().getPluginManager().isPluginEnabled("Citizens");
    }
    @Override
    public @NotNull String getIdentifier() {
        return "pet";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ventan";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.1";
    }
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.compareTo("type")==0) {
            if(plugin.playerHasPet(p)){
                return plugin.getProfileOfPlayersPet(p).getType().nickColor+plugin.getProfileOfPlayersPet(p).getType().toString();
            }
        }
        else if(identifier.compareTo("luck")==0) {
            if(plugin.playerHasPet(p)){
                return String.format("%.2f",plugin.getProfileOfPlayersPet(p).getLuck());
            }
            else return "0";
        }
        else if(identifier.compareTo("attack")==0) {
            if(plugin.playerHasPet(p)){
                return String.format("%.2f",plugin.getProfileOfPlayersPet(p).getAttack());
            }
            else return "0";
        }
        else if(identifier.compareTo("defence")==0) {
            if(plugin.playerHasPet(p)){
                return String.format("%.2f",plugin.getProfileOfPlayersPet(p).getDefence());
            }
            else return "0";
        }
        else if(identifier.compareTo("drop")==0) {
            if(plugin.playerHasPet(p)){
                return String.format("%.2f",plugin.getProfileOfPlayersPet(p).getDrop());
            }
            else return "0";
        }else if(identifier.compareTo("lvl")==0) {
            if(plugin.playerHasPet(p)){
                return String.valueOf(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "0";
        }
        else if(identifier.compareTo("exp")==0) {
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateExp(plugin.getProfileOfPlayersPet(p).getExp());
            }
            else return "0";
        }
        else if(identifier.compareTo("maxExp")==0){
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "0";
        }
        else if(identifier.compareTo("allExp")==0) {
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateExp(plugin.getProfileOfPlayersPet(p).getExp())+"/"+NewPetProfile.generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "0";
        }
        else {
            //test placeholder add points
            Pattern pattern;
            if(p==null){
                pattern = Pattern.compile("(dodaj_)(.*)(_)(\\d+\\.*\\d*)(_)(.+)");
                Matcher tmp = pattern.matcher(identifier);
                if(tmp.find()){
                    p=MainArmoredPets.getInstance().getServer().getPlayer(tmp.group(6));
                }
            }else {
                pattern = Pattern.compile("(dodaj_)(.*)(_)(\\d+\\.*\\d*)");
            }
            Matcher matcher = pattern.matcher(identifier);
            if(matcher.find()){
                if(!MainArmoredPets.getInstance().playerHasPet(p)){
                    return "Brak peta!";
                }
                switch (matcher.group(2)){
                    case "atak":{
                        MainArmoredPets.getInstance().getProfileOfPlayersPet(p).addAttack(Float.parseFloat(matcher.group(4)));
                        return String.valueOf(true);
                    }
                    case "obrona":{
                        MainArmoredPets.getInstance().getProfileOfPlayersPet(p).addDefence(Float.parseFloat(matcher.group(4)));
                        return String.valueOf(true);
                    }
                    case "szczescie":{
                        MainArmoredPets.getInstance().getProfileOfPlayersPet(p).addLuck(Float.parseFloat(matcher.group(4)));
                        return String.valueOf(true);
                    }
                    case "drop":{
                        MainArmoredPets.getInstance().getProfileOfPlayersPet(p).addDrop(Float.parseFloat(matcher.group(4)));
                        return String.valueOf(true);
                    }
                    case "exp":{

                        MainArmoredPets.getInstance().getProfileOfPlayersPet(p).addExp((new Double(Double.parseDouble(matcher.group(4))).longValue()));
                        return String.valueOf(true);
                    }
                    default:
                        return String.valueOf(false);
                }
            }
            else{
                pattern = Pattern.compile("(give_)(.*)(_)(.*)");
                matcher = pattern.matcher(identifier);
                if(matcher.find()){
                    p=MainArmoredPets.getInstance().getServer().getPlayer(matcher.group(4));
                    switch (matcher.group(2)){
                        case "niezidentyfikowany":{
                            ItemStack unindentified = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhlNjUyNTM4NWY3NDgzMjJlZWNjNDE2ODliNjkwYWExZWJiYjIxNzJlYzJjNGEyZWFjMTRmMjJkOTRlZTNiYSJ9fX0=");
                            ItemMeta meta = unindentified.getItemMeta();
                            meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Niezidentyfikowany zwierzak");
                            meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+"Możesz zidentyfikować go u zoologa"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            unindentified.setItemMeta(meta);
                            p.getInventory().addItem(unindentified);
                            return String.valueOf("true");
                        }
                        case "kamienLVL":{
                            ItemStack LVLSTONE = new ItemStack(Material.INK_SACK, 1 ,(short) 12);
                            ItemMeta meta = LVLSTONE.getItemMeta();
                            meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Kamień poziomu peta");
                            meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+"Pozwala dodać poziom Twojemu petowi u zoologa"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            LVLSTONE.setItemMeta(meta);
                            p.getInventory().addItem(LVLSTONE);
                            return String.valueOf("true");
                        }
                        case "kamienUlepsz":{
                            ItemStack PETSTATS = new ItemStack(Material.NETHER_STAR);
                            PETSTATS.addUnsafeEnchantment(Enchantment.DURABILITY,1);
                            ItemMeta meta = PETSTATS.getItemMeta();
                            meta.setDisplayName(ChatColor.BLUE+"Kamień ulepszenia peta");
                            meta.setLore(Arrays.asList(ChatColor.BLUE+"Pozwala dodać losową statystykę do Twojego peta",ChatColor.BLUE+"Możesz użyć tego przedmiotu u zoologa"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            PETSTATS.setItemMeta(meta);
                            p.getInventory().addItem(PETSTATS);
                            return String.valueOf("true");
                        } default:{
                            return String.valueOf("false");
                        }
                    }
                }
            }
            return null;
        }
        //idk why compiler want's that 0.o
        return null;

    }

}
