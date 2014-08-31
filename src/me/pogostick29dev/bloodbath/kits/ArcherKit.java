package me.pogostick29dev.bloodbath.kits;

import me.pogostick29dev.bloodbath.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.AbstractMap.SimpleEntry;

public class ArcherKit extends Kit {

	public ArcherKit() {
		super("Archer");
		
		addItem(Material.BOW, 1, "Archer's Bow", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.ARROW_INFINITE, 1));
		addItem(Material.ARROW, 64, "Archer's Arrows", new String[0]);
	}
}