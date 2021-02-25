package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.utils.PetProfile;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        FileManager.savePlayer(player);
        if(!MainArmoredPets.getInstance().playerHasPet(player))
            return;
        PetProfile pet = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        pet.getInstance().remove();
        pet.interupt();
        MainArmoredPets.getInstance().removePetFromPlayer(player);
    }
}
