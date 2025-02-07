package me.padej.eventmanager.gui;

import me.padej.eventmanager.gui.mace.MaceMainGUI;
import me.padej.eventmanager.gui.spleef.*;
import me.padej.eventmanager.gui.spleef.arena.SpleefArenaGUI;
import me.padej.eventmanager.gui.spleef.arena.SpleefFillArenaGUI;
import me.padej.eventmanager.gui.sumo.*;
import me.padej.eventmanager.gui.sumo.stp.StpPasteGUI;
import me.padej.eventmanager.gui.sumoteam.*;
import me.padej.eventmanager.potionrun.PotionRunGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.padej.eventmanager.messages.*;
import static me.padej.eventmanager.messages.sendHelpInfoMessage;
import static me.padej.eventmanager.utils.ItemUtils.*;

public class MainGUI implements Listener {

    public MainGUI(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 45, "§5§nМенеджер ивентов");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(3, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(4, createSkull("Padej_", "§3:3", "§7by Padej_", "§7help-инфа", "§7Нашли баг? Пишите в дс"));
        gui.setItem(5, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(6, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 2
        gui.setItem(9, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(10, createItemWithoutAttributes(Material.NETHERITE_BOOTS, "§eParkourDuel", "§7Настройки ивента ParkourDuel"));
        gui.setItem(11, createItemWithoutAttributes(Material.HONEYCOMB, "§eSumoTeam", "§7Настройки ивента SumoTeam"));
        gui.setItem(12, createItemWithoutAttributes(Material.BAMBOO, "§aSumo", "§7Настройки ивента Sumo"));
        gui.setItem(13, createItemWithoutAttributes(Material.SPLASH_POTION, "§5PotionRun", "§7Настройки ивента PotionRun"));
        gui.setItem(14, createItemWithoutAttributes(Material.NETHERITE_SHOVEL, "§bSpleef", "§7Настройки ивента Spleef"));
        gui.setItem(15, createItemWithoutAttributes(Material.COMMAND_BLOCK_MINECART, "§9Tools", "§7Различные инструменты"));
        gui.setItem(16, createItemWithoutAttributes(Material.WARPED_FUNGUS_ON_A_STICK, "§6LavaRace", "§7Настройки ивента LavaRace"));
        gui.setItem(17, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 3
        gui.setItem(18, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(19, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(20, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(21, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(22, createItem(Material.MACE, "§6Mace", "§7Mace Event"));
        gui.setItem(23, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(24, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(25, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(26, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));

        // Строка 4
        gui.setItem(27, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(28, createItem(Material.AXOLOTL_BUCKET, "§6Вставка арен", "§7Вставить арены"));
        gui.setItem(29, createItem(Material.PUFFERFISH_BUCKET, "§bЗаполнение арены", "§7Чем и как заполнять арену"));
        gui.setItem(30, createItem(Material.POWDER_SNOW_BUCKET, "§bSpleefUtils", "§7Настройка плагина SpleefUtils"));
        gui.setItem(31, createItem(Material.IRON_SHOVEL, "§bSpleefArena", "§7Настройка сплиф арены"));
        gui.setItem(32, createItem(Material.YELLOW_DYE, "§eParkourDuel", "§7Вкл(ЛКМ) / Выкл(ПКМ) плагин"));
        gui.setItem(33, createItem(Material.PURPLE_DYE, "§5PotionRun", "§7Вкл(ЛКМ) / Выкл(ПКМ) плагин"));
        gui.setItem(34, createItem(Material.LIME_DYE, "§aSumo", "§7Вкл(ЛКМ) / Выкл(ПКМ) плагин"));
        gui.setItem(35, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 5
        gui.setItem(36, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(37, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(38, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(39, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(40, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(41, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(42, createEmptyNamedItem(Material.MAGENTA_STAINED_GLASS_PANE));
        gui.setItem(43, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(44, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5§nМенеджер ивентов") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case PLAYER_HEAD:
                        sendHelpInfoMessage(player);
                        break;
                    case WARPED_FUNGUS_ON_A_STICK:
                        LavaRaceGUI.openGUI(player);
                        break;
                    case COMMAND_BLOCK_MINECART:
                        ToolsGUI.openGUI(player);
                        break;
                    case NETHERITE_SHOVEL:
                        SpleefMainGUI.openGUI(player);
                        break;
                    case NETHERITE_BOOTS:
                        ParkourDuelGUI.openGUI(player);
                        break;
                    case HONEYCOMB:
                        SumoTeamGUI.openGUI(player);
                        break;
                    case BAMBOO:
                        SumoGUI.openGUI(player);
                        break;
                    case SPLASH_POTION:
                        PotionRunGUI.openGUI(player);
                        break;
                    case AXOLOTL_BUCKET:
                        StpPasteGUI.openGUI(player);
                        break;
                    case PUFFERFISH_BUCKET:
                        SpleefFillArenaGUI.openGUI(player);
                        break;
                    case POWDER_SNOW_BUCKET:
                        SpleefUtilsGUI.openGUI(player);
                        break;
                    case IRON_SHOVEL:
                        SpleefArenaGUI.openGUI(player);
                    case MACE:
                        MaceMainGUI.openGUI(player);
                }
                switch (event.getRawSlot()) {
                    case 32:
                        if (event.isLeftClick()) {
                            player.performCommand("parkourutils enable");
                        } else if (event.isRightClick()) {
                            player.performCommand("parkourutils disable");
                        }
                        break;

                    case 33:
                        if (event.isLeftClick()) {
                            player.performCommand("potion enable");
                        } else if (event.isRightClick()) {
                            player.performCommand("potion disable");
                        }
                        break;

                    case 34:
                        if (event.isLeftClick()) {
                            player.performCommand("sumoutils enable");
                        } else if (event.isRightClick()) {
                            player.performCommand("sumoutils disable");
                        }
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                player.closeInventory();
            }
        }
    }
}