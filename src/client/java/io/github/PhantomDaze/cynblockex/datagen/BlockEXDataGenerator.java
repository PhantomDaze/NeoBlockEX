package io.github.PhantomDaze.cynblockex.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BlockEXDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BlockEXModelProvider::new);
        pack.addProvider(BlockEXLootTableProvider::new);
        pack.addProvider((output, registriesFuture) -> new BlockEXLanguageProvider(output, "en_us", registriesFuture, false));
        pack.addProvider((output, registriesFuture) -> new BlockEXLanguageProvider(output, "zh_cn", registriesFuture, true));
    }
}
