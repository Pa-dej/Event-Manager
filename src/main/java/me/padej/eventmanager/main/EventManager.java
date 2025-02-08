package me.padej.eventmanager.main;

import me.padej.eventmanager.atributtes.AttributesGUI;
import me.padej.eventmanager.atributtes.AttributesUtilsGUI;
import me.padej.eventmanager.events.JoinEvent;
import me.padej.eventmanager.gui.LavaRaceGUI;
import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.ParkourDuelGUI;
import me.padej.eventmanager.gui.ToolsGUI;
import me.padej.eventmanager.gui.mace.MaceMainGUI;
import me.padej.eventmanager.gui.spleef.SpleefMainGUI;
import me.padej.eventmanager.gui.spleef.SpleefUtilsGUI;
import me.padej.eventmanager.gui.spleef.arena.SpleefArenaGUI;
import me.padej.eventmanager.gui.spleef.arena.SpleefFillArenaGUI;
import me.padej.eventmanager.gui.sumo.SuGUI;
import me.padej.eventmanager.gui.sumo.SumoGUI;
import me.padej.eventmanager.gui.sumo.stp.StpGUI;
import me.padej.eventmanager.gui.sumo.stp.StpPasteGUI;
import me.padej.eventmanager.gui.sumo.stp.arena.*;
import me.padej.eventmanager.gui.sumoteam.DivisionGUI;
import me.padej.eventmanager.gui.sumoteam.SumoTeamGUI;
import me.padej.eventmanager.potionrun.PotionRunGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class EventManager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("event_manager")).setExecutor(new onCommand());
        Bukkit.getPluginManager().registerEvents(new MainGUI(), this);
        Bukkit.getPluginManager().registerEvents(new LavaRaceGUI(), this);
        Bukkit.getPluginManager().registerEvents(new ToolsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefArenaGUI(), this);
        Bukkit.getPluginManager().registerEvents(new ParkourDuelGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SumoTeamGUI(), this);
        Bukkit.getPluginManager().registerEvents(new DivisionGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SumoGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SuGUI(), this);
        Bukkit.getPluginManager().registerEvents(new StpGUI(), this);
        Bukkit.getPluginManager().registerEvents(new StpPasteGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoClassicGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoHalloweenGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoIceGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoLotusGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoXmasGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefMainGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefUtilsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new SpleefFillArenaGUI(), this);
        Bukkit.getPluginManager().registerEvents(new PotionRunGUI(), this);
        Bukkit.getPluginManager().registerEvents(new MaceMainGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoPinkGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoStarGUI(), this);
        Bukkit.getPluginManager().registerEvents(new sumoStarPGUI(), this);

        Bukkit.getPluginManager().registerEvents(new AttributesGUI(), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUtilsGUI(), this);

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }

    public static JavaPlugin getInstance() {
        return JavaPlugin.getPlugin(EventManager.class);
    }
}
