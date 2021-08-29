package Nodus.Kits3;

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

public class Ghoul implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Ghoul.cooldown = new HashMap<String, Long>();
	}

	public Ghoul(final Main main) {
		Ghoul.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("ghoul")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.ghoul")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Ghoul");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.ghoul.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Ghoul");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ROTTEN_FLESH) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Ghoul.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Ghoul.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void interact(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.ghoul.contains(p.getName()) && p.getItemInHand().getType() == Material.ROTTEN_FLESH) {
			if (!Ghoul.cooldown.containsKey(p.getName())
					|| Ghoul.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				p.sendMessage(ChatColor.RED + "§lMinha fome nao pode ser saciada!");
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 3));
				p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 500, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 2));
				p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1.0f, 1.0f);
				Ghoul.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
				return;
			}
			p.sendMessage(ChatColor.RED + "Faltam "
					+ TimeUnit.MILLISECONDS.toSeconds(Ghoul.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos para poder usar novamente.");
		}
	}
}
