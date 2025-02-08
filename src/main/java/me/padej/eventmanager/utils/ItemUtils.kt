package me.padej.eventmanager.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

object ItemUtils {
    @JvmStatic
    fun createItemWithoutAttributes(material: Material, displayName: String?, vararg lore: String?): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        meta.setDisplayName(displayName)
        meta.lore = Arrays.asList(*lore)

        // Скрытие атрибутов у незеровой брони
        if (material != Material.NETHERITE_BOOTS && material != Material.NETHERITE_SHOVEL) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        }

        item.setItemMeta(meta)
        return item
    }

    @JvmStatic
    fun createEmptyNamedItem(material: Material?): ItemStack {
        val item = ItemStack(material!!)
        val meta = item.itemMeta
        meta.setDisplayName(" ")
        item.setItemMeta(meta)
        return item
    }

    @JvmStatic
    fun createItem(material: Material?, displayName: String?, vararg lore: String?): ItemStack {
        val item = ItemStack(material!!)
        val meta = item.itemMeta
        meta.setDisplayName(displayName)
        meta.lore = Arrays.asList(*lore)

        item.setItemMeta(meta)
        return item
    }

    @JvmStatic
    fun multiLoreLine(material: Material?, displayName: String?, vararg lore: String?): ItemStack {
        val item = ItemStack(material!!)
        val meta = item.itemMeta
        meta.setDisplayName(displayName)

        val loreList = Arrays.asList(*lore)

        // Добавление пустых строк, чтобы обеспечить наличие трех строк описания
        while (loreList.size < 3) {
            loreList.add("")
        }

        meta.lore = loreList

        item.setItemMeta(meta)
        return item
    }

    @JvmStatic
    fun createSkull(owner: String?, displayName: String?, vararg lore: String?): ItemStack {
        val item = ItemStack(Material.PLAYER_HEAD)
        val meta = item.itemMeta as SkullMeta
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner!!))
        meta.setDisplayName(displayName)
        meta.lore = Arrays.asList(*lore)
        item.setItemMeta(meta)
        return item
    }
}
