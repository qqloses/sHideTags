package org.suicidesq.shidetags.managers;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import java.util.Optional;

import static org.bukkit.Bukkit.getServer;

public class TitleManager {
    public TitleManager() {
    }

    public void sendSubtitle(Player player, String subtitle) {
        if (subtitle != null) {
            player.sendTitle("", ChatColor.translateAlternateColorCodes('&', subtitle), 10, 70, 20);
        }
    }

    public String replacePlaceholders(String message, Player player, Player target) {
        message = message.replace("%s", target.getName());

        if (isPlaceholderAPIAvailable()) {
            if (PlaceholderAPI.containsPlaceholders(message)) {
                message = PlaceholderAPI.setPlaceholders(player, message);
            }

            Team team = player.getScoreboard().getEntryTeam(player.getName());
            if (team != null && team.getName().equals("players")) {
                message = PlaceholderAPI.setRelationalPlaceholders(player, target, message);
                return message;
            }
        }

        return message;
    }

    private boolean isPlaceholderAPIAvailable() {
        return Optional.ofNullable(getServer().getPluginManager().getPlugin("PlaceholderAPI"))
                .map(Plugin::isEnabled)
                .orElse(false);
    }
}
