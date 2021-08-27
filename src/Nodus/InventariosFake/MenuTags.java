package Nodus.InventariosFake;

import Nodus.Main.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.*;

public class MenuTags implements Listener
{
    public static Main plugin;
    
    public MenuTags(final Main main) {
        MenuTags.plugin = main;
    }
    
    public static void guitags(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 27, "§3Tags");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§7Slot");
        vidro.setItemMeta(metav);
        final ItemStack dono = new ItemStack(Material.GOLD_HELMET);
        final ItemMeta fornalhameta = dono.getItemMeta();
        fornalhameta.setDisplayName("§4Dono");
        dono.setItemMeta(fornalhameta);
        final ItemStack dono2 = new ItemStack(Material.WOOD_SWORD);
        final ItemMeta fornalhameta2 = dono2.getItemMeta();
        fornalhameta2.setDisplayName("§7Default");
        dono2.setItemMeta(fornalhameta2);
        final ItemStack dono3 = new ItemStack(Material.STONE_SWORD);
        final ItemMeta fornalhameta3 = dono3.getItemMeta();
        fornalhameta3.setDisplayName("§dTrial");
        dono3.setItemMeta(fornalhameta3);
        final ItemStack dono4 = new ItemStack(Material.LAVA_BUCKET);
        final ItemMeta fornalhameta4 = dono4.getItemMeta();
        fornalhameta4.setDisplayName("§3Dev");
        dono4.setItemMeta(fornalhameta4);
        final ItemStack dono5 = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta fornalhameta5 = dono5.getItemMeta();
        fornalhameta5.setDisplayName("§5Mod");
        dono5.setItemMeta(fornalhameta5);
        final ItemStack dono6 = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta fornalhameta6 = dono6.getItemMeta();
        fornalhameta6.setDisplayName("§6Pro");
        dono6.setItemMeta(fornalhameta6);
        final ItemStack dono7 = new ItemStack(Material.IRON_CHESTPLATE);
        final ItemMeta fornalhameta7 = dono7.getItemMeta();
        fornalhameta7.setDisplayName("§cAdmin");
        dono7.setItemMeta(fornalhameta7);
        final ItemStack dono8 = new ItemStack(Material.DIAMOND_HELMET);
        final ItemMeta fornalhameta8 = dono8.getItemMeta();
        fornalhameta8.setDisplayName("§bYoutuber");
        dono8.setItemMeta(fornalhameta8);
        final ItemStack dono9 = new ItemStack(Material.IRON_BOOTS);
        final ItemMeta fornalhameta9 = dono9.getItemMeta();
        fornalhameta9.setDisplayName("§1§lMVP");
        dono9.setItemMeta(fornalhameta9);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        inv.setItem(9, dono6);
        inv.setItem(10, dono9);
        inv.setItem(11, dono3);
        inv.setItem(12, dono2);
        inv.setItem(13, dono);
        inv.setItem(14, dono4);
        inv.setItem(15, dono5);
        inv.setItem(16, dono7);
        inv.setItem(17, dono8);
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
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§3Tags") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.GOLD_HELMET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag dono");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag default");
                return;
            }
            if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag trial");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag dev");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag mod");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag pro");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag admin");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag youtuber");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_BOOTS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/tag mvp");
            }
        }
    }
}
