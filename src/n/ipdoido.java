package n;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import Nodus.Main.Main;
import me.confuser.barapi.BarAPI;

public class ipdoido {
	public static void barrinha(final Player p, final String ip, final String ip2, final String ip3, final String ip4,
			final long time) {
		BarAPI.setMessage(p, ip);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				BarAPI.setMessage(p, ip);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						BarAPI.setMessage(p, ip2);
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
							@Override
							public void run() {
								BarAPI.setMessage(p, ip3);
							}
						}, time);
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
							@Override
							public void run() {
								BarAPI.setMessage(p, ip4);
							}
						}, time);
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
							@Override
							public void run() {
								BarAPI.setMessage(p, ip);
							}
						}, time);
					}
				}, time);
			}
		}, time, time);
	}
}
