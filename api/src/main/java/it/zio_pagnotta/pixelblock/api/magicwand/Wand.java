package it.zio_pagnotta.pixelblock.api.magicwand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public sealed interface Wand permits WandImpl {
    String getMaterial();
    int getStartX();
    int getStartY();
    int getStartZ();
    int getEndX();
    int getEndY();
    int getEndZ();

    void setStartX(int startX);
    void setStartY(int startY);
    void setStartZ(int startZ);
    void setEndX(int endX);
    void setEndY(int endZ);
    void setEndZ(int endY);

    @Contract(value = "_ -> new", pure = true)
    static @NotNull Builder builder(String material) {
        return new WandImpl.Builder(material);
    }

    sealed interface Builder permits WandImpl.Builder {
        Builder startX(int startX);
        Builder startY(int startY);
        Builder startZ(int startZ);
        Builder endX(int endX);
        Builder endY(int endY);
        Builder endZ(int endZ);

        Wand build();
        Wand buildEmpty();
    }
}
