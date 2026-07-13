package io.github.PhantomDaze.cynblockex.datagen;

import io.github.PhantomDaze.cynblockex.ModBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BlockEXLootTableProvider extends FabricBlockLootSubProvider {
    public BlockEXLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        for (String name : ModBlock.names()) {
            Block block = ModBlock.get(name);
            switch (ModBlock.getType(name)) {
                case SLAB -> add(block, createSlabItemTable(block));
                case DOOR -> add(block, createDoorTable(block));
                default -> dropSelf(block);
            }
        }
    }
}
