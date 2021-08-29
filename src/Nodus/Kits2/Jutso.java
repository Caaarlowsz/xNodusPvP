package Nodus.Kits2;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;
import n.Vanish;

public class Jutso implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public ArrayList<String> Cima;
	ArrayList<String> tempo;
	private ArrayList<Block> remover;

	static {
		Jutso.cooldown = new HashMap<String, Long>();
	}

	public Jutso(final Main main) {
		this.Cima = new ArrayList<String>();
		this.tempo = new ArrayList<String>();
		this.remover = new ArrayList<Block>();
		Jutso.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Jutso")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.jutso")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Jutso");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.jutso.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack jutso = new ItemStack(Material.PAPER);
					final ItemMeta bow = jutso.getItemMeta();
					Habilidade.setAbility(p, "Jutso");
					bow.setDisplayName("§3Jutso! ");
					jutso.setItemMeta(bow);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { jutso });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Jutso.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Jutso.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void Clicar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p instanceof Player
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.jutso.contains(p.getName()) && p.getItemInHand().getType() == Material.PAPER) {
			e.setCancelled(true);
			if (!this.tempo.contains(p.getName())) {
				Location Local = e.getPlayer().getLocation();
				Local = Local.getWorld().getHighestBlockAt(Local).getLocation().add(0.0, 0.0, 0.0);
				final Player localPlayer = e.getPlayer();
				for (int x = 0; x <= 0; ++x) {
					for (int z = 0; z <= 0; ++z) {
						final Block a = Local.add((double) x, 0.0, (double) z).getBlock();
						final Block b = Local.add((double) x, 1.0, (double) z).getBlock();
						a.setType(Material.FENCE);
						b.setType(Material.PUMPKIN);
						localPlayer.playSound(localPlayer.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
						localPlayer.getWorld().playEffect(localPlayer.getLocation(), Effect.SMOKE, 10);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 2));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0));
						e.getPlayer().teleport(Local.add(5.0, 8.0, 0.0));
						p.chat(ChatColor.GRAY + "Jutso Substituicao!");
						this.tempo.add(p.getName());
						this.Cima.add(p.getName());
						p.getPlayer().getInventory().setArmorContents((ItemStack[]) null);
						Vanish.makeVanished(p);
						Bukkit.getServer().getScheduler().runTaskLater((Plugin) Jutso.plugin,
								(Runnable) new Runnable() {
									@Override
									public void run() {
										a.setType(Material.AIR);
										b.setType(Material.AIR);
										Jutso.this.remover.remove(a);
										Vanish.makeVisible(p);
										p.sendMessage(ChatColor.BLUE + "Seu Chakra acabou!");
									}
								}, 120L);
					}
				}
			} else {
				p.sendMessage("§c§oCooldown.");
			}
		}
	}

	@EventHandler
	public void Cair(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.Cima.contains(p.getName())) {
			e.setCancelled(true);
			for (final Entity Altura : p.getNearbyEntities(0.0, 0.0, 0.0)) {
				if (Altura instanceof Player) {
					final Player Pular = (Player) Altura;
					final Vector v = p.getLocation().getDirection().multiply(0).setY(0.0);
					Pular.setVelocity(v);
				}
			}
			this.Cima.remove(p.getName());
			Bukkit.getServer().getScheduler().runTaskLater((Plugin) Jutso.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					Jutso.this.tempo.remove(p.getName());
					p.sendMessage("§cChakra pronto");
				}
			}, 450L);
		}
	}
}
