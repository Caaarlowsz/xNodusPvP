package Nodus.Kits3;

import java.util.HashMap;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Hulk implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Hulk.cooldown = new HashMap<String, Long>();
	}

	public Hulk(final Main main) {
		Hulk.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("hulk")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.hulk")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Hulk");
					p.sendMessage("§cUse a mao para pegar os inimigos!");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.hulk.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Hulk");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.AIR) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Hulk.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Hulk.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void pegar(final PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			final Player r = (Player) e.getRightClicked();
			if (Main.hulk.contains(p.getName())
					&& (!Hulk.cooldown.containsKey(p.getName())
							|| Hulk.cooldown.get(p.getName()) <= System.currentTimeMillis())
					&& p.getItemInHand().getType() == Material.AIR) {
				e.setCancelled(true);
				p.updateInventory();
				p.setPassenger((Entity) r);
				p.sendMessage(ChatColor.GOLD + "Voce pegou o player: " + ChatColor.WHITE + r.getName());
				r.sendMessage(ChatColor.GOLD + "Voce foi pego pelo Hulk: " + ChatColor.WHITE + p.getName());
			}
		}
	}

	@EventHandler
	public static void playerInteract(final PlayerInteractEvent event) {
		if (!event.getAction().equals((Object) Action.LEFT_CLICK_AIR)) {
			return;
		}
		final Player player = event.getPlayer();
		if (player.getPassenger() == null || !(player.getPassenger() instanceof Player)) {
			return;
		}
		final Player pass = (Player) player.getPassenger();
		player.eject();
		pass.damage(0.0, (Entity) player);
		pass.setVelocity(new Vector(pass.getVelocity().getX(), 1.0, pass.getVelocity().getZ()));
		pass.sendMessage(ChatColor.RED + "Voc\u00ea foi jogado por " + ChatColor.DARK_RED + player.getName());
	}
}
