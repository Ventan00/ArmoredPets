package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.utils.PetProfile;
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
            podpowiedzi.add(PetProfile.petType.KURCZAK.toString());
            podpowiedzi.add(PetProfile.petType.PSZCZOLKA.toString());
            podpowiedzi.add(PetProfile.petType.SLIMACZEK.toString());
            podpowiedzi.add(PetProfile.petType.LIS.toString());
            podpowiedzi.add(PetProfile.petType.MROWKA.toString());
            podpowiedzi.add(PetProfile.petType.PTASZEK.toString());
        } else {
            podpowiedzi=null;
        }
        return podpowiedzi;
    }
}
