package io.github.PhantomDaze.cynblockex;

public enum BlockType {
    SIMPLE,
    TRANSPARENT,
    PILLAR,
    STAIRS,
    SLAB,
    WALL,
    FENCE,
    FENCE_GATE,
    DOOR,
    TRAPDOOR,
    BUTTON,
    PRESSURE_PLATE;

    public String tabId() {
        return "blocks_" + name().toLowerCase();
    }

    public String tabTranslationKey() {
        return "itemGroup.cynblockex." + name().toLowerCase();
    }
}
