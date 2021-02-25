package me.ventan.ArmoredPets.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class InteractWithArmorstand implements Listener {
    @EventHandler
    public void interactedWithArmorstand(PlayerArmorStandManipulateEvent event){
        event.setCancelled(true);
    }
}
