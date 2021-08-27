package Nodus.InventariosFake;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class MenuKits3 implements Listener
{
    public static void guiKits(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 54, "§6 >>>> Kits [3] <<<<");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§8Slot Bloqueado");
        final ArrayList descdlc = new ArrayList();
        descdlc.add("§bCompre Vip ou ganhe eventos para debloquear");
        metav.setLore((List)descdlc);
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
        final ItemStack thor = new ItemStack(Material.IRON_DOOR);
        final ItemMeta metathor = thor.getItemMeta();
        metathor.setDisplayName("§B§l Voltar");
        thor.setItemMeta(metathor);
        inv.setItem(0, thor);
        if (p.hasPermission("kit.blink")) {
            final ItemStack pyro = new ItemStack(Material.NETHER_STAR);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Blink");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Teleporte para uma folha bem dahora!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.frozem")) {
            final ItemStack pyro = new ItemStack(Material.SNOW_BLOCK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Frozem");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Lerigou lerigou!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.metheor")) {
            final ItemStack pyro = new ItemStack(Material.FIRE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Metheor");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Faca um meteoro cair!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        ItemStack[] arrayOfItemStack;
        for (int descpyro2 = (arrayOfItemStack = inv.getContents()).length, metapyro2 = 0; metapyro2 < descpyro2; ++metapyro2) {
            final ItemStack item = arrayOfItemStack[metapyro2];
            if (item == null) {
                inv.setItem(inv.firstEmpty(), vidro);
            }
        }
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§6 >>>> Kits [3] <<<<") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/blink");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/frozem");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIRE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/metheor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_DOOR) {
                e.setCancelled(true);
                p.closeInventory();
                MenuKits.guiKits(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.ZOMBIE_METAL, 5.0f, 5.0f);
            }
        }
    }
}
