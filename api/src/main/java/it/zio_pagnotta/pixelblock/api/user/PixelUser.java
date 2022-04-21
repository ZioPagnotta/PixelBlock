package it.zio_pagnotta.pixelblock.api.user;

import it.zio_pagnotta.pixelblock.api.block.PixelBlock;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Optional;

public sealed interface PixelUser permits PixelUserImpl {
    List<PixelBlock> getPixelBlocks();

    void addPixelBlock(PixelBlock pixelBlock);
    void removePixelBlock(int x, int y, int z);
    Optional<PixelBlock> getByCoords(int x, int y, int z);

    sealed interface Builder permits PixelUserImpl.Builder {
        Builder pixelBlocks(@Nullable List<PixelBlock> pixelBlocks);
        PixelUser build();
    }
}
