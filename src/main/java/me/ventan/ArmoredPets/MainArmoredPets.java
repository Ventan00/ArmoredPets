package me.ventan.ArmoredPets;

import me.ventan.ArmoredPets.API.PetPlaceholders;
import me.ventan.ArmoredPets.commands.*;
import me.ventan.ArmoredPets.events.*;
import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.NPCs.API.NPCsAPI;
import me.ventan.NPCs.utils.NPCProfile;
import me.ventan.NPCs.utils.NPCs.NPCsNPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainArmoredPets extends JavaPlugin {
    private static MainArmoredPets instance;
    private HashMap<Player, PetProfile> petsOfPlayers;
    private List<NPCProfile> petManagers;

    @Override
    public void onEnable(){
        File file = new File("plugins/ArmoredPets");
        File file2 = new File("plugins/ArmoredPets/players");
        File file3 = new File("plugins/ArmoredPets/lastID.yml");
        File file4 = new File("plugins/ArmoredPets/petManagers.json");
        if(!file.exists()){
            file.mkdir();
        }
        if(!file2.exists()){
            file2.mkdir();
        }
        if(!file3.exists()){
            try {
                file3.createNewFile();
                FileManager.SaveLastID(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!file4.exists()){
            try {
                file4.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getLogger().info("Rozpoczynam ładowanie pluginu...");
        getCommand("spawnPet").setExecutor(new CommandSpawnPet());
        getCommand("despawnPet").setExecutor(new CommandDespawnPet());
        getCommand("zalozPet").setExecutor(new CommandZalozPet());
        getCommand("spawnPetNPC").setExecutor(new CommandSpawnNPC());
        getCommand("removePetNPC").setExecutor(new CommandDespawnNPC());

        getCommand("spawnNpcHelper").setExecutor(new CommandSpawnHelpersTEMP());

        getServer().getPluginManager().registerEvents(new InteractWithArmorstand(),this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new SneakNickHide(), this);
        getServer().getPluginManager().registerEvents(new PutOnPetShiftClick(), this);
        getServer().getPluginManager().registerEvents(new InteractWithManager(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        getCommand("spawnPet").setTabCompleter(new TabCompleterSpawnPet());

        instance=this;
        petsOfPlayers=new HashMap<>();
        petManagers=new ArrayList<>();
        getServer().getOnlinePlayers().forEach(player -> FileManager.readPlayer(player));
        FileManager.LoadLastID();
        FileManager.readNPCs();
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new PetPlaceholders().register();
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Wylaczanie");
        FileManager.SaveLastID(PetProfile.LastID);
        getServer().getOnlinePlayers().forEach(player ->
        {
            if(playerHasPet(player))
            {
                FileManager.savePlayer(player);
                PetProfile pet = getProfileOfPlayersPet(player);
                pet.getInstance().remove();
                pet.interupt();
                MainArmoredPets.getInstance().removePetFromPlayer(player);}

        });
        petsOfPlayers=null;
        petManagers=null;
    }
    public static MainArmoredPets getInstance(){
        return instance;
    }
    public void addPetToPlayer(Player player, PetProfile petProfile){
        petsOfPlayers.put(player,petProfile);
    }
    public PetProfile getProfileOfPlayersPet(Player player){
        return petsOfPlayers.get(player);
    }
    public boolean playerHasPet(Player player){return petsOfPlayers.containsKey(player);}
    public void removePetFromPlayer(Player player){
        petsOfPlayers.remove(player);
    }
    public List<NPCProfile> getNPCs(){return petManagers;}
    public void addNPCManager(NPCProfile entity){
        petManagers.add(entity);
    }
}
