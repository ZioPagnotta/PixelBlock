package it.zio_pagnotta.pixelblock.paper;

import it.zio_pagnotta.pixelblock.api.PixelBlockAPI;

public interface IPluginMain {
    default void onEnable() throws RuntimeException {}
    default void onDisable() throws RuntimeException {}

    PluginMain getLoader();
    PixelBlockAPI getAPI();
}
