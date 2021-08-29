package Nodus.Kits;

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
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Titan implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Titan.cooldown = new HashMap<String, Long>();
	}

	public Titan(final Main main) {
		Titan.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("titan")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.titan")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Titan");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.titan.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Titan");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BEDROCK) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Titan.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Titan.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void interact(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.titan.contains(p.getName()) && p.getItemInHand().getType() == Material.BEDROCK) {
			if (!Titan.cooldown.containsKey(p.getName())
					|| Titan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				p.sendMessage(ChatColor.RED + "§lArmadura em §a100%");
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 3));
				p.playSound(p.getLocation(), Sound.IRONGOLEM_DEATH, 1.0f, 1.0f);
				Titan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
				return;
			}
			p.sendMessage(ChatColor.RED + "Armadura em §a0%");
			p.sendMessage(ChatColor.RED + "Faltam "
					+ TimeUnit.MILLISECONDS.toSeconds(Titan.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos para poder usar novamente.");
		}
	}
}
