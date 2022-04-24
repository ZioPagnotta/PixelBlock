package it.zio_pagnotta.pixelblock.paper;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.BukkitCommandManager;
import com.glyart.mystral.database.AsyncDatabase;
import it.zio_pagnotta.pixelblock.api.PixelBlockAPI;
import it.zio_pagnotta.pixelblock.api.database.AsyncPixelBlockDAOImpl;
import it.zio_pagnotta.pixelblock.api.database.IPixelBlockDAO;
import it.zio_pagnotta.pixelblock.api.database.SQLiteFactory;
import it.zio_pagnotta.pixelblock.api.database.SerialExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import javax.sql.DataSource;
import java.util.function.Function;
import java.util.logging.Level;

import static cloud.commandframework.execution.CommandExecutionCoordinator.simpleCoordinator;

public class PluginMainImpl implements IPluginMain {
    private final PluginMain loader;
    private final PixelBlockAPI pixelBlockAPI;
    private final IPixelBlockDAO database;
    private CommandManager<CommandSender> manager;

    public PluginMainImpl(PluginMain loader) {
        this.loader = loader;
        pixelBlockAPI = PixelBlockAPI.builder(null).build();

        DataSource dataSource = new SQLiteFactory(getLoader().getDataFolder().getPath()).newDataSource();
        AsyncDatabase asyncDatabase = new AsyncDatabase(dataSource, new SerialExecutor(command -> Bukkit.getScheduler().runTaskAsynchronously(getLoader(), command)));
        this.database = new AsyncPixelBlockDAOImpl(asyncDatabase);
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
        if(manager != null) {
            return;
        }

        try {
            manager = new BukkitCommandManager<>(getLoader(), simpleCoordinator(), Function.identity(), Function.identity());
        } catch (Exception e) {
            getLoader().getLogger().log(Level.SEVERE, "Cannot load BukkitCommandManager.", e);
            loader.getPluginLoader().disablePlugin(loader);
            return;
        }

        Command.Builder<CommandSender> parentNode = manager.commandBuilder("pixelblock", "pb");
    }

    @Override
    public PluginMain getLoader() {
        return loader;
    }

    @Override
    public PixelBlockAPI getAPI() {
        return pixelBlockAPI;
    }

    @Override
    public IPixelBlockDAO getDatabase() { return database; }
}
