package Nodus.Kits3;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import Nodus.Main.*;
import com.sk89q.worldguard.protection.flags.*;
import org.bukkit.potion.*;
import java.util.*;
import com.sk89q.worldguard.bukkit.*;
import com.sk89q.worldguard.protection.managers.*;
import com.sk89q.worldguard.protection.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;

public class GladiatorHAB implements Listener
{
    public Plugin plugin;
    public boolean generateGlass;
    public static HashMap<String, Location> oldl;
    public static HashMap<String, String> fighting;
    public static HashMap<Integer, ArrayList<Location>> blocks;
    public static HashMap<Player, Location> localizacao;
    public static HashMap<Location, Block> bloco;
    public static HashMap<Integer, String[]> players;
    public static ArrayList<String> gladgladiator;
    public static HashMap<String, Integer> tasks;
    int nextID;
    public int id1;
    public int id2;
    
    static {
        GladiatorHAB.oldl = new HashMap<String, Location>();
        GladiatorHAB.fighting = new HashMap<String, String>();
        GladiatorHAB.blocks = new HashMap<Integer, ArrayList<Location>>();
        GladiatorHAB.localizacao = new HashMap<Player, Location>();
        GladiatorHAB.bloco = new HashMap<Location, Block>();
        GladiatorHAB.players = new HashMap<Integer, String[]>();
        GladiatorHAB.gladgladiator = new ArrayList<String>();
        GladiatorHAB.tasks = new HashMap<String, Integer>();
    }
    
    public GladiatorHAB(final Main plugin) {
        this.generateGlass = true;
        this.nextID = 0;
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            final Player p = event.getPlayer();
            final Player r = (Player)event.getRightClicked();
            if (p.getItemInHand().getType() == Material.IRON_FENCE && Main.gladiator.contains(p.getName())) {
                final WorldGuardPlugin worldGuard = Main.getWorldGuard();
                final RegionManager regionManager = worldGuard.getRegionManager(p.getWorld());
                final ApplicableRegionSet set = regionManager.getApplicableRegions(p.getLocation());
                final RegionManager rm = worldGuard.getRegionManager(r.getWorld());
                final ApplicableRegionSet seti = rm.getApplicableRegions(r.getLocation());
                if (!seti.allows(DefaultFlag.PVP) || !set.allows(DefaultFlag.PVP)) {
                    if (!seti.allows(DefaultFlag.INVINCIBILITY) || !set.allows(DefaultFlag.INVINCIBILITY)) {
                        event.setCancelled(true);
                    }
                    p.sendMessage(ChatColor.RED + "Sua habilidade so pode ser usada em uma area PvP!");
                    return;
                }
                event.setCancelled(true);
                final Location loc = new Location(p.getWorld(), (double)p.getLocation().getBlockX(), (double)(p.getLocation().getBlockY() + 70), (double)p.getLocation().getBlockZ());
                GladiatorHAB.localizacao.put(p, loc);
                GladiatorHAB.localizacao.put(r, loc);
                final Location loc2 = new Location(p.getWorld(), (double)(p.getLocation().getBlockX() + 8), (double)(p.getLocation().getBlockY() + 73), (double)(p.getLocation().getBlockZ() + 8));
                final Location loc3 = new Location(p.getWorld(), (double)(p.getLocation().getBlockX() - 8), (double)(p.getLocation().getBlockY() + 73), (double)(p.getLocation().getBlockZ() - 8));
                if (GladiatorHAB.fighting.containsKey(p.getName()) || GladiatorHAB.fighting.containsKey(r.getName())) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Voce ja esta em combate!");
                    return;
                }
                final Integer currentID = this.nextID;
                ++this.nextID;
                final ArrayList list = new ArrayList();
                list.add(p.getName());
                list.add(r.getName());
                GladiatorHAB.players.put(currentID, list.toArray(new String[1]));
                GladiatorHAB.oldl.put(p.getName(), p.getLocation());
                GladiatorHAB.oldl.put(r.getName(), r.getLocation());
                if (this.generateGlass) {
                    final List<Location> cuboid = new ArrayList<Location>();
                    cuboid.clear();
                    for (int bX = -10; bX <= 10; ++bX) {
                        for (int bZ = -10; bZ <= 10; ++bZ) {
                            for (int bY = -1; bY <= 10; ++bY) {
                                final Block b = loc.clone().add((double)bX, (double)bY, (double)bZ).getBlock();
                                if (!b.isEmpty()) {
                                    event.setCancelled(true);
                                    p.sendMessage(ChatColor.RED + "Voce nao pode criar sua arena aqui");
                                    return;
                                }
                                if (bY == 10) {
                                    cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                }
                                else if (bY == -1) {
                                    cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                }
                                else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
                                    cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                }
                            }
                        }
                    }
                    for (final Location loc4 : cuboid) {
                        loc4.getBlock().setType(Material.GLASS);
                        GladiatorHAB.bloco.put(loc4, loc4.getBlock());
                    }
                    loc2.setYaw(135.0f);
                    p.teleport(loc2);
                    loc3.setYaw(-45.0f);
                    r.teleport(loc3);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
                    r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
                    p.sendMessage(ChatColor.GREEN + "Voce desafiou um jogador! Voce tem 5 segundos de invencibilidade!");
                    p.sendMessage(ChatColor.AQUA + "Caso nao tenha nenhum vencedor depois de 4 minutos, voce voltara ao seu local de origem!");
                    r.sendMessage(ChatColor.RED + "Voce foi desafiado! Voce tem 5 segundos de invencibilidade!");
                    r.sendMessage(ChatColor.AQUA + "Caso nao tenha nenhum vencedor depois de 4 minutos, voce voltara ao seu local de origem!");
                    GladiatorHAB.fighting.put(p.getName(), r.getName());
                    GladiatorHAB.fighting.put(r.getName(), p.getName());
                    GladiatorHAB.gladgladiator.add(p.getName());
                    GladiatorHAB.gladgladiator.add(r.getName());
                    this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (GladiatorHAB.fighting.containsKey(p.getName()) && GladiatorHAB.fighting.get(p.getName()).equalsIgnoreCase(r.getName()) && GladiatorHAB.fighting.containsKey(r.getName()) && GladiatorHAB.fighting.get(r.getName()).equalsIgnoreCase(p.getName())) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 5));
                                r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 5));
                            }
                        }
                    }, 2400L);
                    this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (GladiatorHAB.fighting.containsKey(p.getName()) && GladiatorHAB.fighting.get(p.getName()).equalsIgnoreCase(r.getName()) && GladiatorHAB.fighting.containsKey(r.getName()) && GladiatorHAB.fighting.get(r.getName()).equalsIgnoreCase(p.getName())) {
                                GladiatorHAB.fighting.remove(p.getName());
                                GladiatorHAB.fighting.remove(r.getName());
                                GladiatorHAB.gladgladiator.remove(p.getName());
                                GladiatorHAB.gladgladiator.remove(r.getName());
                                p.teleport((Location)GladiatorHAB.oldl.get(p.getName()));
                                r.teleport((Location)GladiatorHAB.oldl.get(r.getName()));
                                GladiatorHAB.oldl.remove(p.getName());
                                GladiatorHAB.oldl.remove(r.getName());
                                p.sendMessage(ChatColor.RED + "Nao houve nenhum vencedor, voce foi teleportado para o seu lugar de origem!");
                                r.sendMessage(ChatColor.RED + "Nao houve nenhum vencedor, voce foi teleportado para o seu lugar de origem!");
                                final Location loc = GladiatorHAB.localizacao.get(p);
                                final List<Location> cuboid = new ArrayList<Location>();
                                for (int bX = -10; bX <= 10; ++bX) {
                                    for (int bZ = -10; bZ <= 10; ++bZ) {
                                        for (int bY = -1; bY <= 10; ++bY) {
                                            if (bY == 10) {
                                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                            }
                                            else if (bY == -1) {
                                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                            }
                                            else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
                                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                                            }
                                        }
                                    }
                                }
                                for (final Location loc2 : cuboid) {
                                    loc2.getBlock().setType(Material.AIR);
                                    GladiatorHAB.bloco.get(loc2).setType(Material.AIR);
                                }
                            }
                        }
                    }, 4800L);
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_FENCE && Main.gladiator.contains(p.getName())) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_FENCE && Main.gladiator.contains(p.getName())) {
            e.setCancelled(true);
            p.updateInventory();
            p.sendMessage(ChatColor.RED + "Voce nao pode dropar este item!");
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlyaerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && GladiatorHAB.fighting.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getClickedBlock().setType(Material.BEDROCK);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (GladiatorHAB.fighting.containsKey(e.getPlayer().getName())) {
                        e.getClickedBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && GladiatorHAB.fighting.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getBlock().setType(Material.BEDROCK);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE && GladiatorHAB.fighting.containsKey(e.getPlayer().getName())) {
                        e.getBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeft(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (GladiatorHAB.fighting.containsKey(p.getName())) {
            final Player t = Bukkit.getServer().getPlayer((String)GladiatorHAB.fighting.get(p.getName()));
            GladiatorHAB.fighting.remove(t.getName());
            GladiatorHAB.fighting.remove(p.getName());
            GladiatorHAB.gladgladiator.remove(p.getName());
            GladiatorHAB.gladgladiator.remove(t.getName());
            final Location old = GladiatorHAB.oldl.get(t.getName());
            t.teleport(old);
            t.sendMessage(ChatColor.RED + "O jogador " + p.getName() + " deslogou-se!");
            Bukkit.getScheduler().cancelTask(this.id1);
            Bukkit.getScheduler().cancelTask(this.id2);
            t.removePotionEffect(PotionEffectType.WITHER);
            final Location loc = GladiatorHAB.localizacao.get(p);
            final List<Location> cuboid = new ArrayList<Location>();
            for (int bX = -10; bX <= 10; ++bX) {
                for (int bZ = -10; bZ <= 10; ++bZ) {
                    for (int bY = -1; bY <= 10; ++bY) {
                        if (bY == 10) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                        else if (bY == -1) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                        else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                    }
                }
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                GladiatorHAB.bloco.get(loc2).setType(Material.AIR);
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                GladiatorHAB.bloco.get(loc2).setType(Material.AIR);
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                GladiatorHAB.bloco.get(loc2).setType(Material.AIR);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathGladiator(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (GladiatorHAB.fighting.containsKey(p.getName())) {
            final Player k = Bukkit.getServer().getPlayer((String)GladiatorHAB.fighting.get(p.getName()));
            final Location old = GladiatorHAB.oldl.get(p.getName());
            k.teleport(old);
            k.sendMessage(ChatColor.GREEN + "Voce ganhou a batalha contra " + p.getName() + ChatColor.GREEN + "!");
            Bukkit.getScheduler().cancelTask(this.id1);
            Bukkit.getScheduler().cancelTask(this.id2);
            k.removePotionEffect(PotionEffectType.WITHER);
            k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
            GladiatorHAB.fighting.remove(k.getName());
            GladiatorHAB.fighting.remove(p.getName());
            GladiatorHAB.gladgladiator.remove(p.getName());
            GladiatorHAB.gladgladiator.remove(k.getName());
            final Location loc = GladiatorHAB.localizacao.get(p);
            final List<Location> cuboid = new ArrayList<Location>();
            cuboid.clear();
            for (int bX = -10; bX <= 10; ++bX) {
                for (int bZ = -10; bZ <= 10; ++bZ) {
                    for (int bY = -1; bY <= 10; ++bY) {
                        if (bY == 10) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                        else if (bY == -1) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                        else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
                            cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                        }
                    }
                }
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                GladiatorHAB.bloco.get(loc2).setType(Material.AIR);
            }
        }
    }
}
