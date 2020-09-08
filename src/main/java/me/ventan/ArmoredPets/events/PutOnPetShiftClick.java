package me.ventan.ArmoredPets.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PutOnPetShiftClick implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Player player = event.getPlayer();
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if(player.isSneaking() && (item.getI18NDisplayName().equals("§eKURCZAK") || item.getI18NDisplayName().equals("§dPSZCZOLKA") ||item.getI18NDisplayName().equals("§9PTASZEK") ||item.getI18NDisplayName().equals("§cLIS") || item.getI18NDisplayName().equals("§6MROWKA") || item.getI18NDisplayName().equals("§5SLIMACZEK"))){
                player.performCommand("zalozpet");
                event.setCancelled(true);
            }
        }
    }
}
