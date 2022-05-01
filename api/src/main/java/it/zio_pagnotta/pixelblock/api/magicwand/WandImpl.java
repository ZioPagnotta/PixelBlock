package it.zio_pagnotta.pixelblock.api.magicwand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class WandImpl implements Wand {
    private int startX, startY, startZ, endX, endY, endZ;
    private final String material;

    public WandImpl(String material) {
        this.material = material;
    }

    public WandImpl(String material, int startX, int startY, int startZ, int endX, int endY, int endZ) {
        this.material = material;
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
    }

    @Override
    public String getMaterial() {
        return material;
    }

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
    public void setStartX(int startX) {
        this.startX = startX;
    }

    @Override
    public void setStartY(int startY) {
        this.startY = startY;
    }

    @Override
    public void setStartZ(int startZ) {
        this.startZ = startZ;
    }

    @Override
    public void setEndX(int endX) {
        this.endX = endX;
    }

    @Override
    public void setEndY(int endY) {
        this.endY = endY;
    }

    @Override
    public void setEndZ(int endZ) {
        this.endZ = endZ;
    }

    @Contract(value = "_, _, _, _, _, _, _ -> new", pure = true)
    static @NotNull Wand create(String material, int startX, int startY, int startZ, int endX, int endY, int endZ) {
        return new WandImpl(material, startX, startY, startZ, endX, endY, endZ);
    }

    @Contract(value = "_ -> new", pure = true)
    static @NotNull Wand create(String material) {
        return new WandImpl(material);
    }

    static final class Builder implements Wand.Builder {
        private final String material;
        private int startX, startY, startZ, endX, endY, endZ;

        Builder(String material) {
            this.material = material;
        }

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

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull Wand build() {
            return WandImpl.create(material, startX, startY, startZ, endX, endY, endZ);
        }

        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull Wand buildEmpty() {
            return WandImpl.create(material);
        }
    }
}
