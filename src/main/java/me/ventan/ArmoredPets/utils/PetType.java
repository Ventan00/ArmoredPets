package me.ventan.ArmoredPets.utils;

import org.bukkit.ChatColor;

import static me.ventan.ArmoredPets.utils.RarityName.*;
import static org.bukkit.ChatColor.*;

public enum PetType {
    KURCZAK(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZmZWEyMGY4NTI1MDFkYzJkYzk4ODc3YjhkODY5ODBiZDE2YTliY2I2ZGYzNTgzYjNhMmIzMjU0YTgzNWY1YiJ9fX0=",
            0.5f,
            0f,
            0f,
            0f,
            10,
            POSPOLITY,
            YELLOW
    ),
    PSZCZOLKA(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmEwNjYxZTIwZDI4Yzc0MzIzYjliYTY5MjY4YTYyYjNkMjM2YjY5OThjMTUzNjQzYjRkNjFlNTNjMmNhNTlmMyJ9fX0=",
            1.0f,
            0f,
            0f,
            0f,
            25,
            RZADKI,
            LIGHT_PURPLE
    ),
    SLIMACZEK(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM3M2ZiNTQ4MjNlYWMzMzBhZjAzMzcwMWIwNjk0YTNlY2U5ZjEzMjJkODJiNjhhY2UyN2NhYzhlYTk3MzQxOSJ9fX0=",
            5f,
            2f,
            0.5f,
            0.2f,
            0,
            LEGENDARNY,
            DARK_PURPLE
    ),
    LIS(
            "ewogICJ0aW1lc3RhbXAiIDogMTU5NzI3NjY4MTMwNCwKICAicHJvZmlsZUlkIiA6ICI4NjMyODFlOTBiN2M0ZDIzODAxYmQxZTUyYjk3YzZlMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJHcjBwTWFTdDNyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYxMzc1ODFjMWExYTQ5ZGNhYmUyNzg4MDkyMjVlYzZkN2ZmMTEzOTNkMGE5MTRkNWUxYTcwZDdiOTY2YTcxNjgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
            0.5f,
            1f,
            1f,
            0f,
            50,
            MITYCZNY,
            RED
    ),
    MROWKA(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJmYTk5NzkyYWE2OTZiNzgwYzFmZGUwYTk2ODE2ZjdlZDc3NWY2NWE4MWM1ZmNlZGVmZWQ2NDQwOWZmZjlhNyJ9fX0=",
            2.5f,
            0.5f,
            2f,
            0.1f,
            0,
            BOSKI,
            GOLD
    ),
    PTASZEK(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZiMjE5MGFmOWM5ZDAxMWU3OTYyZThhNTFmNDMwNThjMTIwYWQ2ZWY4ZmYwNzkwMjcxOWIyMjJkNjU5MDNlMiJ9fX0=",
            0f,
            1f,
            1f,
            0f,
            30,
            NIETYPOWY,
            BLUE
    );

    public String texture;
    public float baseLuck;
    public float baseAttack;
    public float baseDefence;
    public float baseDrop;
    public int minLvl;
    public RarityName rarity;
    public ChatColor nickColor;

    PetType(String texture,float baseLuck, float baseAttack, float baseDefence, float baseDrop, int minLvl, RarityName rarity, ChatColor nickColor){
        this.texture = texture;
        this.baseLuck = baseLuck;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
        this.baseDrop = baseDrop;
        this.minLvl=minLvl;
        this.rarity = rarity;
        this.nickColor = nickColor;
    }
}
