package me.padej.eventmanager.gui.spleef.arena;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SpleefFillArenaGUI implements Listener {
    public SpleefFillArenaGUI(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§bSpleefArena§7/§b§nЗаполнение арены");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CHEST_MINECART, "§bУдалить все", "§7Очистить всю арену"));
        gui.setItem(3, createItem(Material.RED_DYE, "§bR = 6", "§7Заполнить окружность с R = 6 снегом"));
        gui.setItem(4, createItem(Material.ORANGE_DYE, "§bR = 11", "§7Заполнить окружность с R = 11 снегом"));
        gui.setItem(5, createItem(Material.YELLOW_DYE, "§bR = 17", "§7Заполнить окружность с R = 17  снегом"));
        gui.setItem(6, createItem(Material.LIME_DYE, "§bR = 35", "§7Заполнить окружность с R = 35  снегом"));
        gui.setItem(7, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§bSpleefArena§7/§b§nЗаполнение арены") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        setSnowBlockChest(player.getLocation());
                        break;
                    case RED_DYE:
                        setSnowBlockRed(player.getLocation());
                        break;
                    case ORANGE_DYE:
                        setSnowBlockOrange(player.getLocation());
                        break;
                    case YELLOW_DYE:
                        setSnowBlockYellow(player.getLocation());
                        break;
                    case LIME_DYE:
                        setSnowBlockLime(player.getLocation());
                        break;

                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SpleefArenaGUI.openGUI(player);
            }
        }
    }

    private void setSnowBlockCircle(Location center, int radius, Material material) {
        World world = center.getWorld();
        int xc = center.getBlockX();
        int yc = center.getBlockY();
        int zc = center.getBlockZ();

        for (int x = xc - radius; x <= xc + radius; x++) {
            for (int z = zc - radius; z <= zc + radius; z++) {
                if ((x - xc) * (x - xc) + (z - zc) * (z - zc) <= radius * radius) {
                    Block block = world.getBlockAt(x, yc, z);
                    block.setType(material);
                }
            }
        }
    }

    private void setSnowBlockChest(Location location) {
        World world = location.getWorld();
        int x1 = 833;
        int y1 = 87;
        int z1 = -687;
        int x2 = 902;
        int y2 = 87;
        int z2 = -618;

        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                }
            }
        }
    }

    private void setSnowBlockRed(Location location) {
        World world = location.getWorld();
        int x1 = 863;
        int y1 = 87;
        int z1 = -647;
        int x2 = 873;
        int y2 = 87;
        int z2 = -657;

        int centerX = (x1 + x2) / 2;
        int centerZ = (z1 + z2) / 2;
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(z2 - z1)) / 2;

        setSnowBlockCircle(new Location(world, centerX, y1, centerZ), radius, Material.SNOW_BLOCK);
    }

    private void setSnowBlockOrange(Location location) {
        World world = location.getWorld();
        int x1 = 858;
        int y1 = 87;
        int z1 = -642;
        int x2 = 878;
        int y2 = 87;
        int z2 = -662;

        int centerX = (x1 + x2) / 2;
        int centerZ = (z1 + z2) / 2;
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(z2 - z1)) / 2;

        setSnowBlockCircle(new Location(world, centerX, y1, centerZ), radius, Material.SNOW_BLOCK);
    }

    private void setSnowBlockYellow(Location location) {
        World world = location.getWorld();
        int x1 = 853;
        int y1 = 87;
        int z1 = -636;
        int x2 = 883;
        int y2 = 87;
        int z2 = -667;

        int centerX = (x1 + x2) / 2;
        int centerZ = (z1 + z2) / 2;
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(z2 - z1)) / 2;

        setSnowBlockCircle(new Location(world, centerX, y1, centerZ), radius, Material.SNOW_BLOCK);
    }

    private void setSnowBlockLime(Location location) {
        World world = location.getWorld();
        int x1 = 833;
        int y1 = 87;
        int z1 = -687;
        int x2 = 902;
        int y2 = 87;
        int z2 = -618;

        int centerX = (x1 + x2) / 2;
        int centerZ = (z1 + z2) / 2;
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(z2 - z1)) / 2;

        setSnowBlockCircle(new Location(world, centerX, y1, centerZ), radius, Material.SNOW_BLOCK);
    }

}
