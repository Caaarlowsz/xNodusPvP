package Nodus.Kits3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Nodus.Main.Main;

public class InfernorHab implements Listener {
	public Plugin plugin;
	public boolean generateGlass;
	public HashMap<String, Location> oldl;
	public static HashMap<String, String> fighting;
	public HashMap<Integer, ArrayList<Location>> blocks;
	public HashMap<Player, Location> localizacao;
	public HashMap<Location, Block> bloco;
	public HashMap<Integer, String[]> players;
	public HashMap<String, Integer> tasks;
	public static List<Player> Infer;
	int nextID;
	public int id1;
	public int id2;
	ArrayList<String> tempo;

	static {
		InfernorHab.fighting = new HashMap<String, String>();
		InfernorHab.Infer = new ArrayList<Player>();
	}

	public InfernorHab(final Main plugin) {
		this.generateGlass = true;
		this.oldl = new HashMap<String, Location>();
		this.blocks = new HashMap<Integer, ArrayList<Location>>();
		this.localizacao = new HashMap<Player, Location>();
		this.bloco = new HashMap<Location, Block>();
		this.players = new HashMap<Integer, String[]>();
		this.tasks = new HashMap<String, Integer>();
		this.nextID = 0;
		this.tempo = new ArrayList<String>();
		this.plugin = (Plugin) plugin;
	}

	@EventHandler
	public void OnInfernoKit(final PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (event.getRightClicked() instanceof Player) {
			if (InfernorHab.Infer.contains(p)) {
				p.sendMessage("§aKit Em Cooldown");
			}
			if (!InfernorHab.Infer.contains(p)) {
				final Player r = (Player) event.getRightClicked();
				final ItemStack i = p.getItemInHand();
				if (i.getType() == Material.NETHER_FENCE && i.hasItemMeta()
						&& i.getItemMeta().getDisplayName().equalsIgnoreCase("§4Infernor")
						&& p.hasPermission("kit.infernor")) {
					final Location loc = new Location(p.getWorld(), (double) p.getLocation().getBlockX(),
							(double) (p.getLocation().getBlockY() + 70), (double) p.getLocation().getBlockZ());
					this.localizacao.put(p, loc);
					this.localizacao.put(r, loc);
					final Location loc2 = new Location(p.getWorld(), (double) (p.getLocation().getBlockX() + 8),
							(double) (p.getLocation().getBlockY() + 73), (double) (p.getLocation().getBlockZ() + 8));
					final Location loc3 = new Location(p.getWorld(), (double) (p.getLocation().getBlockX() - 8),
							(double) (p.getLocation().getBlockY() + 73), (double) (p.getLocation().getBlockZ() - 8));
					if (InfernorHab.fighting.containsKey(p.getName())
							|| InfernorHab.fighting.containsKey(r.getName())) {
						event.setCancelled(true);
						p.sendMessage(ChatColor.RED + "Voce ja esta em combate!");
						return;
					}
					final Integer currentID = this.nextID;
					++this.nextID;
					final ArrayList<String> list = new ArrayList<String>();
					list.add(p.getName());
					list.add(r.getName());
					this.players.put(currentID, list.toArray(new String[1]));
					this.oldl.put(p.getName(), p.getLocation());
					this.oldl.put(r.getName(), r.getLocation());
					this.tempo.add(p.getName());
					if (this.generateGlass) {
						final List<Location> cuboid = new ArrayList<Location>();
						cuboid.clear();
						for (int bX = -10; bX <= 10; ++bX) {
							for (int bZ = -10; bZ <= 10; ++bZ) {
								for (int bY = -1; bY <= 10; ++bY) {
									final Block b = loc.clone().add((double) bX, (double) bY, (double) bZ).getBlock();
									if (!b.isEmpty()) {
										event.setCancelled(true);
										p.sendMessage(ChatColor.RED + "Voce nao pode criar sua arena aqui");
										return;
									}
									if (bY == 10) {
										cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
									} else if (bY == -1) {
										cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
									} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
										cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
									}
								}
							}
						}
						for (final Location loc4 : cuboid) {
							if (new Random().nextBoolean()) {
								loc4.getBlock().setData(DyeColor.RED.getData());
								loc4.getBlock().setType(Material.STAINED_GLASS);
							} else {
								loc4.getBlock().setType(Material.NETHERRACK);
							}
						}
						loc2.setYaw(135.0f);
						p.teleport(loc2);
						loc3.setYaw(-45.0f);
						r.teleport(loc3);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0));
						r.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 0));
						InfernorHab.Infer.add(p);
						InfernorHab.fighting.put(p.getName(), r.getName());
						InfernorHab.fighting.put(r.getName(), p.getName());
						Main.InfernoHab.add(p.getName());
						Main.InfernoHab.add(r.getName());
					}
					this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							if (InfernorHab.fighting.containsKey(p.getName())
									&& InfernorHab.fighting.get(p.getName()).equalsIgnoreCase(r.getName())
									&& InfernorHab.fighting.containsKey(r.getName())) {
								InfernorHab.fighting.get(r.getName()).equalsIgnoreCase(p.getName());
							}
						}
					}, 1800L);
					this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							if (InfernorHab.fighting.containsKey(p.getName())
									&& InfernorHab.fighting.get(p.getName()).equalsIgnoreCase(r.getName())
									&& InfernorHab.fighting.containsKey(r.getName())
									&& InfernorHab.fighting.get(r.getName()).equalsIgnoreCase(p.getName())) {
								InfernorHab.fighting.remove(p.getName());
								InfernorHab.fighting.remove(r.getName());
								Main.InfernoHab.remove(p.getName());
								Main.InfernoHab.remove(r.getName());
								p.teleport((Location) InfernorHab.this.oldl.get(p.getName()));
								r.teleport((Location) InfernorHab.this.oldl.get(r.getName()));
								InfernorHab.this.oldl.remove(p.getName());
								InfernorHab.this.oldl.remove(r.getName());
								p.sendMessage(ChatColor.RED
										+ "Nao houve nenhum vencedor, voce foi teleportado para o seu lugar de origem!");
								r.sendMessage(ChatColor.RED
										+ "Nao houve nenhum vencedor, voce foi teleportado para o seu lugar de origem!");
								final Location loc = InfernorHab.this.localizacao.get(p);
								final List<Location> cuboid = new ArrayList<Location>();
								for (int bX = -10; bX <= 10; ++bX) {
									for (int bZ = -10; bZ <= 10; ++bZ) {
										for (int bY = -1; bY <= 10; ++bY) {
											if (bY == 10) {
												cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
											} else if (bY == -1) {
												cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
											} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
												cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
											}
										}
									}
								}
								for (final Location loc2 : cuboid) {
									loc2.getBlock().setType(Material.AIR);
									InfernorHab.this.bloco.get(loc2).setType(Material.AIR);
								}
							}
						}
					}, 2000L);
				}
			}
			this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (InfernorHab.Infer.contains(p)) {
						InfernorHab.Infer.remove(p);
						p.sendMessage("§cCooldown Acabou!");
					}
				}
			}, 1200L);
		}
	}

	@EventHandler
	public void onPlayerInteractGlad(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getTypeId() == 113) {
			e.setCancelled(true);
			p.updateInventory();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlyaerInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.STAINED_GLASS
				&& e.getClickedBlock().getData() == DyeColor.RED.getData()
				&& e.getPlayer().getGameMode() != GameMode.CREATIVE
				&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.BEDROCK);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
						e.getClickedBlock().setType(Material.NETHERRACK);
						e.getClickedBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
					}
				}
			}, 30L);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(final BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.STAINED_GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE
				&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getBlock().setType(Material.BEDROCK);
			Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (e.getPlayer().getGameMode() != GameMode.CREATIVE
							&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
						e.getBlock().setType(Material.NETHERRACK);
						e.getBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
					}
				}
			}, 30L);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlyaerInteract2(final PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.NETHERRACK
				&& e.getPlayer().getGameMode() != GameMode.CREATIVE
				&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.BEDROCK);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
						e.getClickedBlock().setType(Material.STAINED_GLASS);
						e.getClickedBlock().setData(DyeColor.RED.getData());
					}
				}
			}, 30L);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak2(final BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.NETHERRACK && e.getPlayer().getGameMode() != GameMode.CREATIVE
				&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getBlock().setType(Material.BEDROCK);
			Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					if (e.getPlayer().getGameMode() != GameMode.CREATIVE
							&& InfernorHab.fighting.containsKey(e.getPlayer().getName())) {
						e.getBlock().setType(Material.STAINED_GLASS);
						e.getBlock().setData(DyeColor.RED.getData());
					}
				}
			}, 30L);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeft(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		if (InfernorHab.fighting.containsKey(p.getName())) {
			final Player t = Bukkit.getServer().getPlayer((String) InfernorHab.fighting.get(p.getName()));
			InfernorHab.fighting.remove(t.getName());
			InfernorHab.fighting.remove(p.getName());
			Main.InfernoHab.remove(p.getName());
			Main.InfernoHab.remove(t.getName());
			final Location old = this.oldl.get(t.getName());
			t.teleport(old);
			t.sendMessage(ChatColor.RED + "O jogador " + p.getName() + " deslogou-se!");
			Bukkit.getScheduler().cancelTask(this.id1);
			Bukkit.getScheduler().cancelTask(this.id2);
			t.removePotionEffect(PotionEffectType.WITHER);
			final Location loc = this.localizacao.get(p);
			final List<Location> cuboid = new ArrayList<Location>();
			for (int bX = -10; bX <= 10; ++bX) {
				for (int bZ = -10; bZ <= 10; ++bZ) {
					for (int bY = -1; bY <= 10; ++bY) {
						if (bY == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						}
					}
				}
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				this.bloco.get(loc2).setType(Material.AIR);
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				this.bloco.get(loc2).setType(Material.AIR);
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				this.bloco.get(loc2).setType(Material.AIR);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeathInferno(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if (InfernorHab.fighting.containsKey(p.getName())) {
			final Player k = Bukkit.getServer().getPlayer((String) InfernorHab.fighting.get(p.getName()));
			final Location old = this.oldl.get(p.getName());
			k.teleport(old);
			k.sendMessage(ChatColor.GREEN + "Voce ganhou a batalha contra " + p.getName() + ChatColor.GREEN + "!");
			Bukkit.getScheduler().cancelTask(this.id1);
			Bukkit.getScheduler().cancelTask(this.id2);
			k.removePotionEffect(PotionEffectType.WITHER);
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
			InfernorHab.fighting.remove(k.getName());
			InfernorHab.fighting.remove(p.getName());
			Main.InfernoHab.remove(p.getName());
			Main.InfernoHab.remove(k.getName());
			final Location loc = this.localizacao.get(p);
			final List<Location> cuboid = new ArrayList<Location>();
			cuboid.clear();
			for (int bX = -10; bX <= 10; ++bX) {
				for (int bZ = -10; bZ <= 10; ++bZ) {
					for (int bY = -1; bY <= 10; ++bY) {
						if (bY == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
							cuboid.add(loc.clone().add((double) bX, (double) bY, (double) bZ));
						}
					}
				}
			}
			for (final Location loc2 : cuboid) {
				loc2.getBlock().setType(Material.AIR);
				if (this.bloco.containsKey(loc2)) {
					this.bloco.get(loc2).setType(Material.AIR);
				}
			}
		}
	}
}
