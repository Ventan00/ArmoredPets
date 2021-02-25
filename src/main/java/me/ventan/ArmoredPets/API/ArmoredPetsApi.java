package me.ventan.ArmoredPets.API;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ArmoredPetsApi {
    public void savePetofPlayer(Player player){
        FileManager.savePlayer(player);
    }
    public void loadPetofPlayer(Player player){
        FileManager.readPlayer(player);
    }
    public PetProfile getPetOfOnlinePlayer(Player player){
        if(MainArmoredPets.getInstance().playerHasPet(player)){
            return MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        }
        else
            return null;
    }
    public void addLuckToPet(PetProfile profile, float luck){
        profile.addLuck(luck);
    }
    public void addAtackToPet(PetProfile profile, float attack){
        profile.addLuck(attack);
    }
    public void addDefenceToPet(PetProfile profile, float defence){
        profile.addDefence(defence);
    }
    public void addDropToPet(PetProfile profile, float drop){
        profile.addDrop(drop);
    }
    public void lvlUpPet(PetProfile profile){
        profile.addLvl();
    }
    public void addExpToPet(PetProfile profile, int ammount){
        profile.addExp(ammount);
    }
    public void addPetToPlayerEq(Player player, String Type){
        PetProfile pet=null;
        switch (Type.toUpperCase()){
            case "LIS":
                pet=new PetProfile(PetProfile.petType.LIS,null,null);
                break;
            case "KURCZAK":
                pet=new PetProfile(PetProfile.petType.KURCZAK,null,null);
                break;
            case "MROWKA":
                pet=new PetProfile(PetProfile.petType.MROWKA,null,null);
                break;
            case "PTASZEK":
                pet=new PetProfile(PetProfile.petType.PTASZEK,null,null);
                break;
            case "PSZCZOLKA":
                pet=new PetProfile(PetProfile.petType.PSZCZOLKA,null,null);
                break;
            case "SLIMACZEK":
                pet=new PetProfile(PetProfile.petType.SLIMACZEK,null,null);
                break;
        }
        ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
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
                head.setLore(Arrays.asList(ID,Rzadkosc, WymaganyPoziom, EXP, LVL, ChatColor.WHITE + "Bonusy:", Luck));
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck));
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Attack,Obrona));
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona));
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance));
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance));
                break;
            }
        }
        player.getInventory().addItem(head);
        //z profilu wygenerować item który dodam do eq;
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
