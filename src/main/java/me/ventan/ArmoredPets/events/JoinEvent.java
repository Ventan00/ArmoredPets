package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.utils.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        new BukkitRunnable(){

            @Override
            public void run() {
                FileManager.readPlayer(player);
            }
        }.runTaskLater(MainArmoredPets.getInstance(),20);

    }
}
