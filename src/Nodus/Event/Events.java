package Nodus.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Horse;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import Nodus.Main.Main;

public class Events implements Listener {
	public static Main plugin;

	public Events(final Main main) {
		Events.plugin = main;
	}

	@EventHandler
	public void Dormir(final PlayerBedEnterEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onItemDrop(final ItemSpawnEvent e) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Events.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				e.getEntity().remove();
				e.getLocation().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 10);
			}
		}, 10L);
	}

	@EventHandler
	public void onPickup(final PlayerPickupItemEvent e) {
		e.setCancelled(false);
	}

	@EventHandler
	public void onDropSword(final PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD || Main.usandokit.contains(e.getPlayer().getName())
				|| e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND
				|| e.getItemDrop().getItemStack().getType() == Material.QUARTZ
				|| e.getItemDrop().getItemStack().getType() == Material.WOOD_AXE
				|| e.getItemDrop().getItemStack().getType() == Material.MILK_BUCKET
				|| e.getItemDrop().getItemStack().getType() == Material.NETHER_STAR
				|| e.getItemDrop().getItemStack().getType() == Material.APPLE
				|| e.getItemDrop().getItemStack().getType() == Material.FISHING_ROD
				|| e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD
				|| e.getItemDrop().getItemStack().getType() == Material.STICK
				|| e.getItemDrop().getItemStack().getType() == Material.SADDLE
				|| e.getItemDrop().getItemStack().getType() == Material.WOOD_HOE
				|| e.getItemDrop().getItemStack().getType() == Material.MONSTER_EGG
				|| e.getItemDrop().getItemStack().getType() == Material.GOLD_AXE
				|| e.getItemDrop().getItemStack().getType() == Material.EYE_OF_ENDER
				|| e.getItemDrop().getItemStack().getType() == Material.NETHER_FENCE
				|| e.getItemDrop().getItemStack().getType() == Material.SUGAR
				|| e.getItemDrop().getItemStack().getType() == Material.FLINT
				|| e.getItemDrop().getItemStack().getType() == Material.REDSTONE_TORCH_ON
				|| e.getItemDrop().getItemStack().getType() == Material.BEDROCK
				|| e.getItemDrop().getItemStack().getType() == Material.LEASH
				|| e.getItemDrop().getItemStack().getType() == Material.REDSTONE
				|| e.getItemDrop().getItemStack().getType() == Material.ENCHANTED_BOOK
				|| e.getItemDrop().getItemStack().getType() == Material.STONE_AXE
				|| e.getItemDrop().getItemStack().getType() == Material.SNOW_BALL
				|| e.getItemDrop().getItemStack().getType() == Material.SLIME_BALL
				|| e.getItemDrop().getItemStack().getType() == Material.GOLD_INGOT
				|| e.getItemDrop().getItemStack().getType() == Material.PORTAL
				|| e.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER
				|| e.getItemDrop().getItemStack().getType() == Material.POTION
				|| e.getItemDrop().getItemStack().getType() == Material.BOW
				|| e.getItemDrop().getItemStack().getType() == Material.ARROW
				|| e.getItemDrop().getItemStack().getType() == Material.GOLDEN_APPLE
				|| e.getItemDrop().getItemStack().getType() == Material.FIREWORK
				|| e.getItemDrop().getItemStack().getType() == Material.EMERALD
				|| e.getItemDrop().getItemStack().getType() == Material.CHEST
				|| e.getItemDrop().getItemStack().getType() == Material.GOLD_NUGGET
				|| e.getItemDrop().getItemStack().getType() == Material.BLAZE_ROD
				|| e.getItemDrop().getItemStack().getType() == Material.BREAD
				|| e.getItemDrop().getItemStack().getType() == Material.DRAGON_EGG
				|| e.getItemDrop().getItemStack().getType() == Material.BONE
				|| e.getItemDrop().getItemStack().getType() == Material.FEATHER
				|| e.getItemDrop().getItemStack().getType() == Material.STRING
				|| e.getItemDrop().getItemStack().getType() == Material.COMPASS
				|| e.getItemDrop().getItemStack().getType() == Material.SPONGE
				|| e.getItemDrop().getItemStack().getType() == Material.SKULL_ITEM
				|| e.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER
				|| e.getItemDrop().getItemStack().getType() == Material.WATCH
				|| e.getItemDrop().getItemStack().getType() == Material.COAL_BLOCK
				|| e.getItemDrop().getItemStack().getType() == Material.REDSTONE_BLOCK
				|| e.getItemDrop().getItemStack().getType() == Material.LAPIS_BLOCK
				|| e.getItemDrop().getItemStack().getType() == Material.ENDER_CHEST
				|| e.getItemDrop().getItemStack().getType() == Material.BEACON
				|| e.getItemDrop().getItemStack().getType() == Material.IRON_BARDING
				|| e.getItemDrop().getItemStack().getType() == Material.GOLD_HOE
				|| e.getItemDrop().getItemStack().getType() == Material.WOOD_HOE
				|| e.getItemDrop().getItemStack().getType() == Material.MINECART
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND_BLOCK
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND_CHESTPLATE
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND_BOOTS
				|| e.getItemDrop().getItemStack().getType() == Material.IRON_BOOTS
				|| e.getItemDrop().getItemStack().getType() == Material.IRON_CHESTPLATE
				|| e.getItemDrop().getItemStack().getType() == Material.OBSIDIAN
				|| e.getItemDrop().getItemStack().getType() == Material.VINE
				|| e.getItemDrop().getItemStack().getType() == Material.TNT
				|| e.getItemDrop().getItemStack().getType() == Material.BOOK
				|| e.getItemDrop().getItemStack().getType() == Material.ENDER_PORTAL
				|| e.getItemDrop().getItemStack().getType() == Material.IRON_FENCE
				|| e.getItemDrop().getItemStack().getType() == Material.STONE_BUTTON
				|| e.getItemDrop().getItemStack().getType() == Material.WOOD_BUTTON
				|| e.getItemDrop().getItemStack().getType() == Material.CARROT_ITEM
				|| e.getItemDrop().getItemStack().getType() == Material.ARROW
				|| e.getItemDrop().getItemStack().getType() == Material.WOOL
				|| e.getItemDrop().getItemStack().getType() == Material.PAPER
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND_BLOCK
				|| e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract1(final PlayerInteractEvent e) {
		final ItemStack tigela = new ItemStack(Material.BOWL, 1);
		final ItemMeta tigelameta = tigela.getItemMeta();
		tigelameta.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', Events.plugin.getConfig().getString("nomedopote")));
		tigela.setItemMeta(tigelameta);
		final Damageable hp;
		final Player p = (Player) (hp = (Damageable) e.getPlayer());
		if (hp.getHealth() != 20.0) {
			final int sopa = 7;
			if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
					&& p.getItemInHand().getTypeId() == 282) {
				p.setHealth((hp.getHealth() + sopa > hp.getMaxHealth()) ? hp.getMaxHealth() : (hp.getHealth() + sopa));
				p.getItemInHand().setType(Material.BOWL);
				p.getItemInHand().setItemMeta(tigelameta);
			}
		}
	}

	@EventHandler
	public void Status(final PlayerCommandPreprocessEvent e) {
		final Player p = e.getPlayer();
		if (e.getMessage().equalsIgnoreCase("/status") || e.getMessage().equalsIgnoreCase("/stats")) {
			e.setCancelled(true);
			status(p);
		}
	}

	public static void status(final Player p) {
		final int kills = Events.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".kill-streak");
		final int deaths = Events.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".mortes");
		final int killst = Events.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".kills");
		final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder) p, 27, "§a§lStatus");
		final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		final ItemMeta vidrometa = vidro.getItemMeta();
		vidrometa.setDisplayName(" ");
		vidro.setItemMeta(vidrometa);
		final ItemStack ks = new ItemStack(Material.DIAMOND_SWORD);
		final ItemMeta fpsmeta1 = ks.getItemMeta();
		fpsmeta1.setDisplayName("§aKills: §7" + kills);
		ks.setItemMeta(fpsmeta1);
		final ItemStack ds = new ItemStack(Material.REDSTONE);
		final ItemMeta fpsmeta2 = ds.getItemMeta();
		fpsmeta2.setDisplayName("§4Deaths: §7" + deaths);
		ds.setItemMeta(fpsmeta2);
		final ItemStack ds2 = new ItemStack(Material.SLIME_BALL);
		final ItemMeta fpsmeta3 = ds2.getItemMeta();
		fpsmeta3.setDisplayName("§6KillStreak: §7" + killst);
		ds2.setItemMeta(fpsmeta3);
		inv.setItem(0, vidro);
		inv.setItem(1, vidro);
		inv.setItem(2, vidro);
		inv.setItem(3, vidro);
		inv.setItem(4, vidro);
		inv.setItem(5, vidro);
		inv.setItem(6, vidro);
		inv.setItem(7, vidro);
		inv.setItem(8, vidro);
		inv.setItem(9, vidro);
		inv.setItem(10, vidro);
		inv.setItem(11, ks);
		inv.setItem(12, vidro);
		inv.setItem(13, ds2);
		inv.setItem(14, vidro);
		inv.setItem(15, ds);
		inv.setItem(16, vidro);
		inv.setItem(17, vidro);
		inv.setItem(18, vidro);
		inv.setItem(19, vidro);
		inv.setItem(20, vidro);
		inv.setItem(21, vidro);
		inv.setItem(22, vidro);
		inv.setItem(23, vidro);
		inv.setItem(24, vidro);
		inv.setItem(25, vidro);
		inv.setItem(26, vidro);
		p.openInventory(inv);
	}

	@EventHandler
	public void onPlayerCLickInventry1(final InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§a§lStatus") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			final Player player = event.getPlayer();
			final String cmd = event.getMessage().split(" ")[0];
			final HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if (topic == null) {
				player.sendMessage(String.valueOf(
						ChatColor.translateAlternateColorCodes('&', Events.plugin.getConfig().getString("prefix")))
						+ ChatColor.translateAlternateColorCodes('&',
								Events.plugin.getConfig().getString("comandoinvalido")));
				player.playSound(player.getLocation(), Sound.ZOMBIE_REMEDY, 1.0f, 1.0f);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void chuva(final WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent e) {
		if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
			final Player p = (Player) e.getEntity();
			p.setHealth(0.0);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLoseHunger(final FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLoginBan(final PlayerLoginEvent event) {
		if (event.getResult().equals((Object) PlayerLoginEvent.Result.KICK_BANNED)) {
			event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§4Voce Foi banido do Servidor!");
		}
	}

	@EventHandler
	public void onCreeperSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Creeper) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSkeletonSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Skeleton) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSpiderSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Spider) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onWitherSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Wither) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onZombieSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Zombie) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSlimeSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Slime) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onGhastSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Ghast) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPigZombieSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof PigZombie) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEndermanSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Enderman) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onCaveSpiderSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof CaveSpider) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSilverfishSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Silverfish) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlazeSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Blaze) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onMagmaCubeSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof MagmaCube) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onWitchSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Witch) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSheepSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Sheep) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onCowSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Cow) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onChickenSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Chicken) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSquidSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Squid) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onWolfSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Wolf) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onMooshroomSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof MushroomCow) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onOcelotSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Ocelot) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onVillagerSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Villager) {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onHorseSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Horse) {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onEnderDragonSpawn(final CreatureSpawnEvent e) {
		if (e.getEntity() instanceof EnderDragon) {
			e.setCancelled(true);
		}
	}
}
