package me.pogostick29dev.bloodbath;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GameChest {

	private Chest chest;
	private int tier;
	
	public GameChest(Chest chest, int tier) {
		this.chest = chest;
		this.tier = tier;
	}
	
	public Chest getChest() {
		return chest;
	}
	
	public int getTier() {
		return tier;
	}
	
	public void fillChest() {
		Material[] items1 = new Material[] { Material.GOLDEN_APPLE, Material.IRON_SWORD, Material.IRON_CHESTPLATE, Material.IRON_SWORD };
		Material[] items2 = new Material[] { Material.APPLE, Material.WOOD_SWORD, Material.LEATHER_CHESTPLATE, Material.BOW, Material.ARROW };
		
		Random r = new Random();
		
		int numItems = r.nextInt(5) + 1; // A random number between 1 and 5.
		
		for (int i = 0; i < numItems; i++) {
			Material material = null;
			
			if (tier == 1) {
				material = items1[r.nextInt(items1.length)]; // A random Materials from the items1 array.
			}
			
			else if (tier == 2) {
				material = items2[r.nextInt(items2.length)]; // A random Materials from the items2 array.
			}
			
			ItemStack item = new ItemStack(material, 1);
			
			int index;
			
			do {
				index = r.nextInt(chest.getInventory().getSize());
			} while (chest.getInventory().getItem(index) != null);
			
			chest.getInventory().setItem(index, item);
		}
	}
	
	public void save(ConfigurationSection section) {
		section.set("location.world", chest.getLocation().getWorld().getName());
		section.set("location.x", chest.getLocation().getX());
		section.set("location.y", chest.getLocation().getY());
		section.set("location.z", chest.getLocation().getZ());
		section.set("tier", tier);
	}
	
//	@Override
//	public Map<String, Object> serialize() {
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		
//		data.put("location.world", chest.getLocation().getWorld().getName());
//		data.put("location.x", chest.getLocation().getX());
//		data.put("location.y", chest.getLocation().getY());
//		data.put("location.z", chest.getLocation().getZ());
//		data.put("tier", tier);
//		
//		return data;
//	}
}