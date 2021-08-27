package Nodus.InventariosFake;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class GuiEspada implements Listener
{
    public static void guiEspada(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 9, "§b§lEspadas");
        final ItemStack vidro = new ItemStack(Material.STONE_SWORD);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§9§lEspada Reserva!");
        vidro.setItemMeta(metav);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
    }
}
