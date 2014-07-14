package com.sandlotminecraft.SandlotFactions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Shawn on 7/8/2014.
 */
public class PlayerKill implements Listener {

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();

        // Only drop token if player hasn't died in last 10 minutes AND they died from another player
        if (p != null && (System.currentTimeMillis() - PluginData.getTimeOfLastDeath(p.getName())) >=  600000
                && event.getEntity().getKiller() instanceof Player) {

            event.getDrops().add(PluginData.getToken(1));
        }
    }
}
