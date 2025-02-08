package me.padej.eventmanager.potionrun;

import me.padej.eventmanager.gui.MainGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class PotionRunGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8Менеджер ивентов§7/§8§nPotionRun");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.PURPLE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CLOCK, "§5Отсчет", "§7Отсчет в bossbar"));
        gui.setItem(3, createItem(Material.STRUCTURE_VOID, "§5Undo", "§7//undo"));
        gui.setItem(4, createItem(Material.REDSTONE, "§5Вкл / Выкл", "§7ЛКМ - Вкл / ПКМ - Выкл"));
        gui.setItem(5, createItem(Material.BARRIER, "§5Сетнуть платформу", "§7Удаляет серое стекло в радиусе 30"));
        gui.setItem(6, createItem(Material.SPLASH_POTION, "§5Создать зелье", "§7Создает зелье в центре арены"));
        gui.setItem(7, createEmptyNamedItem(Material.PURPLE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Менеджер ивентов§7/§8§nPotionRun") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CLOCK:
                        player.performCommand("cmi bossbarmsg all +1 Зелье через: [autoTimeLeft]! -sec:-30 -c:green");
                        break;
                    case STRUCTURE_VOID:
                        player.performCommand("/undo");
                        break;
                    case REDSTONE:
                        if (event.isLeftClick()) {
                            player.performCommand("potion enable");
                        } else if (event.isRightClick()) {
                            player.performCommand("potion disable");
                        }
                        break;
                    case BARRIER:
                        player.performCommand("/replacenear 30 gray_stained_glass air");
                        break;
                    case SPLASH_POTION:
                        createInstantDamagePotion(new Location(player.getWorld(), 1116, 222, -1069));
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    public static void createInstantDamagePotion(Location location) {
        ItemStack potionItemStack = new ItemStack(Material.SPLASH_POTION);
        PotionMeta potionMeta = (PotionMeta) potionItemStack.getItemMeta();
        potionMeta.setBasePotionData(new PotionData(PotionType.HARMING));

        potionItemStack.setItemMeta(potionMeta);

        ThrownPotion thrownPotion = location.getWorld().spawn(location, ThrownPotion.class);
        thrownPotion.setItem(potionItemStack);
    }
}
