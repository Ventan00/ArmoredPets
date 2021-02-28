package me.ventan.ArmoredPets.utils;

import me.ventan.ArmoredPets.MainArmoredPets;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MyNPCTrait extends Trait {
    public MyNPCTrait() {
        super("petTrait");
    }
    @EventHandler
    public void click(net.citizensnpcs.api.event.NPCRightClickEvent event) {
            if (event.getNPC() == this.getNPC()) {
                System.out.println("pkt 1");
                Player player = event.getClicker();
                Inventory gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Zidentyfikuj zwierzaka");

                System.out.println("pkt 2");
                ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                ItemStack update;
                ItemStack Pet;
                ItemStack zdejmij;

                //ustawienie identyfikacji peta
                System.out.println("pkt 3");
                if (player.getInventory().getItemInHand().getItemMeta() == null || player.getInventory().getItemInHand().getItemMeta().getLore()==null) {
                    System.out.println("pkt 4");
                    Pet = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZjNjBkYTQxNGJmMDM3MTU5YzhiZThkMDlhOGVjYjkxOWJmODlhMWEyMTUwMWI1YjJlYTc1OTYzOTE4YjdiIn19fQ==");
                    ItemMeta meta = Pet.getItemMeta();
                    meta.setDisplayName(ChatColor.DARK_RED + "Brak peta");
                    meta.setLore(Arrays.asList(ChatColor.RED + "To nie jest " + ChatColor.LIGHT_PURPLE + "Niezidentyfikowany zwierzak", ChatColor.RED + "By zidentyfikować zwierzaka, musisz trzymać go w ręce"));
                    Pet.setItemMeta(meta);
                    System.out.println("pkt 5");
                } else if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Niezidentyfikowany zwierzak")) {
                    System.out.println("pkt 6");
                    Pet = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGE5OTM0MmUyYzczYTlmMzgyMjYyOGU3OTY0ODgyMzRmMjU4NDQ2ZjVhMmQ0ZDU5ZGRlNGFhODdkYjk4In19fQ==");
                    ItemMeta petMeta = Pet.getItemMeta();
                    petMeta.setDisplayName(ChatColor.GREEN + "Zidentyfikuj!");
                    Pet.setItemMeta(petMeta);
                    System.out.println("pkt 7");
                } else {
                    System.out.println("pkt 8");
                    Pet = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZjNjBkYTQxNGJmMDM3MTU5YzhiZThkMDlhOGVjYjkxOWJmODlhMWEyMTUwMWI1YjJlYTc1OTYzOTE4YjdiIn19fQ==");
                    ItemMeta meta = Pet.getItemMeta();
                    meta.setDisplayName(ChatColor.DARK_RED + "Brak peta");
                    meta.setLore(Arrays.asList(ChatColor.RED + "To nie jest " + ChatColor.LIGHT_PURPLE + "Niezidentyfikowany zwierzak", ChatColor.RED + "By zidentyfikować zwierzaka, musisz trzymać go w ręce"));
                    Pet.setItemMeta(meta);
                    System.out.println("pkt 9");
                }

                //ustawienie ulepszenia peta
                System.out.println("pkt 10");
                if (MainArmoredPets.getInstance().playerHasPet(player)) {
                    System.out.println("pkt 11");
                    update = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjJmOTY5OGMzZjE4NmZlNDRjYzYzZDJmM2M0ZjlhMjQxMjIzYWNmMDU4MTc3NWQ5Y2VjZDcwNzUifX19");
                    ItemMeta updateMeta = update.getItemMeta();
                    updateMeta.setDisplayName(ChatColor.YELLOW + "Ulepsz peta!");
                    updateMeta.setLore(Arrays.asList(ChatColor.YELLOW + "Jeśli masz " + ChatColor.LIGHT_PURPLE + "Kamień poziomu peta", ChatColor.YELLOW + "lub " + ChatColor.BLUE + "Kamień ulepszenia peta", ChatColor.YELLOW + "to możesz tutuaj go użyć"));
                    update.setItemMeta(updateMeta);
                    //dopisać generowanie czaski
                    zdejmij = getPetOfPlayer(player);
                    System.out.println("pkt 12");
                } else {
                    System.out.println("pkt 13");
                    update = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=");
                    ItemMeta updateMeta = update.getItemMeta();
                    updateMeta.setDisplayName(ChatColor.RED + "Nie posiadasz założonego peta!");
                    updateMeta.setLore(Arrays.asList(ChatColor.RED + "Jeśli chcesz wzmocnić peta", ChatColor.RED + "to musisz go najpierw założyć"));
                    update.setItemMeta(updateMeta);
                    System.out.println("pkt 14");

                    System.out.println("pkt 15");
                    zdejmij = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhkMjkwMTMzNzM2MWUxOTFkYTMzNTQzYTI5MjdkZmEwMTFmNWViNjk0ZTMxZDUwMWJjY2ExNTI4MWQ0YSJ9fX0");
                    updateMeta = zdejmij.getItemMeta();
                    updateMeta.setDisplayName(ChatColor.DARK_GRAY + "Nie masz założonego peta!");
                    zdejmij.setItemMeta(updateMeta);
                    System.out.println("pkt 16");
                }

                System.out.println("pkt 17");
                ItemMeta meta = nothing.getItemMeta();
                meta.setDisplayName(ChatColor.BLACK + " ");
                meta.setLore(Arrays.asList(ChatColor.BLACK + " "));
                nothing.setItemMeta(meta);
                System.out.println("pkt 18");


                ItemStack[] menu_items = {nothing, nothing, Pet, nothing, update, nothing, zdejmij, nothing, nothing};
                System.out.println("pkt 19");
                gui.setContents(menu_items);
                System.out.println("pkt 20");
                player.openInventory(gui);
                System.out.println("pkt 21");
            }
    }
    private ItemStack getPetOfPlayer(Player player) {
        return MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getItem();
    }
}
