package Nodus.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import me.confuser.barapi.BarAPI;

public class BossKits implements Listener {
	public Main plugin;

	public BossKits(final Main instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void JogadorKIT(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player t = (Player) e.getEntity();
		final Player p = (Player) e.getDamager();
		BarAPI.setMessage(p, " §7 " + t.getDisplayName() + "§b§l >>§f§l " + Habilidade.getAbility(t));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				BarAPI.removeBar(p);
			}
		}, 100L);
	}
}
