package io.github.PhantomDaze.cynblockex.datagen;

import io.github.PhantomDaze.cynblockex.ModBlock;
import io.github.PhantomDaze.cynblockex.ModItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class BlockEXModelProvider extends FabricModelProvider {
    public BlockEXModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        for (String name : ModBlock.names()) {
            Block block = ModBlock.get(name);
            switch (ModBlock.getType(name)) {
                case SIMPLE, TRANSPARENT -> {
                    blockModelGenerators.createTrivialCube(block);
                    blockModelGenerators.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
                }
                case PILLAR -> {
                    blockModelGenerators.createAxisAlignedPillarBlock(block, TexturedModel.COLUMN);
                    blockModelGenerators.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
                }
                default -> {
                }
            }
        }

        for (String name : ModBlock.names()) {
            Block block = ModBlock.get(name);
            Block parent = ModBlock.getParent(name);
            switch (ModBlock.getType(name)) {
                case STAIRS -> blockModelGenerators.family(requireParent(name, parent)).stairs(block);
                case SLAB -> blockModelGenerators.family(requireParent(name, parent)).slab(block);
                case WALL -> blockModelGenerators.family(requireParent(name, parent)).wall(block);
                case FENCE -> blockModelGenerators.family(requireParent(name, parent)).fence(block);
                case FENCE_GATE -> blockModelGenerators.family(requireParent(name, parent)).fenceGate(block);
                case BUTTON -> blockModelGenerators.family(requireParent(name, parent)).button(block);
                case PRESSURE_PLATE -> blockModelGenerators.family(requireParent(name, parent)).pressurePlate(block);
                case DOOR -> blockModelGenerators.createDoor(block);
                case TRAPDOOR -> blockModelGenerators.createTrapdoor(block);
                default -> {
                }
            }
        }
    }

    private static Block requireParent(String name, Block parent) {
        if (parent == null) {
            throw new IllegalStateException("Block '" + name + "' requires a parent block for model generation");
        }
        return parent;
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ModItem.names().forEach(name -> itemModelGenerators.generateFlatItem(ModItem.get(name), ModelTemplates.FLAT_ITEM));
    }
}
