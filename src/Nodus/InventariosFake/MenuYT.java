package Nodus.InventariosFake;

import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.*;

public class MenuYT implements Listener
{
    public static Main plugin;
    
    public MenuYT(final Main main) {
        MenuYT.plugin = main;
    }
    
    public static void MenuYT(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 27, "§b§l -> Youtuber | Pro <-");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta vidrometa = vidro.getItemMeta();
        vidrometa.setDisplayName("");
        vidro.setItemMeta(vidrometa);
        final ItemStack yt = new ItemStack(Material.FIRE);
        final ItemMeta ytMeta = yt.getItemMeta();
        ytMeta.setDisplayName("§6Requisitos");
        yt.setItemMeta(ytMeta);
        final ItemStack Y = new ItemStack(Material.DIAMOND_BLOCK);
        final ItemMeta YM = Y.getItemMeta();
        YM.setDisplayName("Requisitos Youtuber");
        final ArrayList descY = new ArrayList();
        descY.add(ChatColor.translateAlternateColorCodes('&', MenuYT.plugin.getConfig().getString("Yt2")));
        YM.setLore((List)descY);
        Y.setItemMeta(YM);
        final ItemStack Y2 = new ItemStack(Material.GOLD_BLOCK);
        final ItemMeta YM2 = Y2.getItemMeta();
        YM2.setDisplayName("Requisitos Pro");
        final ArrayList descY2 = new ArrayList();
        descY2.add(ChatColor.translateAlternateColorCodes('&', MenuYT.plugin.getConfig().getString("Yt1")));
        YM2.setLore((List)descY2);
        Y2.setItemMeta(YM2);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, yt);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        inv.setItem(9, vidro);
        inv.setItem(10, vidro);
        inv.setItem(11, vidro);
        inv.setItem(12, Y);
        inv.setItem(13, vidro);
        inv.setItem(14, Y2);
        inv.setItem(15, vidro);
        inv.setItem(16, vidro);
        inv.setItem(17, vidro);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, vidro);
        inv.setItem(21, vidro);
        inv.setItem(22, vidro);
        inv.setItem(23, vidro);
        inv.setItem(24, vidro);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void Clicar(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§b§l -> Youtuber | Pro <-") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.DIAMOND_BLOCK) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.GOLD_BLOCK) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.THIN_GLASS) {
                e.setCancelled(true);
            }
        }
    }
}
