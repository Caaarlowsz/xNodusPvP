package Nodus.Kits2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
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
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Sonic implements Listener, CommandExecutor {
	public int boost;
	public static ArrayList<String> Sonic;
	public static HashMap<String, ItemStack[]> Armadura;
	public static Main plugin;
	public static HashMap<String, ItemStack[]> saveinv;
	public static HashMap<String, ItemStack[]> armadura;
	public static HashMap<String, ItemStack[]> Armadura2;
	public static List<Player> cooldownm;

	static {
		Nodus.Kits2.Sonic.Sonic = new ArrayList<String>();
		Nodus.Kits2.Sonic.Armadura = new HashMap<String, ItemStack[]>();
		Nodus.Kits2.Sonic.saveinv = new HashMap<String, ItemStack[]>();
		Nodus.Kits2.Sonic.armadura = new HashMap<String, ItemStack[]>();
		Nodus.Kits2.Sonic.Armadura2 = new HashMap<String, ItemStack[]>();
		Nodus.Kits2.Sonic.cooldownm = new ArrayList<Player>();
	}

	public Sonic(final Main main) {
		this.boost = 6;
		Nodus.Kits2.Sonic.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("sonic")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Sonic")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Sonic");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Sonic.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					Habilidade.setAbility(p, "Sonic");
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("?cSword");
					final ItemStack bow = new ItemStack(Material.LAPIS_BLOCK);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("?bSonic!");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Nodus.Kits2.Sonic.plugin.getConfig().getString("Sem_Permiss\u00c3?o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Nodus.Kits2.Sonic.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void SonicLick(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getPlayer().getItemInHand().getType() == Material.LAPIS_BLOCK
				&& Main.Sonic.contains(event.getPlayer().getName())) {
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK
					|| event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				event.setCancelled(true);
			}
			if (Nodus.Kits2.Sonic.cooldownm.contains(p)) {
				p.sendMessage("?lAguarde o cooldown acabar!");
				return;
			}
			Nodus.Kits2.Sonic.cooldownm.add(p);
			DeshFire.fall.add(p.getName());
			p.setVelocity(p.getEyeLocation().getDirection().multiply(this.boost).add(new Vector(0, 0, 0)));
			p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.SMOKE, 10, 0);
			for (final Entity pertos : p.getNearbyEntities(8.0, 8.0, 8.0)) {
				if (pertos instanceof Player) {
					((Player) pertos).damage(10.0);
					pertos.setVelocity(new Vector(0.1, 0.0, 0.1));
					((Player) pertos).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 105, 105));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 105, 105));
				}
			}
			final ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
			final LeatherArmorMeta kCapacete = (LeatherArmorMeta) Capacete.getItemMeta();
			kCapacete.setColor(Color.BLUE);
			Capacete.setItemMeta((ItemMeta) kCapacete);
			final ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
			final LeatherArmorMeta kPeitoral = (LeatherArmorMeta) Peitoral.getItemMeta();
			kPeitoral.setColor(Color.BLUE);
			Peitoral.setItemMeta((ItemMeta) kPeitoral);
			final ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
			final LeatherArmorMeta kCalss = (LeatherArmorMeta) Calss.getItemMeta();
			kCalss.setColor(Color.BLUE);
			Calss.setItemMeta((ItemMeta) kCalss);
			final ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
			final LeatherArmorMeta kBota = (LeatherArmorMeta) Capacete.getItemMeta();
			kBota.setColor(Color.BLUE);
			Bota.setItemMeta((ItemMeta) kBota);
			Nodus.Kits2.Sonic.Armadura.put(p.getName(), p.getInventory().getArmorContents());
			p.getInventory().setHelmet(Capacete);
			p.getInventory().setChestplate(Peitoral);
			p.getInventory().setLeggings(Calss);
			p.getInventory().setBoots(Bota);
			p.updateInventory();
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.getInventory().setArmorContents((ItemStack[]) null);
					p.updateInventory();
					DeshFire.fall.remove(p.getName());
				}
			}, 50L);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					Nodus.Kits2.Sonic.cooldownm.remove(p);
					Main.Sonic.remove(p.getName());
					p.sendMessage("?3?oVoce pode usar novamente!");
					p.getWorld().playSound(p.getLocation(), Sound.BURP, 5.0f, 5.0f);
				}
			}, 700L);
		}
	}
}
