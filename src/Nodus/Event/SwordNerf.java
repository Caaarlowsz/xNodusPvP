package Nodus.Event;

import Nodus.Main.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.event.*;

public class SwordNerf implements Listener
{
    public SwordNerf(final Main plugin) {
    }
    
    @EventHandler
    public void NerfsDanos(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player player = (Player)event.getDamager();
            if (event.getDamage() > 1.0) {
                event.setDamage(event.getDamage() - 1.0);
            }
            if (event.getDamager() instanceof Player) {
                if (player.getFallDistance() > 0.0f && !player.isOnGround() && !player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    final int NewDamage = (int)(event.getDamage() * 1.5) - (int)event.getDamage();
                    if (event.getDamage() > 1.0) {
                        event.setDamage(event.getDamage() - NewDamage);
                    }
                }
                if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
                    event.setDamage(2.0);
                }
                if (player.getItemInHand().getType() == Material.STONE_SWORD) {
                    event.setDamage(3.5);
                }
                if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                    event.setDamage(5.0);
                }
                if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                    event.setDamage(6.0);
                }
                if (player.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                    event.setDamage(event.getDamage() + 1.0);
                }
                if (player.getFallDistance() > 0.0f && !player.isOnGround() && !player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
                        event.setDamage(event.getDamage() + 1.0);
                    }
                    if (player.getItemInHand().getType() == Material.STONE_SWORD) {
                        event.setDamage(event.getDamage() + 1.0);
                    }
                    if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                        event.setDamage(event.getDamage() + 1.0);
                    }
                    if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                        event.setDamage(event.getDamage() + 1.0);
                    }
                    if (player.getFallDistance() > 0.0f && player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                        if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
                            event.setDamage(event.getDamage() + 1.5);
                        }
                        if (player.getItemInHand().getType() == Material.STONE_SWORD) {
                            event.setDamage(event.getDamage() + 1.5);
                        }
                        if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                            event.setDamage(event.getDamage() + 1.5);
                        }
                        if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                            event.setDamage(event.getDamage() + 1.5);
                        }
                    }
                    if (player.getFallDistance() > 0.0f && player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE) && !player.isOnGround()) {
                        if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
                            event.setDamage(event.getDamage() + 0.5);
                        }
                        if (player.getItemInHand().getType() == Material.STONE_SWORD) {
                            event.setDamage(event.getDamage() + 0.5);
                        }
                        if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                            event.setDamage(event.getDamage() + 0.5);
                        }
                        if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                            event.setDamage(event.getDamage() + 0.5);
                        }
                    }
                }
            }
        }
    }
}
