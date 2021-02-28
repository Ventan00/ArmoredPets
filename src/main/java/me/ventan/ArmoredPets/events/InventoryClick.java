package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.NewPetProfile;
import me.ventan.ArmoredPets.utils.skullCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;

import static me.ventan.ArmoredPets.utils.PetType.*;


//gui manager
public class InventoryClick implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv =event.getInventory();
        Player p = (Player)event.getWhoClicked();
        if(inv.getTitle().compareTo(ChatColor.DARK_PURPLE+"Zidentyfikuj zwierzaka")==0){
            if(event.getSlot()!=-999){
                if(event.getCurrentItem().getItemMeta()!=null){
                    if(event.getCurrentItem().getItemMeta().getDisplayName()!=null){
                        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Zidentyfikuj!")){
                            getRandomPet(p);
                            if(p.getInventory().getItemInHand().getAmount()==1){
                                p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                            }else {
                                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
                            }
                            p.closeInventory();
                        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW+"Ulepsz peta!")){
                            openUpgradeMenu(p);
                        }
                        else if(event.getSlot()==6){
                            p.performCommand("despawnpet");
                            p.closeInventory();
                        }
                    }
                }
            }
            event.setCancelled(true);
        } else if(inv.getTitle().compareTo(ChatColor.DARK_PURPLE+"Ulepsz zwierzaka")==0){
            if(event.getSlot()==11) {
                potwierdzenie(p);
            }
            else if(event.getSlot()==13){
                if(p.getInventory().getItemInHand()!=null && p.getInventory().getItemInHand().getItemMeta()!=null && p.getInventory().getItemInHand().getItemMeta().getDisplayName()!=null) {
                    if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Kamień poziomu peta")){
                        addLvlToPet(p);
                        if(p.getInventory().getItemInHand().getAmount()==1){
                            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                        }else {
                            p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
                        }

                    }
                }
            }
            else if(event.getSlot()==15){
                if(p.getInventory().getItemInHand()!=null && p.getInventory().getItemInHand().getItemMeta()!=null && p.getInventory().getItemInHand().getItemMeta().getDisplayName()!=null && p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE+"Kamień ulepszenia peta")) {
                    addRandomAbilityToPet(p);
                }else
                    p.sendMessage(ChatColor.RED+"Musisz trzymać "+ChatColor.BLUE+"Kamień ulepszenia peta "+ChatColor.RED+"w ręce");
            }
            event.setCancelled(true);
        } else if(inv.getTitle().compareTo(ChatColor.DARK_PURPLE+"Potwierdź usunięcie peta")==0){
            if(event.getSlot()==3){
                 destroypet(p);
                ItemStack PETSTATS = new ItemStack(Material.NETHER_STAR);
                PETSTATS.addUnsafeEnchantment(Enchantment.DURABILITY,1);
                ItemMeta meta = PETSTATS.getItemMeta();
                meta.setDisplayName(ChatColor.BLUE+"Kamień ulepszenia peta");
                meta.setLore(Arrays.asList(ChatColor.BLUE+"Pozwala dodać losową statystykę do Twojego peta",ChatColor.BLUE+"Możesz użyć tego przedmiotu u zoologa"));
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                PETSTATS.setItemMeta(meta);
                p.getInventory().addItem(PETSTATS);
                p.closeInventory();
            }
            else
                openUpgradeMenu(p);
            event.setCancelled(true);
        }
    }

    private void getRandomPet(Player player) {
        Random random = new Random();
        int chance = random.nextInt(1000);
        if(chance<500){
            //kurczak
            NewPetProfile pet = new NewPetProfile(KURCZAK);
            player.getInventory().addItem(pet.getItem());
        }else if(chance < 750){
            //pszczolka
            NewPetProfile pet = new NewPetProfile(PSZCZOLKA);
            player.getInventory().addItem(pet.getItem());
        }
        else if(chance < 875){
            NewPetProfile pet = new NewPetProfile(PTASZEK);
            player.getInventory().addItem(pet.getItem());
        }
        else if(chance < 940){
            //lis
            NewPetProfile pet = new NewPetProfile(LIS);
            player.getInventory().addItem(pet.getItem());
        }
        else if(chance < 980){
            NewPetProfile pet = new NewPetProfile(MROWKA);
            player.getInventory().addItem(pet.getItem());
        }
        else {
            NewPetProfile pet = new NewPetProfile(SLIMACZEK);
            player.getInventory().addItem(pet.getItem());
        }
    }

    private void potwierdzenie(Player player) {
        Inventory gui3 = Bukkit.createInventory(player,9, ChatColor.DARK_PURPLE+"Potwierdź usunięcie peta");
        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 15);
        ItemMeta meta = nothing.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK+" ");
        meta.setLore(Arrays.asList(ChatColor.BLACK+" "));
        nothing.setItemMeta(meta);

        ItemStack accept=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGE5OTM0MmUyYzczYTlmMzgyMjYyOGU3OTY0ODgyMzRmMjU4NDQ2ZjVhMmQ0ZDU5ZGRlNGFhODdkYjk4In19fQ==");
        meta=accept.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+"Akceptuj!");
        accept.setItemMeta(meta);

        ItemStack decline=skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZjNjBkYTQxNGJmMDM3MTU5YzhiZThkMDlhOGVjYjkxOWJmODlhMWEyMTUwMWI1YjJlYTc1OTYzOTE4YjdiIn19fQ==");
        meta=decline.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED+"Anuluj!");
        decline.setItemMeta(meta);

        ItemStack[] menu_items =  {nothing,nothing,nothing,accept,nothing,decline,nothing,nothing,nothing};
        gui3.setContents(menu_items);
        player.openInventory(gui3);
    }

    private void addRandomAbilityToPet(Player player) {
        NewPetProfile profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        if(profile.addUpdates()){
            Random random =  new Random();
            int chance = random.nextInt(100);
            if(chance<25){
                //attack
                profile.addAttack(1);
                player.sendMessage(ChatColor.BLUE+"Dodano +1 do ataku peta");
            }
            else if(chance<50){
                //defence
                profile.addDefence(1);
                player.sendMessage(ChatColor.BLUE+"Dodano +1 do obrony peta");
            }
            else if(chance<75){
                //luck
                profile.addLuck(2);
                player.sendMessage(ChatColor.BLUE+"Dodano +2 do szczęścia peta");
            }
            else if(chance<100){
                //drop
                profile.addDrop(0.5f);
                player.sendMessage(ChatColor.BLUE+"Dodano +0.5 do szansy na drop skrzyni peta");
            }
            player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
        }
        else
            player.sendMessage(ChatColor.RED+"Pet może mieć max 3 ulepszenia!");

    }

    private void addLvlToPet(Player p) {
        NewPetProfile profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(p);
        profile.addExp(MyLvlExp.instance.getPd(profile.getLVL()+1)-MyLvlExp.instance.getPd(profile.getLVL())+1);
    }

    private void destroypet(Player player) {
        NewPetProfile pet = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        pet.despawn();
        FileManager.deletePlayer(player);
    }

    private void openUpgradeMenu(Player player) {
        Inventory gui2 = Bukkit.createInventory(player,27, ChatColor.DARK_PURPLE+"Ulepsz zwierzaka");
        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 15);
        ItemMeta meta = nothing.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK+" ");
        meta.setLore(Arrays.asList(ChatColor.BLACK+" "));
        nothing.setItemMeta(meta);

        ItemStack[] menu_items = new ItemStack[27];
        for(int i=0;i<27;i++){
            menu_items[i]=nothing;
        }

        ItemStack destroy = skullCreator.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA4MGJiZWZjYTg3ZGMwZjM2NTM2YjY1MDg0MjVjZmM0Yjk1YmE2ZThmNWU2YTQ2ZmY5ZTljYjQ4OGE5ZWQifX19");
        meta=destroy.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED+"Zamień na "+ChatColor.BLUE+"Kamień ulepszenia peta");
        meta.setLore(Arrays.asList(ChatColor.RED+"Zniszcz peta by dostać item, który pozwoli dodać",ChatColor.RED+"nową losową statystykę innemu petowi"));
        destroy.setItemMeta(meta);

        ItemStack expBottle = new ItemStack(Material.EXP_BOTTLE);
        meta=expBottle.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN+"Zwiększ lvl peta o 1 poziom!");
        meta.setLore(Arrays.asList(ChatColor.GREEN+"Bierze z ekwipunku "+ChatColor.LIGHT_PURPLE+"Kamień poziomu peta",ChatColor.GREEN+"i dodaje poziom petowi",ChatColor.DARK_RED+"Maksymalny poziom peta to 105"));
        expBottle.setItemMeta(meta);

        ItemStack Kamien_ulepszenia = new ItemStack(Material.NETHER_STAR);
        Kamien_ulepszenia.addUnsafeEnchantment(Enchantment.DURABILITY,1);
        meta=expBottle.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Dodaj losową statystykę");
        meta.setLore(Arrays.asList(ChatColor.YELLOW+"Jeśli masz "+ChatColor.BLUE+"Kamień ulepszenia peta", ChatColor.YELLOW+"i jeśli masz $100k,",ChatColor.YELLOW+"to możesz dodać losową statysytkę swojemu petowi"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Kamien_ulepszenia.setItemMeta(meta);


        menu_items[11] = destroy;
        menu_items[13] = expBottle;
        menu_items[15] = Kamien_ulepszenia;

        gui2.setContents(menu_items);
        player.openInventory(gui2);

    }

}
