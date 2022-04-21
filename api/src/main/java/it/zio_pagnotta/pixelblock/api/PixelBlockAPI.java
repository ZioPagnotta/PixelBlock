package it.zio_pagnotta.pixelblock.api;

import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PixelBlockAPI {
    private final Set<PixelBoard> pixelBoards;

    public PixelBlockAPI(@Nullable Set<PixelBoard> pixelBoards) {
        this.pixelBoards = pixelBoards == null ? new HashSet<>() : pixelBoards;
    }

    public Set<PixelBoard> getPixelBoards() {
        return Collections.unmodifiableSet(pixelBoards);
    }

    public void registerBoard(PixelBoard pixelBoard) {
        pixelBoards.add(pixelBoard);
    }

    public void unregisterBoard(String boardIdentifier) {
        pixelBoards.removeIf(pixelBoard -> pixelBoard.getIdentifier().equals(boardIdentifier));
    }

    public Optional<PixelBoard> getByIdentifier(String boardIdentifier) {
        for(PixelBoard pixelBoard : getPixelBoards()) {
            if(pixelBoard.getIdentifier().equals(boardIdentifier)) {
                return Optional.of(pixelBoard);
            }
        }

        return Optional.empty();
    }
}
