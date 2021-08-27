package Nodus.Commands;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Head implements CommandExecutor, Listener
{
    Main main;
    
    public Head(Main plugin) {
        plugin = this.main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Comandos para players");
            return true;
        }
        if (sender instanceof Player) {
            final Player player1 = (Player)sender;
            if (args.length != 0 && args.length == 1 && player1.hasPermission("nodus.head")) {
                final ItemStack skullItem = new ItemStack(Material.SKULL_ITEM);
                skullItem.setDurability((short)3);
                final SkullMeta skullMeta = (SkullMeta)skullItem.getItemMeta();
                skullMeta.setOwner(args[0]);
                skullItem.setItemMeta((ItemMeta)skullMeta);
                player1.getInventory().addItem(new ItemStack[] { skullItem });
            }
        }
        return false;
    }
}
