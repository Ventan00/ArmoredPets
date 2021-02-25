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
        MainArmoredPets main = MainArmoredPets.getInstance();
        PetProfile pet = main.getProfileOfPlayersPet(player);
        ItemStack head = new ItemStack(skullCreator.getSkull(pet.getTexture()));//giving item to player hand
        switch (pet.getType()){
            case KURCZAK: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc, WymaganyPoziom, EXP, LVL, ChatColor.WHITE + "Bonusy:", Luck,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
            case PSZCZOLKA: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
            case PTASZEK: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Attack,Obrona,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
            case LIS: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
            case MROWKA: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
            case SLIMACZEK: {
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
                head.setLore(Arrays.asList(ID,Rzadkosc,WymaganyPoziom,EXP,LVL,ChatColor.WHITE+"Bonusy:",Luck,Attack,Obrona,DropChance,updates,ChatColor.DARK_RED+""+ChatColor.BOLD+"Zdejmij"));
                break;
            }
        }

        return head;
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
