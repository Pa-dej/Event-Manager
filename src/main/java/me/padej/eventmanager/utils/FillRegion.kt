@file:JvmName("FillRegion")
package me.padej.eventmanager.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block

fun fillRegion(minX: Int, minY: Int, minZ: Int, maxX: Int, maxY: Int, maxZ: Int, fillBlock: Material) {
    val world: World = Bukkit.getWorld("world") ?: return

    for (x in minX..maxX) {
        for (y in minY..maxY) {
            for (z in minZ..maxZ) {
                val block: Block = world.getBlockAt(x, y, z)
                block.type = fillBlock
            }
        }
    }
}

fun fillCircle(center: Location, radius: Int, material: Material) {
    val world: World = center.world ?: return
    val xc = center.blockX
    val yc = center.blockY
    val zc = center.blockZ

    for (x in (xc - radius)..(xc + radius)) {
        for (z in (zc - radius)..(zc + radius)) {
            if ((x - xc) * (x - xc) + (z - zc) * (z - zc) <= radius * radius) {
                world.getBlockAt(x, yc, z).type = material
            }
        }
    }
}
