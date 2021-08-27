package Nodus.Kits2;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.player.*;
import java.util.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class DeathNote implements Listener, CommandExecutor
{
    public static Main plugin;
    public static ArrayList<Player> cooldown;
    
    static {
        DeathNote.cooldown = new ArrayList<Player>();
    }
    
    public DeathNote(final Main main) {
        DeathNote.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("DeathNote")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.DeathNote")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " DeathNote");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.dn.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.BOOK);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bowmeta.setDisplayName("§5DeathNote");
                    Habilidade.setAbility(p, "Deathnote");
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathNote.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', DeathNote.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void click(final PlayerInteractEntityEvent e) {
        final Player p = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            final Player p2 = (Player)e.getRightClicked();
            final ItemStack i = p.getItemInHand();
            if (i.getType() == Material.BOOK && i.hasItemMeta() && i.getItemMeta().getDisplayName().equalsIgnoreCase("§5DeathNote") && p.hasPermission("kit.deathnote")) {
                e.setCancelled(true);
                if (DeathNote.cooldown.contains(p)) {
                    p.sendMessage(ChatColor.DARK_RED + "O kit esta em cooldown!");
                    return;
                }
                DeathNote.cooldown.add(p);
                p.sendMessage(ChatColor.GOLD + "Voce escreveu o player " + p2.getName() + " no Death Note!");
                p2.sendMessage(ChatColor.GOLD + "Seu nome foi escrito no Death Note!!");
                final Random r = new Random();
                final int rand = r.nextInt(100);
                if (rand > 90) {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 5));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta com poison!");
                }
                else if (rand > 80) {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta com slow!");
                }
                else if (rand > 65) {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 5));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta confuso!");
                }
                else if (rand > 50) {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta cego!");
                }
                else if (rand > 25) {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta fraco!");
                }
                else {
                    p2.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 5));
                    p2.sendMessage(ChatColor.GREEN + "Voce esta passando mal!");
                }
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                DeathNote.cooldown.remove(p);
            }
        }, 800L);
    }
}
