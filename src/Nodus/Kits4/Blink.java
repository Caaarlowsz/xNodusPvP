package Nodus.Kits4;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Blink implements Listener, CommandExecutor {
	public static Main plugin;
	public int Thorcooldown;
	public String ThorcooldownMessage;

	public Blink(final Main main) {
		this.Thorcooldown = 30;
		this.ThorcooldownMessage = ChatColor.RED + "cooldown.";
		Blink.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Blink")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Blink")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Blink");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Blink.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					final ItemStack espada2 = new ItemStack(Material.QUARTZ);
					final ItemMeta espadameta2 = espada2.getItemMeta();
					espada2.setItemMeta(espadameta2);
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Blink");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { espada2 });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Blink.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Blink.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void HabilidadeBlink(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (!Main.Blink.contains(p.getName())) {
			return;
		}
		if (!e.getAction().name().contains("RIGHT")) {
			return;
		}
		if (p.getItemInHand().getType() != Material.QUARTZ) {
			return;
		}
		if (Main.cooldown.contains(p.getName())) {
			return;
		}
		Main.cooldown.add(p.getName());
		Main.invencivel.add(p);
		p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1.0f, 1.0f);
		p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.SMOKE, 5);
		final Block b = p.getTargetBlock(null, 9).getRelative(BlockFace.UP);
		final Location loc = b.getLocation();
		loc.setPitch(p.getLocation().getPitch());
		loc.setYaw(p.getLocation().getYaw());
		p.teleport(loc);
		loc.setY(p.getLocation().getY() - 1.0);
		loc.getBlock().setType(Material.LEAVES);
		loc.getBlock().setType(Material.LEAVES);
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 99));
		p.getItemInHand().setType(Material.QUARTZ);
		final Material material = b.getType();
		final byte dataValue = b.getData();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Blink.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				if (Main.Blink.contains(p.getName())) {
					e.getPlayer().sendMessage("§aCooldown acabou.");
					Main.cooldown.remove(e.getPlayer().getName());
					ItemStack[] arrayOfItemStack;
					for (int j = (arrayOfItemStack = e.getPlayer().getInventory().getContents()).length,
							i = 0; i < j; ++i) {
						final ItemStack a = arrayOfItemStack[i];
						if (a != null && a.getType() == Material.QUARTZ) {
							a.setType(Material.QUARTZ);
						}
					}
				}
			}
		}, 80L);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Blink.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				if (Main.Blink.contains(p.getName())) {
					b.setTypeIdAndData(material.getId(), dataValue, true);
					if (loc.getBlock().getType() == Material.LEAVES) {
						loc.getBlock().setType(Material.AIR);
					}
				}
			}
		}, 100L);
		Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Blink.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				Main.invencivel.remove(p);
			}
		}, 0L);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void placeredstonedesligada(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (Main.Blink.contains(p.getName())) {
			e.setCancelled(true);
			p.updateInventory();
		}
	}

	@EventHandler
	public void fallDamageRemove(final EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (Main.invencivel.contains(event.getEntity())) {
			event.setCancelled(true);
		}
	}
}
