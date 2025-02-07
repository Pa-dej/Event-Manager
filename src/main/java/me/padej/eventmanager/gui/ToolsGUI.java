package me.padej.eventmanager.gui;

import me.padej.eventmanager.data.PlayerData;
import me.padej.eventmanager.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static me.padej.eventmanager.utils.ItemUtils.*;

public class ToolsGUI implements Listener {

    public ToolsGUI(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§5Менеджер ивентов§7/§9§nTools");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(3, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(6, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 2
        gui.setItem(9, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(10, createItem(Material.IRON_SWORD, "§9Режим выживания", "§7Переносит вас в gm 0"));
        gui.setItem(11, createItem(Material.MAP, "§6Режим приключений", "§7Переносит вас в gm 2"));
        gui.setItem(12, createItem(Material.ENDER_EYE, "§9Режим наблюдателя", "§7В ремонте"));
        gui.setItem(13, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(14, createItem(Material.RED_DYE, "§9Сила", "§9Дает вам эффект Сила 255 уровня"));
        gui.setItem(15, createItem(Material.MILK_BUCKET, "§9Снять все эффекты", "§7Снимает с вас все эффекты"));
        gui.setItem(16, multiLoreLine(Material.SHULKER_SHELL, "§9Левитрон 3000", "§7При клике по игроку,", "§7дает левитацию, если эффекта нет", "§7если эффект есть, то снимает."));
        gui.setItem(17, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));

        // Строка 3
        gui.setItem(18, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(19, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(20, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(21, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(22, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(23, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(24, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(25, createEmptyNamedItem(Material.BLUE_STAINED_GLASS_PANE));
        gui.setItem(26, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§9§nTools") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            PlayerData data = PlayerDataManager.getData((Player) event.getWhoClicked());

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case RED_DYE:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 9000, 255));
                        break;

                    case IRON_SWORD:
                        player.setGameMode(GameMode.SURVIVAL);
                        break;

                    case MAP:
                        player.setGameMode(GameMode.ADVENTURE);
                        break;

                    case ENDER_EYE:
                        player.setGameMode(GameMode.SPECTATOR);
                        break;

                    case MILK_BUCKET:
                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            player.removePotionEffect(effect.getType());
                        }
                        break;

                    case SHULKER_SHELL:
                        giveLevetron3000(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    public void giveLevetron3000(Player player) {
        ItemStack levetronItem = new ItemStack(Material.SHULKER_SHELL);
        ItemMeta meta = levetronItem.getItemMeta();

        // Установка названия предмета
        meta.setDisplayName("§9Левитрон 3000");

        // Установка CustomModelData
        meta.setCustomModelData(3000);
        meta.setLore(Collections.singletonList("§7 ПКМ - Дать левитацию. Шифт+ПКМ - Снять левитацию."));
        meta.addEnchant(Enchantment.POWER, 1, false);

        // Применение метаданных к предмету
        levetronItem.setItemMeta(meta);

        // Выдача предмета игроку
        player.getInventory().addItem(levetronItem);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();

        // Проверяем, что кликнутая сущность - игрок, а не другая сущность
        if (!(clickedEntity instanceof Player)) return;

        // Проверяем, нажат ли шифт
        if (player.isSneaking()) {
            // Если шифт зажат, снимаем эффект левитации
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if (heldItem.getType() == Material.SHULKER_SHELL && heldItem.hasItemMeta()) {
                ItemMeta itemMeta = heldItem.getItemMeta();
                if ("§9Левитрон 3000".equals(itemMeta.getDisplayName())) {
                    ((Player) clickedEntity).removePotionEffect(PotionEffectType.LEVITATION);
                    ((Player) clickedEntity).sendActionBar("§7Тебя простили uwu");
                }
            }
        } else {
            // Если шифт не зажат, даем эффект левитации на 2 минуты
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if (heldItem.getType() == Material.SHULKER_SHELL && heldItem.hasItemMeta()) {
                ItemMeta itemMeta = heldItem.getItemMeta();
                if ("§9Левитрон 3000".equals(itemMeta.getDisplayName())) {
                    ((Player) clickedEntity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2 * 60 * 20, 0));
                    ((Player) clickedEntity).sendActionBar("§7Полетай пару минут :P");
                }
            }
        }
    }
}
