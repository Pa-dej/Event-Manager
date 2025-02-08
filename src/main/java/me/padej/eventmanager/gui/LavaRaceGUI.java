package me.padej.eventmanager.gui;

import me.padej.eventmanager.main.EventManager;
import me.padej.eventmanager.utils.CountdownUtils;
import me.padej.eventmanager.utils.FillRegion;
import me.padej.eventmanager.utils.BarrierUpdater;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Strider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class LavaRaceGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8Менеджер ивентов§7/§8§nLavaRace");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.STRIDER_SPAWN_EGG, "§6Создать лавомерку", "§7Создает лавомерку с седлом"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§6Начать", "§7Start LavaRace"));
        gui.setItem(5, createItem(Material.WARPED_FUNGUS_ON_A_STICK, "§6Выдать удочки", "§7Выдать удочки всем игрокам в радиусе 20 блоков"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Менеджер ивентов§7/§8§nLavaRace") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case FIREWORK_ROCKET:
                        startCountdown(player);
                        break;
                    case WARPED_FUNGUS_ON_A_STICK:
                        giveWarpedFungusOnAStick(player);
                        break;
                    case STRIDER_SPAWN_EGG:
                        spawnStrider(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    private void spawnStrider(Player player) {
        Location playerLocation = player.getLocation();

        Entity strider = player.getWorld().spawnEntity(playerLocation, EntityType.STRIDER);
        ((Strider) strider).setSaddle(true);

        player.sendActionBar("§aСоздана лавомерка!");
    }

    public void startCountdown(Player whoStarted) {
        CountdownUtils.startCountdown(whoStarted, 5, () -> {
            updatePlatform();
            return null;
        });
    }

    void updatePlatform() {
        fillAir();

        new BukkitRunnable() {
            @Override
            public void run() {
                fillBarrier();
            }
        }.runTaskLater(EventManager.getInstance(), 20); // Запуск через 1 секунду (20 тиков)
    }

    void fillAir() {
        FillRegion.fillRegion(897, 96, -477, 909, 96, -477, Material.AIR);
    }

    void fillBarrier() {
        FillRegion.fillRegion(897, 96, -477, 909, 96, -477, Material.BARRIER);
    }

    private void giveWarpedFungusOnAStick(Player player) {
        // Радиус в блоках
        int radius = 20;

        // Получение координат игрока
        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY();
        double playerZ = player.getLocation().getZ();

        // Получение всех сущностей в радиусе
        for (org.bukkit.entity.Entity nearbyEntity : player.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player) {
                Player nearbyPlayer = (Player) nearbyEntity;
                if (nearbyPlayer != player) {
                    // Получение координат игрока в радиусе
                    double nearbyX = nearbyPlayer.getLocation().getX();
                    double nearbyY = nearbyPlayer.getLocation().getY();
                    double nearbyZ = nearbyPlayer.getLocation().getZ();

                    // Проверка, находится ли игрок в указанном радиусе
                    if (isInRadius(playerX, playerY, playerZ, nearbyX, nearbyY, nearbyZ, radius)) {
                        ItemStack fishingRod = createItem(Material.WARPED_FUNGUS_ON_A_STICK, "§7Удочка", "§7Для LavaRace");
                        nearbyPlayer.getInventory().addItem(fishingRod);
                        nearbyPlayer.sendActionBar("§aВам выдана удочка для LavaRace!");
                    }
                }
            }
        }
        player.sendActionBar("§aУдочки успешно выданы");
    }

    private boolean isInRadius(double centerX, double centerY, double centerZ, double targetX, double targetY, double targetZ, double radius) {
        // Проверка, находится ли точка в указанном радиусе от центра
        double distanceSquared = Math.pow(centerX - targetX, 2) + Math.pow(centerY - targetY, 2) + Math.pow(centerZ - targetZ, 2);
        double radiusSquared = Math.pow(radius, 2);
        return distanceSquared <= radiusSquared;
    }
}
