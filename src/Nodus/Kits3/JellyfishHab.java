package Nodus.Kits3;

import Nodus.Main.*;
import java.util.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.*;

public class JellyfishHab implements Listener
{
    public Main plugin;
    public static ArrayList<Block> naoescorrer;
    
    static {
        JellyfishHab.naoescorrer = new ArrayList<Block>();
    }
    
    public JellyfishHab(final Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void colocaragua(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.jellyfish.contains(p.getName()) && p.getItemInHand().getType() == Material.AIR && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Block b = event.getClickedBlock();
            final BlockFace lado = event.getBlockFace();
            final int x = b.getLocation().getBlockX();
            final int y = b.getLocation().getBlockY();
            final int z = b.getLocation().getBlockZ();
            if (lado == BlockFace.DOWN) {
                final Block b2 = b.getWorld().getBlockAt(x, y - 1, z);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
            else if (lado == BlockFace.UP) {
                final Block b2 = b.getWorld().getBlockAt(x, y + 1, z);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
            else if (lado == BlockFace.NORTH) {
                final Block b2 = b.getWorld().getBlockAt(x, y, z - 1);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
            else if (lado == BlockFace.SOUTH) {
                final Block b2 = b.getWorld().getBlockAt(x, y, z + 1);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
            else if (lado == BlockFace.WEST) {
                final Block b2 = b.getWorld().getBlockAt(x - 1, y, z);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
            else if (lado == BlockFace.EAST) {
                final Block b2 = b.getWorld().getBlockAt(x + 1, y, z);
                if (b2.getType() == Material.AIR) {
                    b2.setType(Material.STATIONARY_WATER);
                    JellyfishHab.naoescorrer.add(b2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            b2.setType(Material.AIR);
                            JellyfishHab.naoescorrer.remove(b2);
                        }
                    }, 60L);
                }
            }
        }
    }
    
    @EventHandler
    public void naoescorrer(final BlockPhysicsEvent event) {
        final Block b = event.getBlock();
        if (b.getType() == Material.STATIONARY_WATER && JellyfishHab.naoescorrer.contains(b)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void veneno(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        final Block b = p.getLocation().getBlock();
        if (Main.jellyfish.contains(p.getName())) {
            p.removePotionEffect(PotionEffectType.POISON);
        }
        else if (b.getType() == Material.STATIONARY_WATER && !p.getInventory().contains(Material.CLAY_BALL) && JellyfishHab.naoescorrer.contains(b)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 0));
        }
    }
}
