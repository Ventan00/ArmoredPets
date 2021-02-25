package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.utils.MyNPCTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class CommandSpawnNPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            if(validate(args))
            {
                Player player = (Player) commandSender;
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, args[0]);
                npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, args[1]);
                npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
                npc.addTrait(new MyNPCTrait());
                npc.spawn(player.getLocation());
            }else{
                commandSender.sendMessage(ChatColor.RED+"poprawne użycie: /spawnpetnpc <nick> <nick skina> ");
            }
        }
        return true;
    }

    private boolean validate(String[] args) {
        return args.length == 2;
    }
}