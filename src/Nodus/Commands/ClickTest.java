package Nodus.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import Nodus.Main.Main;

public class ClickTest implements CommandExecutor, Listener {
	public static ArrayList<Player> sela;
	public static ClickTest m;
	PluginManager pm;
	public static Plugin plugin;

	static {
		ClickTest.sela = new ArrayList<Player>();
	}

	public ClickTest() {
		this.pm = Bukkit.getServer().getPluginManager();
	}

	@EventHandler
	public void bater(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (ClickTest.sela.contains(p) && e.getAction() == Action.LEFT_CLICK_AIR) {
			p.setLevel(p.getLevel() + 1);
			final Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
			final Objective o = sc.registerNewObjective("Jailson7", "Dl6ecia");
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			o.setDisplayName("§bClickTest");
			final org.bukkit.scoreboard.Score kl = o.getScore(Bukkit.getOfflinePlayer("§bClicks » §7" + p.getLevel()));
			final org.bukkit.scoreboard.Score a1 = o.getScore(Bukkit.getOfflinePlayer("§7§m----------"));
			final org.bukkit.scoreboard.Score n1 = o.getScore(Bukkit.getOfflinePlayer("§7§m---------"));
			a1.setScore(3);
			kl.setScore(1);
			n1.setScore(0);
			p.setScoreboard(sc);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (ClickTest.sela.contains(p)) {
						ClickTest.sela.remove(p);
						Main.setScoreBoard(p);
						p.sendMessage("§3Voce fez em 10 segundos §b" + p.getLevel() + " §3clicks");
						p.sendMessage("§3Voce fez a media §b" + p.getLevel() / 10 + " §3clicks por segundo!");
						if (p.getLevel() >= 200) {
							Player[] arrayOfPlayer;
							for (int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length,
									i = 0; i < j; ++i) {
								final Player on = arrayOfPlayer[i];
								if (on.isOp()) {
									on.sendMessage("§nO jogador " + p.getDisplayName()
											+ " §3talvez esteja a usar macro! §bFez §6" + p.getLevel() + "§3clicks!");
								}
							}
						}
					}
				}
			}, 200L);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.setLevel(0);
				}
			}, 260L);
		}
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("clicktest")) {
			final Player p = (Player) sender;
			p.setLevel(0);
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("§3Aguarde 5 Segundos");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("§3Aguarde 4 Segundos");
				}
			}, 20L);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("§3Aguarde 3 Segundos");
				}
			}, 40L);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("§3Aguarde 2 Segundos");
				}
			}, 60L);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("§3Aguarde 1 Segundo");
				}
			}, 80L);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.sendMessage("§6Clique como jamais clicou :O");
					p.sendMessage("§3Clique no §4AR");
					ClickTest.sela.add(p);
				}
			}, 100L);
		}
		return false;
	}
}
