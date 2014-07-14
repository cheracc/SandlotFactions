package com.sandlotminecraft.SandlotFactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


/**
 * Created by Shawn on 7/8/2014.
 */
public class NewPlayerJoin implements Listener {

    @EventHandler
    public void onFirstJoin (PlayerJoinEvent event) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());

        if (!op.hasPlayedBefore()) {
            event.getPlayer().getInventory().addItem(PluginData.getToken(10));
            PluginData.setTimeOfLastDeath(event.getPlayer().getName());
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aWelcome to the Sandlot Factions Server, " + event.getPlayer().getName() + "!"));
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou have been given 10 shop tokens. You can spend them at the shops around Spawn. Use them wisely!"));
        }
    }
}
