package com.sandlotminecraft.SandlotFactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Shawn on 7/7/2014.
 */
public final class PlayerRespawn implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() == null)
            return;
        else {
            Player p = event.getPlayer();
            Location graveyard = new Location(Bukkit.getWorld("factions"), -791.5, 145.0, 247.5, -180, 0);

            event.setRespawnLocation(graveyard);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lYou have died. You have been resurrected at the Spawn Graveyard."));

            //check if player died in last hour
            if (PluginData.getTimeOfLastDeath(p.getName()) == 0 || (System.currentTimeMillis() - PluginData.getTimeOfLastDeath(p.getName())) >= 3600000) {
                p.getInventory().addItem(PluginData.getToken(10));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou have been given 10 shop tokens."));
                PluginData.setTimeOfLastDeath(p.getName());
            }
            // player died within an hour. he gets nothing
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYour last death was too recent to receive shop tokens."));
            }
        }
    }

}
