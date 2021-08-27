package Nodus.Kits3;

import Nodus.Main.*;
import org.bukkit.*;
import org.bukkit.event.player.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class MonkHAb implements Listener
{
    private Main plugin;
    public int cooldown;
    public String monkCooldownMessage;
    public String monkedMessage;
    public int monkItemId;
    public String monkItemName;
    private transient HashMap<ItemStack, Long> monkStaff;
    public boolean sendThroughInventory;
    
    public MonkHAb(final Main instance) {
        this.cooldown = 15;
        this.monkCooldownMessage = ChatColor.BLUE + "Voce so pode usa depois de %s segundos!";
        this.monkedMessage = ChatColor.GREEN + "Monkado!";
        this.monkItemId = Material.BLAZE_ROD.getId();
        this.monkItemName = ChatColor.DARK_PURPLE + "Monk";
        this.monkStaff = new HashMap<ItemStack, Long>();
        this.sendThroughInventory = true;
        this.plugin = instance;
    }
    
    @EventHandler
    public void onRightClick(final PlayerInteractEntityEvent event) {
        final ItemStack item = event.getPlayer().getItemInHand();
        final Entity Entity;
        final Player Player = (Player)(Entity = (Entity)event.getPlayer());
        if (Main.monk.contains(Player.getName()) && Player.getItemInHand().getType() == Material.BLAZE_ROD) {
            long lastUsed = 0L;
            if (this.monkStaff.containsKey(item)) {
                lastUsed = this.monkStaff.get(item);
            }
            if (lastUsed + 1000 * this.cooldown > System.currentTimeMillis()) {
                event.getPlayer().sendMessage(String.format(this.monkCooldownMessage, -((System.currentTimeMillis() - (lastUsed + 1000 * this.cooldown)) / 1000L)));
            }
            else {
                final PlayerInventory inv = ((Player)event.getRightClicked()).getInventory();
                final int slot = new Random().nextInt(this.sendThroughInventory ? 36 : 9);
                ItemStack replaced = inv.getItemInHand();
                if (replaced == null) {
                    replaced = new ItemStack(0);
                }
                ItemStack replacer = inv.getItem(slot);
                if (replacer == null) {
                    replacer = new ItemStack(0);
                }
                inv.setItemInHand(replacer);
                inv.setItem(slot, replaced);
                this.monkStaff.put(item, System.currentTimeMillis());
                event.getPlayer().sendMessage(this.monkedMessage);
            }
        }
    }
}
