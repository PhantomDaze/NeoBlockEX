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
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


public class ModBlock {
    private static Block register(String name, @NotNull Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.@NotNull Properties settings) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        ResourceKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static ResourceKey<Block> keyOfBlock(String name) { return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("cynblockex", name)); }
    private static ResourceKey<Item> keyOfItem(String name) { return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("cynblockex", name)); }
    public static final Block RED_OBSIDIAN = register("red_obsidian", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));
    public static final Block GLOWING_OBSIDIAN = register("glowing_obsidian", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));
    public static final Block OLD_GLOWING_OBSIDIAN = register("old_glowing_obsidian", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));
    public static final Block REDUX_GLOWING_OBSIDIAN = register("redux_glowing_obsidian", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));
    public static final Block REACTOR_0 = register("reactor_0", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK));
    public static final Block REACTOR_1 = register("reactor_1", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK));
    public static final Block REACTOR_2 = register("reactor_2", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK));
    public static final Block P1 = register("p1", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P2 = register("p2", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P3 = register("p3", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P4 = register("p4", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P5 = register("p5", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P6 = register("p6", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P7 = register("p7", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P8 = register("p8", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P9 = register("p9", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P10 = register("p10", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P11 = register("p11", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P12 = register("p12", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Block P13 = register("p13", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static void initialize() {}
}
