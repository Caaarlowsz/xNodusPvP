package Nodus.Event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import Nodus.Main.Habilidade;
import Nodus.Main.Main;

public class QuitEvent implements Listener {
	@EventHandler
	public void Quit(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		Main.removeAbility(p);
		Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("SairMensagem").replaceAll("&", "§")
				.replaceAll("%p", p.getDisplayName()));
		p.getInventory().clear();
		e.setQuitMessage((String) null);
		Habilidade.setAbility(p, "None");
		p.teleport(p.getWorld().getSpawnLocation());
	}
}
