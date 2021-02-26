package me.ventan.ArmoredPets.utils;

import org.bukkit.ChatColor;

import static org.bukkit.ChatColor.*;

public enum  RarityName {
    POSPOLITY(GRAY),RZADKI(DARK_PURPLE),NIETYPOWY(LIGHT_PURPLE),MITYCZNY(BLUE),BOSKI(GOLD),LEGENDARNY(YELLOW);
    public ChatColor color;
    RarityName(ChatColor color){
        this.color=color;
    }
}
