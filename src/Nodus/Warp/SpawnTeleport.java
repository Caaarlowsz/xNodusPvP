package Nodus.Warp;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import Nodus.Main.*;
import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import Nodus.uvu.*;
import org.bukkit.*;
import org.bukkit.event.*;
import Nodus.InventariosFake.*;
import abnodus.*;

public class SpawnTeleport implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public SpawnTeleport(final Main main) {
        SpawnTeleport.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            final Player p = (Player)sender;
            p.getInventory().clear();
            Main.mlg.remove(p);
            p.getInventory().setBoots((ItemStack)null);
            p.getInventory().setChestplate((ItemStack)null);
            p.getInventory().setLeggings((ItemStack)null);
            GuiWarp.Parkour.remove(p.getPlayer());
            p.getInventory().setHelmet((ItemStack)null);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 100));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 100));
            p.sendMessage("§3§l > §7Teleportando em 5 segundos <");
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SpawnTeleport.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    final World w = Bukkit.getServer().getWorld(SpawnTeleport.plugin.getConfig().getString("spawn.world"));
                    final double x = SpawnTeleport.plugin.getConfig().getDouble("spawn.x");
                    final double y = SpawnTeleport.plugin.getConfig().getDouble("spawn.y");
                    final double z = SpawnTeleport.plugin.getConfig().getDouble("spawn.z");
                    final Location lobby = new Location(w, x, y, z);
                    lobby.setPitch((float)SpawnTeleport.plugin.getConfig().getDouble("spawn.pitch"));
                    lobby.setYaw((float)SpawnTeleport.plugin.getConfig().getDouble("spawn.yaw"));
                    Main.removeAbility(p);
                    p.getInventory().setBoots((ItemStack)null);
                    p.getInventory().setChestplate((ItemStack)null);
                    p.getInventory().setLeggings((ItemStack)null);
                    p.getInventory().clear();
                    p.teleport(lobby);
                    p.setLevel(0);
                    p.setExhaustion(20.0f);
                    Habilidade.setAbility(p, "None");
                    p.setFireTicks(0);
                    Main.removeAbility(p);
                    p.setFoodLevel(20000);
                    p.setHealth(20.0);
                    SpawnTeleport.TpSpawn(p);
                    for (final PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                }
            }, 90L);
        }
        return false;
    }
    
    public static void TpSpawn(final Player p) {
        final World w = Bukkit.getServer().getWorld(SpawnTeleport.plugin.getConfig().getString("spawn.world"));
        final double x = SpawnTeleport.plugin.getConfig().getDouble("spawn.x");
        final double y = SpawnTeleport.plugin.getConfig().getDouble("spawn.y");
        final double z = SpawnTeleport.plugin.getConfig().getDouble("spawn.z");
        final Location lobby = new Location(w, x, y, z);
        lobby.setPitch((float)SpawnTeleport.plugin.getConfig().getDouble("spawn.pitch"));
        lobby.setYaw((float)SpawnTeleport.plugin.getConfig().getDouble("spawn.yaw"));
        Main.removeAbility(p);
        p.getInventory().setBoots((ItemStack)null);
        p.getInventory().setChestplate((ItemStack)null);
        p.getInventory().setLeggings((ItemStack)null);
        Main.mlg.remove(p.getPlayer());
        p.getInventory().clear();
        p.teleport(lobby);
        p.setLevel(0);
        p.setExhaustion(20.0f);
        Habilidade.setAbility(p, "None");
        p.setFireTicks(0);
        Main.removeAbility(p);
        p.setFoodLevel(20000);
        p.setHealth(20.0);
        final ItemStack chest = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta chestm = chest.getItemMeta();
        chestm.setDisplayName("§b§l1v1-Teleport");
        chest.setItemMeta(chestm);
        final ItemStack menu = new ItemStack(Material.PISTON_STICKY_BASE);
        final ItemMeta menum = menu.getItemMeta();
        menum.setDisplayName("§a§lMenu");
        menu.setItemMeta(menum);
        final ItemStack as = new ItemStack(Material.CARPET, 1, (short)14);
        final ItemMeta ass = as.getItemMeta();
        ass.setDisplayName(ChatColor.RED + " ");
        as.setItemMeta(ass);
        final ItemStack vine = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        final ItemMeta vinew = vine.getItemMeta();
        vinew.setDisplayName(ChatColor.RED + " ");
        vine.setItemMeta(vinew);
        final ItemStack kits = new ItemStack(Material.BOOK);
        final ItemMeta kitsm = kits.getItemMeta();
        kitsm.setDisplayName("§3§lKits");
        kits.setItemMeta(kitsm);
        final ItemStack kits2 = new ItemStack(Material.NETHER_STAR);
        final ItemMeta kitsm2 = kits2.getItemMeta();
        kitsm2.setDisplayName("§bClick-Test");
        kits2.setItemMeta(kitsm2);
        final ItemStack loja = new ItemStack(Material.DIAMOND);
        final ItemMeta kitsm3 = loja.getItemMeta();
        kitsm3.setDisplayName("§b§lNosso Site");
        loja.setItemMeta(kitsm3);
        final ItemStack loja2 = new ItemStack(Material.EMERALD);
        final ItemMeta kitsm4 = loja2.getItemMeta();
        kitsm4.setDisplayName("§9§lLoja Kits");
        loja2.setItemMeta(kitsm4);
        final ItemStack loja3 = new ItemStack(Material.ENDER_CHEST);
        final ItemMeta kitsm5 = loja3.getItemMeta();
        kitsm5.setDisplayName("§7§lWarps");
        loja3.setItemMeta(kitsm5);
        p.getInventory().setItem(0, loja2);
        p.getInventory().setItem(1, kits);
        p.getInventory().setItem(8, loja3);
        p.getInventory().setItem(7, kits2);
        p.getInventory().setItem(3, loja);
        p.getInventory().setItem(4, menu);
        p.getInventory().setItem(5, chest);
        for (final PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
    }
    
    @EventHandler
    public void a1v1zika(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b§l1v1-Teleport")) {
            e.setCancelled(true);
            final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            p.teleport(lobby);
            Set1v1.daritens(p);
            Main.removeAbility(p);
            a.jogadores.add(p);
            p.sendMessage(" §7§l[1v1]  §3§lBem-vindo ao 1v1 , bom treino");
            p.sendMessage(" §7§l[1v1]  §cPara sair da 1v1 clique na redstone.");
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void menu(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.PISTON_STICKY_BASE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lMenu")) {
            e.setCancelled(true);
            Selector.guiWarp(p);
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void clicktest(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.NETHER_STAR && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§bClick-Test")) {
            e.setCancelled(true);
            p.chat("/clicktest");
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void Warps(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.ENDER_CHEST && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7§lWarps")) {
            e.setCancelled(true);
            GuiWarp.menuYT(p);
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void kits(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.BOOK && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lKits")) {
            e.setCancelled(true);
            p.chat("/kits");
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void nemsei(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.EMERALD && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lLoja Kits")) {
            Shop.guiMake(p);
            e.setCancelled(true);
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
    
    @EventHandler
    public void diamante(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.DIAMOND && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lNosso Site")) {
            e.setCancelled(true);
            p.sendMessage("§7§m------------------------------");
            p.sendMessage("§7§m ");
            p.sendMessage("§7§m ");
            p.sendMessage("§b       Nosso site para comprar vips e etc.");
            p.sendMessage("         " + Main.getInstance().getConfig().getString("Site").replaceAll("&", "§"));
            p.sendMessage("§7§m ");
            p.sendMessage("§7§m ");
            p.sendMessage("§7§m------------------------------");
            p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            p.updateInventory();
        }
    }
}
