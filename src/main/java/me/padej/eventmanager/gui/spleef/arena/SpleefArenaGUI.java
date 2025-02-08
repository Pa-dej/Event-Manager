package me.padej.eventmanager.gui.spleef.arena;

import me.padej.eventmanager.gui.spleef.SpleefMainGUI;
import me.padej.eventmanager.utils.FillRegion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.padej.eventmanager.utils.ItemUtils.*;
import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;

public class SpleefArenaGUI implements Listener {

    public SpleefArenaGUI(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§bSpleef§7/§b§nSpleefArena");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createItem(Material.PUFFERFISH_BUCKET, "§bЗаполнение арены", "§7Чем и как заполнять арену"));
        gui.setItem(2, createItem(Material.ENDER_PEARL, "§bТелепорт", "§7Телепортироваться на Spleef"));
        gui.setItem(3, multiLoreLine(Material.WOODEN_PICKAXE, "§bРежим кирки", "§7Дает предмету в вашей руке", "§7свойства кирки для удаления снега.", "§cНе работает с:", "§cБлоками, предметами, которые пропадают за 1 клик(снежок) и тд"));
        gui.setItem(4, createItem(Material.BARRIER, "§bВыключить режим кирки", "§7Выключает режим кирки на предмете, на котором он есть"));
        gui.setItem(5, createItem(Material.NETHERITE_SHOVEL, "§bВыдать лопаты", "§7Выдать всем игрокам в радиусе 50 блоков лопаты"));
        gui.setItem(6, createItem(Material.BEDROCK, "§bЗакончить", "§7Заполнить область бедроком"));
        gui.setItem(7, createItem(Material.SNOW_BLOCK, "§bОбновить", "§7Заполнить область снегом"));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§bSpleef§7/§b§nSpleefArena") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case WOODEN_PICKAXE:
                        player.performCommand("brush s 0 5");
                        player.performCommand("mask snow_block");
                        break;
                    case BARRIER:
                        player.performCommand("unbind");
                        break;
                    case NETHERITE_SHOVEL:
                        giveNetheriteShovel(player);
                        break;
                    case ENDER_PEARL:
                        player.teleport(new Location(player.getWorld(), 868.5, 90, -652.5));
                        event.setCancelled(true);
                        break;
                    case SNOW_BLOCK:
                        fillSnowBlock();
                        break;
                    case BEDROCK:
                        fillBedrock();
                        break;
                    case PUFFERFISH_BUCKET:
                        SpleefFillArenaGUI.openGUI(player);
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SpleefMainGUI.openGUI(player);
            }
        }
    }

    private void giveNetheriteShovel(Player player) {
        // Радиус в блоках
        int radius = 50;

        // Получение координат игрока
        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY();
        double playerZ = player.getLocation().getZ();

        // Получение всех сущностей в радиусе
        for (Entity nearbyEntity : player.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player nearbyPlayer) {
                if (nearbyPlayer != player) {
                    // Получение координат игрока в радиусе
                    double nearbyX = nearbyPlayer.getLocation().getX();
                    double nearbyY = nearbyPlayer.getLocation().getY();
                    double nearbyZ = nearbyPlayer.getLocation().getZ();

                    // Проверка, находится ли игрок в указанном радиусе
                    if (isInRadius(playerX, playerY, playerZ, nearbyX, nearbyY, nearbyZ, radius)) {
                        ItemStack spleefShovel = createItem(Material.NETHERITE_SHOVEL, "§9Лопата");
                        nearbyPlayer.getInventory().addItem(spleefShovel);
                        nearbyPlayer.sendActionBar("§aВам выдана лопата!");
                    }
                }
            }
        }
        player.sendActionBar("§aЛопаты успешно выданы");
    }

    private boolean isInRadius(double centerX, double centerY, double centerZ, double targetX, double targetY, double targetZ, double radius) {
        // Проверка, находится ли точка в указанном радиусе от центра
        double distanceSquared = Math.pow(centerX - targetX, 2) + Math.pow(centerY - targetY, 2) + Math.pow(centerZ - targetZ, 2);
        double radiusSquared = Math.pow(radius, 2);
        return distanceSquared <= radiusSquared;
    }

    void fillBedrock() {
        FillRegion.fillRegion(833, 87, -687, 902, 87, -618, Material.BEDROCK);
    }

    void fillSnowBlock() {
        FillRegion.fillRegion(833, 87, -687, 902, 87, -618, Material.SNOW_BLOCK);
    }
}
