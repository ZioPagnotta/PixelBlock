package it.zio_pagnotta.pixelblock.api;

import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import it.zio_pagnotta.pixelblock.api.magicwand.Wand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public sealed interface PixelBlockAPI permits PixelBlockAPIImpl {
    Set<PixelBoard> getPixelBoards();
    Map<UUID, Wand> getUserWands();

    void registerBoards(List<PixelBoard> pixelBoards);
    void unregisterBoard(String boardIdentifier);

    void addUserWand(UUID uuid, Wand wand);
    void removeUserWand(UUID uuid);

    Optional<PixelBoard> getByIdentifier(String boardIdentifier);
    Optional<Wand> getByUser(UUID uuid);

    @Contract(value = "_ -> new", pure = true)
    static @NotNull Builder builder(@Nullable Set<PixelBoard> pixelBoards) {
        return new PixelBlockAPIImpl.Builder(pixelBoards);
    }

    sealed interface Builder permits PixelBlockAPIImpl.Builder {
        PixelBlockAPI build();
    }
}
