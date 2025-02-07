package me.padej.eventmanager.data;

import org.bukkit.entity.Player;

public class PlayerData {

    private Player player;

    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
