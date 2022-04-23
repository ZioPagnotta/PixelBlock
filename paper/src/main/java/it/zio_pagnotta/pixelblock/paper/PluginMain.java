package it.zio_pagnotta.pixelblock.paper;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    private IPluginMain pluginMain;

    @Override
    public void onEnable() {
        pluginMain = new PluginMainImpl(this);
        pluginMain.onEnable();
    }

    @Override
    public void onDisable() {
        pluginMain.onDisable();
    }
}
