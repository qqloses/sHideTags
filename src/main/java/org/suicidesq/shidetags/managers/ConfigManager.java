package org.suicidesq.shidetags.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.suicidesq.shidetags.Main;

import java.io.*;

public class ConfigManager {
    private final Main plugin;
    private FileConfiguration config;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
    }

    public void unloadConfig() {
        this.config = null;
    }

    public void loadConfig() {
        unloadConfig();
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            try (InputStream is = plugin.getResource("config.yml");
                 OutputStream os = new FileOutputStream(configFile)) {

                if (is != null) {
                    byte[] buffer = is.readAllBytes();
                    os.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public String getSubtitle() {
        return config.getString("settings.subtitle", "%s");
    }
}
