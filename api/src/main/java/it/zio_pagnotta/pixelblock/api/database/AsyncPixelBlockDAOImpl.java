package it.zio_pagnotta.pixelblock.api.database;

import com.glyart.mystral.database.AsyncDatabase;
import it.zio_pagnotta.pixelblock.api.service.IService;

public class AsyncPixelBlockDAOImpl implements IService, IPixelBlockDAO {
    private final AsyncDatabase database;

    public AsyncPixelBlockDAOImpl(AsyncDatabase database) {
        this.database = database;
    }

    @Override
    public void enable() {
        database.update("CREATE TABLE users (" +
            "uuid TEXT " +
            "PRIMARY KEY(uuid)" +
            ")", false);

        database.update("CREATE TABLE IF NOT EXISTS users_blocks (" +
            "owner TEXT," +
            "x INTEGER, " +
            "y INTEGER, " +
            "z INTEGER, " +
            "material TEXT, " +
            "FOREIGN KEY(owner) REFERENCES users(uuid) ON DELETE CASCADE" +
            ")", false);

        database.update("CREATE TABLE IF NOT EXISTS boards (" +
            "identifier TEXT," +
            "startX INTEGER, " +
            "startY INTEGER, " +
            "startZ INTEGER, " +
            "endX INTEGER, " +
            "endY INTEGER, " +
            "endZ INTEGER, " +
            "default_material TEXT, " +
            "can_draw_non_empty TINYINT, " + // 0 = false - 1 = true
            "PRIMARY KEY(identifier)" +
            ")", false);

        database.update("CREATE TABLE IF NOT EXISTS users_boards (" +
            "owner TEXT," +
            "identifier TEXT," +
            "FOREIGN KEY(identifier) REFERENCES boards(identifier) ON DELETE CASCADE" +
            ")", false);

        database.update("CREATE TABLE sqlite_sequence(name,seq)", false);
    }
}
