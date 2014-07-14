package com.sandlotminecraft.SandlotFactions;

import com.massivecraft.factions.event.FactionsEventMembershipChange;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shawn on 7/12/2014.
 */
public class FactionChangeCooldown implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onFactionChange (FactionsEventMembershipChange event) {
        String player = event.getUPlayer().getPlayer().getName();
        long timeleft = System.currentTimeMillis() - PluginData.getTimeOfFactionChange(player);

        if (timeleft < 3600000 && !event.getUPlayer().getPlayer().hasPermission("group.moderator")) {
            timeleft = 3600000 - timeleft;
            String time = String.format("%d minutes and %d seconds",
                    TimeUnit.MILLISECONDS.toMinutes(timeleft),
                    TimeUnit.MILLISECONDS.toSeconds(timeleft) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeleft)));

            event.setCancelled(true);
            event.getUPlayer().getPlayer().sendMessage(ChatColor.RED + "You may only change your faction once per hour. You may leave or join a new faction in "
                    + ChatColor.DARK_PURPLE + time + ChatColor.RED + ".");
        } else {
            PluginData.setTimeOfFactionChange(player);
        }
    }
}
