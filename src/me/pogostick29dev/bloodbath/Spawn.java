package me.pogostick29dev.bloodbath;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

	private Location location;
	private Player player;
	
	public Spawn(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean hasPlayer() {
		return player != null;
	}
}