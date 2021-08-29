package n;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.Main.Main;

public class d implements Listener {
	public static String PegarConfig(final String nomeconfig, final Player p) {
		return ChatColor.translateAlternateColorCodes('&',
				Main.plugin.getConfig().getString(nomeconfig).replaceAll("%p", p.getName()));
	}

	public static String get(final String nomeconfig) {
		return ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString(nomeconfig));
	}
}
