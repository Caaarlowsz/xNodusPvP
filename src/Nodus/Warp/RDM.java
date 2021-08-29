package Nodus.Warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Main.Main;

public class RDM implements Listener, CommandExecutor {
	public static Main plugin;

	public RDM(final Main main) {
		RDM.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (cmd.getName().equalsIgnoreCase("rdm")) {
			final Player p = (Player) sender;
			final World w = Bukkit.getServer().getWorld(RDM.plugin.getConfig().getString("rdm.world"));
			final double x = RDM.plugin.getConfig().getDouble("rdm.x");
			final double y = RDM.plugin.getConfig().getDouble("rdm.y");
			final double z = RDM.plugin.getConfig().getDouble("rdm.z");
			final Location lobby = new Location(w, x, y, z);
			lobby.setPitch((float) RDM.plugin.getConfig().getDouble("rdm.pitch"));
			lobby.setYaw((float) RDM.plugin.getConfig().getDouble("rdm.yaw"));
			p.getInventory().clear();
			p.setHealthScale(1.0);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 100));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 100));
			p.sendMessage("§3§l > §7Teleportando em 5 segundos <");
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) RDM.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					p.teleport(lobby);
					p.getInventory().clear();
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("§a[§3!§a]  §aTeleportado!");
					p.setHealthScale(20.0);
					Main.removeAbility(p);
					Main.usandokit.add(p.getName());
					p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
					p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
					final ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
					p.getInventory().setItem(0, espada);
					p.setFireTicks(0);
					Main.giveSoup(p, 35);
					for (final PotionEffect effect : p.getActivePotionEffects()) {
						p.removePotionEffect(effect.getType());
					}
				}
			}, 90L);
			return false;
		}
		return false;
	}
}
