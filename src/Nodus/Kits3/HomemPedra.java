package Nodus.Kits3;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class HomemPedra implements Listener, CommandExecutor {
	public static Main plugin;

	public HomemPedra(final Main main) {
		HomemPedra.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("homempedra")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.homempedra")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "HomemPedra");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.homempedra.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "HomemPedra");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							HomemPedra.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						HomemPedra.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onPlayerCamel(final PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE
				|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.COBBLESTONE)
				&& Main.homempedra.contains(p.getName())) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
		}
	}
}
