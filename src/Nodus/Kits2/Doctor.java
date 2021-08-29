package Nodus.Kits2;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
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
import org.bukkit.potion.PotionEffect;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;
import me.confuser.barapi.BarAPI;

public class Doctor implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Doctor.cooldown = new HashMap<String, Long>();
	}

	public Doctor(final Main main) {
		Doctor.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Doctor")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.doctor")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Doctor");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.kr.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack bow = new ItemStack(Material.SLIME_BALL);
					final ItemMeta bowmeta = bow.getItemMeta();
					Habilidade.setAbility(p, "Doctor");
					bowmeta.setDisplayName("§bAmbulancia portatil ;-; ");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Doctor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Doctor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void flash(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.kr.contains(p.getName()) && p.getItemInHand().getType() == Material.SLIME_BALL) {
			if (!Doctor.cooldown.containsKey(p.getName())
					|| Doctor.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				BarAPI.setMessage(p, "§7Voce foi curado!", 6);
				for (final PotionEffect effect : p.getActivePotionEffects()) {
					p.removePotionEffect(effect.getType());
				}
				Doctor.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5L));
				return;
			}
			p.sendMessage(ChatColor.GRAY + "Fazendo mais remedio! Tempo restante: "
					+ TimeUnit.MILLISECONDS.toSeconds(Doctor.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos.");
		}
	}
}
