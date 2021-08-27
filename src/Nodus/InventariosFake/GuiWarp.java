package Nodus.InventariosFake;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import Nodus.Main.*;
import org.bukkit.enchantments.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;

public class GuiWarp implements Listener
{
    public static Main plugin;
    public static ArrayList<String> Parkour;
    
    static {
        GuiWarp.Parkour = new ArrayList<String>();
    }
    
    public GuiWarp(final Main main) {
        GuiWarp.plugin = main;
    }
    
    public static void menuYT(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 27, "§bWarp");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta vidrometa = vidro.getItemMeta();
        vidrometa.setDisplayName(" ");
        vidro.setItemMeta(vidrometa);
        final ItemStack vidro2 = new ItemStack(Material.IRON_FENCE);
        final ItemMeta vidrometa2 = vidro2.getItemMeta();
        vidrometa2.setDisplayName(" ");
        vidro2.setItemMeta(vidrometa2);
        final ItemStack folha = new ItemStack(Material.VINE);
        final ItemMeta folhameta = folha.getItemMeta();
        folhameta.setDisplayName(ChatColor.translateAlternateColorCodes('&', GuiWarp.plugin.getConfig().getString("NomeServer")));
        folha.setItemMeta(folhameta);
        final ItemStack fornalha = new ItemStack(Material.FURNACE);
        final ItemMeta fornalhameta = fornalha.getItemMeta();
        fornalhameta.setDisplayName("§5§lWarps - KitPvP");
        fornalha.setItemMeta(fornalhameta);
        final ItemStack madeira = new ItemStack(Material.POTION);
        final ItemMeta madeirameta = madeira.getItemMeta();
        madeirameta.setDisplayName("§9§lWarp Main");
        madeira.setItemMeta(madeirameta);
        final ItemStack bedrock = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta bedrockmeta = bedrock.getItemMeta();
        bedrockmeta.setDisplayName("§a§l1v1");
        bedrock.setItemMeta(bedrockmeta);
        final ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
        final ItemMeta lavameta = lava.getItemMeta();
        lavameta.setDisplayName("§3§lWarp Challenge");
        lava.setItemMeta(lavameta);
        final ItemStack lava2 = new ItemStack(Material.CAKE);
        final ItemMeta lavameta2 = lava2.getItemMeta();
        lavameta2.setDisplayName("§b§lWarp RDM");
        lava2.setItemMeta(lavameta2);
        final ItemStack fps = new ItemStack(Material.SPONGE);
        final ItemMeta fpsmeta = fps.getItemMeta();
        fpsmeta.setDisplayName("§e§lWarp FPS");
        fps.setItemMeta(fpsmeta);
        final ItemStack fps2 = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta fpsmeta2 = fps2.getItemMeta();
        fpsmeta2.setDisplayName("§3§lWarp MDR");
        fps2.setItemMeta(fpsmeta2);
        final ItemStack fps3 = new ItemStack(Material.WATER_BUCKET);
        final ItemMeta fpsmeta3 = fps2.getItemMeta();
        fpsmeta3.setDisplayName("§5§lWarp MLG");
        fps3.setItemMeta(fpsmeta3);
        final ItemStack tx = new ItemStack(Material.WOOL);
        final ItemMeta tx2 = tx.getItemMeta();
        tx2.setDisplayName("§2§lWarp Texturas");
        tx.setItemMeta(tx2);
        final ItemStack tx3 = new ItemStack(Material.DIAMOND_BOOTS);
        final ItemMeta tx4 = tx3.getItemMeta();
        tx4.setDisplayName("§9§lWarp Parkour");
        tx3.setItemMeta(tx4);
        inv.setItem(0, folha);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro2);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(9, vidro);
        inv.setItem(8, folha);
        inv.setItem(10, fps3);
        inv.setItem(11, tx);
        inv.setItem(12, madeira);
        inv.setItem(13, lava2);
        inv.setItem(14, lava);
        inv.setItem(15, fps2);
        inv.setItem(16, fps);
        inv.setItem(17, vidro);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, vidro);
        inv.setItem(21, vidro);
        inv.setItem(21, vidro);
        inv.setItem(22, tx3);
        inv.setItem(23, vidro);
        inv.setItem(24, vidro);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerCLickInventry1(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§bWarp") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.POTION) {
                e.setCancelled(true);
                p.closeInventory();
                final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("main.world"));
                final double x = Main.plugin.getConfig().getDouble("main.x");
                final double y = Main.plugin.getConfig().getDouble("main.y");
                final double z = Main.plugin.getConfig().getDouble("main.z");
                final Location lobby = new Location(w, x, y, z);
                lobby.setPitch((float)Main.plugin.getConfig().getDouble("main.pitch"));
                lobby.setYaw((float)Main.plugin.getConfig().getDouble("main.yaw"));
                p.teleport(lobby);
                Habilidade.setAbility(p, "Main");
                p.getInventory().clear();
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§a[§3!§a]  §aTeleportado!");
                p.removePotionEffect(PotionEffectType.SLOW);
                p.removePotionEffect(PotionEffectType.JUMP);
                Main.removeAbility(p);
                Main.usandokit.add(p.getName());
                final ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
                final ItemMeta espadameta = espada.getItemMeta();
                espadameta.setDisplayName("§cMain Sword");
                espada.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                p.setFireTicks(0);
                p.getInventory().setItem(0, espada);
                p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
                Main.giveSoup(p, 35);
                return;
            }
            if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.getInventory().clear();
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                Habilidade.setAbility(p, "Challenge");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§a[§3!§a]  §aTeleportado!");
                Main.removeAbility(p);
                Main.usandokit.add(p.getName());
                p.getInventory().setBoots((ItemStack)null);
                p.getInventory().setChestplate((ItemStack)null);
                p.getInventory().setLeggings((ItemStack)null);
                p.getInventory().setHelmet((ItemStack)null);
                p.setFireTicks(0);
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BROWN_MUSHROOM, 64) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.RED_MUSHROOM, 64) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BOWL, 64) });
                Main.giveSoup(p, 33);
                final World w = Bukkit.getServer().getWorld(GuiWarp.plugin.getConfig().getString("challenge.world"));
                final double x = GuiWarp.plugin.getConfig().getDouble("challenge.x");
                final double y = GuiWarp.plugin.getConfig().getDouble("challenge.y");
                final double z = GuiWarp.plugin.getConfig().getDouble("challenge.z");
                final Location lobby = new Location(w, x, y, z);
                lobby.setPitch((float)GuiWarp.plugin.getConfig().getDouble("challenge.pitch"));
                lobby.setYaw((float)GuiWarp.plugin.getConfig().getDouble("challenge.yaw"));
                p.teleport(lobby);
                for (final PotionEffect effect : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect.getType());
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/mdr");
                Habilidade.setAbility(p, "Mdr");
                return;
            }
            if (e.getCurrentItem().getType() == Material.CAKE) {
                e.setCancelled(true);
                p.closeInventory();
                Habilidade.setAbility(p, "Rdm");
                p.chat("/rdm");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPONGE) {
                e.setCancelled(true);
                p.closeInventory();
                final World w = Bukkit.getServer().getWorld(GuiWarp.plugin.getConfig().getString("fps.world"));
                final double x = GuiWarp.plugin.getConfig().getDouble("fps.x");
                final double y = GuiWarp.plugin.getConfig().getDouble("fps.y");
                final double z = GuiWarp.plugin.getConfig().getDouble("fps.z");
                final Location lobby = new Location(w, x, y, z);
                lobby.setPitch((float)GuiWarp.plugin.getConfig().getDouble("fps.pitch"));
                lobby.setYaw((float)GuiWarp.plugin.getConfig().getDouble("fps.yaw"));
                p.getInventory().clear();
                p.setHealthScale(1.0);
                p.teleport(lobby);
                p.getInventory().clear();
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§a[§3!§a]  §aTeleportado!");
                Main.removeAbility(p);
                Habilidade.setAbility(p, "Fps");
                Main.usandokit.add(p.getName());
                p.getInventory().setBoots((ItemStack)null);
                p.getInventory().setChestplate((ItemStack)null);
                p.getInventory().setLeggings((ItemStack)null);
                p.setHealthScale(20.0);
                final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                p.getInventory().setItem(0, espada);
                p.setFireTicks(0);
                Main.giveSoup(p, 35);
                for (final PotionEffect effect2 : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect2.getType());
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOL) {
                e.setCancelled(true);
                p.closeInventory();
                final World w = Bukkit.getServer().getWorld(GuiWarp.plugin.getConfig().getString("textura.world"));
                final double x = GuiWarp.plugin.getConfig().getDouble("textura.x");
                final double y = GuiWarp.plugin.getConfig().getDouble("textura.y");
                final double z = GuiWarp.plugin.getConfig().getDouble("textura.z");
                final Location lobby = new Location(w, x, y, z);
                lobby.setPitch((float)GuiWarp.plugin.getConfig().getDouble("textura.pitch"));
                lobby.setYaw((float)GuiWarp.plugin.getConfig().getDouble("textura.yaw"));
                p.getInventory().clear();
                p.setHealthScale(1.0);
                p.teleport(lobby);
                Habilidade.setAbility(p, "Texturas");
                p.getInventory().clear();
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§a[§3!§a]  §aTeleportado!");
                Main.removeAbility(p);
                Main.usandokit.add(p.getName());
                p.getInventory().setBoots((ItemStack)null);
                p.getInventory().setChestplate((ItemStack)null);
                p.getInventory().setLeggings((ItemStack)null);
                p.getInventory().setHelmet((ItemStack)null);
                p.setHealthScale(20.0);
                final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_SWORD) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW) });
                final ItemStack bow = new ItemStack(Material.BOW);
                final ItemMeta bowmeta = bow.getItemMeta();
                bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                bow.setItemMeta(bowmeta);
                p.getInventory().setItem(6, bow);
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_CHESTPLATE) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_BOOTS) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_HELMET) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_LEGGINGS) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_LEGGINGS) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_CHESTPLATE) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_BOOTS) });
                p.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_HELMET) });
                p.setFireTicks(0);
                for (final PotionEffect effect3 : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect3.getType());
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                p.chat("/1v1");
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
                p.closeInventory();
                GuiWarp.Parkour.add(p.getName());
                final World w = Bukkit.getServer().getWorld(GuiWarp.plugin.getConfig().getString("parkour.world"));
                final double x = GuiWarp.plugin.getConfig().getDouble("parkour.x");
                final double y = GuiWarp.plugin.getConfig().getDouble("parkour.y");
                final double z = GuiWarp.plugin.getConfig().getDouble("parkour.z");
                final Location lobby = new Location(w, x, y, z);
                lobby.setPitch((float)GuiWarp.plugin.getConfig().getDouble("parkour.pitch"));
                lobby.setYaw((float)GuiWarp.plugin.getConfig().getDouble("parkour.yaw"));
                p.getInventory().clear();
                p.teleport(lobby);
                p.getInventory().clear();
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§a[§3!§a]  §aTeleportado!");
                Main.removeAbility(p);
                Habilidade.setAbility(p, "Parkour");
                p.setHealthScale(20.0);
                p.setFireTicks(0);
                for (final PotionEffect effect : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect.getType());
                }
            }
            if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
                Main.mlgss(p);
                p.closeInventory();
            }
        }
    }
    
    public static void removerparkour(final Player target) {
        GuiWarp.Parkour.remove(target);
    }
}
