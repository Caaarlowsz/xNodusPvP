package Nodus.Bloquinhos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import Nodus.Main.Main;
import me.confuser.barapi.BarAPI;

public class jump implements Listener {
	Main main;
	public static ArrayList<String> jump;
	ArrayList<String> nofalldamagec;
	ArrayList<String> nofalldamagewaitsd;

	static {
		Nodus.Bloquinhos.jump.jump = new ArrayList<String>();
	}

	public jump(Main plugin) {
		this.nofalldamagec = new ArrayList<String>();
		this.nofalldamagewaitsd = new ArrayList<String>();
		plugin = this.main;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJump(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
			Nodus.Bloquinhos.jump.jump.remove(p.getName());
			final Vector sponge = p.getLocation().getDirection().multiply(0).setY(2.0);
			p.setVelocity(sponge);
			p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0f, 2.0f);
			p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
			Nodus.Bloquinhos.jump.jump.add(p.getName());
			Nodus.Bloquinhos.jump.jump.remove(p.getName());
			Nodus.Bloquinhos.jump.jump.add(p.getName());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJum2p(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.CLAY) {
			Main.economy.depositPlayer(e.getPlayer().getName(), 500.0);
			Bukkit.broadcastMessage("§6O jogador: §7" + e.getPlayer().getName()
					+ " §3Ganhou 500$ por passar o nivel Hard do challenge!");
			p.closeInventory();
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
			Main.removeAbility(p);
			Main.usandokit.add(p.getName());
			p.getInventory().setBoots((ItemStack) null);
			p.getInventory().setChestplate((ItemStack) null);
			p.getInventory().setLeggings((ItemStack) null);
			p.getInventory().setHelmet((ItemStack) null);
			p.setFireTicks(0);
			p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BROWN_MUSHROOM, 64) });
			p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.RED_MUSHROOM, 64) });
			p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BOWL, 64) });
			final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("challenge.world"));
			Main.giveSoup(p, 33);
			final double x = Main.plugin.getConfig().getDouble("challenge.x");
			final double y = Main.plugin.getConfig().getDouble("challenge.y");
			final double z = Main.plugin.getConfig().getDouble("challenge.z");
			final Location lobby = new Location(w, x, y, z);
			lobby.setPitch((float) Main.plugin.getConfig().getDouble("challenge.pitch"));
			lobby.setYaw((float) Main.plugin.getConfig().getDouble("challenge.yaw"));
			p.teleport(lobby);
			for (final PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJwum2p(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.HARD_CLAY) {
			Main.economy.depositPlayer(e.getPlayer().getName(), 500.0);
			Bukkit.broadcastMessage(
					"§6O jogador: §7" + e.getPlayer().getName() + " §3Ganhou 1000$ por passar o Parkour!");
			p.closeInventory();
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
			Main.removeAbility(p);
			Main.usandokit.add(p.getName());
			p.getInventory().setBoots((ItemStack) null);
			p.getInventory().setChestplate((ItemStack) null);
			p.getInventory().setLeggings((ItemStack) null);
			p.getInventory().setHelmet((ItemStack) null);
			p.setFireTicks(0);
			final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("parkour.world"));
			final double x = Main.plugin.getConfig().getDouble("parkour.x");
			final double y = Main.plugin.getConfig().getDouble("parkour.y");
			final double z = Main.plugin.getConfig().getDouble("parkour.z");
			final Location lobby = new Location(w, x, y, z);
			lobby.setPitch((float) Main.plugin.getConfig().getDouble("parkour.pitch"));
			lobby.setYaw((float) Main.plugin.getConfig().getDouble("parkour.yaw"));
			p.getInventory().clear();
			p.teleport(lobby);
			p.teleport(lobby);
			for (final PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
		}
	}

	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		if (Main.mlg.contains(player.getName()) && (player.getLocation().getBlock().getType() == Material.WATER
				|| player.getLocation().getBlock().getType() == Material.STATIONARY_WATER)) {
			Main.mlgss(player);
			BarAPI.setMessage(player, "§aAcertou!", 5);
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 999));
		}
	}

	@EventHandler
	public void onPlayerFrentes(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) {
			final Vector sponge = p.getLocation().getDirection().multiply(0.1).setY(5.0);
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, (Object) Material.SPONGE, 1);
			p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
			p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
			p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
			p.getWorld().playSound(p.getLocation(), Sound.STEP_GRASS, 1.0f, 1.0f);
			p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0f, 2.0f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 999));
			p.setVelocity(sponge);
			if (!this.nofalldamagec.contains(p.getName())) {
				this.nofalldamagec.add(p.getName());
			}
		}
	}

	@EventHandler
	public void onPlayerFrente(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK) {
			final Vector sponge = p.getLocation().getDirection().multiply(3.5).setY(0.5);
			p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1);
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 999));
			p.getPlayer().playEffect(p.getPlayer().getEyeLocation(), Effect.STEP_SOUND,
					(Object) Material.DIAMOND_BLOCK);
			p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0f, 2.0f);
			p.setVelocity(sponge);
		}
	}

	@EventHandler
	public void onPlayerCamel(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE
				|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.PACKED_ICE) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1));
		}
	}

	@EventHandler
	public void onPlayerCamel2(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SOUL_SAND
				|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SOUL_SAND) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1));
		}
	}

	@EventHandler
	public void EfeitoDahora(final PlayerMoveEvent e) {
	}
}
