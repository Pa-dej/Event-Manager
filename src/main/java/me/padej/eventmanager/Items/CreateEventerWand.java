package me.padej.eventmanager.Items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CreateEventerWand {
    public static ItemStack EventerWand(Player player) {
        ItemStack wand = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = wand.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.of("#FF0000") + "Eventer Menu");
            meta.setLore(null);  // Опционально, если у вас есть описание предмета
            wand.setItemMeta(meta);
        }

        player.getInventory().addItem(wand);
        return wand;
    }
}
