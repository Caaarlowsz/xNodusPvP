package Nodus.InventariosFake;

import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import Nodus.Event.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.plugin.*;

public class Selector implements Listener
{
    public static Main plugin;
    
    public Selector(final Main main) {
        Selector.plugin = main;
    }
    
    public static void guiWarp(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 54, "§2" + ChatColor.translateAlternateColorCodes('&', Selector.plugin.getConfig().getString("NomeServer")));
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
        vidro.setDurability((short)15);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§5§o");
        vidro.setItemMeta(metav);
        final ItemStack vidro2 = new ItemStack(Material.STAINED_GLASS_PANE);
        vidro2.setDurability((short)15);
        final ItemMeta vidro3m = vidro2.getItemMeta();
        vidro3m.setDisplayName(" ");
        vidro2.setItemMeta(vidro3m);
        final ItemStack vidro3 = new ItemStack(Material.VINE);
        final ItemMeta vidro1m = vidro3.getItemMeta();
        vidro1m.setDisplayName("§c ");
        vidro3.setItemMeta(vidro1m);
        final ItemStack itens = new ItemStack(Material.GLOWSTONE_DUST);
        final ItemMeta itensm = itens.getItemMeta();
        itensm.setDisplayName("§3Shop De Itens");
        itens.setItemMeta(itensm);
        final ItemStack itens2 = new ItemStack(Material.MAGMA_CREAM);
        final ItemMeta itensm2 = itens.getItemMeta();
        itensm2.setDisplayName("§6Click-Test");
        itens2.setItemMeta(itensm2);
        final ItemStack b = new ItemStack(Material.COMPASS);
        final ItemMeta c = itens.getItemMeta();
        c.setDisplayName("§2Warps");
        b.setItemMeta(c);
        final ItemStack z = new ItemStack(Material.BLAZE_POWDER);
        final ItemMeta x = itens.getItemMeta();
        x.setDisplayName("§6Informa\u00e7oes");
        z.setItemMeta(x);
        final ItemStack f = new ItemStack(Material.APPLE);
        final ItemMeta g = itens.getItemMeta();
        g.setDisplayName("§2Cabecas");
        f.setItemMeta(g);
        final ItemStack f2 = new ItemStack(Material.CHEST);
        final ItemMeta g2 = itens.getItemMeta();
        g2.setDisplayName("§2Todos os Kits");
        f2.setItemMeta(g2);
        final ItemStack s = new ItemStack(Material.IRON_FENCE);
        final ItemMeta sm = itens.getItemMeta();
        sm.setDisplayName("§a");
        s.setItemMeta(sm);
        final ItemStack s2 = new ItemStack(Material.DIAMOND);
        final ItemMeta sm2 = itens.getItemMeta();
        sm2.setDisplayName("§bYoutuber");
        s2.setItemMeta(sm2);
        final ItemStack sa = new ItemStack(Material.VINE);
        sa.setDurability((short)15);
        final ItemMeta saa = itens.getItemMeta();
        saa.setDisplayName("§a");
        sa.setItemMeta(saa);
        final ItemStack so = new ItemStack(Material.MUSHROOM_SOUP);
        final ItemMeta so2 = itens.getItemMeta();
        so2.setDisplayName("§6Modo sopa!");
        so.setItemMeta(so2);
        final ItemStack so3 = new ItemStack(Material.POTION, 1, (short)8229);
        final ItemMeta so4 = itens.getItemMeta();
        so4.setDisplayName("§dModo Potion");
        so3.setItemMeta(so4);
        final ItemStack so5 = new ItemStack(Material.SLIME_BALL);
        final ItemMeta so6 = itens.getItemMeta();
        so6.setDisplayName("§2Status");
        so5.setItemMeta(so6);
        final ItemStack mad = new ItemStack(Material.LAVA_BUCKET);
        final ItemMeta madm = itens.getItemMeta();
        madm.setDisplayName("§4" + ChatColor.translateAlternateColorCodes('&', Selector.plugin.getConfig().getString("Anuncio")));
        mad.setItemMeta(madm);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, f2);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        inv.setItem(9, so3);
        inv.setItem(10, vidro);
        inv.setItem(11, vidro3);
        inv.setItem(12, vidro);
        inv.setItem(13, itens2);
        inv.setItem(14, vidro);
        inv.setItem(15, vidro3);
        inv.setItem(16, vidro);
        inv.setItem(17, so);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, vidro3);
        inv.setItem(21, s);
        inv.setItem(22, so5);
        inv.setItem(23, s);
        inv.setItem(24, vidro3);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        inv.setItem(27, b);
        inv.setItem(28, vidro);
        inv.setItem(29, vidro3);
        inv.setItem(30, s);
        inv.setItem(31, mad);
        inv.setItem(32, s);
        inv.setItem(33, vidro3);
        inv.setItem(34, vidro);
        inv.setItem(35, s2);
        inv.setItem(36, vidro);
        inv.setItem(37, vidro);
        inv.setItem(38, vidro3);
        inv.setItem(39, vidro);
        inv.setItem(40, vidro);
        inv.setItem(41, vidro);
        inv.setItem(42, vidro3);
        inv.setItem(43, vidro);
        inv.setItem(44, vidro);
        inv.setItem(45, vidro);
        inv.setItem(46, vidro);
        inv.setItem(47, vidro);
        inv.setItem(48, vidro);
        inv.setItem(49, f);
        inv.setItem(50, vidro);
        inv.setItem(51, vidro);
        inv.setItem(52, vidro);
        inv.setItem(53, vidro);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§2" + ChatColor.translateAlternateColorCodes('&', Selector.plugin.getConfig().getString("NomeServer"))) && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.DIAMOND) {
                e.setCancelled(true);
                p.closeInventory();
                MenuYT.MenuYT(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 10.0f, 10.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.SLIME_BALL) {
                e.setCancelled(true);
                p.closeInventory();
                Events.status(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 10.0f, 10.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.CHEST) {
                e.setCancelled(true);
                p.closeInventory();
                TodosKit.guitodos(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 10.0f, 10.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.COMPASS) {
                e.setCancelled(true);
                p.closeInventory();
                GuiWarp.menuYT(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 10.0f, 10.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.APPLE) {
                e.setCancelled(true);
                p.closeInventory();
                GuiCabecas.menu(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 10.0f, 10.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
                e.setCancelled(true);
                p.closeInventory();
                Main.p.remove(p.getName());
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.BURP, -15.0f, -15.0f);
                p.sendMessage("§6Modo alterado para Sopa");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MAGMA_CREAM) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/clicktest");
            }
            if (e.getCurrentItem().getType() == Material.POTION) {
                e.setCancelled(true);
                p.closeInventory();
                Main.p.add(p.getName());
                p.sendMessage("§6Modo alterado para Potion");
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.DRINK, 1.0f, 1.0f);
                p.sendMessage("§dSo os mestres conseguem jogar de modo potion!");
            }
        }
    }
    
    @EventHandler
    public void bater(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.ct.contains(p) && e.getAction() == Action.LEFT_CLICK_AIR) {
            p.setLevel(p.getLevel() + 1);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Selector.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (Main.ct.contains(p)) {
                        Main.ct.remove(p);
                        p.sendMessage("§aVoce fez em 10 segundos §6" + p.getLevel() + " §aclicks");
                        p.sendMessage("§aVoce fez em media §6" + p.getLevel() / 10 + " §aclicks por segundo!");
                        if (p.getLevel() >= 200) {
                            Player[] arrayOfPlayer;
                            for (int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length, i = 0; i < j; ++i) {
                                final Player on = arrayOfPlayer[i];
                                if (on.isOp()) {
                                    on.sendMessage("§cO jogador " + p.getDisplayName() + " §ctalvez esteja a usar macro! §cFez §6" + p.getLevel() + "§cclicks!");
                                }
                            }
                        }
                    }
                }
            }, 200L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Selector.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setLevel(0);
                }
            }, 260L);
        }
    }
}
