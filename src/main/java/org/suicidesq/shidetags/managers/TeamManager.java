package org.suicidesq.shidetags.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    private final Scoreboard board;
    private final Team playerTeam;
    private final Team visibleMobTag;

    public TeamManager() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        this.board = scoreboardManager.getNewScoreboard();

        this.playerTeam = board.registerNewTeam("players");
        this.playerTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        this.playerTeam.setCanSeeFriendlyInvisibles(false);

        this.visibleMobTag = board.registerNewTeam("mobs");
        this.visibleMobTag.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        this.visibleMobTag.setCanSeeFriendlyInvisibles(true);
    }

    public void setInvisiblePlayerName(Player player) {
        playerTeam.addEntry(player.getName());
        player.setScoreboard(board);
    }

    public void setVisibleMobTag() {
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                visibleMobTag.addEntry(entity.getUniqueId().toString());
            }
        }
    }

    public void Init() {
        Bukkit.getOnlinePlayers().forEach(this::setInvisiblePlayerName);
        setVisibleMobTag();
    }
}
