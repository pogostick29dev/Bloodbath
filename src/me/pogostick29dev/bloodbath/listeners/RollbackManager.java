package me.pogostick29dev.bloodbath.listeners;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.ArenaManager;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class RollbackManager implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		handle(e.getBlock().getState(), e.getPlayer());
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		handle(e.getBlock().getState(), e.getPlayer());
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		handle(e.getBlock().getState());
	}
	
	// TODO: Implement all other types of block events.
	
	/*
	 * If you don't have a player.
	 */
	private void handle(BlockState state) {
		for (Arena arena : ArenaManager.getInstance().getArenas()) {
			if (arena.getBounds().contains(state.getLocation())) {
				handle(state, arena);
				break;
			}
		}
	}
	
	private void handle(BlockState state, Player player) {
		Arena arena = ArenaManager.getInstance().getArena(player);
		
		if (arena == null) {
			return;
		}
		
		handle(state, arena);
	}
	
	private void handle(BlockState state, Arena arena) {
		arena.addBlockState(state);
	}
}