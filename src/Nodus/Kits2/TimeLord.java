package Nodus.Kits2;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class TimeLord implements Listener, CommandExecutor {
	public static Main plugin;
	public static ArrayList<String> frozenPlayers;
	public static ArrayList<Player> cooldownm;

	static {
		TimeLord.frozenPlayers = new ArrayList<String>();
		TimeLord.cooldownm = new ArrayList<Player>();
	}

	public TimeLord(final Main main) {
		TimeLord.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("TimeLord")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.TimeLord")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " TimeLord");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.timelord.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack TimeLord = new ItemStack(Material.WATCH);
					final ItemMeta bow = TimeLord.getItemMeta();
					Habilidade.setAbility(p, "Timelord");
					bow.setDisplayName("§3Break the time!");
					TimeLord.setItemMeta(bow);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { TimeLord });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							TimeLord.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						TimeLord.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public static void TIMELORDHAB(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (player.getItemInHand().getType().equals((Object) Material.WATCH)
				&& event.getAction().name().contains("RIGHT")) {
			if (TimeLord.cooldownm.contains(player)) {
				player.sendMessage(ChatColor.RED + "Nao se exalte meu jovem, aguarde um pouco");
				return;
			}
			if (Main.timelord.contains(event.getPlayer().getName())) {
				for (final Entity target : event.getPlayer().getNearbyEntities(5.0, 5.0, 5.0)) {
					if (target instanceof Player) {
						final Player t = (Player) target;
						TimeLord.frozenPlayers.add(t.getName());
						player.sendMessage(ChatColor.GREEN + "Voce congelou os jogadores a 5 blocos ao seu redor!");
						t.sendMessage(ChatColor.RED + "congelado pelo TimeLord " + player.getName() + "!");
						t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
						TimeLord.cooldownm.add(player);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
							@Override
							public void run() {
								TimeLord.cooldownm.remove(player);
							}
						}, 800L);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
							@Override
							public void run() {
								TimeLord.frozenPlayers.remove(t.getName());
								t.sendMessage(ChatColor.GREEN + "Efeito cabou e.e");
							}
						}, 150L);
					}
				}
			}
		}
	}

	@EventHandler
	public void TIMELORDMOVEALVO(final PlayerMoveEvent e) {
		if (TimeLord.frozenPlayers.contains(e.getPlayer().getName())) {
			e.getPlayer().teleport((Entity) e.getPlayer());
		}
	}
}
