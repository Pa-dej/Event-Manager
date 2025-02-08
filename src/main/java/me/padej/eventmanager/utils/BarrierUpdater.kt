package me.padej.eventmanager.utils

import me.padej.eventmanager.main.EventManager
import org.bukkit.Material
import org.bukkit.scheduler.BukkitRunnable

abstract class BarrierUpdater {

    open fun updatePlatform(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
        fillAir(x1, y1, z1, x2, y2, z2)

        object : BukkitRunnable() {
            override fun run() {
                fillBarrier(x1, y1, z1, x2, y2, z2)
            }
        }.runTaskLater(EventManager.getInstance(), 20L) // Запуск через 1 секунду (20 тиков)
    }

    fun fillAir(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
        fillRegion(x1, y1, z1, x2, y2, z2, Material.AIR)
    }

    fun fillBarrier(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
        fillRegion(x1, y1, z1, x2, y2, z2, Material.BARRIER)
    }
}
