package Nodus.Kits2;

import java.util.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.block.*;
import org.bukkit.event.*;
import org.bukkit.metadata.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;

public class Avatar implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    ArrayList<Snowball> Soltou;
    public int id1;
    ArrayList<Snowball> Soltou1;
    public int id11;
    
    static {
        Avatar.cooldown = new HashMap<String, Long>();
        Avatar.teias = new HashMap<String, Egg>();
    }
    
    public Avatar(final Main main) {
        this.Soltou = new ArrayList<Snowball>();
        this.Soltou1 = new ArrayList<Snowball>();
        Avatar.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Avatar")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Avatar")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Avatar");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Avatar.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Avatar");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack iron = new ItemStack(Material.BEACON);
                    final ItemMeta ironm = iron.getItemMeta();
                    ironm.setDisplayName("§bAvatar!");
                    iron.setItemMeta(ironm);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { iron });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Avatar.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Avatar.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void Trocar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack Terra = new ItemStack(Material.GRASS);
        final ItemMeta terram = Terra.getItemMeta();
        terram.setDisplayName(ChatColor.DARK_GREEN + "Terra");
        Terra.setItemMeta(terram);
        final ItemStack Agua = new ItemStack(Material.LAPIS_BLOCK);
        final ItemMeta Aguam = Agua.getItemMeta();
        Aguam.setDisplayName(ChatColor.DARK_AQUA + "Agua");
        Agua.setItemMeta(Aguam);
        final ItemStack Aguac = new ItemStack(Material.BEACON);
        final ItemMeta Aguacm = Aguac.getItemMeta();
        Aguacm.setDisplayName(ChatColor.DARK_AQUA + "B");
        Aguac.setItemMeta(Aguacm);
        final ItemStack AR = new ItemStack(Material.WOOL);
        final ItemMeta ARm = AR.getItemMeta();
        ARm.setDisplayName("AR");
        AR.setItemMeta(ARm);
        final ItemStack Fogo = new ItemStack(Material.REDSTONE_BLOCK);
        final ItemMeta Fogom = Fogo.getItemMeta();
        Fogom.setDisplayName(ChatColor.RED + "Fogo");
        Fogo.setItemMeta(Fogom);
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().getType() == Material.BEACON) {
            p.setItemInHand(AR);
            Main.Ferro2.add(p.getName());
        }
        else if (Main.Ferro2.contains(p.getName()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().equals((Object)AR)) {
            p.setItemInHand(Agua);
            Main.Ferro2.remove(p.getName());
            Main.AvatarH.add(p.getName());
        }
        else if (Main.AvatarH.contains(p.getName()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().equals((Object)Agua)) {
            p.setItemInHand(Terra);
            Main.AvatarH.remove(p.getName());
            Main.Terra2.add(p.getName());
        }
        else if (Main.Terra2.contains(p.getName()) && Main.Terra2.contains(p.getName()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().equals((Object)Terra)) {
            p.setItemInHand(Fogo);
            Main.Terra2.remove(p.getName());
            Main.Red2.add(p.getName());
        }
        else if (Main.Red2.contains(p.getName()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().equals((Object)Fogo)) {
            p.setItemInHand(AR);
            Main.Red2.remove(p.getName());
            Main.Ferro2.add(p.getName());
        }
    }
    
    @EventHandler
    public void AvatarAr(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.Ferro2.contains(p.getName()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().getType() == Material.WOOL) {
            if (Main.SemPvP(p)) {
                event.setCancelled(true);
                if (!Main.Tempo.contains(p)) {
                    Main.Tempo.add(p);
                    final Vector Ferro = p.getLocation().getDirection().normalize().multiply(55);
                    final Snowball FerroH = (Snowball)p.launchProjectile((Class)Snowball.class);
                    FerroH.setVelocity(Ferro);
                    FerroH.setMetadata("Ar", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                    final Location pegou = p.getEyeLocation();
                    final BlockIterator Ferrao = new BlockIterator(pegou, 0.0, 40);
                    while (Ferrao.hasNext()) {
                        final Location Ferrao2 = Ferrao.next().getLocation();
                        final Effect camelo = Effect.STEP_SOUND;
                        p.playSound(p.getLocation(), Sound.STEP_WOOL, 5.5f, 5.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOL, 1.5f, 1.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOL, 2.5f, 2.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOL, 3.5f, 3.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOL, 4.5f, 4.5f);
                        p.getWorld().playEffect(Ferrao2, camelo, 35);
                    }
                    this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Main.Tempo.contains(p)) {
                                Main.Tempo.remove(p);
                                p.sendMessage("§aJa pode usar seu kit");
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 15.5f, 15.5f);
                            }
                        }
                    }, 250L);
                }
                else {
                    p.sendMessage("§cCooldown.");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler
    public void EntityDamageEvent(final EntityDamageByEntityEvent event) {
        final Entity Tomou2 = event.getEntity();
        if (event.getDamager() instanceof Snowball) {
            final Snowball Tomou3 = (Snowball)event.getDamager();
            if (Tomou3.hasMetadata("Ar")) {
                event.setDamage(16.0);
                ((LivingEntity)Tomou2).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0), true);
            }
        }
    }
    
    @EventHandler
    public void AvatarAr1(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.AvatarH.contains(p.getName()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().getType() == Material.LAPIS_BLOCK) {
            if (Main.SemPvP(p)) {
                event.setCancelled(true);
                if (!Main.Tempo.contains(p)) {
                    Main.Tempo.add(p);
                    final Vector Agua = p.getLocation().getDirection().normalize().multiply(55);
                    final Snowball AguaH = (Snowball)p.launchProjectile((Class)Snowball.class);
                    AguaH.setVelocity(Agua);
                    AguaH.setMetadata("Agua", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                    final Location pegou = p.getEyeLocation();
                    final BlockIterator Aguao = new BlockIterator(pegou, 0.0, 40);
                    while (Aguao.hasNext()) {
                        final Location Aguao2 = Aguao.next().getLocation();
                        final Effect camelo = Effect.STEP_SOUND;
                        p.playSound(p.getLocation(), Sound.STEP_WOOD, 5.5f, 5.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOD, 1.5f, 1.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOD, 2.5f, 2.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOD, 3.5f, 3.5f);
                        p.playSound(p.getLocation(), Sound.STEP_WOOD, 4.5f, 4.5f);
                        p.getWorld().playEffect(Aguao2, camelo, 9);
                    }
                    this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Main.Tempo.contains(p)) {
                                Main.Tempo.remove(p);
                                p.sendMessage("§aJa pode usar seu kit");
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 15.5f, 15.5f);
                            }
                        }
                    }, 250L);
                }
                else {
                    p.sendMessage("§cCooldown.");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler
    public void EntityDamageEvent11(final EntityDamageByEntityEvent event) {
        final Entity Tomou2 = event.getEntity();
        if (event.getDamager() instanceof Snowball) {
            final Snowball Tomou3 = (Snowball)event.getDamager();
            if (Tomou3.hasMetadata("Agua")) {
                event.setDamage(11.0);
                ((LivingEntity)Tomou2).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 0), true);
                final Vector vector = Tomou2.getLocation().getDirection();
                vector.multiply(-1.0f);
                Tomou2.setVelocity(vector);
            }
        }
    }
    
    @EventHandler
    public void AvatarAr11(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.Red2.contains(p.getName()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().getType() == Material.REDSTONE_BLOCK) {
            if (Main.SemPvP(p)) {
                event.setCancelled(true);
                if (!Main.Tempo.contains(p)) {
                    Main.Tempo.add(p);
                    final Vector Fogo = p.getLocation().getDirection().normalize().multiply(55);
                    final Snowball FogoH = (Snowball)p.launchProjectile((Class)Snowball.class);
                    FogoH.setVelocity(Fogo);
                    FogoH.setMetadata("Fogo", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                    final Location pegou = p.getEyeLocation();
                    final BlockIterator Fogao = new BlockIterator(pegou, 0.0, 40);
                    while (Fogao.hasNext()) {
                        final Location Fogao2 = Fogao.next().getLocation();
                        final Effect camelo = Effect.STEP_SOUND;
                        p.playSound(p.getLocation(), Sound.FIRE, 5.5f, 5.5f);
                        p.playSound(p.getLocation(), Sound.FIRE, 1.5f, 1.5f);
                        p.playSound(p.getLocation(), Sound.FIRE, 2.5f, 2.5f);
                        p.playSound(p.getLocation(), Sound.FIRE, 3.5f, 3.5f);
                        p.playSound(p.getLocation(), Sound.FIRE, 4.5f, 4.5f);
                        p.getWorld().playEffect(Fogao2, camelo, 10);
                    }
                    this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Main.Tempo.contains(p)) {
                                Main.Tempo.remove(p);
                                p.sendMessage("§aJa pode usar seu kit");
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 15.5f, 15.5f);
                            }
                        }
                    }, 250L);
                }
                else {
                    p.sendMessage("§cCooldown.");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler
    public void EntityDamageEvent1(final EntityDamageByEntityEvent event) {
        final Entity Tomou2 = event.getEntity();
        if (event.getDamager() instanceof Snowball) {
            final Snowball Tomou3 = (Snowball)event.getDamager();
            if (Tomou3.hasMetadata("Fogo")) {
                event.setDamage(12.0);
                Tomou2.setFireTicks(150);
            }
        }
    }
    
    @EventHandler
    public void AvatarTerra(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Main.Terra2.contains(p.getName()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Avatar.contains(p.getName()) && p.getItemInHand().getType() == Material.GRASS) {
            if (Main.SemPvP(p)) {
                event.setCancelled(true);
                if (!Main.Tempo.contains(p)) {
                    Main.Tempo.add(p);
                    final Vector Terra = p.getLocation().getDirection().normalize().multiply(55);
                    final Snowball TerraH = (Snowball)p.launchProjectile((Class)Snowball.class);
                    TerraH.setVelocity(Terra);
                    TerraH.setMetadata("Terra", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                    final Location pegou = p.getEyeLocation();
                    final BlockIterator Terrao = new BlockIterator(pegou, 0.0, 40);
                    while (Terrao.hasNext()) {
                        final Location Terrao2 = Terrao.next().getLocation();
                        final Effect camelo = Effect.STEP_SOUND;
                        p.playSound(p.getLocation(), Sound.STEP_GRASS, 5.5f, 5.5f);
                        p.playSound(p.getLocation(), Sound.STEP_GRASS, 1.5f, 1.5f);
                        p.playSound(p.getLocation(), Sound.STEP_GRASS, 2.5f, 2.5f);
                        p.playSound(p.getLocation(), Sound.STEP_GRASS, 3.5f, 3.5f);
                        p.playSound(p.getLocation(), Sound.STEP_GRASS, 4.5f, 4.5f);
                        p.getWorld().playEffect(Terrao2, camelo, 2);
                    }
                    this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Main.Tempo.contains(p)) {
                                Main.Tempo.remove(p);
                                p.sendMessage("§aJa pode usar seu kit");
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 15.5f, 15.5f);
                            }
                        }
                    }, 250L);
                }
                else {
                    p.sendMessage("§cCooldown.");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler
    public void EntityDamageEvent111(final EntityDamageByEntityEvent event) {
        final Entity Tomou2 = event.getEntity();
        if (event.getDamager() instanceof Snowball) {
            final Snowball Tomou3 = (Snowball)event.getDamager();
            if (Tomou3.hasMetadata("Terra")) {
                event.setDamage(14.0);
                final Vector vector = Tomou2.getLocation().getDirection();
                vector.multiply(-3.2f);
                Tomou2.setVelocity(vector);
            }
        }
    }
    
    @EventHandler
    public void drop(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (Main.Ferro2.contains(p.getName()) && item.getType() == Material.WOOL) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void drop2(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (Main.AvatarH.contains(p.getName()) && item.getType() == Material.LAPIS_BLOCK) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void drop3(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (Main.Red2.contains(p.getName()) && item.getType() == Material.REDSTONE_BLOCK) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void drop4(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (Main.Terra2.contains(p.getName()) && item.getType() == Material.GRASS) {
            e.setCancelled(true);
        }
    }
}
