package me.padej.eventmanager.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class ItemUtils {


    public static ItemStack createItemWithoutAttributes(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));

        // Скрытие атрибутов у незеровой брони
        if (material != Material.NETHERITE_BOOTS && material != Material.NETHERITE_SHOVEL) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createEmptyNamedItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack multiLoreLine(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        List<String> loreList = Arrays.asList(lore);

        // Добавление пустых строк, чтобы обеспечить наличие трех строк описания
        while (loreList.size() < 3) {
            loreList.add("");
        }

        meta.setLore(loreList);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSkull(String owner, String displayName, String... lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
}
