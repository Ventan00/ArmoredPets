package me.ventan.ArmoredPets.commands;

import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandSpawnHelpersTEMP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args[0].equalsIgnoreCase("LVL"))
        {
            Player player = (Player) commandSender;
            ItemStack LVLSTONE = new ItemStack(Material.INK_SACK, 1 ,(short) 12);
            ItemMeta meta = LVLSTONE.getItemMeta();
            meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Kamień poziomu peta");
            meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+"Pozwala dodać poziom Twojemu petowi u zoologa"));
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            LVLSTONE.setItemMeta(meta);
            player.getInventory().addItem(LVLSTONE);
        }
        else if(args[0].equalsIgnoreCase("niezidentyfikowany")){
            Player player = (Player) commandSender;
            ItemStack unindentified = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhlNjUyNTM4NWY3NDgzMjJlZWNjNDE2ODliNjkwYWExZWJiYjIxNzJlYzJjNGEyZWFjMTRmMjJkOTRlZTNiYSJ9fX0=");
            ItemMeta meta = unindentified.getItemMeta();
            meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Niezidentyfikowany zwierzak");
            meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+"Możesz zidentyfikować go u zoologa"));
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            unindentified.setItemMeta(meta);
            player.getInventory().addItem(unindentified);
        }
        else{
            Player player = (Player) commandSender;
            ItemStack PETSTATS = new ItemStack(Material.NETHER_STAR);
            PETSTATS.addUnsafeEnchantment(Enchantment.DURABILITY,1);
            ItemMeta meta = PETSTATS.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE+"Kamień ulepszenia peta");
            meta.setLore(Arrays.asList(ChatColor.BLUE+"Pozwala dodać losową statystykę do Twojego peta",ChatColor.BLUE+"Możesz użyć tego przedmiotu u zoologa"));
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PETSTATS.setItemMeta(meta);
            player.getInventory().addItem(PETSTATS);
        }
        return false;
    }
}
