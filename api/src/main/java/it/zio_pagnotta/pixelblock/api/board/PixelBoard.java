package it.zio_pagnotta.pixelblock.api.board;

import it.zio_pagnotta.pixelblock.api.user.PixelUser;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public sealed interface PixelBoard permits PixelBoardImpl {
    String getIdentifier();
    int getStartX();
    int getStartY();
    int getStartZ();
    int getEndX();
    int getEndY();
    int getEndZ();
    boolean canDrawNonEmpty();

    void setCanDrawNonEmpty(boolean bool);

    String getDefaultMaterial();
    ConcurrentMap<UUID, PixelUser> getUsers();

    void addUser(UUID uuid, PixelUser pixelUser);
    void removeUser(UUID uuid);
    Optional<PixelUser> getUser(UUID uuid);

    static Builder builder(String identifier) {
        return new PixelBoardImpl.Builder(identifier);
    }

    sealed interface Builder permits PixelBoardImpl.Builder {
        Builder startX(int startX);
        Builder startY(int startY);
        Builder startZ(int startZ);
        Builder endX(int endX);
        Builder endY(int endY);
        Builder endZ(int endZ);
        Builder defaultMaterial(String defaultMaterial);
        Builder canDrawNonEmpty(boolean bool);
        Builder users(@Nullable ConcurrentMap<UUID, PixelUser> users);

        PixelBoard build();
    }
}
