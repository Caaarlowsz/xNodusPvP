package Nodus.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Main.Main;
import me.confuser.barapi.BarAPI;

public class Refil implements CommandExecutor, Listener {
	public static Main plugin;
	public List<String> weak;

	public Refil(final Main kitPvP) {
		this.weak = new ArrayList<String>();
		Refil.plugin = kitPvP;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		final ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
		final ItemMeta sMeta = sopa.getItemMeta();
		sMeta.setDisplayName("?b?oRefill");
		sopa.setItemMeta(sMeta);
		final Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("refill")) {
			final ItemStack[] inventoryContents = player.getInventory().getContents();
			final ItemStack[] armorContents = player.getInventory().getArmorContents();
			player.getInventory().clear();
			player.getInventory().setArmorContents((ItemStack[]) null);
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 9));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 9));
			Refil.plugin.weak.add(player.getName());
			player.playSound(player.getLocation(), Sound.ZOMBIE_WOOD, 10.0f, 1.0f);
			BarAPI.setMessage(player, ChatColor.GOLD + "Resoup em 5 segundos!", 5);
			player.setHealth(0.5);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9));
			player.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK, 1));
			Refil.plugin.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Refil.plugin,
					(Runnable) new Runnable() {
						@Override
						public void run() {
							player.getInventory().setArmorContents(armorContents);
							player.getInventory().setContents(inventoryContents);
							Refil.plugin.weak.remove(player.getName());
							BarAPI.setMessage(player, ChatColor.RED + "Resoup concluido", 1);
							player.playSound(player.getLocation(), Sound.ZOMBIE_REMEDY, 10.0f, 1.0f);
							player.setHealth(20.0);
							final PlayerInventory inv = player.getInventory();
							ItemStack[] arrayOfItemStack;
							for (int descpyro1 = (arrayOfItemStack = inv.getContents()).length,
									metapyro1 = 0; metapyro1 < descpyro1; ++metapyro1) {
								final ItemStack item = arrayOfItemStack[metapyro1];
								if (item == null) {
									inv.setItem(inv.firstEmpty(), sopa);
								}
							}
						}
					}, 100L);
		}
		return false;
	}
}
