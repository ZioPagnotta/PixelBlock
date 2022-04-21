package it.zio_pagnotta.pixelblock.api.block;

public sealed interface PixelBlock permits PixelBlockImpl {
    void setOwner(String owner);
    String getOwner();

    void setMaterial(String material);
    String getMaterial();

    int getX();
    int getY();
    int getZ();

    sealed interface Builder permits PixelBlockImpl.Builder {
        Builder owner(String owner);
        Builder material(String material);

        PixelBlock build();
    }
}
