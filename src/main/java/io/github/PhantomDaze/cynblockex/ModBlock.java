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
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ModBlock {
    public record BlockDefinition(String name, Block baseBlock, BlockType type, String parentName) {
        public BlockDefinition(String name, Block baseBlock) {
            this(name, baseBlock, BlockType.SIMPLE, null);
        }

        public BlockDefinition(String name, Block baseBlock, BlockType type) {
            this(name, baseBlock, type, null);
        }
    }

    public record VariantDefinition(String baseName, Block baseBlock, BlockType type, String... variants) {
        List<BlockDefinition> expand() {
            return Arrays.stream(variants)
                    .map(variant -> new BlockDefinition(variant + "_" + baseName, baseBlock, type, null))
                    .toList();
        }
    }

    private static final List<BlockDefinition> DEFINITIONS = List.of(
            new BlockDefinition("red_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("old_glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("redux_glowing_obsidian", Blocks.OBSIDIAN),
            new BlockDefinition("reactor_0", Blocks.NETHERITE_BLOCK),
            new BlockDefinition("reactor_1", Blocks.NETHERITE_BLOCK),
            new BlockDefinition("reactor_2", Blocks.NETHERITE_BLOCK)
    );

    private static final List<VariantDefinition> VARIANT_DEFINITIONS = List.of();

    private static final ModRegistry<Block> BLOCKS = new ModRegistry<>();
    private static final Map<String, BlockDefinition> META = new LinkedHashMap<>();

    static {
        registerAll();
    }

    private static void registerAll() {
        Stream.concat(DEFINITIONS.stream(), VARIANT_DEFINITIONS.stream().flatMap(v -> v.expand().stream()))
                .forEach(definition -> {
                    Block block = register(definition);
                    BLOCKS.put(definition.name(), block);
                    META.put(definition.name(), definition);
                });
    }

    private static Block register(BlockDefinition definition) {
        ResourceKey<Block> blockKey = keyOfBlock(definition.name());
        Block block = createBlock(definition, blockKey);
        ResourceKey<Item> itemKey = keyOfItem(definition.name());
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Block createBlock(BlockDefinition definition, ResourceKey<Block> blockKey) {
        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(definition.baseBlock()).setId(blockKey);
        return switch (definition.type()) {
            case SIMPLE -> new Block(props);
            case TRANSPARENT -> new TransparentBlock(props);
            case PILLAR -> new RotatedPillarBlock(props);
            case STAIRS -> new StairBlock(definition.baseBlock().defaultBlockState(), props);
            case SLAB -> new SlabBlock(props);
            case WALL -> new WallBlock(props);
            case FENCE -> new FenceBlock(props);
            case FENCE_GATE -> new FenceGateBlock(WoodType.OAK, props);
            case DOOR -> new DoorBlock(BlockSetType.STONE, props);
            case TRAPDOOR -> new TrapDoorBlock(BlockSetType.STONE, props);
            case BUTTON -> new ButtonBlock(BlockSetType.STONE, 20, props);
            case PRESSURE_PLATE -> new PressurePlateBlock(BlockSetType.STONE, props);
        };
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BlockEXMod.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BlockEXMod.MOD_ID, name));
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

    public static BlockType getType(String name) {
        BlockDefinition definition = META.get(name);
        return definition == null ? BlockType.SIMPLE : definition.type();
    }

    public static Block getParent(String name) {
        BlockDefinition definition = META.get(name);
        if (definition == null || definition.parentName() == null) {
            return null;
        }
        return BLOCKS.get(definition.parentName());
    }

    public static List<Block> allOfType(BlockType type) {
        List<Block> result = new ArrayList<>();
        for (Map.Entry<String, BlockDefinition> entry : META.entrySet()) {
            if (entry.getValue().type() == type) {
                result.add(BLOCKS.get(entry.getKey()));
            }
        }
        return result;
    }

    public static void initialize() {
    }
}
