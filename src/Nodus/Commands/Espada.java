package Nodus.Commands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Espada implements Listener, CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("Espada")) {
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 4.0f, 4.0f);
			}
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
		}
		return true;
	}
}
