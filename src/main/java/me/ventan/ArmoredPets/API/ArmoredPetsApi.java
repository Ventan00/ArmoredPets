package me.ventan.ArmoredPets.API;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.NewPetProfile;
import me.ventan.ArmoredPets.utils.PetType;
import org.bukkit.entity.Player;

public class ArmoredPetsApi {
    public void savePetofPlayer(Player player){
        FileManager.savePlayer(player);
    }
    public void loadPetofPlayer(Player player){
        FileManager.readPlayer(player);
    }
    public NewPetProfile getPetOfOnlinePlayer(Player player){
        if(MainArmoredPets.getInstance().playerHasPet(player)){
            return MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        }
        else
            return null;
    }
    public void addLuckToPet(NewPetProfile profile, float luck){
        profile.addLuck(luck);
    }
    public void addAtackToPet(NewPetProfile profile, float attack){
        profile.addLuck(attack);
    }
    public void addDefenceToPet(NewPetProfile profile, float defence){
        profile.addDefence(defence);
    }
    public void addExpToPet(NewPetProfile profile, int ammount){
        profile.addExp(ammount);
    }
    public void addPetToPlayerEq(Player player, String Type){
        NewPetProfile pet=null;
        switch (Type.toUpperCase()){
            case "LIS":
                pet=new NewPetProfile(PetType.LIS);
                break;
            case "KURCZAK":
                pet=new NewPetProfile(PetType.KURCZAK);
                break;
            case "MROWKA":
                pet=new NewPetProfile(PetType.MROWKA);
                break;
            case "PTASZEK":
                pet=new NewPetProfile(PetType.PTASZEK);
                break;
            case "PSZCZOLKA":
                pet=new NewPetProfile(PetType.PSZCZOLKA);
                break;
            case "SLIMACZEK":
                pet=new NewPetProfile(PetType.SLIMACZEK);
                break;
        }
        player.getInventory().addItem(pet.getItem());
        //z profilu wygenerować item który dodam do eq;
    }
}
