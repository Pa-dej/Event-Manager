package me.padej.eventmanager;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.DumperOptions;

import java.util.Random;

public class messages {

    public static void sendHelpInfoMessage(Player player) {
        player.sendMessage(
                ChatColor.of(colors.light_gray) + "[ ====== <" +
                        ChatColor.of(colors.pastel_purple) + "EventManager" +
                        ChatColor.of(colors.light_gray) + "> ====== ]"
        );
        player.sendMessage(
                ChatColor.of(colors.light_gray) + "<Version: " +
                        ChatColor.of(colors.lemon) + 2.5 +
                        ChatColor.of(colors.light_gray) + ">"
        );
        player.sendMessage(
                ChatColor.of(colors.pastel_purple) + " - " +
                        ChatColor.of(colors.light_gray) + "/event_manager - открыть главное меню"
        );
    }

    public static void sendRandomMessage(Player player) {
        String[] messages = new String[]{
                ChatColor.of(colors.red_pepper) + "Эээ...нее :P",
                ChatColor.of(colors.red_pepper) + "Куда, куда, куда. Не-не",
                ChatColor.of(colors.red_pepper) + "Не сегодня ;)",
                ChatColor.of(colors.red_pepper) + "Самый умний? :)",
                ChatColor.of(colors.red_pepper) + "Ваш игровой режим изменен на 'не креатив'",
                ChatColor.of(colors.red_pepper) + "Сначала станьте минимум team",
                ChatColor.of(colors.red_pepper) + "Ну зачем тебе это? ;Р",
                ChatColor.of(colors.red_pepper) + "Что вы ждали?",
                ChatColor.of(colors.red_pepper) + "Сколько сообщений вы уже просмотрели?",
                ChatColor.of(colors.red_pepper) + "Ты сюда не ходи, ты туда ходи)",
                ChatColor.of(colors.red_pepper) + "Вы подожгли это сообщение 🔥🔥🔥",
                ChatColor.of(colors.red_pepper) + "ಠ_ಠ",
                ChatColor.of(colors.red_pepper) + "( ´･･)ﾉ(._.`)",
                ChatColor.of(colors.red_pepper) + "(　o=^•ェ•)o　┏━┓",
                ChatColor.of(colors.red_pepper) + "(〜￣▽￣)〜",
                ChatColor.of(colors.red_pepper) + "(～o￣3￣)～",
                ChatColor.of(colors.red_pepper) + "༼ つ ◕_◕ ༽つ",
                ChatColor.of(colors.red_pepper) + "(^・ω・^ )",
                ChatColor.of(colors.red_pepper) + "＼（〇_ｏ）／",
                ChatColor.of(colors.red_pepper) + ".______.",
                ChatColor.of(colors.red_pepper) + "(￣_,￣ )"
        };

        Random random = new Random();
        int index = random.nextInt(messages.length);
        player.sendActionBar(messages[index]);
    }
}
