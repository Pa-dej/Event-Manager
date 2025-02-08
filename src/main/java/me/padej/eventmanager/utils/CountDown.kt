@file:JvmName("CountdownUtils")
package me.padej.eventmanager.utils

import me.padej.eventmanager.main.EventManager
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

private var countdownActive = false

fun startCountdown(player: Player, seconds: Int, onComplete: () -> Unit) {
    if (countdownActive) {
        player.sendActionBar("§cОтсчёт уже активен.")
        return
    }

    countdownActive = true
    player.sendActionBar("§eОтсчёт начинается...")

    val plugin = JavaPlugin.getPlugin(EventManager::class.java)

    object : BukkitRunnable() {
        var countdown = seconds

        override fun run() {
            if (countdown > 0) {
                player.world.players
                    .filter { it.location.distance(player.location) <= 20 }
                    .forEach {
                        it.sendTitle("§6$countdown", "", 10, 10, 10)
                        it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_BANJO, 1f, 1f)
                    }
                countdown--
            } else {
                player.world.players
                    .filter { it.location.distance(player.location) <= 20 }
                    .forEach {
                        it.sendTitle("§aВперёд!", "", 10, 40, 10)
                        it.playSound(it.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
                    }

                countdownActive = false
                cancel()
                onComplete()
            }
        }
    }.runTaskTimer(plugin, 0, 20) // Запуск каждую секунду (20 тиков)
}
