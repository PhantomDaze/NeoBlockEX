package io.github.PhantomDaze.cynblockex.datagen;

import io.github.PhantomDaze.cynblockex.BlockType;
import io.github.PhantomDaze.cynblockex.ModBlock;
import io.github.PhantomDaze.cynblockex.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BlockEXLanguageProvider extends FabricLanguageProvider {
    private static final Map<String, String> EN_BLOCKS = Map.ofEntries(
            Map.entry("red_obsidian", "Red Obsidian"),
            Map.entry("glowing_obsidian", "Glowing Obsidian"),
            Map.entry("old_glowing_obsidian", "Old Glowing Obsidian"),
            Map.entry("redux_glowing_obsidian", "Redux Glowing Obsidian"),
            Map.entry("reactor_0", "Reactor 0"),
            Map.entry("reactor_1", "Reactor 1"),
            Map.entry("reactor_2", "Reactor 2")
    );

    private static final Map<String, String> ZH_BLOCKS = Map.ofEntries(
            Map.entry("red_obsidian", "红色黑曜石"),
            Map.entry("glowing_obsidian", "发光黑曜石"),
            Map.entry("old_glowing_obsidian", "旧发光黑曜石"),
            Map.entry("redux_glowing_obsidian", "Redux发光黑曜石"),
            Map.entry("reactor_0", "反应核0"),
            Map.entry("reactor_1", "反应核1"),
            Map.entry("reactor_2", "反应核2")
    );

    private static final Map<String, String> EN_ITEMS = Map.ofEntries(
            Map.entry("cyn", "C.Y.N."),
            Map.entry("unknown_item", "Unknown Item")
    );

    private static final Map<String, String> ZH_ITEMS = Map.ofEntries(
            Map.entry("cyn", "C.Y.N."),
            Map.entry("unknown_item", "不为人知的物品")
    );

    private static final Map<BlockType, String> EN_TABS = Map.ofEntries(
            Map.entry(BlockType.SIMPLE, "Blocks"),
            Map.entry(BlockType.TRANSPARENT, "Transparent Blocks"),
            Map.entry(BlockType.PILLAR, "Pillars"),
            Map.entry(BlockType.STAIRS, "Stairs"),
            Map.entry(BlockType.SLAB, "Slabs"),
            Map.entry(BlockType.WALL, "Walls"),
            Map.entry(BlockType.FENCE, "Fences"),
            Map.entry(BlockType.FENCE_GATE, "Fence Gates"),
            Map.entry(BlockType.DOOR, "Doors"),
            Map.entry(BlockType.TRAPDOOR, "Trapdoors"),
            Map.entry(BlockType.BUTTON, "Buttons"),
            Map.entry(BlockType.PRESSURE_PLATE, "Pressure Plates")
    );

    private static final Map<BlockType, String> ZH_TABS = Map.ofEntries(
            Map.entry(BlockType.SIMPLE, "方块"),
            Map.entry(BlockType.TRANSPARENT, "透明方块"),
            Map.entry(BlockType.PILLAR, "柱子"),
            Map.entry(BlockType.STAIRS, "楼梯"),
            Map.entry(BlockType.SLAB, "台阶"),
            Map.entry(BlockType.WALL, "墙"),
            Map.entry(BlockType.FENCE, "栅栏"),
            Map.entry(BlockType.FENCE_GATE, "栅栏门"),
            Map.entry(BlockType.DOOR, "门"),
            Map.entry(BlockType.TRAPDOOR, "活板门"),
            Map.entry(BlockType.BUTTON, "按钮"),
            Map.entry(BlockType.PRESSURE_PLATE, "压力板")
    );

    private final boolean chinese;

    public BlockEXLanguageProvider(FabricPackOutput output, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup, boolean chinese) {
        super(output, languageCode, registryLookup);
        this.chinese = chinese;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        for (BlockType type : BlockType.values()) {
            translationBuilder.add(type.tabTranslationKey(), (chinese ? ZH_TABS : EN_TABS).get(type));
        }
        translationBuilder.add("itemGroup.cynblockex.items", chinese ? "物品" : "Items");
        translationBuilder.add("itemGroup.cynblockex.vanilla", chinese ? "原版方块" : "Vanilla Blocks");
        translationBuilder.add("message.cynblockex.update_available", chinese
                ? "服务端BlockEX Mod不为最新，请通知服务器管理员及时更新"
                : "Server's BlockEX Mod is not lastest, remind server manager upgrade it, please");

        Map<String, String> blocks = chinese ? ZH_BLOCKS : EN_BLOCKS;
        ModBlock.names().forEach(name -> translationBuilder.add(ModBlock.get(name).asItem(), blocks.getOrDefault(name, name)));

        Map<String, String> items = chinese ? ZH_ITEMS : EN_ITEMS;
        ModItem.names().forEach(name -> translationBuilder.add(ModItem.get(name), items.getOrDefault(name, name)));
    }
}
