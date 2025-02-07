package me.padej.eventmanager.gui.mace;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.spleef.SpleefMainGUI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class MaceMainGUI implements Listener {

    private boolean countdownActive = false;

    private final JavaPlugin plugin;
    public MaceMainGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§6§nMace");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.OAK_DOOR, "§bOpen", "§7Открыть"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§bStart", "§7Start Mace"));
        gui.setItem(5, createItem(Material.IRON_DOOR, "§bClose", "§7Закрыть"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§6§nMace") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case OAK_DOOR:
                        fillAir(player.getWorld());
                        break;
                    case FIREWORK_ROCKET:
                        start(player);
                        break;
                    case IRON_DOOR:
                        fillOrangeStainedGlass(player.getWorld());
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    private void start(Player player) {
        if (countdownActive) {
            player.sendActionBar("§cОтсчёт уже активен.");
            return;
        }

        countdownActive = true;
        player.sendActionBar("§eОтсчёт начинается...");

        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    // Бродкаст отсчёта в радиусе 20 блоков
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (nearbyPlayer.getLocation().distance(player.getLocation()) <= 20) {
                            nearbyPlayer.sendTitle("§6" + countdown, "", 10, 10, 10);
                            nearbyPlayer.playSound(nearbyPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        }
                    }
                    countdown--;
                } else {
                    // Бродкаст сообщения о старте в радиусе 20 блоков
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (nearbyPlayer.getLocation().distance(player.getLocation()) <= 20) {
                            nearbyPlayer.sendTitle("§aВперёд!", "", 10, 40, 10);
                            nearbyPlayer.playSound(nearbyPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        }
                    }
                    countdownActive = false;
                    cancel();
                    fillAir(player.getWorld());

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            fillOrangeStainedGlass(player.getWorld());
                        }
                    }.runTaskLater(plugin, 20); // Запуск через 1 секунду (20 тиков)
                }
            }
        }.runTaskTimer(plugin, 0, 20); // Запуск каждую секунду (20 тиков)
    }

    private void fillAir(World world) {
        // Main square
        fill(-411, 138, 6618, -403, 138, 6626, Material.AIR, world);

        // Corners
        fill(-409, 138, 6617, -405, 138, 6627, Material.AIR, world);
        fill(-412, 138, 6620, -402, 138, 6624, Material.AIR, world);
    }

    private void fillOrangeStainedGlass(World world) {
        // Main square
        fill(-411, 138, 6618, -403, 138, 6626, Material.ORANGE_STAINED_GLASS, world);

        // Corners
        fill(-409, 138, 6617, -405, 138, 6627, Material.ORANGE_STAINED_GLASS, world);
        fill(-412, 138, 6620, -402, 138, 6624, Material.ORANGE_STAINED_GLASS, world);
    }

    private void fill(int x1, int y1, int z1, int x2, int y2, int z2, Material material, World world) {

        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(material);
                }
            }
        }
    }
}
