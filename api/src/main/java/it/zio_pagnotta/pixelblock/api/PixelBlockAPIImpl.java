package it.zio_pagnotta.pixelblock.api;

import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import it.zio_pagnotta.pixelblock.api.magicwand.Wand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

public final class PixelBlockAPIImpl implements PixelBlockAPI {
    private final Set<PixelBoard> pixelBoards;
    private final Map<UUID, Wand> userWands;

    private PixelBlockAPIImpl(@Nullable Set<PixelBoard> pixelBoards) {
        this.pixelBoards = pixelBoards == null ? new HashSet<>() : pixelBoards;
        this.userWands = new HashMap<>();
    }

    @Contract(pure = true)
    @Override
    public @NotNull @UnmodifiableView Set<PixelBoard> getPixelBoards() {
        return Collections.unmodifiableSet(pixelBoards);
    }

    @Contract(pure = true)
    @Override
    public @NotNull @UnmodifiableView Map<UUID, Wand> getUserWands() {
        return Collections.unmodifiableMap(userWands);
    }

    @Override
    public void registerBoards(PixelBoard... pixelBoards) {
        this.pixelBoards.addAll(List.of(pixelBoards));
    }

    @Override
    public void unregisterBoard(String boardIdentifier) {
        pixelBoards.removeIf(pixelBoard -> pixelBoard.getIdentifier().equals(boardIdentifier));
    }

    @Override
    public void addUserWand(UUID uuid, Wand wand) {
        userWands.putIfAbsent(uuid, wand);
    }

    @Override
    public void removeUserWand(UUID uuid) {
        userWands.remove(uuid);
    }

    @Override
    public Optional<PixelBoard> getByIdentifier(String boardIdentifier) {
        for(PixelBoard pixelBoard : getPixelBoards()) {
            if(pixelBoard.getIdentifier().equals(boardIdentifier)) {
                return Optional.of(pixelBoard);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Wand> getByUser(UUID uuid) {
        return Optional.ofNullable(getUserWands().getOrDefault(uuid, null));
    }

    @Contract(value = "_ -> new", pure = true)
    static @NotNull PixelBlockAPIImpl create(@Nullable Set<PixelBoard> pixelBoards) {
        return new PixelBlockAPIImpl(pixelBoards);
    }

    static final class Builder implements PixelBlockAPI.Builder {
        Set<PixelBoard> pixelBoards;

        Builder(@Nullable Set<PixelBoard> pixelBoards) {
            this.pixelBoards = pixelBoards == null ? new HashSet<>() : pixelBoards;
        }

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull PixelBlockAPI build() {
            return PixelBlockAPIImpl.create(pixelBoards);
        }
    }
}
