package Nodus.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Config implements Listener {
	static Config instance;
	Plugin p;
	static FileConfiguration config;
	static File cfile;
	static FileConfiguration data;
	static File dfile;
	static FileConfiguration kill;
	static File sfile;
	static FileConfiguration kills;
	static File filed;

	static {
		Config.instance = new Config();
	}

	public static Config getInstance() {
		return Config.instance;
	}

	public void setup(final Plugin p) {
		Config.cfile = new File(p.getDataFolder(), "config.yml");
		Config.config = p.getConfig();
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		Config.dfile = new File(p.getDataFolder(), "data.yml");
		if (!Config.dfile.exists()) {
			try {
				Config.dfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta data.yml");
			}
		}
		Config.data = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.dfile);
		Config.sfile = new File(p.getDataFolder(), "kill.yml");
		if (!Config.sfile.exists()) {
			try {
				Config.sfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta kill.yml!");
			}
		}
		Config.kill = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.sfile);
		Config.filed = new File(p.getDataFolder(), "config.yml");
		if (!Config.filed.exists()) {
			try {
				Config.filed.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta config.yml!");
			}
		}
		Config.config = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.filed);
	}

	public FileConfiguration getkills() {
		return Config.config;
	}

	public void savekills() {
		try {
			Config.config.save(Config.filed);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
		}
	}

	public void saveip() {
		try {
			Config.kill.save(Config.sfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar kill.yml!");
		}
	}

	public void reloadip() {
		Config.kill = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.sfile);
	}

	public FileConfiguration getData() {
		return Config.data;
	}

	public void saveData() {
		try {
			Config.data.save(Config.dfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar data.yml!");
		}
	}

	public void reloadData() {
		Config.data = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.dfile);
	}

	public FileConfiguration getConfig() {
		return Config.config;
	}

	public void saveConfig() {
		try {
			Config.config.save(Config.cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
		}
	}

	public static int getKills(final Player p) {
		return Config.config.getInt(String.valueOf(p.getName().toLowerCase()) + ".kills");
	}

	public static int getDeaths(final Player p) {
		return Config.config.getInt(String.valueOf(p.getName().toLowerCase()) + ".deaths");
	}

	public static void addKills(final Player p) {
		Config.config.set(String.valueOf(p.getName().toLowerCase()) + ".kills", (Object) (getKills(p) + 1));
		save();
	}

	public static void addDeaths(final Player p) {
		Config.config.set(String.valueOf(p.getName().toLowerCase()) + ".deaths", (Object) (getDeaths(p) + 1));
		save();
	}

	public static void resetkd(final Player p) {
		Config.config.set(String.valueOf(p.getName().toLowerCase()) + ".deaths", (Object) (getDeaths(p) - 300));
		Config.config.set(String.valueOf(p.getName().toLowerCase()) + ".kills", (Object) (getKills(p) - 300));
		save();
	}

	private static void save() {
		try {
			Config.config.save(Config.cfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Plugin getPlugin() {
		return this.p;
	}

	public Config getNewConfig(final String string) {
		return null;
	}
}
