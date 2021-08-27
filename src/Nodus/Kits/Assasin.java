package Nodus.Kits;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.*;
import org.bukkit.event.*;

public class Assasin implements Listener, CommandExecutor
{
    public static Main plugin;
    private ArrayList<Player> cooldown;
    
    public Assasin(final Main main) {
        this.cooldown = new ArrayList<Player>();
        Assasin.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("assasin")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.assasin")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Assasin");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.assasin.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Assasin");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Assasin.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Assasin.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void Miley(final PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (Main.assasin.contains(p.getName()) && p.isSneaking()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0));
            if (!p.isSneaking()) {
                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            }
        }
    }
}
