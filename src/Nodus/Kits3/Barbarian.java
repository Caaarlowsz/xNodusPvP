package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.enchantments.*;
import org.bukkit.event.*;

public class Barbarian implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Barbarian(final Main main) {
        Barbarian.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Barbarian")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Barbarian")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Barbarian");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Barbarian.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Barbarian");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Barbarian.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Barbarian.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onKill(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player k = e.getEntity().getKiller();
            if (Main.barbarian.contains(k.getName())) {
                if (k.getItemInHand().getType() == Material.STONE_SWORD) {
                    k.setItemInHand(new ItemStack(Material.IRON_SWORD));
                    k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "1");
                }
                else if (k.getItemInHand().getType() == Material.IRON_SWORD) {
                    k.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
                    k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "2");
                }
                else if (k.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                    final int max = 5;
                    if (k.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                        final int lvl = k.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
                        if (lvl + 1 <= max) {
                            k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
                            k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, lvl + 1);
                            k.updateInventory();
                            k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                            if (lvl + 1 == 2) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "5");
                            }
                            else if (lvl + 1 == 3) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "6");
                            }
                            else if (lvl + 1 == 4) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "7");
                            }
                            else if (lvl + 1 == 5) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "8");
                            }
                        }
                        else {
                            k.sendMessage(ChatColor.GOLD + "Voce esta no level maximo!");
                        }
                    }
                    else {
                        k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "4");
                    }
                }
            }
        }
    }
}
