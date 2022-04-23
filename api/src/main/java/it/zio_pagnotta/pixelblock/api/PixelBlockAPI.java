package it.zio_pagnotta.pixelblock.api;

import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public sealed interface PixelBlockAPI permits PixelBlockAPIImpl {
    Set<PixelBoard> getPixelBoards();

    void registerBoards(PixelBoard... pixelBoards);
    void unregisterBoard(String boardIdentifier);

    Optional<PixelBoard> getByIdentifier(String boardIdentifier);

    static Builder builder(@Nullable Set<PixelBoard> pixelBoards) {
        return new PixelBlockAPIImpl.Builder(pixelBoards);
    }

    sealed interface Builder permits PixelBlockAPIImpl.Builder {
        PixelBlockAPI build();
    }
}
