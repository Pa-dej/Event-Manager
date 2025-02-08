package me.padej.eventmanager.gui.spleef;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SpleefUtilsGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 36, "§8Spleef§7/§8§nSpleefUtils");

        // Строка 1
        gui.setItem(0, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(1, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(2, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(3, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(4, createItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§7Отобразить список функций"));
        gui.setItem(5, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(6, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(7, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(8, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));

        // Строка 2
        gui.setItem(9, createItem(Material.SNOWBALL, "§bВыпадение снежков", "§7Вкл/Выкл"));
        gui.setItem(10, createItem(Material.GLASS_BOTTLE, "§bАнонимность", "§7Вкл/Выкл"));
        gui.setItem(11, createItem(Material.DRAGON_BREATH, "§bСлучайные эффекты", "§7Вкл/Выкл"));
        gui.setItem(12, createItem(Material.ENDER_PEARL, "§bВыпадение Эндер-жемчуга", "§7Вкл/Выкл"));
        gui.setItem(13, createItem(Material.OAK_HANGING_SIGN, "§bОтобразить информацию", "§7Отображает информацию о состояние модулей"));
        gui.setItem(14, createItem(Material.PHANTOM_MEMBRANE, "§bСлабая гравитация", "§7Вкл/Выкл"));
        gui.setItem(15, createItem(Material.FIRE_CHARGE, "§bСнежки ломают блоки", "§7Вкл/Выкл"));
        gui.setItem(16, createItem(Material.EGG, "§bЯйца-ловушки", "§7Вкл/Выкл"));
        gui.setItem(17, createItem(Material.REDSTONE, "§bВсе функции", "§7Вкл/Выкл"));

        // Строка 3
        gui.setItem(18, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(19, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(20, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(21, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(22, createItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "§7Отобразить список функций"));
        gui.setItem(23, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(24, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(25, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(26, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));

        // Строка 4
        gui.setItem(27, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(28, createItem(Material.FEATHER, "§bВыпадение перьев", "§7Вкл/Выкл"));
        gui.setItem(29, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));
        gui.setItem(30, createItem(Material.GRAY_STAINED_GLASS_PANE, ""));
        gui.setItem(31, createItem(Material.GRAY_STAINED_GLASS_PANE, ""));
        gui.setItem(32, createItem(Material.GRAY_STAINED_GLASS_PANE, ""));
        gui.setItem(33, createItem(Material.LIME_STAINED_GLASS_PANE, "§aВключить функцию"));
        gui.setItem(34, createItem(Material.GLISTERING_MELON_SLICE, "§bРегенерация блоков", "§7Вкл/Выкл"));
        gui.setItem(35, createItem(Material.RED_STAINED_GLASS_PANE, "§cВыключить функцию"));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Spleef§7/§8§nSpleefUtils") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (event.getRawSlot()) {
                    case 0:
                        player.performCommand("spleefutil drop disable");
                        break;
                    case 1:
                        player.performCommand("spleefutil anonim disable");
                        break;
                    case 2:
                        player.performCommand("spleefutil random_effect disable");
                        break;
                    case 3:
                        player.performCommand("spleefutil ender_pearl disable");
                        break;
                    case 4:
                    case 13:
                    case 22:
                        player.performCommand("spleefutil list");
                        break;
                    case 5:
                        player.performCommand("spleefutil low_gravity disable");
                        break;
                    case 6:
                        player.performCommand("spleefutil destroy_snowballs disable");
                        break;
                    case 7:
                        player.performCommand("spleefutil powder_snow_trap disable");
                        break;
                    case 8:
                        player.performCommand("spleefutil disable");
                        break;
                    case 18:
                        player.performCommand("spleefutil drop enable");
                        break;
                    case 19:
                        player.performCommand("spleefutil anonim enable");
                        break;
                    case 20:
                        player.performCommand("spleefutil random_effect enable");
                        break;
                    case 21:
                        player.performCommand("spleefutil ender_pearl enable");
                        break;
                    case 23:
                        player.performCommand("spleefutil low_gravity enable");
                        break;
                    case 24:
                        player.performCommand("spleefutil destroy_snowballs enable");
                        break;
                    case 25:
                        player.performCommand("spleefutil powder_snow_trap enable");
                        break;
                    case 26:
                        player.performCommand("spleefutil enable");
                        break;
                    case 27:
                        player.performCommand("spleefutil dash enable");
                        break;
                    case 29:
                        player.performCommand("spleefutil dash disable");
                        break;
                    case 33:
                        player.performCommand("spleefutil blocks_regen enable");
                        break;
                    case 35:
                        player.performCommand("spleefutil blocks_regen disable");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SpleefMainGUI.openGUI(player);
            }
        }
    }
}
