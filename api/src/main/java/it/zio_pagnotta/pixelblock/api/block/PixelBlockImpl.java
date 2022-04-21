package it.zio_pagnotta.pixelblock.api.block;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class PixelBlockImpl implements PixelBlock {
    private final int x, y, z;
    private String owner, material;

    private PixelBlockImpl(int x, int y, int z, String owner, String material) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.owner = owner;
        this.material = material;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Contract("_, _, _, _, _ -> new")
    static @NotNull PixelBlock create(int x, int y, int z, String owner, String material) {
        return new PixelBlockImpl(x, y, z, owner, material);
    }

    static final class Builder implements PixelBlock.Builder {
        final int x, y, z;
        String owner, material;

        Builder(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public Builder owner(String owner) {
            this.owner = owner;
            return this;
        }

        @Override
        public Builder material(String material) {
            this.material = material;
            return this;
        }

        @Override
        public PixelBlock build() {
            return PixelBlockImpl.create(x, y, z, owner, material);
        }
    }
}
