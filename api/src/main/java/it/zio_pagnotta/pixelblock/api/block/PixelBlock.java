package it.zio_pagnotta.pixelblock.api.block;

import java.util.UUID;

public sealed interface PixelBlock permits PixelBlockImpl {
    void setOwner(UUID owner);
    UUID getOwner();

    void setMaterial(String material);
    String getMaterial();

    int getX();
    int getY();
    int getZ();

    String getBoard();

    static Builder builder(String board, int x, int y, int z) {
        return new PixelBlockImpl.Builder(board, x, y, z);
    }

    sealed interface Builder permits PixelBlockImpl.Builder {
        Builder owner(UUID owner);
        Builder material(String material);

        PixelBlock build();
    }
}
