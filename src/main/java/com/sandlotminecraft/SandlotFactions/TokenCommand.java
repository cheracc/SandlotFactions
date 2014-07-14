package com.sandlotminecraft.SandlotFactions;

import com.massivecraft.factions.entity.UPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shawn on 7/11/2014.
 */
public class TokenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length > 0) {
            sender.sendMessage("The /tokens command does not require any arguments.");
            return false;
        }

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("tokens")) {
                long timeleft = System.currentTimeMillis() - PluginData.getTimeOfLastDeath(p.getName());
                if (timeleft < 3600000) {
                    timeleft = 3600000 - timeleft;
                    String time = String.format("%d minutes and %d seconds",
                            TimeUnit.MILLISECONDS.toMinutes(timeleft),
                            TimeUnit.MILLISECONDS.toSeconds(timeleft) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeleft)));
                    p.sendMessage(ChatColor.RED + "You have " + ChatColor.DARK_PURPLE + time + ChatColor.RED + " remaining until you can gain more tokens.");
                    return true;
                } else {
                    UPlayer up = UPlayer.get(p);

                    if (up.getPower() >= 2)
                        up.setPower(up.getPower() - 2);
                    else
                        up.setPower(0.0);

                    p.getInventory().addItem(PluginData.getToken(10));
                    PluginData.setTimeOfLastDeath(p.getName());
                    p.sendMessage(ChatColor.AQUA +
                            "You have been given" + ChatColor.GOLD + " 10 tokens" +
                            ChatColor.AQUA + ". Your power has been reduced by 2. Your new power is: " +
                            ChatColor.GREEN + String.format("%.2f", up.getPower()));
                    return true;
                }
            }
        } else {
                sender.sendMessage("You must be a player to use /tokens");
                return false;
        }
        return false;
    }
}
