package Nodus.Kits2;

import org.bukkit.event.*;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import Nodus.Main.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;

public class Grandpa implements Listener, CommandExecutor
{
    public static Main plugin;
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Egg> teias;
    public int cooldown11;
    public String cooldownMessage;
    ArrayList<String> thor;
    private transient HashMap<String, Long> lastThored;
    
    static {
        Grandpa.cooldown = new HashMap<String, Long>();
        Grandpa.teias = new HashMap<String, Egg>();
    }
    
    public Grandpa(final Main main) {
        this.cooldown11 = 40;
        this.cooldownMessage = ChatColor.RED + "Aguarde!";
        this.thor = new ArrayList<String>();
        this.lastThored = new HashMap<String, Long>();
        Grandpa.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Grandpa")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Grandpa")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Grandpa");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.Grandpa.add(sender.getName());
                    p.getInventory().clear();
                    Habilidade.setAbility(p, "Grandpa");
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    final ItemStack bow = new ItemStack(Material.STICK);
                    bow.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
                    final ItemMeta bowmeta = bow.getItemMeta();
                    bow.setItemMeta(bowmeta);
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Grandpa.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Grandpa.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
}
