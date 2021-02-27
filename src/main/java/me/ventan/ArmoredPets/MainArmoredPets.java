package me.ventan.ArmoredPets;

import me.ventan.ArmoredPets.API.PetPlaceholders;
import me.ventan.ArmoredPets.commands.*;
import me.ventan.ArmoredPets.events.*;
import me.ventan.ArmoredPets.utils.MyNPCTrait;
import me.ventan.ArmoredPets.utils.NewPetProfile;
import me.ventan.ArmoredPets.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class MainArmoredPets extends JavaPlugin {
    private static MainArmoredPets instance;
    private HashMap<Player, NewPetProfile> petsOfPlayers;

    @Override
    public void onEnable(){
        net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(MyNPCTrait.class).withName("petTrait"));
        File file = new File("plugins/ArmoredPets");
        File file2 = new File("plugins/ArmoredPets/players");
        File file3 = new File("plugins/ArmoredPets/lastID.yml");
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
        getLogger().info("Rozpoczynam ładowanie pluginu...");
        getCommand("spawnPet").setExecutor(new CommandSpawnPet());
        getCommand("despawnPet").setExecutor(new CommandDespawnPet());
        getCommand("zalozPet").setExecutor(new CommandZalozPet());
        getCommand("spawnPetNPC").setExecutor(new CommandSpawnNPC());

        getCommand("spawnNpcHelper").setExecutor(new CommandSpawnHelpersTEMP());

        getServer().getPluginManager().registerEvents(new InteractWithArmorstand(),this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new SneakNickHide(), this);
        getServer().getPluginManager().registerEvents(new PutOnPetShiftClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        getCommand("spawnPet").setTabCompleter(new TabCompleterSpawnPet());

        instance=this;
        petsOfPlayers=new HashMap<>();
        getServer().getOnlinePlayers().forEach(player -> FileManager.readPlayer(player));
        FileManager.LoadLastID();
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new PetPlaceholders().register();
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Wylaczanie");
        FileManager.SaveLastID(NewPetProfile.LastID);
        getServer().getOnlinePlayers().forEach(player ->
        {
            if(playerHasPet(player))
            {
                FileManager.savePlayer(player);
                NewPetProfile pet = getProfileOfPlayersPet(player);
                pet.despawn();
            }

        });
        petsOfPlayers=null;
    }
    public static MainArmoredPets getInstance(){
        return instance;
    }
    public void addPetToPlayer(Player player, NewPetProfile petProfile){
        petsOfPlayers.put(player,petProfile);
    }
    public NewPetProfile getProfileOfPlayersPet(Player player){
        return petsOfPlayers.get(player);
    }
    public boolean petIsSpanwed(NewPetProfile profile){
        return petsOfPlayers.containsValue(profile);
    }
    public Player getPetOwner(NewPetProfile profile){
        return petsOfPlayers.entrySet()
                .stream()
                .filter(entry -> entry.getValue()==profile)
                .findFirst()
                .get()
                .getKey();
    }
    public boolean playerHasPet(Player player){return petsOfPlayers.containsKey(player);}
    public void removePetFromPlayer(Player player){
        petsOfPlayers.remove(player);
    }
}
