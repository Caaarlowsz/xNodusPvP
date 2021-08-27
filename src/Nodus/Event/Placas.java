package Nodus.Event;

import Nodus.Main.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;

public class Placas implements Listener
{
    private Main plugin;
    
    public Placas(final Main instance) {
        this.plugin = instance;
    }
    
    @EventHandler
    public void onSignChangeSoup(final SignChangeEvent event) {
        if (event.getLine(0).equals("sopa")) {
            event.setLine(0, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha1")));
            event.setLine(1, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha2")));
            event.setLine(2, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha3")));
            event.setLine(3, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha4")));
        }
    }
    
    @EventHandler
    public void SignClickEvent(final PlayerInteractEvent e) {
        final ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
        final ItemMeta sopas = sopa.getItemMeta();
        sopas.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("nomedasopa")));
        sopa.setItemMeta(sopas);
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            final Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha1"))) && sign.getLine(1).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha2"))) && sign.getLine(2).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha3"))) && sign.getLine(3).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("linha4")))) {
                final Inventory v = Bukkit.createInventory((InventoryHolder)null, 36, "§4" + ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("NomeServer")));
                for (int i = 0; i < 36; ++i) {
                    v.addItem(new ItemStack[] { new ItemStack(sopa) });
                }
                e.getPlayer().openInventory(v);
            }
        }
    }
    
    @EventHandler
    public void onSignChange2Soup(final SignChangeEvent event) {
        if (event.getLine(0).equals("mlg")) {
            event.setLine(1, "§bMLG BALDES");
        }
    }
    
    @EventHandler
    public void SignClickEv3ent(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            final Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(1).equalsIgnoreCase("§bMLG BALDES")) {
                e.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.WATER_BUCKET) });
            }
        }
    }
    
    @EventHandler
    public void onSignChangeCogu(final SignChangeEvent event) {
        if (event.getLine(0).equals("recraft")) {
            event.setLine(0, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha1")));
            event.setLine(1, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha2")));
            event.setLine(2, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha3")));
            event.setLine(3, ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha4")));
        }
    }
    
    @EventHandler
    public void SignClickEvent1(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            final Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha1"))) && sign.getLine(1).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha2"))) && sign.getLine(2).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha3"))) && sign.getLine(3).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("recraftlinha4")))) {
                final Player p = e.getPlayer();
                final ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
                final ItemMeta n = cogu.getItemMeta();
                n.setDisplayName("§4Cogu");
                cogu.setItemMeta(n);
                final ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
                final ItemMeta m = cogu2.getItemMeta();
                m.setDisplayName("§6Cogu");
                cogu2.setItemMeta(m);
                final ItemStack pote = new ItemStack(Material.BOWL, 64);
                final ItemMeta o = pote.getItemMeta();
                o.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("nomedopote")));
                pote.setItemMeta(o);
                p.getInventory().setItem(15, pote);
                p.getInventory().setItem(14, cogu);
                p.getInventory().setItem(16, cogu2);
                p.updateInventory();
                p.sendMessage("§6Recraft caiu no inventario aperte §6'§4E§6' Para ve-lo");
            }
        }
    }
}
