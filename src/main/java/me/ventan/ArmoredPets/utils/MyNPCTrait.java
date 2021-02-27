package me.ventan.ArmoredPets.utils;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
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
    public void click(net.citizensnpcs.api.event.NPCRightClickEvent event){
        if(event.getNPC()==this.getNPC()){
            Player player = event.getClicker();
            Inventory gui  = Bukkit.createInventory(player,9, ChatColor.DARK_PURPLE+"Zidentyfikuj zwierzaka");

            ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 15);
            ItemStack update;
            ItemStack Pet;
            ItemStack zdejmij;

            //ustawienie identyfikacji peta

            if (player.getInventory().getItemInMainHand().getLore()==null)
            {
                Pet=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZjNjBkYTQxNGJmMDM3MTU5YzhiZThkMDlhOGVjYjkxOWJmODlhMWEyMTUwMWI1YjJlYTc1OTYzOTE4YjdiIn19fQ==");
                ItemMeta meta =Pet.getItemMeta();
                meta.setDisplayName(ChatColor.DARK_RED+"Brak peta");
                meta.setLore(Arrays.asList(ChatColor.RED+"To nie jest "+ChatColor.LIGHT_PURPLE+"Niezidentyfikowany zwierzak",ChatColor.RED+"By zidentyfikować zwierzaka, musisz trzymać go w ręce"));
                Pet.setItemMeta(meta);
            }
            else if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Niezidentyfikowany zwierzak"))
            {
                Pet=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGE5OTM0MmUyYzczYTlmMzgyMjYyOGU3OTY0ODgyMzRmMjU4NDQ2ZjVhMmQ0ZDU5ZGRlNGFhODdkYjk4In19fQ==");
                ItemMeta petMeta = Pet.getItemMeta();
                petMeta.setDisplayName(ChatColor.GREEN+"Zidentyfikuj!");
                Pet.setItemMeta(petMeta);
            }
            else{
                Pet=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZjNjBkYTQxNGJmMDM3MTU5YzhiZThkMDlhOGVjYjkxOWJmODlhMWEyMTUwMWI1YjJlYTc1OTYzOTE4YjdiIn19fQ==");
                ItemMeta meta =Pet.getItemMeta();
                meta.setDisplayName(ChatColor.DARK_RED+"Brak peta");
                meta.setLore(Arrays.asList(ChatColor.RED+"To nie jest "+ChatColor.LIGHT_PURPLE+"Niezidentyfikowany zwierzak",ChatColor.RED+"By zidentyfikować zwierzaka, musisz trzymać go w ręce"));
                Pet.setItemMeta(meta);
            }

            //ustawienie ulepszenia peta
            if(MainArmoredPets.getInstance().playerHasPet(player)){
                update = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjJmOTY5OGMzZjE4NmZlNDRjYzYzZDJmM2M0ZjlhMjQxMjIzYWNmMDU4MTc3NWQ5Y2VjZDcwNzUifX19");
                ItemMeta updateMeta = update.getItemMeta();
                updateMeta.setDisplayName(ChatColor.YELLOW+"Ulepsz peta!");
                updateMeta.setLore(Arrays.asList(ChatColor.YELLOW+"Jeśli masz "+ChatColor.LIGHT_PURPLE+"Kamień poziomu peta", ChatColor.YELLOW+"lub "+ChatColor.BLUE+"Kamień ulepszenia peta",ChatColor.YELLOW+"to możesz tutuaj go użyć"));
                update.setItemMeta(updateMeta);

                //dopisać generowanie czaski
                zdejmij = getPetOfPlayer(player);
            }
            else{
                update=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=");
                ItemMeta updateMeta = update.getItemMeta();
                updateMeta.setDisplayName(ChatColor.RED+"Nie posiadasz założonego peta!");
                updateMeta.setLore(Arrays.asList(ChatColor.RED+"Jeśli chcesz wzmocnić peta",ChatColor.RED+"to musisz go najpierw założyć"));
                update.setItemMeta(updateMeta);

                zdejmij = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhkMjkwMTMzNzM2MWUxOTFkYTMzNTQzYTI5MjdkZmEwMTFmNWViNjk0ZTMxZDUwMWJjY2ExNTI4MWQ0YSJ9fX0");
                updateMeta = zdejmij.getItemMeta();
                updateMeta.setDisplayName(ChatColor.DARK_GRAY+"Nie masz założonego peta!");
                zdejmij.setItemMeta(updateMeta);
            }


            ItemMeta meta = nothing.getItemMeta();
            meta.setDisplayName(ChatColor.BLACK+" ");
            meta.setLore(Arrays.asList(ChatColor.BLACK+" "));
            nothing.setItemMeta(meta);


            ItemStack[] menu_items =  {nothing,nothing,Pet,nothing,update,nothing,zdejmij,nothing,nothing};
            gui.setContents(menu_items);
            player.openInventory(gui);
        }
    }
    private ItemStack getPetOfPlayer(Player player) {
        return MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getItem();
    }
}
