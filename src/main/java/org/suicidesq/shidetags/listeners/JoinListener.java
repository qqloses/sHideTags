package org.suicidesq.shidetags.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.suicidesq.shidetags.Main;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.getTeamManager().hideName(event.getPlayer());
    }
}
