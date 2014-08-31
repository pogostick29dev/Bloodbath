package me.pogostick29dev.bloodbath;

import me.pogostick29dev.bloodbath.kits.ArcherKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class KitManager {
	
	private static KitManager instance = new KitManager();
	
	public static KitManager getInstance() {
		return instance;
	}
	
	private ArrayList<Kit> kits;
	private Inventory gui;

	private KitManager() {
		kits = new ArrayList<Kit>();
		
		kits.add(new ArcherKit());
		// TODO: Add all other kits.
		
		gui = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + "Kit Selector");
		
		for (Kit kit : kits) {
			ItemStack item = new ItemStack(kit.getItems().get(0).getType(), 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(kit.getName());
			meta.setLore(Arrays.asList("Click here", "to select", "the " + kit.getName() + " kit."));
			item.setItemMeta(meta);
			gui.addItem(item);
		}
	}
	
	public Kit getKit(String name) {
		for (Kit kit : kits) {
			if (kit.getName().equals(name)) {
				return kit;
			}
		}
		
		return null;
	}
	
	public Inventory getGUI() {
		return gui;
	}
}