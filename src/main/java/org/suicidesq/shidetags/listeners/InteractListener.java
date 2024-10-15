package org.suicidesq.shidetags.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.suicidesq.shidetags.Main;
import org.suicidesq.shidetags.managers.TitleManager;

public class InteractListener implements Listener {
    private final TitleManager titleManager;

    public InteractListener() {
        this.titleManager = Main.getTitleManager();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player clickedPlayer) {
            String subtitle = Main.getConfigManager().getSubtitle();
            String formattedSubtitle = titleManager.replacePlaceholders(subtitle, event.getPlayer(), clickedPlayer);
            titleManager.sendSubtitle(event.getPlayer(), formattedSubtitle);
        }
    }
}
