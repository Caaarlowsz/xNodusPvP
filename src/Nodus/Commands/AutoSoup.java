package Nodus.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Main.Main;

public class AutoSoup implements CommandExecutor, Listener {
	public static HashMap<String, ItemStack[]> saveinv;
	public static HashMap<String, ItemStack[]> armadura;

	static {
		AutoSoup.saveinv = new HashMap<String, ItemStack[]>();
		AutoSoup.armadura = new HashMap<String, ItemStack[]>();
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String arg2, final String[] args) {
		final Player p = (Player) sender;
		if (p.hasPermission("nodus.autosoup") && cmd.getName().equalsIgnoreCase("autosoup")) {
			if (args.length == 0) {
				p.sendMessage("/autosoup <Jogador>");
			}
			final ItemStack knokbackf = new ItemStack(Material.MUSHROOM_SOUP);
			final ItemMeta knokbackaf = knokbackf.getItemMeta();
			knokbackaf.setDisplayName("§6Sopa-AutoSoup");
			knokbackf.setItemMeta(knokbackaf);
			if (args.length == 1) {
				final Player o = Bukkit.getPlayer(args[0]);
				if (o != null) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							AutoSoup.saveinv.put(o.getName(), o.getInventory().getContents());
							AutoSoup.armadura.put(o.getName(), o.getInventory().getArmorContents());
							o.getInventory().clear();
							o.setHealth(4.0);
							((HumanEntity) sender).openInventory((Inventory) o.getInventory());
						}
					}, 1L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setItem(15, knokbackf);
							o.setHealth(4.0);
						}
					}, 20L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setItem(16, knokbackf);
							o.setHealth(4.0);
						}
					}, 60L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setItem(17, knokbackf);
							o.setHealth(4.0);
						}
					}, 80L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setItem(18, knokbackf);
							o.setHealth(4.0);
						}
					}, 100L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setItem(19, knokbackf);
							o.getInventory().setItem(20, knokbackf);
							o.setHealth(4.0);
						}
					}, 120L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							sender.sendMessage("§aSopas Tomadas: §c" + AutoSoup.getAmount(o, Material.BOWL) + "/5");
						}
					}, 135L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							o.getInventory().setContents((ItemStack[]) AutoSoup.saveinv.get(o.getName()));
							o.getInventory().setArmorContents((ItemStack[]) AutoSoup.armadura.get(o.getName()));
						}
					}, 140L);
				}
				p.sendMessage("§6Player Testado");
				Player[] arrayOfPlayer;
				for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
					final Player p2 = arrayOfPlayer[i];
					p2.hasPermission("nodus.admin");
				}
			}
		}
		return false;
	}

	public static int getAmount(final Player p, final Material m) {
		int amount = 0;
		ItemStack[] arrayOfItemStack;
		for (int j = (arrayOfItemStack = p.getInventory().getContents()).length, i = 0; i < j; ++i) {
			final ItemStack item = arrayOfItemStack[i];
			if (item != null && item.getType() == m && item.getAmount() > 0) {
				amount += item.getAmount();
			}
		}
		return amount;
	}
}
