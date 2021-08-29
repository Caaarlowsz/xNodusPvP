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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Resouper implements Listener, CommandExecutor {
	public static Main plugin;

	public Resouper(final Main main) {
		Resouper.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("resouper")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.resouper")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Resouper");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.resouper.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Resouper");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Resouper.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Resouper.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onKill(final PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			final Player k = e.getEntity().getKiller();
			if (Main.resouper.contains(k.getName())) {
				k.getInventory().remove(Material.BOWL);
				try {
					for (int i = 0; i < 34; ++i) {
						k.getInventory().setItem(k.getInventory().firstEmpty(), new ItemStack(Material.MUSHROOM_SOUP));
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
				}
				k.sendMessage(ChatColor.GREEN + "Resoup Automatico Concluido!");
				k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
			}
		}
	}
}
