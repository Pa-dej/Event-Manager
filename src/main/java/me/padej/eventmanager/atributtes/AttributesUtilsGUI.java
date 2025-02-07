package me.padej.eventmanager.atributtes;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static me.padej.eventmanager.utils.ItemUtils.*;

public class AttributesUtilsGUI implements Listener {

    private final Map<Player, Player> selectedPlayers;

    private final Map<Player, Double> attackDamageModifiers;
    private final Map<Player, Double> attackSpeedModifiers;
    private final Map<Player, Double> knockBackResistanceModifiers;
    private final Map<Player, Double> luckModifiers;
    private final Map<Player, Double> maxHealthModifiers;
    private final Map<Player, Double> movementSpeedModifiers;

    public AttributesUtilsGUI() {
        this.attackDamageModifiers = new HashMap<>();
        this.attackSpeedModifiers = new HashMap<>();
        this.knockBackResistanceModifiers = new HashMap<>();
        this.luckModifiers = new HashMap<>();
        this.maxHealthModifiers = new HashMap<>();
        this.movementSpeedModifiers = new HashMap<>();
        this.selectedPlayers = new HashMap<>();
    }

    private ItemStack createAttributeItemAttackDamage(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = attackDamageModifiers.getOrDefault(clickedPlayer, 1.0);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createAttributeItemAttackSpeed(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = attackSpeedModifiers.getOrDefault(clickedPlayer, 4.0);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createAttributeItemKnockBackResistance(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = knockBackResistanceModifiers.getOrDefault(clickedPlayer, 0.0);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createAttributeItemLuck(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = luckModifiers.getOrDefault(clickedPlayer, 0.0);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createAttributeItemMaxHealth(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = maxHealthModifiers.getOrDefault(clickedPlayer, 20.0);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createAttributeItemMovementSpeed(Player player, Material material, String attributeName) {
        Player clickedPlayer = selectedPlayers.get(player);
        double modifier = movementSpeedModifiers.getOrDefault(clickedPlayer, 0.10000000149011612);
        modifier = Math.round(modifier * 100.0) / 100.0; // Округление до сотых

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(attributeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "" + modifier));
        item.setItemMeta(meta);

        return item;
    }

    // Метод для обновления атрибутов выбранного игрока
    private void updateSelectedPlayerAttributes(Player clickedPlayer) {
        // Получаем модификаторы атрибутов
        double modifierAttackDamage = attackDamageModifiers.getOrDefault(clickedPlayer, 1.0);
        double modifierAttackSpeed = attackSpeedModifiers.getOrDefault(clickedPlayer, 4.0);
        double modifierKnockBackResistance = knockBackResistanceModifiers.getOrDefault(clickedPlayer, 0.0);
        double modifierLuck = luckModifiers.getOrDefault(clickedPlayer, 0.0);
        double modifierMaxHealth = maxHealthModifiers.getOrDefault(clickedPlayer, 20.0);
        double modifierMovementSpeed = movementSpeedModifiers.getOrDefault(clickedPlayer, 0.10000000149011612);

        // Обновляем атрибуты выбранного игрока
        clickedPlayer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(+modifierAttackDamage);
        clickedPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(+modifierAttackSpeed);
        clickedPlayer.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(+modifierKnockBackResistance);
        clickedPlayer.getAttribute(Attribute.GENERIC_LUCK).setBaseValue(+modifierLuck);
        clickedPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(+modifierMaxHealth);
        clickedPlayer.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(+modifierMovementSpeed);
    }

    public void openGUI(Player player, Player clickedPlayer) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§8§lAnother");

        inventory.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(1, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(3, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(4, createSkull(clickedPlayer.getName(), "§r§b§k21 §r§6Настройка атрибутов §r§b§k21", "§7Настраиваемый игрок:", "§a" + clickedPlayer.getName()));
        inventory.setItem(5, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(7, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        inventory.setItem(9, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(10, createAttributeItemAttackDamage(player, Material.IRON_SWORD, ChatColor.DARK_RED + "attack_damage"));
        inventory.setItem(11, createAttributeItemAttackSpeed(player, Material.GOLDEN_PICKAXE, ChatColor.YELLOW + "attack_speed"));
        inventory.setItem(12, createAttributeItemKnockBackResistance(player, Material.NETHERITE_INGOT, ChatColor.DARK_GRAY + "knockback_resistance"));
        inventory.setItem(13, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(14, createAttributeItemLuck(player, Material.RABBIT_FOOT, ChatColor.GREEN + "luck"));
        inventory.setItem(15, createAttributeItemMaxHealth(player, Material.GLISTERING_MELON_SLICE, ChatColor.RED + "max_health"));
        inventory.setItem(16, createAttributeItemMovementSpeed(player, Material.SUGAR, ChatColor.AQUA + "movement_speed"));
        inventory.setItem(17, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        inventory.setItem(18, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(19, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(20, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(21, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(22, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(23, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(24, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(25, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        inventory.setItem(26, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getView().getTopInventory())) {

            if (event.getView().getTitle().equals("§8§lAnother")) {
                event.setCancelled(true);

                ItemStack clickedItem = event.getCurrentItem();
                Player player = (Player) event.getWhoClicked();
                Player clickedPlayer = selectedPlayers.get(player);

                clickedPlayer.playSound(clickedPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                if (clickedItem.getType() == Material.IRON_SWORD) {
                    double currentModifier = attackDamageModifiers.getOrDefault(clickedPlayer, 1.0);

                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            attackDamageModifiers.put(clickedPlayer, currentModifier + 0.1);
                        } else {
                            attackDamageModifiers.put(clickedPlayer, currentModifier + 0.01);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            attackDamageModifiers.put(clickedPlayer, currentModifier - 0.1);
                        } else {
                            attackDamageModifiers.put(clickedPlayer, currentModifier - 0.01);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        attackDamageModifiers.clear();
                        attackDamageModifiers.put(clickedPlayer, 1.0);
                    }

                } else if (clickedItem.getType() == Material.GOLDEN_PICKAXE) {
                    double currentModifier = attackSpeedModifiers.getOrDefault(clickedPlayer, 4.0);

                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            attackSpeedModifiers.put(clickedPlayer, currentModifier + 0.1);
                        } else {
                            attackSpeedModifiers.put(clickedPlayer, currentModifier + 0.01);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            attackSpeedModifiers.put(clickedPlayer, currentModifier - 0.1);
                        } else {
                            attackSpeedModifiers.put(clickedPlayer, currentModifier - 0.01);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        attackSpeedModifiers.clear();
                        attackSpeedModifiers.put(clickedPlayer, 4.0);
                    }

                } else if (clickedItem.getType() == Material.NETHERITE_INGOT) {
                    double currentModifier = knockBackResistanceModifiers.getOrDefault(clickedPlayer, 0.0);

                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            knockBackResistanceModifiers.put(clickedPlayer, currentModifier + 0.1);
                        } else {
                            knockBackResistanceModifiers.put(clickedPlayer, currentModifier + 0.01);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            knockBackResistanceModifiers.put(clickedPlayer, currentModifier - 0.1);
                        } else {
                            knockBackResistanceModifiers.put(clickedPlayer, currentModifier - 0.01);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        knockBackResistanceModifiers.clear();
                        knockBackResistanceModifiers.put(clickedPlayer, 0.0);
                    }

                } else if (clickedItem.getType() == Material.RABBIT_FOOT) {
                    double currentModifier = luckModifiers.getOrDefault(clickedPlayer, 0.0);

                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            luckModifiers.put(clickedPlayer, currentModifier + 0.1);
                        } else {
                            luckModifiers.put(clickedPlayer, currentModifier + 0.01);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            luckModifiers.put(clickedPlayer, currentModifier - 0.1);
                        } else {
                            luckModifiers.put(clickedPlayer, currentModifier - 0.01);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        luckModifiers.clear();
                        luckModifiers.put(clickedPlayer, 0.0);
                    }

                } else if (clickedItem.getType() == Material.GLISTERING_MELON_SLICE) {
                    double currentModifier = maxHealthModifiers.getOrDefault(clickedPlayer, 20.0);

                    // Обрабатываем события взаимодействия
                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            // Shift+ЛКМ: Увеличивает аттрибут attack_damage для игрока на 0.1
                            maxHealthModifiers.put(clickedPlayer, currentModifier + 2.0);
                        } else {
                            // ЛКМ: Увеличивает аттрибут attack_damage для игрока на 0.01
                            maxHealthModifiers.put(clickedPlayer, currentModifier + 1.0);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            // Shift+ПКМ: Уменьшает аттрибут attack_damage для игрока на 0.1
                            maxHealthModifiers.put(clickedPlayer, currentModifier - 2.0);
                        } else {
                            // ПКМ: Уменьшает аттрибут attack_damage для игрока на 0.01
                            maxHealthModifiers.put(clickedPlayer, currentModifier - 1.0);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        maxHealthModifiers.clear();
                        maxHealthModifiers.put(clickedPlayer, 20.0);
                    }

                } else if (clickedItem.getType() == Material.SUGAR) {
                    double currentModifier = movementSpeedModifiers.getOrDefault(clickedPlayer, 0.10000000149011612);

                    if (event.isLeftClick()) {
                        if (event.isShiftClick()) {
                            movementSpeedModifiers.put(clickedPlayer, currentModifier + 0.1);
                        } else {
                            movementSpeedModifiers.put(clickedPlayer, currentModifier + 0.01);
                        }
                    } else if (event.isRightClick()) {
                        if (event.isShiftClick()) {
                            movementSpeedModifiers.put(clickedPlayer, currentModifier - 0.1);
                        } else {
                            movementSpeedModifiers.put(clickedPlayer, currentModifier - 0.01);
                        }
                    } else if (event.getClick().equals(ClickType.DROP)) {
                        movementSpeedModifiers.clear();
                        movementSpeedModifiers.put(clickedPlayer, 0.10000000149011612);
                    }
                }

                for (Player observer : clickedPlayer.getWorld().getPlayers()) {
                    observer.spawnParticle(Particle.END_ROD, clickedPlayer.getLocation().add(0, 1, 0), 1);
                }

                // Обновляем GUI
                openGUI(player, clickedPlayer);
                updateSelectedPlayerAttributes(clickedPlayer);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getInventory().getItemInMainHand();

        if (handItem.getType() == Material.JIGSAW && getCustomModelData(handItem) == 3000) {
            // Получаем игрока, на которого кликнули
            Player clickedPlayer = (Player) event.getRightClicked();

            // Записываем выбранного игрока в хэш-карту
            selectedPlayers.clear();
            selectedPlayers.put(player, clickedPlayer);

            // Открываем GUI для настройки атрибутов, но для вас
            openGUI(player, clickedPlayer);
        }
    }

    // Метод для получения CustomModelData из предмета
    private int getCustomModelData(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasCustomModelData()) {
            return meta.getCustomModelData();
        }
        return -1; // или другое значение по умолчанию, если не найдено
    }
}