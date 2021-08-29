package Nodus.Managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import Nodus.Main.Main;

public class Stats implements Listener {
	Main a;
	public final ArrayList<Player> b;
	public final ArrayList<Player> c;
	public final ArrayList<Player> d;
	public final ArrayList<Player> e;
	public final ArrayList<Player> f;
	public final ArrayList<Player> g;

	public Stats(final Main plugin) {
		this.b = new ArrayList<Player>();
		this.c = new ArrayList<Player>();
		this.d = new ArrayList<Player>();
		this.e = new ArrayList<Player>();
		this.f = new ArrayList<Player>();
		this.g = new ArrayList<Player>();
		this.a = plugin;
	}

	public void a(final Player player, final Player killer) {
		if (player.getKiller() instanceof Player) {
			try {
				this.a.getConfig().set("jogador." + killer.getName().toLowerCase() + ".kill-streak", (Object) (this.a
						.getConfig().getDouble("jogador." + killer.getName().toLowerCase() + ".kill-streak") + 1.0));
				if (this.a.getConfig().getInt("jogador." + killer.getName().toLowerCase() + ".kill-streak") >= this.a
						.getConfig().getDouble("jogador." + killer.getName().toLowerCase() + ".max-kill-streak")) {
					this.a.getConfig().set("jogador." + killer.getName().toLowerCase() + ".max-kill-streak",
							(Object) this.a.getConfig()
									.getDouble("jogador." + killer.getName().toLowerCase() + ".kill-streak"));
				}
			} catch (Exception e) {
				this.a.getConfig().set("jogador." + killer.getName().toLowerCase() + ".max-kill-streak", (Object) 0);
			}
			try {
				if (this.a.getConfig().getDouble("jogador." + player.getName().toLowerCase() + ".kill-streak") >= this.a
						.getConfig().getInt("jogador." + player.getName().toLowerCase() + ".max-kill-streak")) {
					this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".max-kill-streak",
							(Object) this.a.getConfig()
									.getInt("jogador." + player.getName().toLowerCase() + ".kill-streak"));
				}
			} catch (Exception e) {
				this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".max-kill-streak", (Object) 0);
				this.a.getConfig();
			}
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".kill-streak", (Object) 0);
			this.a.getConfig();
		}
	}

	@EventHandler
	public void a(final PlayerDeathEvent event) {
		final Player player = event.getEntity();
		if (event.getEntity().getKiller() instanceof Player) {
			final Player killer = player.getKiller();
			this.a(player, killer);
			final int i = this.a.getConfig().getInt("jogador." + killer.getName().toLowerCase() + ".kill-streak");
			if (i >= 5 && i % 5 == 0) {
				Bukkit.getServer()
						.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
								this.a.getConfig().getString("messages.killstreak-5")
										.replace("{NUM}", Integer.toString(i)).replace("{PLAYER}", killer.getName())));
			}
		} else {
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".kill-streak", (Object) 0);
		}
	}

	@EventHandler
	public void a(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".name", (Object) player.getName());
		this.a.saveConfig();
		if (this.a.getConfig().get("jogador." + player.getName().toLowerCase() + ".kills") == null) {
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".kills", (Object) 0);
			this.a.saveConfig();
		}
		if (this.a.getConfig().get("jogador." + player.getName().toLowerCase() + ".mortes") == null) {
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".mortes", (Object) 0);
			this.a.saveConfig();
		}
		if (this.a.getConfig().get("jogador." + player.getName().toLowerCase() + ".kill-streak") == null) {
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".kill-streak", (Object) 0);
			this.a.saveConfig();
		}
		if (this.a.getConfig().get("jogador." + player.getName().toLowerCase() + ".max-kill-streak") == null) {
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".max-kill-streak", (Object) 0);
			this.a.saveConfig();
		}
	}

	@EventHandler
	public void b(final PlayerDeathEvent event) {
		final Player player = event.getEntity();
		if (!this.a.getConfig().contains("jogador." + player.getName().toLowerCase() + ".mortes")) {
			final double zip = 0.0;
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".mortes", (Object) zip);
		} else {
			final double l = this.a.getConfig().getDouble("jogador." + player.getName().toLowerCase() + ".mortes");
			this.a.getConfig().set("jogador." + player.getName().toLowerCase() + ".mortes", (Object) (l + 1.0));
		}
		final Long gg = this.a.getConfig().getLong("stats.kills-cooldowns");
		if (player.getKiller() instanceof Player) {
			final Player killer = player.getKiller();
			if (!this.a.getConfig().contains("jogador." + killer.getName().toLowerCase() + ".kills")) {
				this.a.getConfig().set("jogador." + killer.getName().toLowerCase() + ".kills", (Object) 1);
			} else {
				final double i = this.a.getConfig().getDouble("jogador." + killer.getName().toLowerCase() + ".kills");
				this.a.getConfig().set("jogador." + killer.getName().toLowerCase() + ".kills", (Object) (i + 1.0));
			}
			if (this.g.contains(killer)) {
				this.g.remove(killer);
				if (this.a.getConfig().getBoolean("stats.kill-extreme")) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							this.a.getConfig().getString("messages.kill-6").replace("{PLAYER}", killer.getName())));
				}
				return;
			}
			if (this.e.contains(killer)) {
				this.e.remove(killer);
				if (this.a.getConfig().getBoolean("stats.kill-quintuple")) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							this.a.getConfig().getString("messages.kill-5").replace("{PLAYER}", killer.getName())));
				}
				return;
			}
			if (this.d.contains(killer)) {
				this.d.remove(killer);
				this.e.add(killer);
				if (this.a.getConfig().getBoolean("stats.kill-quadruple")) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							this.a.getConfig().getString("messages.kill-4").replace("{PLAYER}", killer.getName())));
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.a, (Runnable) new Runnable() {
					@Override
					public void run() {
						Stats.this.e.remove(killer);
					}
				}, gg * 20L);
				return;
			}
			if (this.c.contains(killer)) {
				this.c.remove(killer);
				this.d.add(killer);
				if (this.a.getConfig().getBoolean("stats.kill-triple")) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							this.a.getConfig().getString("messages.kill-3").replace("{PLAYER}", killer.getName())));
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.a, (Runnable) new Runnable() {
					@Override
					public void run() {
						Stats.this.d.remove(killer);
					}
				}, gg * 20L);
				return;
			}
			if (this.b.contains(killer)) {
				this.b.remove(killer);
				this.c.add(killer);
				if (this.a.getConfig().getBoolean("stats.kill-double")) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							this.a.getConfig().getString("messages.kill-2").replace("{PLAYER}", killer.getName())));
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.a, (Runnable) new Runnable() {
					@Override
					public void run() {
						Stats.this.c.remove(killer);
					}
				}, gg * 20L);
				return;
			}
			if (!this.b.contains(killer) || !this.c.contains(killer) || !this.d.contains(killer)
					|| !this.e.contains(killer) || !this.f.contains(killer)) {
				this.b.add(killer);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.a, (Runnable) new Runnable() {
					@Override
					public void run() {
						Stats.this.b.remove(killer);
					}
				}, gg * 20L);
			}
		}
	}
}
