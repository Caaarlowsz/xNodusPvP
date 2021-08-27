package Nodus.Kits2;

import org.bukkit.inventory.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.util.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class DeshFire implements Listener, CommandExecutor
{
    public int boost;
    public static ArrayList<String> Deshfire;
    static ArrayList<String> fall;
    public static HashMap<String, ItemStack[]> Armadura;
    public static HashMap<String, ItemStack[]> saveinv;
    public static HashMap<String, ItemStack[]> armadura;
    public static HashMap<String, ItemStack[]> Armadura2;
    public static List<Player> cooldownm;
    public static Main plugin;
    
    static {
        DeshFire.Deshfire = new ArrayList<String>();
        DeshFire.fall = new ArrayList<String>();
        DeshFire.Armadura = new HashMap<String, ItemStack[]>();
        DeshFire.saveinv = new HashMap<String, ItemStack[]>();
        DeshFire.armadura = new HashMap<String, ItemStack[]>();
        DeshFire.Armadura2 = new HashMap<String, ItemStack[]>();
        DeshFire.cooldownm = new ArrayList<Player>();
    }
    
    public DeshFire(final Main main) {
        this.boost = 6;
        DeshFire.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("deshfire")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Deshfire")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Deshfire");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Deshfire.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.REDSTONE_BLOCK);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§bDeshfire!");
                    Habilidade.setAbility(p, "Deshfire");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', DeshFire.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', DeshFire.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void DeshClick(final PlayerInteractEvent event) {
        final int fire = 6;
        final Player p = event.getPlayer();
        if (event.getPlayer().getItemInHand().getType() == Material.REDSTONE_BLOCK && Main.Deshfire.contains(event.getPlayer().getName())) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                event.setCancelled(true);
            }
            if (DeshFire.cooldownm.contains(p)) {
                p.sendMessage("§lAguarde o cooldown acabar!");
                return;
            }
            DeshFire.cooldownm.add(p);
            DeshFire.fall.add(p.getName());
            p.setVelocity(p.getEyeLocation().getDirection().multiply(this.boost).add(new Vector(0, 0, 0)));
            p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.SMOKE, 10, 0);
            final Location loc = p.getLocation();
            for (final Entity pertos : p.getNearbyEntities(8.0, 8.0, 8.0)) {
                if (pertos instanceof Player) {
                    final Player perto = (Player)pertos;
                    ((Player)pertos).damage(10.0);
                    pertos.setVelocity(new Vector(0.1, 0.0, 0.1));
                    ((Player)pertos).setFireTicks(fire * 20);
                }
            }
            final ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
            final LeatherArmorMeta kCapacete = (LeatherArmorMeta)Capacete.getItemMeta();
            kCapacete.setColor(Color.RED);
            Capacete.setItemMeta((ItemMeta)kCapacete);
            final ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
            final LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
            kPeitoral.setColor(Color.RED);
            Peitoral.setItemMeta((ItemMeta)kPeitoral);
            final ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
            final LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
            kCalss.setColor(Color.RED);
            Calss.setItemMeta((ItemMeta)kCalss);
            final ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
            final LeatherArmorMeta kBota = (LeatherArmorMeta)Capacete.getItemMeta();
            kBota.setColor(Color.RED);
            Bota.setItemMeta((ItemMeta)kBota);
            DeshFire.Armadura.put(p.getName(), p.getInventory().getArmorContents());
            p.getInventory().setHelmet(Capacete);
            p.getInventory().setChestplate(Peitoral);
            p.getInventory().setLeggings(Calss);
            p.getInventory().setBoots(Bota);
            p.updateInventory();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.getInventory().setArmorContents((ItemStack[])null);
                    p.updateInventory();
                    DeshFire.fall.remove(p.getName());
                }
            }, 50L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    DeshFire.cooldownm.remove(p);
                    Main.Deshfire.remove(p);
                    p.sendMessage("§3§oVoce pode usar novamente!");
                    p.getWorld().playSound(p.getLocation(), Sound.BURP, 5.0f, 5.0f);
                }
            }, 700L);
        }
    }
    
    @EventHandler
    public void AiMeuCuv5(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL && DeshFire.fall.contains(p.getName())) {
            DeshFire.fall.remove(p.getName());
            e.setDamage(6.5);
        }
    }
}
