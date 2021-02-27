package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.MainArmoredPets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakNickHide implements Listener {
    @EventHandler
    public void onShift(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        if(MainArmoredPets.getInstance().playerHasPet(player)){
            if(event.isSneaking()){
                MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getArmorstand().setCustomNameVisible(false);
            }
            else{
                MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getArmorstand().setCustomNameVisible(true);
            }
        }
    }
}
