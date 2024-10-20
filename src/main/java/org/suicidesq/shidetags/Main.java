package org.suicidesq.shidetags;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.suicidesq.shidetags.listeners.InteractListener;
import org.suicidesq.shidetags.listeners.JoinListener;
import org.suicidesq.shidetags.managers.ConfigManager;
import org.suicidesq.shidetags.managers.TeamManager;
import org.suicidesq.shidetags.managers.TitleManager;

public final class Main extends JavaPlugin {
    private static TeamManager teamManager;
    private static TitleManager titleManager;
    private static ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        if (!isPluginEnabled()) {
            return;
        }

        teamManager = new TeamManager();
        titleManager = new TitleManager();

        registerEventListeners();
        teamManager.Init();
    }

    private boolean isPluginEnabled() {
        boolean isEnabled = getConfig().getBoolean("settings.enabled", true);
        if (!isEnabled) {
            getServer().getPluginManager().disablePlugin(this);
        }
        return isEnabled;
    }

    private void registerEventListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
    }

    public static TeamManager getTeamManager() {
        return teamManager;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static TitleManager getTitleManager() {
        return titleManager;
    }
}
