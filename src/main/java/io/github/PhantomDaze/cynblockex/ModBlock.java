package io.github.PhantomDaze.cynblockex;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;


public class ModBlock {
    public record BlockDefinition(String name, Block baseBlock) {}

    private static final List<BlockDefinition> DEFINITIONS = List.of(
            new BlockDefinition("red_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("old_glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("redux_glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("reactor_0", Blocks.NETHERITE_BLOCK),
            new BlockDefinition("reactor_1", Blocks.NETHERITE_BLOCK),
            new BlockDefinition("reactor_2", Blocks.NETHERITE_BLOCK)
    );

    private static final ModRegistry<Block> BLOCKS = new ModRegistry<>();

    static {
        registerAll();
    }

    private static void registerAll() {
        DEFINITIONS.forEach(definition -> BLOCKS.put(definition.name(), register(definition.name(), definition.baseBlock())));
    }

    private static Block register(String name, Block baseBlock) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.ofFullCopy(baseBlock);
        Block block = new Block(settings.setId(blockKey));
        ResourceKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("cynblockex", name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("cynblockex", name));
    }

    public static Block get(String name) {
        return BLOCKS.get(name);
    }

    public static List<Block> all() {
        return BLOCKS.all();
    }

    public static List<String> names() {
        return BLOCKS.names();
    }

    public static void initialize() {
    }
}
