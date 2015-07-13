package io.github.pxy.pxytech;



public class Quirks {
	
	private final PxyTech plugin;
	
	public Quirks(PxyTech plugin) {
		this.plugin = plugin;
	}
	
	public void start() {
		/* Register commands */
		plugin.getCommand("quirks").setExecutor(new QuirksCommandExecutor(this));
		
		/* Register event listeners */
		plugin.getServer().getPluginManager().registerEvents(new VegetarianQuirksListener(this), plugin);

		plugin.getLogger().info("[Quirks] Enabled");
	}

	public void stop() {

		plugin.getLogger().info("[Quirks] Disabled");
	}
	
	public PxyTech getPlugin() {return plugin;}


}
