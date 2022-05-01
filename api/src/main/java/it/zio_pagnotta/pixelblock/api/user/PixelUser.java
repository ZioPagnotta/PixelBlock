package it.zio_pagnotta.pixelblock.api.user;

import it.zio_pagnotta.pixelblock.api.block.PixelBlock;
import it.zio_pagnotta.pixelblock.api.board.PixelBoard;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public sealed interface PixelUser permits PixelUserImpl {
    UUID getUUID();
    List<PixelBlock> getPixelBlocks();
    boolean canDrawPixel(PixelBlock pixelBlock, PixelBoard pixelBoard);

    void addPixelBlock(PixelBlock pixelBlock);
    void removePixelBlock(int x, int y, int z);
    Optional<PixelBlock> getByCoords(int x, int y, int z);

    static Builder builder() {
        return new PixelUserImpl.Builder();
    }

    sealed interface Builder permits PixelUserImpl.Builder {
        Builder player(UUID uuid);
        Builder pixelBlocks(@Nullable List<PixelBlock> pixelBlocks);
        PixelUser build();
    }
}
