package it.zio_pagnotta.pixelblock.paper;

import it.zio_pagnotta.pixelblock.api.PixelBlockAPI;
import org.bukkit.event.Listener;

public class PluginMainImpl implements IPluginMain {
    private final PluginMain loader;
    private final PixelBlockAPI pixelBlockAPI;

    public PluginMainImpl(PluginMain loader) {
        this.loader = loader;
        pixelBlockAPI = PixelBlockAPI.builder(null).build();
    }

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents(Listener... listeners) {
        for(Listener listener : listeners) {
            getLoader().getServer().getPluginManager().registerEvents(listener, getLoader());
        }
    }

    private void registerCommands() {

    }

    @Override
    public PluginMain getLoader() {
        return loader;
    }

    @Override
    public PixelBlockAPI getAPI() {
        return pixelBlockAPI;
    }
}
