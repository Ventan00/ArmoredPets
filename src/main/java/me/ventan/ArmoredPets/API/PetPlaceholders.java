package me.ventan.ArmoredPets.API;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.NewPetProfile;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        else if(identifier.compareTo("lvl")==0) {
            if(plugin.playerHasPet(p)){
                return String.valueOf(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "Brak peta!";
        }
        else if(identifier.compareTo("exp")==0) {
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateExp(plugin.getProfileOfPlayersPet(p).getExp());
            }
            else return "Brak peta!";
        }
        else if(identifier.compareTo("maxExp")==0){
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "Brak peta!";
        }
        else if(identifier.compareTo("allExp")==0) {
            if(plugin.playerHasPet(p)){
                return NewPetProfile.generateExp(plugin.getProfileOfPlayersPet(p).getExp())+"/"+NewPetProfile.generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "Brak peta!";
        }
        else {
            //test placeholder add points
            Pattern pattern;
            if(p==null){
                pattern = Pattern.compile("(dodaj_)(.*)(_)(\\d+\\.*\\d*)(_)(.+)");
                Matcher tmp = pattern.matcher(identifier);
                if(tmp.find()){
                    p=MainArmoredPets.getInstance().getServer().getPlayer(tmp.group(6));
                }else{
                    return String.valueOf(false);
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
            return null;
        }
        //idk why compiler want's that 0.o
        return null;

    }

}
