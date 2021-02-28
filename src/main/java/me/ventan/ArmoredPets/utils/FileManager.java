package me.ventan.ArmoredPets.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.ventan.ArmoredPets.MainArmoredPets;
import org.bukkit.entity.Player;

import java.io.*;

public class FileManager {
    // TODO: 26.02.2021 przerobić do newPetProfile
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
        float luck = profile.getAsJsonObject().get("luck").getAsFloat();
        float attack = profile.getAsJsonObject().get("attack").getAsFloat();
        float defence = profile.getAsJsonObject().get("defence").getAsFloat();
        float drop = profile.getAsJsonObject().get("drop").getAsFloat();
        int updates = profile.getAsJsonObject().get("updates").getAsInt();
        NewPetProfile pet = new NewPetProfile(ID,type,exp,luck,attack,defence,drop,updates);
        pet.spawn(player);

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
            FileReader reader = new FileReader(file);
            NewPetProfile.LastID=Integer.parseInt(new BufferedReader(reader).readLine());
            reader.close();
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

}
