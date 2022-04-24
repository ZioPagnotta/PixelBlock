package it.zio_pagnotta.pixelblock.paper;

import it.zio_pagnotta.pixelblock.api.PixelBlockAPI;
import it.zio_pagnotta.pixelblock.api.database.IPixelBlockDAO;

public interface IPluginMain {
    default void onEnable() throws RuntimeException {}
    default void onDisable() throws RuntimeException {}

    PluginMain getLoader();
    PixelBlockAPI getAPI();
    IPixelBlockDAO getDatabase();
}
