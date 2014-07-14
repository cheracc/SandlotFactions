package com.sandlotminecraft.SandlotFactions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shawn on 7/7/2014.
 */
public class PluginData {
    public static FileConfiguration config;

    public static void setTimeOfFactionChange(String player) {
        config.set("Players." + player + ".lastfchange", System.currentTimeMillis());
    }

    public static long getTimeOfFactionChange(String player) {
        long time = 0;

        if (config.getConfigurationSection("Players").contains(player)) {
            if (config.getConfigurationSection("Players." + player).contains("lastfchange")) {
                time = config.getLong("Players." + player + ".lastfchange");
            }
        }

        return time;
    }

    public static void setTimeOfLastDeath(String player) {
        config.set("Players." + player + ".lastdeath", System.currentTimeMillis());
    }

    public static long getTimeOfLastDeath(String player) {
        long time = 0;

        if (config.getConfigurationSection("Players").contains(player)) {
            if (config.getConfigurationSection("Players." + player).contains("lastdeath")) {
                time = config.getLong("Players." + player + ".lastdeath");
            }
        }

        return time;
    }

    public static ItemStack getToken(Integer amount) {

        ItemStack token = new ItemStack(Material.GOLD_NUGGET, amount);
        ItemMeta im = token.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Shop Token");
        im.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&9&o Used as money at Spawn shops.")));
        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        token.setItemMeta(im);
        return token;
    }
}
