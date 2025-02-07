package me.padej.eventmanager.events;

import me.padej.eventmanager.data.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerDataManager.setData(event.getPlayer());
    }
}
