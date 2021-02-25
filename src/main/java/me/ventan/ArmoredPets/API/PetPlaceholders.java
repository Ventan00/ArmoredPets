package me.ventan.ArmoredPets.API;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.utils.PetProfile.petType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.ventan.ArmoredPets.utils.PetProfile.petType.*;

public class PetPlaceholders extends PlaceholderExpansion {
    MainArmoredPets plugin = MainArmoredPets.getInstance();
    @Override
    public boolean canRegister(){
        return plugin.getServer().getPluginManager().isPluginEnabled("NPCs");
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
        return "1.0.0";
    }
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if (identifier.compareTo("type")==0) {
            if(plugin.playerHasPet(p)){
                switch (plugin.getProfileOfPlayersPet(p).getType()){
                    case KURCZAK:
                        return "§eKURCZAK";
                    case PSZCZOLKA:
                        return "§dPSZCZOLKA";
                    case PTASZEK:
                        return "§9PTASZEK";
                    case MROWKA:
                        return "§6MROWKA";
                    case LIS:
                        return "§cLIS";
                    case SLIMACZEK:
                        return "§5SLIMACZEK";
                }
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
                return generateExp(plugin.getProfileOfPlayersPet(p).getExp());
            }
            else return "Brak peta!";
        }
        else if(identifier.compareTo("maxExp")==0){
            if(plugin.playerHasPet(p)){
                return generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "Brak peta!";
        }
        else if(identifier.compareTo("allExp")==0)
        {
            if(plugin.playerHasPet(p)){
                return generateExp(plugin.getProfileOfPlayersPet(p).getExp())+"/"+generateMaxEXPForLevel(plugin.getProfileOfPlayersPet(p).getLVL());
            }
            else return "Brak peta!";
        }
        return null;
    }
    private String generateMaxEXPForLevel(int LVL){
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
    private String generateExp(long EXP){
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
