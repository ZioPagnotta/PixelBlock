package it.zio_pagnotta.pixelblock.api.database;

import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import it.zio_pagnotta.pixelblock.api.user.PixelUser;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IPixelBlockDAO {
    CompletableFuture<Boolean> existUser(UUID uuid);
    CompletableFuture<Boolean> existBoard(String identifier);

    CompletableFuture<PixelUser> fetchUser(UUID uuid);
    CompletableFuture<List<PixelUser>> fetchAllUsers(String pixelBoard);

    CompletableFuture<PixelBoard> fetchBoard(String identifier);
    CompletableFuture<List<PixelBoard>> fetchAllBoards();
}
