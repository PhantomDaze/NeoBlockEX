package io.github.PhantomDaze.cynblockex.datagen;

import io.github.PhantomDaze.cynblockex.ModBlock;
import io.github.PhantomDaze.cynblockex.ModItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;

public class BlockEXModelProvider extends FabricModelProvider {
    public BlockEXModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        ModBlock.names().forEach(name -> {
            var block = ModBlock.get(name);
            blockModelGenerators.createTrivialCube(block);
            blockModelGenerators.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
        });
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ModItem.names().forEach(name -> itemModelGenerators.generateFlatItem(ModItem.get(name), ModelTemplates.FLAT_ITEM));
    }
}
