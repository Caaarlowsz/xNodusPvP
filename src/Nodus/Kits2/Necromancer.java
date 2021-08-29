package Nodus.Kits2;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Necromancer implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Necromancer.cooldown = new HashMap<String, Long>();
	}

	public Necromancer(final Main main) {
		Necromancer.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("necromancer")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Necromancer")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Necromancer");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Necromancer.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Necromancer");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.OBSIDIAN) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Necromancer.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Necromancer.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void interact(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.Necromancer.contains(p.getName()) && p.getItemInHand().getType() == Material.OBSIDIAN) {
			if (!Necromancer.cooldown.containsKey(p.getName())
					|| Necromancer.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				final ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
				final LeatherArmorMeta kPeitoral = (LeatherArmorMeta) Peitoral.getItemMeta();
				kPeitoral.setColor(Color.BLACK);
				Peitoral.setItemMeta((ItemMeta) kPeitoral);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 500, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 500, 0));
				p.getInventory().setChestplate((ItemStack) null);
				p.getInventory().setBoots((ItemStack) null);
				p.getInventory().setChestplate(Peitoral);
				p.getInventory().setHelmet(new ItemStack(Material.SKULL_ITEM));
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						p.getInventory().setHelmet((ItemStack) null);
					}
				}, 50L);
				p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1.0f, 1.0f);
				Necromancer.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L));
				return;
			}
			p.sendMessage(ChatColor.RED + "Faltam "
					+ TimeUnit.MILLISECONDS
							.toSeconds(Necromancer.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos para poder usar novamente.");
		}
	}
}
