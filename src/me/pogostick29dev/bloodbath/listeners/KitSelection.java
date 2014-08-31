package me.pogostick29dev.bloodbath.listeners;

import me.pogostick29dev.bloodbath.Kit;
import me.pogostick29dev.bloodbath.KitManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class KitSelection implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		if (e.getItem() == null || !e.getItem().hasItemMeta() || !e.getItem().getItemMeta().hasDisplayName()) {
			return;
		}
		
		if (!e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Kit Selector")) {
			return;
		}
		
		e.getPlayer().openInventory(KitManager.getInstance().getGUI());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		
		Player p = (Player) e.getWhoClicked();
		
		if (!e.getInventory().getName().equals(ChatColor.GOLD + "Kit Selector")) {
			return;
		}
		
		e.setCancelled(true);
		e.getWhoClicked().closeInventory();
		
		Kit kit = KitManager.getInstance().getKit(e.getCurrentItem().getItemMeta().getDisplayName());
		
		p.getInventory().clear();
		
		for (ItemStack item : kit.getItems()) {
			p.getInventory().addItem(item);
		}
		
		p.sendMessage(ChatColor.GREEN + "You have chosen kit " + kit.getName() + ".");
	}
}