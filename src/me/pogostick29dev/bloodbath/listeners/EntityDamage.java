package me.pogostick29dev.bloodbath.listeners;

import me.pogostick29dev.bloodbath.Arena;
import me.pogostick29dev.bloodbath.Arena.ArenaState;
import me.pogostick29dev.bloodbath.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		
		Player p = (Player) e.getEntity();
		
		Arena a = ArenaManager.getInstance().getArena(p);
		
		if (a == null) {
			return;
		}
		
		if (a.getState() == ArenaState.STARTED) {
			return;
		}
		
		e.setCancelled(true);
	}
}