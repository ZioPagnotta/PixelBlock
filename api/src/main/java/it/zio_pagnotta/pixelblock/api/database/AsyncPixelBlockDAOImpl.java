package it.zio_pagnotta.pixelblock.api.database;

import com.glyart.mystral.database.AsyncDatabase;
import it.zio_pagnotta.pixelblock.api.block.PixelBlock;
import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import it.zio_pagnotta.pixelblock.api.service.IService;
import it.zio_pagnotta.pixelblock.api.user.PixelUser;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.sql.Types.*;

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
            "owner TEXT, " +
            "board TEXT, " +
            "x INTEGER, " +
            "y INTEGER, " +
            "z INTEGER, " +
            "material TEXT, " +
            "FOREIGN KEY(owner) REFERENCES users(uuid) ON DELETE CASCADE" +
            ")", false);

        database.update("CREATE TABLE IF NOT EXISTS boards (" +
            "identifier TEXT, " +
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
            "owner TEXT, " +
            "identifier TEXT, " +
            "FOREIGN KEY(identifier) REFERENCES boards(identifier) ON DELETE CASCADE" +
            ")", false);

        database.update("CREATE TABLE sqlite_sequence(name,seq)", false);
    }

    @Override
    public CompletableFuture<Boolean> existUser(@NotNull UUID uuid) {
        return database.query("SELECT * FROM users WHERE uuid = ?", new Object[] {uuid.toString()}, ResultSet::isBeforeFirst, VARCHAR);
    }

    @Override
    public CompletableFuture<Boolean> existBoard(String identifier) {
        return database.query("SELECT * FROM boards WHERE identifier = ?", new Object[] {identifier}, ResultSet::isBeforeFirst, VARCHAR);
    }

    @Override
    public CompletableFuture<PixelUser> fetchUser(@NotNull UUID uuid) {
        return database.queryForObject("SELECT * FROM users_blocks WHERE owner = ?", new Object[] {uuid.toString()}, (rs, i) -> PixelUser.builder()
            .pixelBlocks(List.of(PixelBlock.builder(rs.getString("board"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"))
                    .material(rs.getString("material"))
                    .owner(UUID.fromString(rs.getString("owner")))
                    .build()))
            .build(), VARCHAR);
    }

    @Override
    public CompletableFuture<List<PixelUser>> fetchAllUsers(String pixelBoard) {
        return database.queryForList("SELECT * FROM users_blocks WHERE board = ?", new Object[] {pixelBoard}, (rs, i) -> PixelUser.builder()
                .player(UUID.fromString(rs.getString("owner")))
                .pixelBlocks(List.of(PixelBlock.builder(rs.getString("board"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"))
                        .material(rs.getString("material"))
                        .owner(UUID.fromString(rs.getString("owner")))
                        .build()))
                .build(), VARCHAR);
    }

    @Override
    public CompletableFuture<PixelBoard> fetchBoard(String identifier) {
        return database.queryForObject("SELECT * FROM boards WHERE identifier = ?", new Object[] {identifier}, (rs, i) -> PixelBoard.builder(rs.getString("identifier"))
                .startX(rs.getInt("startX"))
                .startY(rs.getInt("startY"))
                .startZ(rs.getInt("startZ"))
                .endX(rs.getInt("endX"))
                .endY(rs.getInt("endY"))
                .endZ(rs.getInt("endZ"))
                .defaultMaterial(rs.getString("default_material"))
                .canDrawNonEmpty(rs.getBoolean("can_draw_non_empty"))
                .users(null)
                .build(), VARCHAR);
    }

    @Override
    public CompletableFuture<List<PixelBoard>> fetchAllBoards() {
        return database.queryForList("SELECT * FROM boards", new Object[]{}, (rs, i) -> PixelBoard.builder(rs.getString("identifier"))
                .startX(rs.getInt("startX"))
                .startY(rs.getInt("startY"))
                .startZ(rs.getInt("startZ"))
                .endX(rs.getInt("endX"))
                .endY(rs.getInt("endY"))
                .endZ(rs.getInt("endZ"))
                .defaultMaterial(rs.getString("default_material"))
                .canDrawNonEmpty(rs.getBoolean("can_draw_non_empty"))
                .users(null)
                .build()
        );
    }
}
