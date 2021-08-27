package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class Espada implements Listener, CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("Espada")) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 4.0f, 4.0f);
            }
            player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
        }
        return true;
    }
}
