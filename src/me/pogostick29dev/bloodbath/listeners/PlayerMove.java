package me.pogostick29dev.bloodbath.listeners;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.Arena.ArenaState;
import me.pogostick29dev.bloodbath.ArenaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Arena a = ArenaManager.getInstance().getArena(e.getPlayer());
		
		if (a == null) {
			return;
		}
		
		if (a.getState() == ArenaState.STARTED) {
			return;
		}
		
		if (e.getTo().getX() == e.getFrom().getX() && e.getTo().getZ() == e.getFrom().getZ()) { // Ignore y so they can jump.
			return; // Remove this if statement if you don't want to allow looking around.
		}
		
		e.getPlayer().teleport(e.getFrom().getBlock().getLocation());
	}
}