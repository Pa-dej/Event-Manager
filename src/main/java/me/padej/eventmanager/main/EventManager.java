package me.padej.eventmanager.main;

import me.padej.eventmanager.atributtes.*;
import me.padej.eventmanager.gui.*;
import me.padej.eventmanager.gui.mace.MaceMainGUI;
import me.padej.eventmanager.gui.spleef.arena.*;
import me.padej.eventmanager.gui.spleef.*;
import me.padej.eventmanager.gui.sumo.*;
import me.padej.eventmanager.gui.sumo.stp.*;
import me.padej.eventmanager.gui.sumo.stp.arena.*;
import me.padej.eventmanager.gui.sumoteam.*;
import me.padej.eventmanager.events.JoinEvent;
import me.padej.eventmanager.potionrun.PotionRunGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class EventManager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getCommand("event_manager").setExecutor(new onCommand());
        Bukkit.getPluginManager().registerEvents(new MainGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new LavaRaceGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new ToolsGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SpleefArenaGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new ParkourDuelGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SumoTeamGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new DivisionGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SumoGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SuGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new StpGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new StpPasteGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new sumoClassicGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new sumoHalloweenGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new sumoIceGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new sumoLotusGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new sumoXmasGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SpleefMainGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SpleefUtilsGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new SpleefFillArenaGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new PotionRunGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new MaceMainGUI(this), this);

        Bukkit.getPluginManager().registerEvents(new AttributesGUI(this), this);
        Bukkit.getPluginManager().registerEvents(new AttributesUtilsGUI(), this);

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }
}
