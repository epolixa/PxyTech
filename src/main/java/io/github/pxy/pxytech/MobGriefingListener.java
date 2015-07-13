package io.github.pxy.pxytech;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;



public class MobGriefingListener implements Listener
{		
	/* Explosions */
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityExplode(EntityExplodeEvent event)
	{
		Entity ent = event.getEntity();
		
		if (ent instanceof Creeper
			|| ent instanceof SmallFireball
			|| ent instanceof Fireball
			|| ent instanceof WitherSkull
			|| ent instanceof Wither)
		{					
			for (Block b : new ArrayList<Block>(event.blockList()))
				event.blockList().remove(b);		
			
			event.setYield(0f);
		}
	}
	
	/* Endermen Stealing & Wither Smashing */
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityChangeBlock(EntityChangeBlockEvent event)
	{
		if (event.getEntity() instanceof Enderman
			|| event.getEntity() instanceof Wither)
		{
			event.setCancelled(true);
		}
	}
}
