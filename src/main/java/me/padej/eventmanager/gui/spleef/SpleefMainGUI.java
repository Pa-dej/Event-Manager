package me.padej.eventmanager.gui.spleef;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.spleef.arena.SpleefArenaGUI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SpleefMainGUI implements Listener {

    private boolean countdownActive = false;

    private final JavaPlugin plugin;
    public SpleefMainGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§b§nSpleef");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.IRON_SHOVEL, "§bSpleefArena", "§7Настройка сплиф арены"));
        gui.setItem(3, createItem(Material.POWDER_SNOW_BUCKET, "§bSpleefUtils", "§7Настройка плагина SpleefUtils"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§bНачать", "§7Start Spleef"));
        gui.setItem(5, createItem(Material.OAK_DOOR, "§bОткрыть", "§7Открывает проход на арену"));
        gui.setItem(6, createItem(Material.IRON_DOOR, "§bЗакрыть", "§7Закрывает проход на арену"));
        gui.setItem(7, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§b§nSpleef") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case IRON_SHOVEL:
                        SpleefArenaGUI.openGUI(player);
                        break;
                    case POWDER_SNOW_BUCKET:
                        SpleefUtilsGUI.openGUI(player);
                        break;
                    case OAK_DOOR:
                        open(new Location(player.getWorld(), 832, 102, -655), new Location(player.getWorld(), 832, 107, -650), player);
                        break;
                    case IRON_DOOR:
                        close(new Location(player.getWorld(), 832, 102, -655), new Location(player.getWorld(), 832, 107, -650), player);
                        break;
                    case FIREWORK_ROCKET:
                        start(player);
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
            player.sendActionBar("§cОтсчет уже активен.");
            return;
        }

        countdownActive = true;
        player.sendActionBar("§eОтсчет начинается...");

        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    // Бродкаст отсчета в радиусе 20 блоков
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
                            nearbyPlayer.sendTitle("§aВперед!", "", 10, 40, 10);
                            nearbyPlayer.playSound(nearbyPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        }
                    }
                    countdownActive = false;
                    cancel();
                    setSnowBlock(player.getLocation());
                }
            }
        }.runTaskTimer(plugin, 0, 20); // Запуск каждую секунду (20 тиков)
    }

    private void setSnowBlock(Location location) {
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
                    block.setType(Material.SNOW_BLOCK);
                }
            }
        }
    }

    public void open(Location start, Location end, Player player) {
        World world = player.getWorld();

        for (int x = start.getBlockX(); x <= end.getBlockX(); x++) {
            for (int y = start.getBlockY(); y <= end.getBlockY(); y++) {
                for (int z = start.getBlockZ(); z <= end.getBlockZ(); z++) {
                    world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }
    }

    public void close(Location start, Location end, Player player) {
        World world = player.getWorld();

        for (int x = start.getBlockX(); x <= end.getBlockX(); x++) {
            for (int y = start.getBlockY(); y <= end.getBlockY(); y++) {
                for (int z = start.getBlockZ(); z <= end.getBlockZ(); z++) {
                    world.getBlockAt(x, y, z).setType(Material.BLUE_STAINED_GLASS);
                }
            }
        }
    }
}
