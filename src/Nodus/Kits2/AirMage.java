package Nodus.Kits2;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.util.*;
import java.util.*;
import org.bukkit.event.*;

public class AirMage implements Listener, CommandExecutor
{
    public static Main plugin;
    public ArrayList<Player> cooldownm;
    
    public AirMage(final Main main) {
        this.cooldownm = new ArrayList<Player>();
        AirMage.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("AirMage")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.AirMage")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " AirMage");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.AirMage.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Airmage");
                    espadameta.setDisplayName("§cSword");
                    final ItemStack AirMage = new ItemStack(Material.WOOL);
                    final ItemMeta bow = AirMage.getItemMeta();
                    bow.setDisplayName("§f§lLevitar");
                    AirMage.setItemMeta(bow);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { AirMage });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', AirMage.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', AirMage.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void AirMageHAB(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals((Object)Material.WOOL) && event.getAction().name().contains("RIGHT")) {
            if (this.cooldownm.contains(player)) {
                player.sendMessage(ChatColor.RED + "Acalme-se meu jovem mago");
                return;
            }
            if (Main.AirMage.contains(event.getPlayer().getName())) {
                for (final Entity target : event.getPlayer().getNearbyEntities(8.0, 5.0, 8.0)) {
                    if (target instanceof Player) {
                        final Player t = (Player)target;
                        final Vector sponge = t.getLocation().getDirection().multiply(0).setY(0.8);
                        final Vector sponge2 = t.getLocation().getDirection().multiply(0).setY(1);
                        final Vector sponge3 = t.getLocation().getDirection().multiply(0).setY(1.2);
                        final Vector sponge4 = t.getLocation().getDirection().multiply(0).setY(1.2);
                        t.setVelocity(sponge);
                        player.sendMessage(ChatColor.GREEN + "Voce esta levitando os jogadores a 8 blocos ao seu redor!");
                        t.sendMessage(ChatColor.RED + "§fUm airmage esta te Levitando!");
                        this.cooldownm.add(player);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                t.setVelocity(sponge4);
                            }
                        }, 15L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                t.setVelocity(sponge4);
                            }
                        }, 15L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                t.setVelocity(sponge4);
                            }
                        }, 20L);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.GREEN + "Cooldown Acabou");
                                AirMage.this.cooldownm.remove(player);
                            }
                        }, 600L);
                    }
                }
            }
        }
    }
}
