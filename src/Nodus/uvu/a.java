package Nodus.uvu;

import Nodus.Event.*;
import Nodus.Main.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import Nodus.Warp.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;

public class a implements Listener
{
    public static Config config;
    public static Map<String, String> convites;
    public static Map<String, String> lutadores;
    public static ArrayList<Player> jogadores;
    static ArrayList<Player> hide;
    static ArrayList<Player> randomQueue;
    public static Main plugin;
    
    static {
        a.config = Config.getInstance();
        a.convites = new HashMap<String, String>();
        a.lutadores = new HashMap<String, String>();
        a.jogadores = new ArrayList<Player>();
        a.hide = new ArrayList<Player>();
        a.randomQueue = new ArrayList<Player>();
    }
    
    public a(final Main main) {
    }
    
    public static Location getLocation(final String s) {
        final String[] loc = s.split(":");
        return new Location(Bukkit.getWorld(loc[0]), (double)Double.valueOf(loc[1]), (double)Double.valueOf(loc[2]), (double)Double.valueOf(loc[3]), (float)Float.valueOf(loc[4]), (float)Float.valueOf(loc[5]));
    }
    
    public int GetSoupAmount(final Inventory i) {
        int quantidade = 0;
        for (final ItemStack itemstack : i) {
            if (itemstack != null && itemstack.getType().equals((Object)Material.MUSHROOM_SOUP)) {
                ++quantidade;
            }
        }
        return quantidade;
    }
    
    public void setLocation(final String s, final Location l) {
        final String location = String.valueOf(String.valueOf(l.getWorld().getName())) + ":" + String.valueOf(l.getX()) + ":" + String.valueOf(l.getY()) + ":" + String.valueOf(l.getZ()) + ":" + String.valueOf(l.getYaw()) + ":" + String.valueOf(l.getPitch());
        a.config.getData().set(s, (Object)location);
        Main.getInstance();
        Main.getPlugin().saveConfig();
    }
    
    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        if (a.jogadores.contains(event.getPlayer().getName())) {
            if (event.getItemDrop().getItemStack().getType() == Material.REDSTONE) {
                event.setCancelled(true);
                event.getPlayer().updateInventory();
            }
            if (event.getItemDrop().getItemStack().getType() == Material.BLAZE_ROD) {
                event.setCancelled(true);
                event.getPlayer().updateInventory();
            }
            if (event.getItemDrop().getItemStack().getType() == Material.THIN_GLASS) {
                event.setCancelled(true);
                event.getPlayer().updateInventory();
            }
        }
    }
    
    public void Accept(final Player p1, final Player p2, final boolean random) {
        p1.getInventory().clear();
        p1.getInventory().setHelmet((ItemStack)null);
        p1.getInventory().setChestplate((ItemStack)null);
        p1.getInventory().setLeggings((ItemStack)null);
        p1.getInventory().setBoots((ItemStack)null);
        p2.getInventory().clear();
        p2.getInventory().setHelmet((ItemStack)null);
        p2.getInventory().setChestplate((ItemStack)null);
        p2.getInventory().setLeggings((ItemStack)null);
        p2.getInventory().setBoots((ItemStack)null);
        p1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        p2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p1.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        p2.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
        a.lutadores.put(p1.getName(), p2.getName());
        a.lutadores.put(p2.getName(), p1.getName());
        a.convites.remove(p1.getName());
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player pp = onlinePlayers[i];
            p1.hidePlayer(pp);
        }
        a.hide.add(p1);
        Player[] onlinePlayers2;
        for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
            final Player pp = onlinePlayers2[j];
            p2.hidePlayer(pp);
        }
        a.hide.add(p2);
        p1.showPlayer(p2);
        p2.showPlayer(p1);
        p1.updateInventory();
        p2.updateInventory();
        if (random) {
            p1.sendMessage(" §7§l[1v1]  §3Seu adversario e §7" + p2.getName() + " .");
            p2.sendMessage(" §7§l[1v1]  §3Seu adversario e §7" + p1.getName() + " .");
        }
        else {
            p1.sendMessage(" §7§l[1v1]  §3O jogador " + p2.getName() + " aceitou seu pedido de 1v1.");
            p2.sendMessage(" §7§l[1v1]  §3Voc\u00ea aceitou o pedido de 1v1 de " + p1.getName() + ".");
            final World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("pos2.world"));
            final double x2 = Main.plugin.getConfig().getDouble("pos2.x");
            final double y2 = Main.plugin.getConfig().getDouble("pos2.y");
            final double z2 = Main.plugin.getConfig().getDouble("pos2.z");
            final Location lobby2 = new Location(w2, x2, y2, z2);
            lobby2.setPitch((float)Main.plugin.getConfig().getDouble("pos2.pitch"));
            lobby2.setYaw((float)Main.plugin.getConfig().getDouble("pos2.yaw"));
            p1.getWorld().playEffect(lobby2, Effect.ENDER_SIGNAL, 5);
            p1.getPlayer().playSound(p1.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            p1.teleport(lobby2);
            final World w3 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x3 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y3 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z3 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby3 = new Location(w3, x3, y3, z3);
            lobby3.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby3.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            p2.getWorld().playEffect(lobby3, Effect.ENDER_SIGNAL, 5);
            p2.getPlayer().playSound(p2.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            p2.teleport(lobby3);
        }
    }
    
    @EventHandler
    public void Drop(final PlayerDropItemEvent e) {
        if (a.lutadores.containsKey(e.getPlayer().getName()) || a.lutadores.containsValue(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
        else if (a.jogadores.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    
    void ExecuteDeath(final PlayerDeathEvent e) {
        if (a.jogadores.contains(e.getEntity().getPlayer())) {
            final Player p = e.getEntity().getPlayer();
            p.updateInventory();
            if (a.randomQueue.contains(p)) {
                a.randomQueue.clear();
            }
        }
    }
    
    void ExecuteQuit(final PlayerQuitEvent e) {
        if (a.jogadores.contains(e.getPlayer())) {
            final Player p = e.getPlayer();
            p.updateInventory();
            if (a.randomQueue.contains(p)) {
                a.randomQueue.clear();
            }
        }
    }
    
    void ExecuteKick(final PlayerKickEvent e) {
        if (a.jogadores.contains(e.getPlayer())) {
            final Player p = e.getPlayer();
            p.updateInventory();
            if (a.randomQueue.contains(p)) {
                a.randomQueue.clear();
            }
        }
    }
    
    @EventHandler
    public void Damage(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                if (a.jogadores.contains(e.getEntity())) {
                    final Player p = (Player)e.getDamager();
                    if (!a.lutadores.containsKey(p.getName()) && !a.lutadores.containsValue(p.getName())) {
                        e.setCancelled(true);
                    }
                    if (!a.lutadores.containsKey(e.getEntity()) && !a.lutadores.containsValue(p.getName())) {
                        e.setCancelled(true);
                    }
                }
            }
            else if (e.getDamager() instanceof Projectile) {
                final Projectile a = (Projectile)e.getDamager();
                final Player p2 = (Player)a.getShooter();
                final Player target = (Player)e.getEntity();
                if (Nodus.uvu.a.jogadores.contains(target)) {
                    if (Nodus.uvu.a.lutadores.containsKey(p2.getName())) {
                        Nodus.uvu.a.lutadores.get(p2.getName()).equalsIgnoreCase(target.getName());
                    }
                    else if (Nodus.uvu.a.lutadores.containsKey(target.getName()) && Nodus.uvu.a.lutadores.get(target.getName()).equalsIgnoreCase(p2.getName())) {
                        return;
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void CommandsDisable(final PlayerCommandPreprocessEvent e) {
        if (a.jogadores.contains(e.getPlayer()) && !e.getPlayer().hasPermission("pvp.1v1comandos")) {
            e.getPlayer().sendMessage(" §7§l[1v1]  §3Enquanto estiver na 1v1 voce nao podera executar comandos");
            e.getPlayer().sendMessage(" §7§l[1v1]  §3Para ir ao spawn clique com a redstone");
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void joine(final PlayerJoinEvent e) {
        for (final Player p : a.hide) {
            p.hidePlayer(e.getPlayer());
        }
    }
    
    @EventHandler
    public void Quit(final PlayerQuitEvent e) {
        this.ExecuteQuit(e);
        if (a.lutadores.containsKey(e.getPlayer().getName())) {
            final Player matou = Bukkit.getServer().getPlayerExact((String)a.lutadores.get(e.getPlayer()));
            final Player perdedor = e.getPlayer();
            matou.sendMessage(" §7§l[1v1]  §cSeu adversaio arregou ;-;");
            Set1v1.daritens(matou);
            final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            perdedor.getWorld().playEffect(lobby, Effect.ENDER_SIGNAL, 5);
            perdedor.getPlayer().playSound(perdedor.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            perdedor.teleport(lobby);
            perdedor.getInventory().clear();
            perdedor.getInventory().setHelmet((ItemStack)null);
            perdedor.getInventory().setChestplate((ItemStack)null);
            perdedor.getInventory().setLeggings((ItemStack)null);
            perdedor.getInventory().setBoots((ItemStack)null);
            a.lutadores.remove(perdedor.getName());
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(perdedor);
            Player[] onlinePlayers;
            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                final Player pp = onlinePlayers[i];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers2;
            for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
                final Player pp = onlinePlayers2[j];
                perdedor.showPlayer(pp);
            }
            a.hide.remove(perdedor);
            matou.setHealth(20);
            matou.setFireTicks(0);
            final World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x2 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y2 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z2 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby2 = new Location(w2, x2, y2, z2);
            lobby2.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby2.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby2, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby2);
            matou.updateInventory();
        }
        else if (a.lutadores.containsValue(e.getPlayer().getName())) {
            Player matou = null;
            Player perdedor = null;
            for (final Map.Entry m : a.lutadores.entrySet()) {
                if (m.getValue().equalsIgnoreCase(e.getPlayer().getName())) {
                    matou = Bukkit.getServer().getPlayerExact((String)m.getKey());
                    perdedor = Bukkit.getServer().getPlayerExact((String)m.getValue());
                    break;
                }
            }
            matou.sendMessage(" §7§l[1v1]  §cSeu adversaio arregou ;-;");
            Set1v1.daritens(matou);
            final World w3 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x3 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y3 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z3 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby3 = new Location(w3, x3, y3, z3);
            lobby3.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby3.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby3, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby3);
            matou.updateInventory();
            perdedor.getInventory().clear();
            perdedor.getInventory().setHelmet((ItemStack)null);
            perdedor.getInventory().setChestplate((ItemStack)null);
            perdedor.getInventory().setLeggings((ItemStack)null);
            perdedor.getInventory().setBoots((ItemStack)null);
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(perdedor);
            Player[] onlinePlayers3;
            for (int length3 = (onlinePlayers3 = Bukkit.getOnlinePlayers()).length, k = 0; k < length3; ++k) {
                final Player pp = onlinePlayers3[k];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers4;
            for (int length4 = (onlinePlayers4 = Bukkit.getOnlinePlayers()).length, l = 0; l < length4; ++l) {
                final Player pp = onlinePlayers4[l];
                perdedor.showPlayer(pp);
            }
            a.hide.remove(perdedor);
            matou.setHealth(20);
            matou.setFireTicks(0);
            final World w4 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x4 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y4 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z4 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby4 = new Location(w4, x4, y4, z4);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby3, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby3);
            matou.updateInventory();
        }
        else if (a.jogadores.contains(e.getPlayer())) {
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setHelmet((ItemStack)null);
            e.getPlayer().getInventory().setChestplate((ItemStack)null);
            e.getPlayer().getInventory().setLeggings((ItemStack)null);
            e.getPlayer().getInventory().setBoots((ItemStack)null);
            a.jogadores.remove(e.getPlayer());
            SpawnTeleport.TpSpawn(e.getPlayer());
        }
    }
    
    @EventHandler
    public void Kick(final PlayerKickEvent e) {
        this.ExecuteKick(e);
        if (a.lutadores.containsKey(e.getPlayer().getName())) {
            final Player matou = Bukkit.getServer().getPlayerExact((String)a.lutadores.get(e.getPlayer()));
            final Player perdedor = e.getPlayer();
            matou.sendMessage(" §7§l[1v1]  §cSeu adversaio arregou ;-;");
            Set1v1.daritens(matou);
            final World w1 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x1 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y1 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z1 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby1 = new Location(w1, x1, y1, z1);
            lobby1.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby1.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            perdedor.getWorld().playEffect(lobby1, Effect.ENDER_SIGNAL, 5);
            perdedor.getPlayer().playSound(perdedor.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            perdedor.teleport(lobby1);
            perdedor.updateInventory();
            perdedor.getInventory().clear();
            perdedor.getInventory().setHelmet((ItemStack)null);
            perdedor.getInventory().setChestplate((ItemStack)null);
            perdedor.getInventory().setLeggings((ItemStack)null);
            perdedor.getInventory().setBoots((ItemStack)null);
            a.lutadores.remove(perdedor.getName());
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(perdedor);
            Player[] onlinePlayers;
            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                final Player pp = onlinePlayers[i];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers2;
            for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
                final Player pp = onlinePlayers2[j];
                perdedor.showPlayer(pp);
            }
            a.hide.remove(perdedor);
            matou.setHealth(20);
            matou.setFireTicks(0);
            matou.teleport(lobby1);
            matou.updateInventory();
        }
        else if (a.lutadores.containsValue(e.getPlayer().getName())) {
            Player matou = null;
            Player perdedor = null;
            for (final Map.Entry m : a.lutadores.entrySet()) {
                if (m.getValue().equalsIgnoreCase(e.getPlayer().getName())) {
                    matou = Bukkit.getServer().getPlayerExact((String)m.getKey());
                    perdedor = Bukkit.getServer().getPlayerExact((String)m.getValue());
                    break;
                }
            }
            matou.sendMessage(" §7§l[1v1]  §cSeu adversaio arregou ;-;");
            Set1v1.daritens(matou);
            final World w1 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x1 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y1 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z1 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby1 = new Location(w1, x1, y1, z1);
            lobby1.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby1.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby1, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby1);
            matou.updateInventory();
            perdedor.getInventory().clear();
            perdedor.getInventory().setHelmet((ItemStack)null);
            perdedor.getInventory().setChestplate((ItemStack)null);
            perdedor.getInventory().setLeggings((ItemStack)null);
            perdedor.getInventory().setBoots((ItemStack)null);
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(perdedor);
            Player[] onlinePlayers3;
            for (int length3 = (onlinePlayers3 = Bukkit.getOnlinePlayers()).length, k = 0; k < length3; ++k) {
                final Player pp = onlinePlayers3[k];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers4;
            for (int length4 = (onlinePlayers4 = Bukkit.getOnlinePlayers()).length, l = 0; l < length4; ++l) {
                final Player pp = onlinePlayers4[l];
                perdedor.showPlayer(pp);
            }
            a.hide.remove(perdedor);
            matou.setHealth(20);
            matou.setFireTicks(0);
            final World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x2 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y2 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z2 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby2 = new Location(w2, x2, y2, z2);
            lobby2.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby2.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby2, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby1);
            matou.updateInventory();
        }
        else if (a.jogadores.contains(e.getPlayer())) {
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setHelmet((ItemStack)null);
            e.getPlayer().getInventory().setChestplate((ItemStack)null);
            e.getPlayer().getInventory().setLeggings((ItemStack)null);
            e.getPlayer().getInventory().setBoots((ItemStack)null);
            a.jogadores.remove(e.getPlayer());
            final Player matou = e.getPlayer();
            final World w3 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x3 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y3 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z3 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby3 = new Location(w3, x3, y3, z3);
            lobby3.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby3.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby3, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby3);
            matou.updateInventory();
        }
    }
    
    @EventHandler
    public void Death(final PlayerDeathEvent e) {
        this.ExecuteDeath(e);
        if (a.lutadores.containsKey(e.getEntity().getPlayer().getName())) {
            final Player matou = Bukkit.getServer().getPlayerExact((String)a.lutadores.get(e.getEntity().getPlayer().getName()));
            final Player morreu = e.getEntity().getPlayer();
            final Damageable hp1 = (Damageable)matou;
            matou.sendMessage(" §7§l[1v1]  §3Voce matou §7" + morreu.getName() + " §3que ficou com §7" + String.valueOf(this.GetSoupAmount((Inventory)morreu.getInventory())) + " §3sopas restantes.");
            morreu.sendMessage(" §7§l[1v1]  §3Voce morreu para §7" + matou.getName() + " §3que ficou com§7 " + (int)hp1.getHealth() / 2.0 + " §3coracoes e §7" + String.valueOf(this.GetSoupAmount((Inventory)matou.getInventory())) + " §3sopas restantes.");
            Set1v1.daritens(matou);
            a.lutadores.remove(morreu.getName());
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(morreu);
            Player[] onlinePlayers;
            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                final Player pp = onlinePlayers[i];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers2;
            for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
                final Player pp = onlinePlayers2[j];
                morreu.showPlayer(pp);
            }
            a.hide.remove(morreu);
            matou.setHealth(20);
            matou.setFireTicks(0);
            final World w1 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x1 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y1 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z1 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby1 = new Location(w1, x1, y1, z1);
            lobby1.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby1.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby1, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby1);
            matou.updateInventory();
            matou.updateInventory();
        }
        else if (a.lutadores.containsValue(e.getEntity().getPlayer().getName())) {
            Player matou = null;
            Player morreu = null;
            for (final Map.Entry m : a.lutadores.entrySet()) {
                if (m.getValue().equalsIgnoreCase(e.getEntity().getPlayer().getName())) {
                    matou = Bukkit.getServer().getPlayerExact((String)m.getKey());
                    morreu = Bukkit.getServer().getPlayerExact((String)m.getValue());
                    break;
                }
            }
            final Damageable hp1 = (Damageable)matou;
            matou.sendMessage(" §7§l[1v1]  §3Voce matou §7" + morreu.getName() + " §3que ficou com §7" + String.valueOf(this.GetSoupAmount((Inventory)morreu.getInventory())) + " §3sopas restantes.");
            morreu.sendMessage(" §7§l[1v1]  §3Voce morreu para §7" + matou.getName() + " §3que ficou com§7 " + (int)hp1.getHealth() / 2.0 + " §3coracoes e §7" + String.valueOf(this.GetSoupAmount((Inventory)matou.getInventory())) + " §3sopas restantes.");
            Set1v1.daritens(matou);
            a.lutadores.remove(morreu.getName());
            a.lutadores.remove(matou.getName());
            a.jogadores.remove(morreu);
            Player[] onlinePlayers3;
            for (int length3 = (onlinePlayers3 = Bukkit.getOnlinePlayers()).length, k = 0; k < length3; ++k) {
                final Player pp = onlinePlayers3[k];
                matou.showPlayer(pp);
            }
            a.hide.remove(matou);
            Player[] onlinePlayers4;
            for (int length4 = (onlinePlayers4 = Bukkit.getOnlinePlayers()).length, l = 0; l < length4; ++l) {
                final Player pp = onlinePlayers4[l];
                morreu.showPlayer(pp);
            }
            a.hide.remove(morreu);
            matou.setHealth(20);
            matou.setFireTicks(0);
            final World w1 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x1 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y1 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z1 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby1 = new Location(w1, x1, y1, z1);
            lobby1.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby1.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby1, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby1);
            matou.updateInventory();
            matou.updateInventory();
        }
        else if (a.jogadores.contains(e.getEntity().getPlayer())) {
            e.getEntity().getPlayer().getInventory().clear();
            e.getEntity().getPlayer().getInventory().setHelmet((ItemStack)null);
            e.getEntity().getPlayer().getInventory().setChestplate((ItemStack)null);
            e.getEntity().getPlayer().getInventory().setLeggings((ItemStack)null);
            e.getEntity().getPlayer().getInventory().setBoots((ItemStack)null);
            a.jogadores.remove(e.getEntity().getPlayer());
            final Player matou = e.getEntity().getPlayer();
            final World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn3.world"));
            final double x2 = Main.plugin.getConfig().getDouble("spawn3.x");
            final double y2 = Main.plugin.getConfig().getDouble("spawn3.y");
            final double z2 = Main.plugin.getConfig().getDouble("spawn3.z");
            final Location lobby2 = new Location(w2, x2, y2, z2);
            lobby2.setPitch((float)Main.plugin.getConfig().getDouble("spawn3.pitch"));
            lobby2.setYaw((float)Main.plugin.getConfig().getDouble("spawn3.yaw"));
            matou.getWorld().playEffect(lobby2, Effect.ENDER_SIGNAL, 5);
            matou.getPlayer().playSound(matou.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
            matou.teleport(lobby2);
            matou.updateInventory();
        }
    }
    
    @EventHandler
    public void InteractItem(final PlayerInteractEvent e) {
        if (a.jogadores.contains(e.getPlayer())) {
            if ((e.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && e.getItem().getType() == Material.REDSTONE) {
                e.setCancelled(true);
                final Player p = e.getPlayer();
                a.jogadores.remove(p);
                SpawnTeleport.TpSpawn(p);
                if (a.randomQueue.contains(p)) {
                    a.randomQueue.clear();
                }
                p.sendMessage(" §7§l[1v1]  §3Saiu do 1v1.");
                p.updateInventory();
                return;
            }
            if ((e.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && e.getItem().getType() == Material.SLIME_BALL) {
                e.setCancelled(true);
                final Player p = e.getPlayer();
                if (!a.randomQueue.contains(p)) {
                    a.randomQueue.add(p);
                    p.sendMessage(" §7§l[1v1]  §3Procurando Partida...");
                    if (a.randomQueue.size() == 2) {
                        p.sendMessage(" §7§l[1v1]  §3Partida Encontrada!");
                        this.Accept(a.randomQueue.get(0), a.randomQueue.get(1), false);
                        a.randomQueue.clear();
                    }
                    Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (a.randomQueue.contains(p)) {
                                p.sendMessage("§7Nenhuma partida foi encontrada!");
                                a.randomQueue.remove(p);
                            }
                        }
                    }, 200L);
                }
            }
            if ((e.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && e.getItem().getType() == Material.THIN_GLASS) {
                e.setCancelled(true);
                e.getPlayer().updateInventory();
            }
        }
    }
    
    @EventHandler
    public void InteractItem(final PlayerInteractEntityEvent e) {
        if (a.jogadores.contains(e.getPlayer()) && e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
            final Player p = e.getPlayer();
            final Player target = (Player)e.getRightClicked();
            if (a.convites.containsKey(target.getName())) {
                if (a.convites.get(target.getName()).equalsIgnoreCase(p.getName())) {
                    this.Accept(target, p, false);
                }
                else if (!a.convites.containsKey(p.getName())) {
                    p.sendMessage("§3Desafiou §7" + target.getName() + " §3para o 1v1.");
                    target.sendMessage("§7" + p.getName() + " §3lhe desafiou para 1v1.");
                    a.convites.put(p.getName(), target.getName());
                    Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (a.convites.containsKey(p.getName())) {
                                a.convites.remove(p.getName());
                                p.sendMessage("§7" + target.getName() + " §3nao aceitou o pedido.");
                            }
                        }
                    }, 200L);
                }
                else {
                    p.sendMessage(" §7§l[1v1]  §cAguarde para desafiar novamente.");
                }
            }
            else if (!a.convites.containsKey(p.getName())) {
                p.sendMessage("§3Desafiou §7" + target.getName() + " §3para o 1v1.");
                target.sendMessage("§7" + p.getName() + " §3lhe desafiou para 1v1.");
                a.convites.put(p.getName(), target.getName());
                Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (a.convites.containsKey(p.getName())) {
                            a.convites.remove(p.getName());
                            p.sendMessage("§7" + target.getName() + " §3nao aceitou o pedido.");
                        }
                    }
                }, 200L);
            }
            else {
                p.sendMessage(" §7§l[1v1]  §cAguarde para desafiar novamente.");
            }
        }
    }
    
    @EventHandler
    public void interactmenu(final InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && a.jogadores.contains(e.getWhoClicked()) && (e.getCurrentItem().getType() == Material.REDSTONE || e.getCurrentItem().getType() == Material.BLAZE_ROD || e.getCurrentItem().getType() == Material.THIN_GLASS)) {
            e.setCancelled(true);
        }
    }
}
