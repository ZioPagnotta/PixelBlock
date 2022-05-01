package it.zio_pagnotta.pixelblock.api.user;

import it.zio_pagnotta.pixelblock.api.block.PixelBlock;
import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

public final class PixelUserImpl implements PixelUser {
    private final UUID uuid;
    private final List<PixelBlock> pixelBlocks;

    private PixelUserImpl(UUID uuid, @Nullable List<PixelBlock> pixelBlocks) {
        this.uuid = uuid;
        this.pixelBlocks = pixelBlocks == null ? new ArrayList<>() : pixelBlocks;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Contract(pure = true)
    @Override
    public @NotNull @UnmodifiableView List<PixelBlock> getPixelBlocks() {
        return Collections.unmodifiableList(pixelBlocks);
    }

    @Override
    public void addPixelBlock(PixelBlock pixelBlock) {
        pixelBlocks.add(pixelBlock);
    }

    @Override
    public void removePixelBlock(int x, int y, int z) {
        for(PixelBlock pixelBlock : getPixelBlocks()) {
            if(pixelBlock.getX() == x && pixelBlock.getY() == y && pixelBlock.getZ() == z) {
                pixelBlocks.remove(pixelBlock);
            }
        }
    }

    @Override
    public Optional<PixelBlock> getByCoords(int x, int y, int z) {
        for(PixelBlock pixelBlock : getPixelBlocks()) {
            if(pixelBlock.getX() == x && pixelBlock.getY() == y && pixelBlock.getZ() == z) {
                return Optional.of(pixelBlock);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean canDrawPixel(@NotNull PixelBlock pixelBlock, @NotNull PixelBoard pixelBoard) {
        return !pixelBlock.getMaterial().equals(pixelBoard.getDefaultMaterial()) && pixelBlock.getOwner() == null;
    }

    @Contract(value = "_, _ -> new", pure = true)
    static @NotNull PixelUser create(UUID uuid, @Nullable List<PixelBlock> pixelBlocks) {
        return new PixelUserImpl(uuid, pixelBlocks);
    }

    static final class Builder implements PixelUser.Builder {
        UUID uuid;
        List<PixelBlock> pixelBlocks;

        @Override
        public Builder player(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        @Override
        public Builder pixelBlocks(@Nullable List<PixelBlock> pixelBlocks) {
            this.pixelBlocks = pixelBlocks == null ? new ArrayList<>() : pixelBlocks;
            return this;
        }

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull PixelUser build() {
            return PixelUserImpl.create(uuid, pixelBlocks);
        }
    }
}
