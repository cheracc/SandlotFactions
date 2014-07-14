package com.sandlotminecraft.SandlotFactions;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Shawn on 7/7/2014.
 */
public final class Main extends JavaPlugin implements Listener {
    File configFile;

    @Override
    public void onEnable() {
        getLogger().info("Loading SandlotFactions");
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new NewPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerKill(), this);
        getServer().getPluginManager().registerEvents(new ArmorEffects(), this);
        getServer().getPluginManager().registerEvents(new FactionChangeCooldown(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("tokens").setExecutor(new TokenCommand());

        initConfig();
    }

    public void onDisable() {
        getLogger().info("Disabling SandlotFactions");

        try {
            PluginData.config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        try {
            PluginData.config.save(configFile);
            getLogger().info("Saving Config.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initConfig() {
        configFile = new File(getDataFolder(), "config.yml");
        
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }

        PluginData.config = new YamlConfiguration();
        try {
            PluginData.config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


