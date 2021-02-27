package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.utils.PetType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompleterSpawnPet implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> podpowiedzi;
        if(args.length==1)
        {
            podpowiedzi = new ArrayList<>();
            podpowiedzi.add(PetType.KURCZAK.toString());
            podpowiedzi.add(PetType.PSZCZOLKA.toString());
            podpowiedzi.add(PetType.SLIMACZEK.toString());
            podpowiedzi.add(PetType.LIS.toString());
            podpowiedzi.add(PetType.MROWKA.toString());
            podpowiedzi.add(PetType.PTASZEK.toString());
        } else {
            podpowiedzi=null;
        }
        return podpowiedzi;
    }
}
