package me.pogostick29dev.bloodbath.listeners;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerLeaveArena implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		handle(e.getPlayer());
		e.setRespawnLocation(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		handle(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
	}
	
	private void handle(Player p) {
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (a == null) {
			return;
		}
		
		a.removePlayer(p);
	}
}