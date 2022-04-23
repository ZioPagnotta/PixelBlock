package it.zio_pagnotta.pixelblock.api.board;

import it.zio_pagnotta.pixelblock.api.user.PixelUser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class PixelBoardImpl implements PixelBoard {
    private final int startX, startY, startZ, endX, endY, endZ;
    private final String identifier, defaultMaterial;
    private boolean canDrawNonEmpty;
    private final ConcurrentMap<UUID, PixelUser> users;

    private PixelBoardImpl(String identifier, int startX, int startY, int startZ, int endX, int endY, int endZ, String defaultMaterial, boolean canDrawNonEmpty, @Nullable ConcurrentMap<UUID, PixelUser> users) {
        this.identifier = identifier;
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.defaultMaterial = defaultMaterial;
        this.canDrawNonEmpty = canDrawNonEmpty;
        this.users = users == null ? new ConcurrentHashMap<>() : users;
    }

    @Override
    public String getIdentifier() { return identifier; }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public int getStartZ() {
        return startZ;
    }

    @Override
    public int getEndX() {
        return endX;
    }

    @Override
    public int getEndY() {
        return endY;
    }

    @Override
    public int getEndZ() {
        return endZ;
    }

    @Override
    public String getDefaultMaterial() {
        return defaultMaterial;
    }

    @Override
    public boolean canDrawNonEmpty() { return canDrawNonEmpty; }

    @Override
    public void setCanDrawNonEmpty(boolean bool) {
        canDrawNonEmpty = bool;
    }

    @Contract(" -> new")
    @Override
    public @NotNull ConcurrentMap<UUID, PixelUser> getUsers() {
        return new ConcurrentHashMap<>(users);
    }

    @Override
    public void addUser(UUID uuid, PixelUser pixelUser) {
        users.put(uuid, pixelUser);
    }

    @Override
    public void removeUser(UUID uuid) {
        users.remove(uuid);
    }

    @Override
    public Optional<PixelUser> getUser(UUID uuid) {
        return Optional.ofNullable(users.getOrDefault(uuid, null));
    }

    @Contract("_, _, _, _, _, _, _, _, _, _ -> new")
    static @NotNull PixelBoard create(String identifier, int startX, int startY, int startZ, int endX, int endY, int endZ, String defaultMaterial, boolean canDrawNonEmpty, @Nullable ConcurrentMap<UUID, PixelUser> users) {
        return new PixelBoardImpl(identifier, startX, startY, startZ, endX, endY, endZ, defaultMaterial, canDrawNonEmpty, users);
    }

    static final class Builder implements PixelBoard.Builder {
        private final String identifier;
        private int startX, startY, startZ, endX, endY, endZ;
        private String defaultMaterial;
        private boolean canDrawNonEmpty;
        private ConcurrentMap<UUID, PixelUser> users;

        Builder(String identifier) { this.identifier = identifier; }

        @Override
        public Builder startX(int startX) {
            this.startX = startX;
            return this;
        }

        @Override
        public Builder startY(int startY) {
            this.startY = startY;
            return this;
        }

        @Override
        public Builder startZ(int startZ) {
            this.startZ = startZ;
            return this;
        }

        @Override
        public Builder endX(int endX) {
            this.endX = endX;
            return this;
        }

        @Override
        public Builder endY(int endY) {
            this.endY = endY;
            return this;
        }

        @Override
        public Builder endZ(int endZ) {
            this.endZ = endZ;
            return this;
        }

        @Override
        public Builder defaultMaterial(String defaultMaterial) {
            this.defaultMaterial = defaultMaterial;
            return this;
        }

        @Override
        public Builder canDrawNonEmpty(boolean bool) {
            this.canDrawNonEmpty = bool;
            return this;
        }

        @Override
        public Builder users(@Nullable ConcurrentMap<UUID, PixelUser> users) {
            this.users = users == null ? new ConcurrentHashMap<>() : users;
            return this;
        }

        @Contract(" -> new")
        @Override
        public @NotNull PixelBoard build() {
            return PixelBoardImpl.create(identifier, startX, startY, startZ, endX, endY, endZ, defaultMaterial, canDrawNonEmpty, users);
        }
    }
}
