package Nodus.InventariosFake;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiEspada implements Listener {
	public static void guiEspada(final Player p) {
		final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder) p, 9, "§b§lEspadas");
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
