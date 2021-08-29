package Nodus.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements Listener {
	@EventHandler
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		if (!event.getPlayer().hasPermission("nodus.chat") && !CopyOfCommandChat.chat) {
			event.getPlayer().sendMessage("§9§lCHAT DESATIVADO!");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onColorandChangeChat(final PlayerChatEvent e) {
		final Player p = e.getPlayer();
		if (!p.hasPermission("chat.color")) {
			e.setFormat("§7 " + p.getDisplayName() + " §3>>" + "§7 " + e.getMessage());
		} else {
			e.setFormat("§7 " + p.getDisplayName() + " §b>>" + "§7 " + e.getMessage().replaceAll("&", "§"));
		}
	}
}
