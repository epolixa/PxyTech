package io.github.pxy.pxytech;

import java.util.UUID;

import org.bukkit.entity.Player;

public class AFKIdleTimer implements Runnable 
{	
	private final AFK afk;
	private final PxyTech plugin;
	
	public AFKIdleTimer(AFK afk)
	{
		this.afk = afk;
		this.plugin = afk.getPlugin();
	}
	
	public void run() 
	{
		UUID uuid;
		
		for (Player p : plugin.getServer().getOnlinePlayers())
		{
			uuid = p.getUniqueId();
			if (!afk.getAFKstatus(uuid))
			{				 
				afk.setIdleTime(uuid, afk.getIdleTime(uuid) + 1);
				if (afk.getIdleTime(uuid) >= plugin.getConfig().getInt("IDLE_TIME"))
				{
					afk.goAFK(p, "");
				}
			}
		}		
	}
}
