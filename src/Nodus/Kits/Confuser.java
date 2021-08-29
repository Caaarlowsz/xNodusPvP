package Nodus.Kits;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Confuser implements Listener, CommandExecutor {
	public static Main plugin;

	public Confuser(final Main main) {
		Confuser.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("confuser")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.confuser")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Confuser");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.confuser.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Confuser");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Confuser.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Confuser.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	public void onEntityDamage(final EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		final LivingEntity entity = (LivingEntity) e.getEntity();
		final Player p = (Player) e.getDamager();
		if (!Main.confuser.contains(p.getName())) {
			return;
		}
		final Random rand = new Random();
		final int percent = rand.nextInt(100);
		if (percent <= 33) {
			entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 0));
		}
	}
}
