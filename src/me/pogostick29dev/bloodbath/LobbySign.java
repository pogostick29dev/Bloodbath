package me.pogostick29dev.bloodbath;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

public class LobbySign {

	private Location loc;
	private Sign sign;
	private Arena arena;
	
	public LobbySign(Location loc, Arena arena) {
		this.loc = loc;
		this.sign = (Sign) loc.getBlock().getState();
		this.arena = arena;
	}

	public Location getLocation() {
		return loc;
	}

	public Sign getSign() {
		return sign;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public void update() {
		sign.setLine(0, "[SurvivalGames]");
		sign.setLine(1, arena.getID());
		sign.setLine(2, arena.getState().toString());
		sign.setLine(3, arena.getPlayers().length + "/" + arena.getMaxPlayers());
		sign.update();
	}
	
	public void save(ConfigurationSection section) {
		section.set("location.world", loc.getWorld().getName());
		section.set("location.x", loc.getX());
		section.set("location.y", loc.getY());
		section.set("location.z", loc.getZ());
		section.set("arena", arena.getID());
	}

//	@Override
//	public Map<String, Object> serialize() {
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		
//		data.put("location.world", loc.getWorld().getName());
//		data.put("location.x", loc.getX());
//		data.put("location.y", loc.getY());
//		data.put("location.z", loc.getZ());
//		data.put("arena", arena.getID());
//		
//		return data;
//	}
}