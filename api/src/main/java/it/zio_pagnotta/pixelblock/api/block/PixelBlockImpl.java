package it.zio_pagnotta.pixelblock.api.block;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class PixelBlockImpl implements PixelBlock {
    private final int x, y, z;
    private String material;
    private final String board;
    private UUID owner;

    private PixelBlockImpl(int x, int y, int z, UUID owner, String material, String board) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.owner = owner;
        this.material = material;
        this.board = board;
    }

    @Override
    public void setOwner(UUID owner) {
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
    public UUID getOwner() {
        return owner;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String getBoard() { return board; }

    @Contract("_, _, _, _, _, _ -> new")
    static @NotNull PixelBlock create(int x, int y, int z, UUID owner, String material, String board) {
        return new PixelBlockImpl(x, y, z, owner, material, board);
    }

    static final class Builder implements PixelBlock.Builder {
        final int x, y, z;
        String material;
        final String board;
        UUID owner;

        Builder(String board, int x, int y, int z) {
            this.board = board;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public Builder owner(UUID owner) {
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
            return PixelBlockImpl.create(x, y, z, owner, material, board);
        }
    }
}
