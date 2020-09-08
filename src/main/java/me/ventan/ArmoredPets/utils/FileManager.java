package me.ventan.ArmoredPets.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.ventan.ArmoredPets.MainArmoredPets;
import me.ventan.ArmoredPets.Math.MyMath;
import me.ventan.NPCs.API.NPCsAPI;
import me.ventan.NPCs.MainNPCs;
import me.ventan.NPCs.utils.NPCs.NPCsNPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONArray;

import java.io.*;

public class FileManager {
    public static boolean readPlayer(Player player) {
        File file = new File("plugins/ArmoredPets/players/"+player.getUniqueId().toString()+".json");
        if(!file.exists())
            return false;
        JsonParser parser = new JsonParser();
        JsonElement profile = null;
        try {
            profile = parser.parse(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ID = profile.getAsJsonObject().get("id").getAsInt();
        String type = profile.getAsJsonObject().get("type").getAsString();
        long exp = profile.getAsJsonObject().get("exp").getAsLong();
        int LVL = profile.getAsJsonObject().get("lvl").getAsInt();
        float luck = profile.getAsJsonObject().get("luck").getAsFloat();
        float attack = profile.getAsJsonObject().get("attack").getAsFloat();
        float defence = profile.getAsJsonObject().get("defence").getAsFloat();
        float drop = profile.getAsJsonObject().get("drop").getAsFloat();
        int updates = profile.getAsJsonObject().get("updates").getAsInt();
        float[] params = calculateComplexParameters(player.getLocation().getYaw());
        Location loc = player.getLocation().add(params[0],0.5,params[1]);
        ArmorStand armorStand = (ArmorStand) loc.getWorld().spawn(loc,ArmorStand.class);
        BukkitTask task = new BukkitRunnable(){
            final float maxval=0.3f;
            final float minval=-0.3f;
            float val=0f;
            float toAdd=0.05f;
            long timestamp=System.currentTimeMillis()+40*1000;

            @Override
            public void run() {
                val+=toAdd;
                if(val>=maxval)
                    toAdd=-0.05f;
                if(val<=minval)
                    toAdd=0.05f;
                float[] parametryToAdd = calculateComplexParameters(player.getLocation().getYaw());
                armorStand.teleport(player.getLocation().add(parametryToAdd[0],1+val,parametryToAdd[1]));
                if(timestamp<=System.currentTimeMillis()){
                    timestamp=System.currentTimeMillis()+40*1000;
                    player.getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_BAT_AMBIENT, 3.0f,1.094f);
                }
            }
        }.runTaskTimer(MainArmoredPets.getInstance(),20,1); //animating moving
        MainArmoredPets.getInstance().addPetToPlayer(player,new PetProfile(ID, type, exp, LVL,luck,attack,defence,drop,updates,armorStand,task));
        ItemStack head = new ItemStack(skullCreator.getSkull(MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getTexture()));
        armorStand.setHelmet(head);
        armorStand.setGravity(false);
        armorStand.setCanMove(true);
        armorStand.setCanPickupItems(false);
        armorStand.setCollidable(false);
        armorStand.setSmall(true);
        switch (type.toLowerCase()){
            case "kurczak":
                armorStand.setCustomName(ChatColor.YELLOW+"Kurczak"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            case "pszczolka":
                armorStand.setCustomName(ChatColor.LIGHT_PURPLE+"Pszczolka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            case "slimaczek":
                armorStand.setCustomName(ChatColor.DARK_PURPLE+"Slimaczek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            case "lis":
                armorStand.setCustomName(ChatColor.RED+"Lis"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            case "mrowka":
                armorStand.setCustomName(ChatColor.GOLD+"Mrowka"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            case "ptaszek":
                armorStand.setCustomName(ChatColor.BLUE+"Ptaszek"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
                break;
            default:
                armorStand.setCustomName(ChatColor.DARK_RED+"OKURWACOSSIEZJEBALO"+ChatColor.WHITE+" ("+ChatColor.AQUA+"Lvl "+MainArmoredPets.getInstance().getProfileOfPlayersPet(player).getLVL()+ChatColor.WHITE+")");
        }
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        return true;
    }
    public static boolean savePlayer(Player player){
        if(!MainArmoredPets.getInstance().playerHasPet(player))
            return false;
        JsonObject profile = MainArmoredPets.getInstance().getProfileOfPlayersPet(player).toJsonObject();
        File file = new File("plugins/ArmoredPets/players/"+player.getUniqueId().toString()+".json");
        if(file.exists())
            file.delete();
        try {
            file.createNewFile();
            BufferedWriter bw =  new BufferedWriter(new FileWriter("plugins/ArmoredPets/players/"+player.getUniqueId().toString()+".json"));
            bw.write(profile.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    public static boolean LoadLastID(){
        File file = new File("plugins/ArmoredPets/lastID.yml");
        if(!file.exists())
            return false;
        try {
            PetProfile.LastID=Integer.parseInt(new BufferedReader(new FileReader(file)).readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean SaveLastID(int ID){
        File file = new File("plugins/ArmoredPets/lastID.yml");
        if(file.exists())
            file.delete();
        try {
            file.createNewFile();
            BufferedWriter bw =  new BufferedWriter(new FileWriter("plugins/ArmoredPets/lastID.yml"));
            bw.write(String.valueOf(ID));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    public static boolean deletePlayer(Player player){
        File file = new File("plugins/ArmoredPets/players/"+player.getUniqueId().toString()+".json");
        file.delete();
        return true;
    }
    private static float[] calculateComplexParameters(float yaw) {
        float[] result = new float[2];
        yaw+=180;
        result[0]= 1.5f* (float) MyMath.getTable().getCos((int) yaw);
        result[1]= 1.5f* (float) MyMath.getTable().getSine((int) yaw);
        return result;
    }

    public static boolean readNPCs() {
        File file = new File("plugins/ArmoredPets/petManagers.json");
        if(!file.exists())
        {
            MainNPCs.getInstance().getLogger().severe("brak petManagers.json");
            return false;
        }
        if(file.length()==0) {
            return true;
        }
        JsonParser parser = new JsonParser();
        JsonElement temp = null;
        try {
            FileReader reader = new FileReader(new File("plugins/ArmoredPets/petManagers.json"));
            StringBuilder sb = new StringBuilder();
            int b;
            while((b=reader.read())!=-1)
            {
                sb.append((char)b);
            }
            reader.close();
            temp = parser.parse(sb.toString());
        }  catch (IOException e) {
            e.printStackTrace();
        }
        JsonArray npcsManagers = temp.getAsJsonArray();
        npcsManagers.forEach(jsonElement -> {
               MainArmoredPets.getInstance().addNPCManager(NPCsAPI.getFromID(jsonElement.getAsInt()));
            });
        return true;
    }
    public static boolean saveNPCs() {
        File file = new File("plugins/ArmoredPets/petManagers.json");
        if(file.exists())
            file.delete();
        try {
            JSONArray array = new JSONArray();
            MainArmoredPets.getInstance().getNPCs().forEach( npc ->{
                array.add(npc.getID());
            });
            file.createNewFile();
            BufferedWriter bw =  new BufferedWriter(new FileWriter("plugins/ArmoredPets/petManagers.json"));
            bw.write(array.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
