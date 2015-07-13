package io.github.pxy.pxytech;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VegetarianQuirksListener implements Listener
{
	private final Quirks quirks;

	public VegetarianQuirksListener(Quirks quirks)
	{
		this.quirks = quirks;
	}

	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent event)
	{
		if (event.getItem().getType() == Material.PORK ||
			event.getItem().getType() == Material.GRILLED_PORK ||
			event.getItem().getType() == Material.RAW_CHICKEN ||
			event.getItem().getType() == Material.COOKED_CHICKEN ||
			event.getItem().getType() == Material.RAW_BEEF ||
			event.getItem().getType() == Material.COOKED_BEEF ||
			event.getItem().getType() == Material.RAW_FISH ||
			event.getItem().getType() == Material.COOKED_FISH ||
			event.getItem().getType() == Material.MUTTON ||
			event.getItem().getType() == Material.COOKED_MUTTON ||
			event.getItem().getType() == Material.RABBIT ||
			event.getItem().getType() == Material.COOKED_RABBIT ||
			event.getItem().getType() == Material.RABBIT_STEW ||
			event.getItem().getType() == Material.ROTTEN_FLESH ||
			event.getItem().getType() == Material.SPIDER_EYE)
		{
			
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 2400, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2400, 0));
		}
		else if (event.getItem().getType() == Material.MILK_BUCKET)
		{
			event.setCancelled(true);
			event.getPlayer().setItemInHand(new ItemStack(Material.BUCKET));
		}
	}

}
