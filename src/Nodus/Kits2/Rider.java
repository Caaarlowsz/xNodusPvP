package Nodus.Kits2;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Rider implements Listener, CommandExecutor {
	public static Main plugin;
	public static ItemStack[] armorContents;
	public static ItemStack[] inventoryContents;
	public int horseHealth;
	public String horseName;
	public double jumpStrength;
	public boolean modifyHorseStats;
	public boolean nameHorse;
	public boolean preventOthersFromUsing;
	private HashMap<Player, Horse> horses;
	public double runSpeed;

	public Rider(final Main main) {
		this.horseHealth = 40;
		this.horseName = "%s";
		this.jumpStrength = 1.0;
		this.modifyHorseStats = true;
		this.nameHorse = true;
		this.preventOthersFromUsing = true;
		this.horses = new HashMap<Player, Horse>();
		this.runSpeed = 1.0;
		Rider.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Rider")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Rider")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Rider");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.rider.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack bow = new ItemStack(Material.CARROT_ITEM);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("§6 Cavalin ");
					Habilidade.setAbility(p, "Rider");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Rider.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Rider.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onClick888(final InventoryClickEvent event) {
		if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.CARROT_ITEM
				&& event.getWhoClicked().getVehicle() != null
				&& this.horses.containsValue(event.getWhoClicked().getVehicle())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDeath(final EntityDeathEvent event) {
		if (this.horses.containsValue(event.getEntity())) {
			event.setDroppedExp(0);
			final Iterator<Player> itel = this.horses.keySet().iterator();
			while (itel.hasNext()) {
				if (this.horses.get(itel.next()) == event.getEntity()) {
					itel.remove();
				}
			}
		}
	}

	@EventHandler
	public void onInteract4(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.CARROT_ITEM) {
			if (this.horses.containsKey(p)) {
				final Horse horse = this.horses.remove(p);
				if (!horse.isDead()) {
					horse.eject();
					horse.leaveVehicle();
					horse.remove();
				}
			} else {
				final Horse horse = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
				this.horses.put(p, horse);
				if (this.nameHorse) {
					horse.setCustomName(String.format(this.horseName, "§aCavalo Blindaum"));
					horse.setCustomNameVisible(true);
				}
				horse.setBreed(false);
				horse.setTamed(true);
				horse.setDomestication(horse.getMaxDomestication());
				horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
				horse.setOwner((AnimalTamer) p);
				if (this.modifyHorseStats) {
					horse.setJumpStrength(this.jumpStrength);
					horse.setHealth(40.0);
					horse.setColor(Horse.Color.WHITE);
					horse.setStyle(Horse.Style.WHITE_DOTS);
					horse.setCarryingChest(true);
				}
			}
		}
	}

	@EventHandler
	public void onRightClick1(final PlayerInteractEntityEvent event) {
		if (this.preventOthersFromUsing && this.horses.containsValue(event.getRightClicked())) {
			for (final Player p : this.horses.keySet()) {
				if (this.horses.get(p) == event.getRightClicked() && event.getPlayer() != p) {
					event.setCancelled(true);
					break;
				}
			}
		}
	}
}
