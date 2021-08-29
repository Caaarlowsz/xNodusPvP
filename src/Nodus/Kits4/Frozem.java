package Nodus.Kits4;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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

public class Frozem implements Listener, CommandExecutor {
	public static Main plugin;
	private ArrayList<Player> cooldown;

	public Frozem(final Main main) {
		this.cooldown = new ArrayList<Player>();
		Frozem.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Frozem")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Frozem")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Frozem");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Frozem.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					final ItemStack espada2 = new ItemStack(Material.SUGAR);
					final ItemMeta espadameta2 = espada2.getItemMeta();
					espadameta2.setDisplayName("§bLerigou");
					espada2.setItemMeta(espadameta2);
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Frozem");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { espada2 });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Frozem.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Frozem.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void frosty(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		final ItemStack i = p.getItemInHand();
		if (i.getType() == Material.SUGAR && i.hasItemMeta()
				&& i.getItemMeta().getDisplayName().equalsIgnoreCase("§bLerigou") && Main.Frozem.contains(p.getName())) {
			final Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			if (b.getType() == Material.SNOW_BLOCK) {
				p.removePotionEffect(PotionEffectType.SPEED);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
			}
		}
	}

	@EventHandler
	public void onFrozen(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() != null && p.getItemInHand().getItemMeta() != null
				&& p.getItemInHand().getItemMeta().getDisplayName() != null
				&& p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§bLerigou")
				&& e.getAction() == Action.RIGHT_CLICK_BLOCK
				&& e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(1.0, 1.0, 0.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(0.0, 1.0, 1.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(-1.0, 1.0, 0.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(0.0, 1.0, -1.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(-1.0, 1.0, -1.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(1.0, 1.0, 1.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(-1.0, 1.0, 1.0).getBlock().getType() == Material.AIR
				&& e.getClickedBlock().getLocation().add(1.0, 1.0, -1.0).getBlock().getType() == Material.AIR) {
			if (!this.cooldown.contains(p)) {
				e.setCancelled(true);
				this.cooldown.add(p);
				e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(1.0, 1.0, 0.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(0.0, 1.0, 1.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(-1.0, 1.0, 0.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(0.0, 1.0, -1.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(-1.0, 1.0, -1.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(1.0, 1.0, 1.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(-1.0, 1.0, 1.0).getBlock().setType(Material.PACKED_ICE);
				e.getClickedBlock().getLocation().add(1.0, 1.0, -1.0).getBlock().setType(Material.PACKED_ICE);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						e.getClickedBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(1.0, 1.0, 0.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(0.0, 1.0, 1.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(-1.0, 1.0, 0.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(0.0, 1.0, -1.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(-1.0, 1.0, -1.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(1.0, 1.0, 1.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(-1.0, 1.0, 1.0).getBlock().setType(Material.AIR);
						e.getClickedBlock().getLocation().add(1.0, 1.0, -1.0).getBlock().setType(Material.AIR);
						p.sendMessage("§cSUMIU!");
					}
				}, 200L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						Frozem.this.cooldown.remove(p);
						p.sendMessage("§aCooldown acabou");
					}
				}, 300L);
			} else {
				p.sendMessage("§CCooldown.");
			}
		}
	}
}
