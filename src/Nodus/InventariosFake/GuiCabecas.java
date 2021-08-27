package Nodus.InventariosFake;

import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.*;

public class GuiCabecas implements Listener
{
    public static Main plugin;
    
    public GuiCabecas(final Main main) {
        GuiCabecas.plugin = main;
    }
    
    public static void menu(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 27, "§aHeads");
        final ItemStack vidro = new ItemStack(Material.IRON_FENCE);
        final ItemMeta vidrometa = vidro.getItemMeta();
        vidrometa.setDisplayName(" ");
        vidro.setItemMeta(vidrometa);
        final ItemStack vidro2 = new ItemStack(Material.STAINED_GLASS_PANE);
        final ItemMeta vidrometa2 = vidro2.getItemMeta();
        vidrometa2.setDisplayName(" ");
        vidro2.setItemMeta(vidrometa2);
        final ItemStack folha = new ItemStack(Material.FIRE);
        final ItemMeta folhameta = folha.getItemMeta();
        folhameta.setDisplayName(ChatColor.translateAlternateColorCodes('&', GuiCabecas.plugin.getConfig().getString("NomeServer")));
        folha.setItemMeta(folhameta);
        final ItemStack a = new ItemStack(Material.JACK_O_LANTERN);
        final ItemStack b = new ItemStack(Material.BOOKSHELF);
        final ItemStack c = new ItemStack(Material.DISPENSER);
        final ItemStack d = new ItemStack(Material.GLASS);
        final ItemStack e = new ItemStack(Material.PISTON_STICKY_BASE);
        final ItemStack f = new ItemStack(Material.WORKBENCH);
        final ItemStack g = new ItemStack(Material.COBBLESTONE_STAIRS);
        final ItemStack h = new ItemStack(Material.TNT);
        inv.setItem(0, folha);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro2);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, folha);
        inv.setItem(9, vidro);
        inv.setItem(10, vidro);
        inv.setItem(11, a);
        inv.setItem(12, b);
        inv.setItem(13, c);
        inv.setItem(14, d);
        inv.setItem(15, e);
        inv.setItem(16, vidro);
        inv.setItem(17, vidro);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, f);
        inv.setItem(21, vidro);
        inv.setItem(22, vidro);
        inv.setItem(23, vidro);
        inv.setItem(24, h);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlaye4254rCLic54kInventr432y15(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§aHeads") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            p.closeInventory();
            if (e.getCurrentItem().getType() == Material.JACK_O_LANTERN) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
                return;
            }
            if (e.getCurrentItem().getType() == Material.BOOKSHELF) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.BOOKSHELF));
                return;
            }
            if (e.getCurrentItem().getType() == Material.DISPENSER) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.DISPENSER));
                return;
            }
            if (e.getCurrentItem().getType() == Material.GLASS) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.GLASS));
                return;
            }
            if (e.getCurrentItem().getType() == Material.PISTON_STICKY_BASE) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.PISTON_STICKY_BASE));
                return;
            }
            if (e.getCurrentItem().getType() == Material.TNT) {
                e.setCancelled(true);
                p.getInventory().setHelmet(new ItemStack(Material.TNT));
                return;
            }
            if (e.getCurrentItem().getType() == Material.WORKBENCH) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().setHelmet(new ItemStack(Material.WORKBENCH));
            }
        }
    }
}
