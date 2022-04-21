package it.zio_pagnotta.pixelblock.api.user;

import it.zio_pagnotta.pixelblock.api.block.PixelBlock;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class PixelUserImpl implements PixelUser {
    private final List<PixelBlock> pixelBlocks;

    private PixelUserImpl(@Nullable List<PixelBlock> pixelBlocks) {
        this.pixelBlocks = pixelBlocks == null ? new ArrayList<>() : pixelBlocks;
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

    @Contract(value = "_ -> new", pure = true)
    static @NotNull PixelUser create(@Nullable List<PixelBlock> pixelBlocks) {
        return new PixelUserImpl(pixelBlocks);
    }

    static final class Builder implements PixelUser.Builder {
        List<PixelBlock> pixelBlocks;

        Builder() {}

        @Override
        public Builder pixelBlocks(@Nullable List<PixelBlock> pixelBlocks) {
            this.pixelBlocks = pixelBlocks == null ? new ArrayList<>() : pixelBlocks;
            return this;
        }

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull PixelUser build() {
            return PixelUserImpl.create(pixelBlocks);
        }
    }
}
