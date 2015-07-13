package io.github.pxy.pxytech;

import org.bukkit.plugin.java.JavaPlugin;

/* PxyTech
 *
 * a custom plugin for the p-xy's server to add various social and
 * technical features
 *
 * @author p-xy (https://github.com/p-xy)
 */

public final class PxyTech extends JavaPlugin
{
	private static AFK afk;
	//private static Reply reply;
	private static Quirks quirks;

	@Override /* Logic to be performed when plugin is enabled */
	public void onEnable()
	{
		/* Start parent classes */
		getLogger().info("Starting subclasses...");
		afk = new AFK(this); afk.start();
		//reply = new Reply(this); reply.start();
		quirks = new Quirks(this); quirks.start();
		
		/* Register orphan commands */

		/* Register orphan event listeners */
		//getServer().getPluginManager().registerEvents(new MobGriefingListener(), this);

		getLogger().info("Enabled successfully");
	}

	@Override /* Logic to be performed when plugin is disabled */
	public void onDisable()
	{
		/* Stop parent classes */
		getLogger().info("Stopping subclasses...");
		afk.stop();
		//reply.stop();
		quirks.stop();

		getLogger().info("Disabled successfully");
	}
}
