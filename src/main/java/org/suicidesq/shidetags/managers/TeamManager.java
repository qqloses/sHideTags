package org.suicidesq.shidetags.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    private Scoreboard board;
    private Team team;

    public TeamManager() {
        setupTeams();
    }

    private void setupTeams() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        if (scoreboardManager != null) {
            this.board = scoreboardManager.getNewScoreboard();
            this.team = this.board.registerNewTeam("shidetags");
            this.team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
            this.team.setCanSeeFriendlyInvisibles(false);
        }
    }

    public void hideName(Player player) {
        if (team != null) {
            team.addEntry(player.getName());
            player.setScoreboard(board);
        }
    }

    public void HideName() {
        if (board != null) {
            Bukkit.getOnlinePlayers().forEach(this::hideName);
        }
    }
}
