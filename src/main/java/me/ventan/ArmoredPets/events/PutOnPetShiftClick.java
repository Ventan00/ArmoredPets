package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.utils.PetType;
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
            ItemStack item = event.getPlayer().getInventory().getItemInHand();
            if(item.getItemMeta()!=null) {
                if(item.getItemMeta().getDisplayName()!=null){
                    PetType type = PetType.typeOrNull(item.getItemMeta().getDisplayName().substring(2));
                    if (player.isSneaking() && type != null) {
                        player.performCommand("zalozpet");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
