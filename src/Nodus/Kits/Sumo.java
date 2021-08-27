package Nodus.Kits;

import org.bukkit.plugin.*;
import org.bukkit.block.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.util.*;

public class Sumo implements Listener, CommandExecutor
{
    public static int maca;
    public static ArrayList<Player> sumo;
    public Plugin plugin;
    public ArrayList<String> Cima;
    ArrayList<String> tempo;
    private ArrayList<Block> remover;
    
    static {
        Sumo.maca = Material.APPLE.getId();
        Sumo.sumo = new ArrayList<Player>();
    }
    
    public Sumo(final Main plugin) {
        this.Cima = new ArrayList<String>();
        this.tempo = new ArrayList<String>();
        this.remover = new ArrayList<Block>();
        this.plugin = (Plugin)plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("sumo")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.sumo")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Sumo");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Sumo.sumo.add(p);
                    Main.usandokit.add(p.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Sumo");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.APPLE) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void Morrer(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        Sumo.sumo.remove(p);
        this.tempo.remove(p.getName());
        this.Cima.remove(p.getName());
    }
    
    @EventHandler
    public void Sair(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        Sumo.sumo.remove(p);
        this.tempo.remove(p.getName());
        this.Cima.remove(p.getName());
    }
    
    @EventHandler
    public void Clicar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p instanceof Player && Sumo.sumo.contains(p)) {
            if (p.getItemInHand().equals((Object)Sumo.maca)) {
                return;
            }
            if (!e.getAction().name().contains("RIGHT")) {
                return;
            }
            if (p.getItemInHand().getTypeId() != Sumo.maca) {
                return;
            }
            e.setCancelled(true);
            if (!this.tempo.contains(p.getName())) {
                Location Local = e.getPlayer().getLocation();
                Local = Local.getWorld().getHighestBlockAt(Local).getLocation().add(0.0, 22.0, 0.0);
                for (int x = 0; x <= 0; ++x) {
                    for (int z = 0; z <= 0; ++z) {
                        final Block a = Local.add((double)x, 0.0, (double)z).getBlock();
                        a.setType(Material.BEDROCK);
                        e.getPlayer().teleport(Local.add(0.0, 1.0, 0.0));
                        this.tempo.add(p.getName());
                        this.Cima.add(p.getName());
                        Bukkit.getServer().getScheduler().runTaskLater(this.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                a.setType(Material.AIR);
                                Sumo.this.remover.remove(a);
                            }
                        }, 50L);
                    }
                }
            }
            else {
                p.sendMessage("\u00ef¿½cAguarde mais um pouco para usar sua habilidade !");
            }
        }
    }
    
    @EventHandler
    public void Cair(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.Cima.contains(p.getName())) {
            e.setCancelled(true);
            for (final Entity Altura : p.getNearbyEntities(8.0, 8.0, 8.0)) {
                if (Altura instanceof Player) {
                    final Player Pular = (Player)Altura;
                    final Vector v = p.getLocation().getDirection().multiply(0).setY(2.0);
                    Pular.setVelocity(v);
                }
            }
            this.Cima.remove(p.getName());
            e.setDamage(9.0);
            Bukkit.getServer().getScheduler().runTaskLater(this.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    Sumo.this.tempo.remove(p.getName());
                }
            }, 600L);
        }
    }
}
