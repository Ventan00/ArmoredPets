package me.ventan.ArmoredPets.events;

import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyLvlExp;
import me.ventan.ArmoredPets.utils.FileManager;
import me.ventan.ArmoredPets.utils.PetProfile;
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

import static me.ventan.ArmoredPets.utils.PetProfile.petType.*;


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
                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
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
                if(p.getInventory().getItemInMainHand()!=null && p.getInventory().getItemInMainHand().getItemMeta()!=null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()!=null) {
                    if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE+"Kamień poziomu peta")){
                        addLvlToPet(p);
                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
                    }
                }
            }
            else if(event.getSlot()==15){
                if(p.getInventory().getItemInMainHand()!=null && p.getInventory().getItemInMainHand().getItemMeta()!=null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()!=null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE+"Kamień ulepszenia peta")) {
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
                PETSTATS.setItemMeta(meta);
                PETSTATS.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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
            PetProfile pet = new PetProfile(KURCZAK,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE + "Rzadkosc: " + ChatColor.GRAY + " pospolity";
            String WymaganyPoziom = ChatColor.WHITE + "Wymagany poziom: " + ChatColor.GREEN + "10";
            String LVL = ChatColor.WHITE + "Lvl: " + ChatColor.GREEN + pet.getLVL();
            String EXP = ChatColor.WHITE + "Exp: " + ChatColor.GREEN + generateExp(pet.getExp()) + ChatColor.WHITE + "/" + ChatColor.GREEN + generateMaxEXPForLevel(pet.getLVL());
            String Luck = ChatColor.GREEN + "Szczescie: " + pet.getLuck()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc, WymaganyPoziom, EXP, LVL, ChatColor.WHITE + "Bonusy:", Luck,updates));
            player.getInventory().addItem(head);
        }else if(chance>=500&&chance<750){
            //pszczolka
            PetProfile pet = new PetProfile(PSZCZOLKA,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE+pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.DARK_PURPLE +" rzadki";
            String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"25";
            String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
            String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
            String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,updates));
            player.getInventory().addItem(head);
        }
        else if(chance>=750&&chance<875){
            //ptaszek
            PetProfile pet = new PetProfile(PTASZEK,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.BLUE+pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.LIGHT_PURPLE +" Nietypowy";
            String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"30";
            String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
            String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
            String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
            String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Attack,Obrona,updates));
            player.getInventory().addItem(head);
        }
        else if(chance>=875&&chance<940){
            //lis
            PetProfile pet = new PetProfile(LIS,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.RED+pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.BLUE +" Mityczny";
            String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"50";
            String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
            String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
            String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
            String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
            String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,updates));
            player.getInventory().addItem(head);
        }
        else if(chance>=940&&chance<980){
            //mrowka
            PetProfile pet = new PetProfile(MROWKA,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD+pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.GOLD +" Boski";
            String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"0";
            String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
            String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
            String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
            String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
            String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
            String DropChance = ChatColor.GOLD+"Drop skrzyni: "+pet.getDrop()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates));
            player.getInventory().addItem(head);
        }
        else if(chance>=980&&chance<1000){
            //slimaczek
            PetProfile pet = new PetProfile(SLIMACZEK,null,null);
            ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));
            ItemMeta itemMeta = head.getItemMeta();
            itemMeta.setDisplayName(ChatColor.DARK_PURPLE+pet.getType().toString());
            head.setItemMeta(itemMeta);
            String ID = ChatColor.DARK_GRAY+"ID: " + pet.getID();
            String Rzadkosc = ChatColor.WHITE+"Rzadkosc: "+ChatColor.YELLOW +" Legendarny";
            String WymaganyPoziom = ChatColor.WHITE+"Wymagany poziom: "+ChatColor.GREEN+"0";
            String LVL = ChatColor.WHITE+"Lvl: "+ChatColor.GREEN+pet.getLVL();
            String EXP = ChatColor.WHITE+"Exp: "+ChatColor.GREEN+generateExp(pet.getExp())+ChatColor.WHITE+"/"+ChatColor.GREEN+generateMaxEXPForLevel(pet.getLVL());
            String Attack = ChatColor.RED+"Atak: "+pet.getAttack()+"%";
            String Obrona = ChatColor.BLUE+"Obrona: "+pet.getDefence()+"%";
            String Luck = ChatColor.GREEN+"Szczescie: "+pet.getLuck()+"%";
            String DropChance = ChatColor.GOLD+"Drop skrzyni: "+pet.getDrop()+"%";
            String updates= ChatColor.DARK_GRAY+"Ulepszenia: "+pet.getUpdates()+"/3";
            head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates));
            player.getInventory().addItem(head);
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
        PetProfile profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        if(profile.addUpdates()){
            Random random =  new Random();
            int chance = random.nextInt(100);
            if(chance<25){
                //attack
                profile.addAttack(1);
                player.sendMessage(ChatColor.BLUE+"Dodano +1 do ataku peta");
            }
            else if(chance>=25&&chance<50){
                //defence
                profile.addDefence(1);
                player.sendMessage(ChatColor.BLUE+"Dodano +1 do obrony peta");
            }
            else if(chance>=50&&chance<75){
                //luck
                profile.addLuck(2);
                player.sendMessage(ChatColor.BLUE+"Dodano +2 do szczęścia peta");
            }
            else if(chance>=75&&chance<100){
                //drop
                profile.addDrop(0.5f);
                player.sendMessage(ChatColor.BLUE+"Dodano +0.5 do szansy na drop skrzyni peta");
            }
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        }
        else
            player.sendMessage(ChatColor.RED+"Pet może mieć max 3 ulepszenia!");

    }

    private void addLvlToPet(Player p) {
        PetProfile profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(p);
        profile.addExp(MyLvlExp.instance.getPd(profile.getLVL()+9));
        for(int i=0;i<10&&profile.getLVL()!=105;i++)
            profile.addLvl();
        //change display name
        ArmorStand instance = profile.getInstance();
        switch (profile.getType()){
            case KURCZAK:
                instance.setCustomName(ChatColor.YELLOW+"Kurczak"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
            case PSZCZOLKA:
                instance.setCustomName(ChatColor.LIGHT_PURPLE+"Pszczolka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
            case SLIMACZEK:
                instance.setCustomName(ChatColor.DARK_PURPLE+"Slimaczek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
            case LIS:
                instance.setCustomName(ChatColor.RED+"Lis"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
            case MROWKA:
                instance.setCustomName(ChatColor.GOLD+"Mrowka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
            case PTASZEK:
                instance.setCustomName(ChatColor.BLUE+"Ptaszek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+profile.getLVL()+ChatColor.WHITE+")");
                break;
        }
    }

    private void destroypet(Player player) {
        PetProfile pet = MainArmoredPets.getInstance().getProfileOfPlayersPet(player);
        pet.interupt();
        pet.getInstance().remove();
        MainArmoredPets.getInstance().removePetFromPlayer(player);
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
        meta.setDisplayName(ChatColor.DARK_GREEN+"Zwiększ lvl peta o 10 poziomów!");
        meta.setLore(Arrays.asList(ChatColor.GREEN+"Bierze z ekwipunku "+ChatColor.LIGHT_PURPLE+"Kamień poziomu peta",ChatColor.GREEN+"i dodaje 10 poziomów petowi",ChatColor.DARK_RED+"Maksymalny poziom peta to 105"));
        expBottle.setItemMeta(meta);

        ItemStack Kamien_ulepszenia = new ItemStack(Material.NETHER_STAR);
        Kamien_ulepszenia.addUnsafeEnchantment(Enchantment.DURABILITY,1);
        meta=expBottle.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Dodaj losową statystykę");
        meta.setLore(Arrays.asList(ChatColor.YELLOW+"Jeśli masz "+ChatColor.BLUE+"Kamień ulepszenia peta", ChatColor.YELLOW+"i jeśli masz $100k,",ChatColor.YELLOW+"to możesz dodać losową statysytkę swojemu petowi"));
        Kamien_ulepszenia.setItemMeta(meta);
        Kamien_ulepszenia.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        menu_items[11] = destroy;
        menu_items[13] = expBottle;
        menu_items[15] = Kamien_ulepszenia;

        gui2.setContents(menu_items);
        player.openInventory(gui2);

    }

    public String generateMaxEXPForLevel(int LVL){
        StringBuilder output = new StringBuilder();
        String longL= Long.toString(MyLvlExp.instance.getPd(LVL));
        int length = longL.length();
        if(length>5) {
            if ((length + 1) % 3 == 1) {
                output.append(longL.charAt(0));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else if ((length + 1) % 3 == 2) {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                output.append(longL.charAt(2));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            }
            output.append('k');
            return output.toString();
        }
        else{
            return longL;
        }
    }
    public String generateExp(long EXP){
        StringBuilder output = new StringBuilder();
        String longL = String.valueOf(EXP);
        int length = longL.length();
        if(length>5) {
            if ((length + 1) % 3 == 1) {
                output.append(longL.charAt(0));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else if ((length + 1) % 3 == 2) {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            } else {
                output.append(longL.charAt(0));
                output.append(longL.charAt(1));
                output.append(longL.charAt(2));
                if (longL.length() > 6)
                    output.append('k');
                if (longL.length() > 9)
                    output.append('k');
            }
            output.append('k');
            return output.toString();
        }
        else{
            return longL;
        }
    }
}
