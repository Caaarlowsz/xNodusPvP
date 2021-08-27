package Nodus.InventariosFake;

import Nodus.Main.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class MenuKits implements Listener
{
    public static Main plugin;
    
    public MenuKits(final Main main) {
        MenuKits.plugin = main;
    }
    
    public static void guiKits(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 54, "§6 >>>> Kits [1] <<<<");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§8Slot Bloqueado");
        final ArrayList descdlc = new ArrayList();
        descdlc.add("§bCompre Vip ou ganhe eventos para debloquear");
        metav.setLore((List)descdlc);
        vidro.setItemMeta(metav);
        final ItemStack pvp = new ItemStack(Material.STONE_SWORD);
        final ItemMeta metapyro = pvp.getItemMeta();
        metapyro.setDisplayName(ChatColor.AQUA + "PvP");
        final ArrayList descpyro = new ArrayList();
        descpyro.add("§3§l Hab §9§l>> §7Tenha uma espada sharp 1");
        metapyro.setLore((List)descpyro);
        pvp.setItemMeta(metapyro);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, pvp);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        final ItemStack thor2 = new ItemStack(Material.ACTIVATOR_RAIL);
        final ItemMeta metathor2 = thor2.getItemMeta();
        metathor2.setDisplayName("§aOutros Kits!");
        thor2.setItemMeta(metathor2);
        inv.setItem(8, thor2);
        if (p.hasPermission("kit.Archer")) {
            final ItemStack gege = new ItemStack(Material.BOW);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Archer");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Mate seus inimigos com seu arco e flecha");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Fireman")) {
            final ItemStack gege = new ItemStack(Material.LAVA);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Fireman");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Nao Tome Dano Da Lava e");
            descgege.add("§3§l Hab §9§l>> §7Queime Seus Inimigos com sua espada");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Anchor")) {
            final ItemStack gege = new ItemStack(Material.ANVIL);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Anchor");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Nao de knockback e nem leve knockback");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Kangaroo")) {
            final ItemStack gege = new ItemStack(Material.FIREWORK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Kangaroo");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7De duplos pulos com sua firework");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.milkman")) {
            final ItemStack gege = new ItemStack(Material.MILK_BUCKET);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Milkman");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe Regeneracao, Forca, Velocidade e Resistencia ao Fogo!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Lobisomem")) {
            final ItemStack gege = new ItemStack(Material.MONSTER_EGG);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Lobisomem");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe velocidade e Super Pulo ao virar um lobisomem!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Ninja")) {
            final ItemStack gege = new ItemStack(Material.NETHER_STAR);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Ninja");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Bata em um jogador aperte shift");
            descgege.add("§3§l Hab §9§l>> §7para se teleportar ate ele");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Frosty")) {
            final ItemStack gege = new ItemStack(Material.SNOW_BLOCK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Frosty");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe forca e Velocidade na Neve");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Ghoul")) {
            final ItemStack gege = new ItemStack(Material.REDSTONE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Ghoul");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Se Torne um verdadeiro Ghoul!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Titan")) {
            final ItemStack gege = new ItemStack(Material.BEDROCK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Titan");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe Regeneracao IV");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Fisherman")) {
            final ItemStack gege = new ItemStack(Material.FISHING_ROD);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Fisherman");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Use sua vara de pesca para puxar jogadores");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Viper")) {
            final ItemStack gege = new ItemStack(Material.SPIDER_EYE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Viper");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ao bater em jogadores voce tem 33% de chance");
            descgege.add("§3§l Hab §9§l>> §7de dar a eles o efeito de Veneno!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Viking")) {
            final ItemStack gege = new ItemStack(Material.DIAMOND_AXE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Viking");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Cause o dano de uma espada de Iron com seu machado!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.infernor")) {
            final ItemStack gege = new ItemStack(Material.NETHER_FENCE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Infernor");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Crie Uma Arena Infernal!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Snail")) {
            final ItemStack gege = new ItemStack(Material.WEB);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Snail");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ao bater em jogadores voce tem 33% de chance");
            descgege.add("§3§l Hab §9§l>> §7de dar a eles o efeito de Lentidao!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.grandpa")) {
            final ItemStack gege = new ItemStack(Material.CARROT_STICK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Grandpa");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7KnockBack!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Naruto")) {
            final ItemStack gege = new ItemStack(Material.GOLD_INGOT);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Naruto");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tenha o poder de virar a Raposa de 9 caldas!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Specialist")) {
            final ItemStack gege = new ItemStack(Material.BOOK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Specialist");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tenha uma Mesa de Encantos Portatil");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Jellyfish")) {
            final ItemStack gege = new ItemStack(Material.STATIONARY_WATER);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Jellyfish");
            final ArrayList descgege = new ArrayList();
            descgege.add("§4MANUTENCAO!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Confuser")) {
            final ItemStack gege = new ItemStack(Material.APPLE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Confuser");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Deixe seus inimigos com Confusao");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Stomper")) {
            final ItemStack gege = new ItemStack(Material.DIAMOND_BOOTS);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Stomper");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Esmague seus inimigos");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Endermage")) {
            final ItemStack gege = new ItemStack(Material.PORTAL);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Endermage");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Puxe quem estiver acima ou abaixo de voce!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Switcher")) {
            final ItemStack gege = new ItemStack(Material.SNOW_BALL);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Switcher");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ao jogar uma bola de neve em um player");
            descgege.add("§3§l Hab §9§l>> §7voce sera teleportado ate ele!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Monk")) {
            final ItemStack gege = new ItemStack(Material.BLAZE_ROD);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Monk");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Coloque items em um local");
            descgege.add("§3§l Hab §9§l>> §7aleatorio no inventario do inimigo!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Camel")) {
            final ItemStack gege = new ItemStack(Material.SAND);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Camel");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Na areia ganhe velocidade e regenera\u00e7o!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Poseidon")) {
            final ItemStack gege = new ItemStack(Material.WATER_BUCKET);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Poseidon");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Quando estiver na neve ganhe forca e speed!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.thor")) {
            final ItemStack gege = new ItemStack(Material.WOOD_AXE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Thor");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Cause raios como o thor");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Urgal")) {
            final ItemStack gege = new ItemStack(Material.POTION, 1, (short)8265);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Urgal");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe mais forca!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.sumo")) {
            final ItemStack gege = new ItemStack(Material.EMERALD_BLOCK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Sumo");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Lance seus inimigos para o alto");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.scorpion")) {
            final ItemStack gege = new ItemStack(Material.ARROW);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Scorpion");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tenha a flecha do Scorpion");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Gladiator")) {
            final ItemStack gege = new ItemStack(Material.IRON_FENCE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Gladiator");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Puxe players para uma arena 1v1!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.WeakSword")) {
            final ItemStack gege = new ItemStack(Material.BONE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "WeakSword");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Cause efeito de Whiter nos seus inimigos");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Critical")) {
            final ItemStack gege = new ItemStack(Material.GOLDEN_APPLE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Critical");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tenha chance de dar golpes criticos nos inimigos");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Berserker")) {
            final ItemStack gege = new ItemStack(Material.OBSIDIAN);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Berserker");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tenha um monstro de si mesmo!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Hunter")) {
            final ItemStack gege = new ItemStack(Material.STICK);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Hunter");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Atire Dardos nos seus inimigos! E os Cause:");
            descgege.add("§3§l Hab §9§l>> §7Veneno, Confusao, Cegueira e Lentidao");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Grappler")) {
            final ItemStack gege = new ItemStack(Material.LEASH);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Grappler");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Se Pendure Onde Quiser!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Boxer")) {
            final ItemStack gege = new ItemStack(Material.QUARTZ);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Boxer");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Tome Dano Reduzido e Cause um Dano imenso com suas luvas!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Hulk")) {
            final ItemStack gege = new ItemStack(Material.DIAMOND);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Hulk");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Levante seus inimigos e os arremesse");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Reaper")) {
            final ItemStack gege = new ItemStack(Material.WOOD_HOE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Reaper");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Com sua enchada deixe seus inmigos com efeito de whiter");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Launcher")) {
            final ItemStack gege = new ItemStack(Material.SPONGE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Launcher");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Arremesse seus inimigos para o ar");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.HomemPedra")) {
            final ItemStack gege = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Homem-Pedra");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ganhe Velocidade e Regeneracao na Pedra");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Resouper")) {
            final ItemStack gege = new ItemStack(Material.MUSHROOM_SOUP);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Resouper");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ao matar um jogador ganhe sopas no inventario!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.Turtle")) {
            final ItemStack gege = new ItemStack(Material.DIAMOND_CHESTPLATE);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Turtle");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Ao pressionar SHIFT tome dano reduzido");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.DarkGod")) {
            final ItemStack gege = new ItemStack(Material.HOPPER);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "DarkGod");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Deixe seus inimigos cegos!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        if (p.hasPermission("kit.thresh")) {
            final ItemStack gege = new ItemStack(Material.SOUL_SAND);
            final ItemMeta metagege = gege.getItemMeta();
            metagege.setDisplayName(ChatColor.AQUA + "Thresh");
            final ArrayList descgege = new ArrayList();
            descgege.add("§3§l Hab §9§l>> §7Puxe as inimiga recalcada!");
            metagege.setLore((List)descgege);
            gege.setItemMeta(metagege);
            inv.addItem(new ItemStack[] { gege });
        }
        ItemStack[] arrayOfItemStack;
        for (int descgege2 = (arrayOfItemStack = inv.getContents()).length, metagege2 = 0; metagege2 < descgege2; ++metagege2) {
            final ItemStack item = arrayOfItemStack[metagege2];
            if (item == null) {
                inv.setItem(inv.firstEmpty(), vidro);
            }
        }
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§6 >>>> Kits [1] <<<<") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/pvp");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BOW) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/archer");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ANVIL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/anchor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SOUL_SAND) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/thresh");
                return;
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/ghoul");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BEDROCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/titan");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MONSTER_EGG) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/lobisomem");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIREWORK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/kangaroo");
                return;
            }
            if (e.getCurrentItem().getType() == Material.CARROT_STICK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/grandpa");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/turtle");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/viking");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BONE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/weaksword");
                return;
            }
            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/ninja");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/fisherman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/viper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/homempedra");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/hulk");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WEB) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/snail");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/critical");
                return;
            }
            if (e.getCurrentItem().getType() == Material.STICK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/hunter");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LEASH) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/grappler");
                return;
            }
            if (e.getCurrentItem().getType() == Material.HOPPER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/darkgod");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOD_HOE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/reaper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.QUARTZ) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/boxer");
                return;
            }
            if (e.getCurrentItem().getType() == Material.STATIONARY_WATER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/jellyfish");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/resouper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPONGE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/launcher");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/stomper");
                return;
            }
            if (e.getCurrentItem().getType() == Material.NETHER_FENCE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/infernor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.PORTAL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/endermage");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MILK_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/milkman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/naruto");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BOOK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/specialist");
                return;
            }
            if (e.getCurrentItem().getType() == Material.OBSIDIAN) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/berserker");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/switcher");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/monk");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SAND) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/camel");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/poseidon");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/frosty");
                return;
            }
            if (e.getCurrentItem().getType() == Material.POTION) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/urgal");
                return;
            }
            if (e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/sumo");
                return;
            }
            if (e.getCurrentItem().getType() == Material.APPLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/confuser");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ARROW) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/scorpion");
                return;
            }
            if (e.getCurrentItem().getType() == Material.LAVA) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/fireman");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOD_AXE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/thor");
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/gladiator");
                return;
            }
            if (e.getCurrentItem().getType() == Material.ACTIVATOR_RAIL) {
                e.setCancelled(true);
                p.closeInventory();
                MenuKits2.guiKits(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.ZOMBIE_METAL, 5.0f, 5.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.IRON_DOOR) {
                e.setCancelled(true);
                p.closeInventory();
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.ZOMBIE_METAL, 5.0f, 5.0f);
                Selector.guiWarp(p);
            }
        }
    }
}
