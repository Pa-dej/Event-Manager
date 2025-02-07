package me.padej.eventmanager.gui.spleef;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ArenaCutters {

    private final World world;
    private final Location startLocation;
    private Location currentLocation;
    private int direction;
    private BukkitTask task;

    public ArenaCutters(World world, Location startLocation) {
        this.world = world;
        this.startLocation = startLocation.clone();
        this.currentLocation = startLocation.clone();
        this.direction = 0;
    }

    public void startCutting() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                cutSnowBlocks();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("YourPluginName"), 0L, 1L);
    }

    private void cutSnowBlocks() {
        Material blockType = world.getBlockAt(currentLocation).getType();

        if (blockType == Material.SNOW_BLOCK || blockType == Material.AIR) {
            world.getBlockAt(currentLocation).setType(Material.AIR);

            switch (direction) {
                case 0:
                    currentLocation.add(1, 0, 0);
                    break;
                case 1:
                    currentLocation.add(0, 0, 1);
                    break;
                case 2:
                    currentLocation.subtract(1, 0, 0);
                    break;
                case 3:
                    currentLocation.subtract(0, 0, 1);
                    break;
            }

            // Проверяем, вернулись ли в начальную точку
            if (currentLocation.equals(startLocation)) {
                task.cancel(); // Останавливаем таск
            }
        } else {
            direction = (direction + 1) % 4;

            switch (direction) {
                case 0:
                    currentLocation.add(1, 0, 0);
                    break;
                case 1:
                    currentLocation.add(0, 0, 1);
                    break;
                case 2:
                    currentLocation.subtract(1, 0, 0);
                    break;
                case 3:
                    currentLocation.subtract(0, 0, 1);
                    break;
            }
        }
    }
}
