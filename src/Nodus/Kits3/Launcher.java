package Nodus.Kits3;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Launcher implements Listener, CommandExecutor {
	public static Main plugin;

	public Launcher(final Main main) {
		Launcher.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("launcher")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.launcher")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Launcher");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.launcher.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Launcher");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.FISHING_ROD) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Launcher.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Launcher.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onPlayerHitFishingrodThrower(final PlayerFishEvent event) {
		final Player player = event.getPlayer();
		if (Main.launcher.contains(player.getName()) && event.getCaught() instanceof Player) {
			final Player caught = (Player) event.getCaught();
			if (player.getItemInHand().getType() == Material.FISHING_ROD) {
				caught.setVelocity(new Vector(0, 2, 0));
			}
		}
	}
}
