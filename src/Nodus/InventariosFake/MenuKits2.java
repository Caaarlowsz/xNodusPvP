package Nodus.InventariosFake;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class MenuKits2 implements Listener
{
    public static void guiKits(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 54, "§6 >>>> Kits [2] <<<<");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("§8Slot Bloqueado");
        final ArrayList descdlc = new ArrayList();
        descdlc.add("§bCompre Vip ou ganhe eventos para debloquear");
        metav.setLore((List)descdlc);
        vidro.setItemMeta(metav);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        final ItemStack thor2 = new ItemStack(Material.ACTIVATOR_RAIL);
        final ItemMeta metathor2 = thor2.getItemMeta();
        metathor2.setDisplayName("§aOutros Kits! 2");
        thor2.setItemMeta(metathor2);
        inv.setItem(8, thor2);
        final ItemStack thor3 = new ItemStack(Material.IRON_DOOR);
        final ItemMeta metathor3 = thor3.getItemMeta();
        metathor3.setDisplayName("§B§l Voltar");
        thor3.setItemMeta(metathor3);
        inv.setItem(0, thor3);
        if (p.hasPermission("kit.teleporter")) {
            final ItemStack pyro = new ItemStack(Material.ARROW);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Teleporter");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Jogue sua Flecha e telporte-se onde ela cair");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.assasin")) {
            final ItemStack pyro = new ItemStack(Material.REDSTONE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Assasin");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Ao Segurar Shift Ganhe For\u00e7a");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.dwarf")) {
            final ItemStack pyro = new ItemStack(Material.GOLD_BOOTS);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Dwarf");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Segure shift e carregue seu poder, depois aremesse seus inimigos para longe");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.sniper")) {
            final ItemStack pyro = new ItemStack(Material.IRON_BARDING);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Sniper");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Sua sniper e poderosa!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.dj")) {
            final ItemStack pyro = new ItemStack(Material.MELON);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Dj");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Faca um mix de itens!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.trocador")) {
            final ItemStack pyro = new ItemStack(Material.COBBLE_WALL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Trocador");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Troque a armadura!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.jumper")) {
            final ItemStack pyro = new ItemStack(Material.ENDER_PEARL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Jumper");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Domine o poder do teleporte!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.pao")) {
            final ItemStack pyro = new ItemStack(Material.BREAD);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Pao");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7afaste os inimigos com o seu pao!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.spiderman")) {
            final ItemStack pyro = new ItemStack(Material.STRING);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "SpiderMan");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Atire teias!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.madman")) {
            final ItemStack pyro = new ItemStack(Material.RED_ROSE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Madman");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Deixe os inimigos mais fracos ou roube a resistencia deles!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.exploder")) {
            final ItemStack pyro = new ItemStack(Material.TNT);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Exploder");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Atire TnTs!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.achilles")) {
            final ItemStack pyro = new ItemStack(Material.WOOD_SWORD);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Achilles");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7O dano de espadas sobre voce e mais fraco!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.vacuum")) {
            final ItemStack pyro = new ItemStack(Material.BEACON);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Avatar");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Domine os 4 elementos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.deshfire")) {
            final ItemStack pyro = new ItemStack(Material.REDSTONE_BLOCK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Deshfire");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Espalhe fogo por perto!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.nofall")) {
            final ItemStack pyro = new ItemStack(Material.CHAINMAIL_BOOTS);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "NoFall");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Seja um nofall sem dano de queda!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.witherman")) {
            final ItemStack pyro = new ItemStack(Material.SKULL_ITEM);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Wither");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Atire cabecas de wither e nao tome dano de explosoes!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.sponger")) {
            final ItemStack pyro = new ItemStack(Material.SPONGE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Sponger");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha uma esponja portatil! (habilidade stomper)");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.sonic")) {
            final ItemStack pyro = new ItemStack(Material.LAPIS_BLOCK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Sonic");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Seus espinhos sao venenosos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.vacuum")) {
            final ItemStack pyro = new ItemStack(Material.EYE_OF_ENDER);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Vacuum");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Puxe os inimigos com um buraco negro!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.necromancer")) {
            final ItemStack pyro = new ItemStack(Material.OBSIDIAN);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Necromancer");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha poderes demoniacos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.phantom")) {
            final ItemStack pyro = new ItemStack(Material.FEATHER);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Phantom");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha a habilidade de voar!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.taurus")) {
            final ItemStack pyro = new ItemStack(Material.EMERALD_BLOCK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Taurus");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Lance quem estiver por perto para cima!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.velotrol")) {
            final ItemStack pyro = new ItemStack(Material.MINECART);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Velotrol");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7De um boost e exploda seus inimigos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.bloodgun")) {
            final ItemStack pyro = new ItemStack(Material.WOOD_HOE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Bloodgun");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha uma arma de sangue!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.forcefield")) {
            final ItemStack pyro = new ItemStack(Material.NETHER_FENCE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "ForceField");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Hask OpenGL!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.burrower")) {
            final ItemStack pyro = new ItemStack(Material.GOLD_HOE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Burstmaster");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Atire balas explosivas, §4ALLAHU AKBAR!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.doctor")) {
            final ItemStack pyro = new ItemStack(Material.SLIME_BALL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Doctor");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Cure-se de qualquer efeito");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.deathnote")) {
            final ItemStack pyro = new ItemStack(Material.BOOK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "DeathNote");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Escreva o nome de seus inimigos no deathnote");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.flash")) {
            final ItemStack pyro = new ItemStack(Material.REDSTONE_TORCH_ON);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Flash");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Teleporte-se em um Flash!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.ryu")) {
            final ItemStack pyro = new ItemStack(Material.DIAMOND_BLOCK);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Ryu");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Hadouken!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.rider")) {
            final ItemStack pyro = new ItemStack(Material.SADDLE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Rider");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha um cavalin portatil(§5Qnt futurismo).");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.jutso")) {
            final ItemStack pyro = new ItemStack(Material.PAPER);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Jutso");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Jutso de substituicao!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.rain")) {
            final ItemStack pyro = new ItemStack(Material.GHAST_TEAR);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Rain");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Fassa flechas perseguirem seu inimigo ehauehu!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.drain")) {
            final ItemStack pyro = new ItemStack(Material.NETHER_STAR);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Drain");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Ao matar alguem sua vida dobra para 20 coracoes!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.timelord")) {
            final ItemStack pyro = new ItemStack(Material.WATCH);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "TimeLord");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Congele quem estiver ao redor!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.c4")) {
            final ItemStack pyro = new ItemStack(Material.STONE_BUTTON);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "C4");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Exploda os inimigos com uma bomba!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.magma")) {
            final ItemStack pyro = new ItemStack(Material.MAGMA_CREAM);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Magma");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Tenha chance de colocar fogo nos inimigos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.airmage")) {
            final ItemStack pyro = new ItemStack(Material.WOOL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "AirMage");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Fa\u00e7a os inimigos levitar!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.zeus")) {
            final ItemStack pyro = new ItemStack(Material.SUGAR);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Zeus");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Jogue raios!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.barbarian")) {
            final ItemStack pyro = new ItemStack(Material.DIAMOND_SWORD);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Barbarian");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Evolua sua espada a cada Kill");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.pyro")) {
            final ItemStack pyro = new ItemStack(Material.FIREBALL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Pyro");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Lance bolas de fogo!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.terrorista")) {
            final ItemStack pyro = new ItemStack(Material.getMaterial(289));
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Terrorista");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Caia e provoque explosoes!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.macaco")) {
            final ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Macaco");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Escale qualquer coisa defendendo com sua espada! e nao tome dano de queda!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.granadier")) {
            final ItemStack pyro = new ItemStack(Material.SNOW_BALL);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Granadier");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Jogue granadas!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.vampire")) {
            final ItemStack pyro = new ItemStack(Material.SPIDER_EYE);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Vampire");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Mate e ganhe pocao de Veneno!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        if (p.hasPermission("kit.Blink")) {
            final ItemStack pyro = new ItemStack(Material.GOLD_INGOT);
            final ItemMeta metapyro = pyro.getItemMeta();
            metapyro.setDisplayName(ChatColor.AQUA + "Blink");
            final ArrayList descpyro = new ArrayList();
            descpyro.add("§3§l Hab §9§l>> §7Teleporte-se e ganhe invencibilidade por alguns segundos!");
            metapyro.setLore((List)descpyro);
            pyro.setItemMeta(metapyro);
            inv.addItem(new ItemStack[] { pyro });
        }
        ItemStack[] arrayOfItemStack;
        for (int descpyro2 = (arrayOfItemStack = inv.getContents()).length, metapyro2 = 0; metapyro2 < descpyro2; ++metapyro2) {
            final ItemStack item = arrayOfItemStack[metapyro2];
            if (item == null) {
                inv.setItem(inv.firstEmpty(), vidro);
            }
        }
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§6 >>>> Kits [2] <<<<") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.ARROW) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/teleporter");
                return;
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/assasin");
                return;
            }
            if (e.getCurrentItem().getType() == Material.getMaterial(289)) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/terrorista");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/vampire");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/blink");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/macaco");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/granadier");
                return;
            }
            if (e.getCurrentItem().getType() == Material.SUGAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/zeus");
                return;
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/barbarian");
                return;
            }
            if (e.getCurrentItem().getType() == Material.FIREBALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/pyro");
                return;
            }
            if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/vacuum");
                return;
            }
            if (e.getCurrentItem().getType() == Material.WOOL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/airmage");
                return;
            }
            if (e.getCurrentItem().getType() == Material.MAGMA_CREAM) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/magma");
                return;
            }
            if (e.getCurrentItem().getType() == Material.GOLD_BOOTS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/dwarf");
            }
            if (e.getCurrentItem().getType() == Material.IRON_BARDING) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/sniper");
            }
            if (e.getCurrentItem().getType() == Material.ACTIVATOR_RAIL) {
                e.setCancelled(true);
                p.closeInventory();
                MenuKits3.guiKits(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.ZOMBIE_METAL, 5.0f, 5.0f);
                return;
            }
            if (e.getCurrentItem().getType() == Material.MELON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/dj");
            }
            if (e.getCurrentItem().getType() == Material.COBBLE_WALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/trocador");
            }
            if (e.getCurrentItem().getType() == Material.BREAD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/pao");
            }
            if (e.getCurrentItem().getType() == Material.STRING) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/spiderman");
            }
            if (e.getCurrentItem().getType() == Material.RED_ROSE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/madman");
            }
            if (e.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/nofall");
            }
            if (e.getCurrentItem().getType() == Material.TNT) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/exploder");
            }
            if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/achilles");
            }
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/witherman");
            }
            if (e.getCurrentItem().getType() == Material.SPONGE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/sponger");
                return;
            }
            if (e.getCurrentItem().getType() == Material.BEACON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/avatar");
            }
            if (e.getCurrentItem().getType() == Material.LAPIS_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/sonic");
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/deshfire");
            }
            if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/jumper");
            }
            if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/vacuum");
            }
            if (e.getCurrentItem().getType() == Material.OBSIDIAN) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/necromancer");
            }
            if (e.getCurrentItem().getType() == Material.FEATHER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/phantom");
            }
            if (e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/taurus");
            }
            if (e.getCurrentItem().getType() == Material.MINECART) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/velotrol");
            }
            if (e.getCurrentItem().getType() == Material.WOOD_HOE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/bloodgun");
            }
            if (e.getCurrentItem().getType() == Material.IRON_DOOR) {
                e.setCancelled(true);
                p.closeInventory();
                MenuKits.guiKits(p);
                p.getPlayer().getLocation().getWorld().playSound(p.getLocation(), Sound.ZOMBIE_METAL, 5.0f, 5.0f);
            }
            if (e.getCurrentItem().getType() == Material.NETHER_FENCE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/forcefield");
            }
            if (e.getCurrentItem().getType() == Material.GOLD_HOE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/Burstmaster");
            }
            if (e.getCurrentItem().getType() == Material.SLIME_BALL) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/doctor");
            }
            if (e.getCurrentItem().getType() == Material.BOOK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/deathnote");
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_BLOCK) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/ryu");
            }
            if (e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/flash");
            }
            if (e.getCurrentItem().getType() == Material.SADDLE) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/rider");
            }
            if (e.getCurrentItem().getType() == Material.PAPER) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/jutso");
            }
            if (e.getCurrentItem().getType() == Material.GHAST_TEAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/rain");
            }
            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/drain");
            }
            if (e.getCurrentItem().getType() == Material.STONE_BUTTON) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/c4");
            }
            if (e.getCurrentItem().getType() == Material.WATCH) {
                e.setCancelled(true);
                p.closeInventory();
                p.chat("/timelord");
            }
        }
    }
}
