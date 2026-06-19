package io.github.PhantomDaze.cynblockex;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Objects;


public class ModGroup {
    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath("cynblockex", "item_group"));
    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(Objects.requireNonNull(ModItem.get("cyn"), "Missing item: cyn")))
            .title(Component.translatable("itemGroup.cynblockex"))
            .build();

    private static final List<net.minecraft.world.level.block.Block> VANILLA_BLOCKS = List.of(
            Blocks.GLASS,
            Blocks.TINTED_GLASS,
            Blocks.RED_STAINED_GLASS,
            Blocks.PINK_STAINED_GLASS,
            Blocks.BLUE_STAINED_GLASS,
            Blocks.CYAN_STAINED_GLASS,
            Blocks.GRAY_STAINED_GLASS,
            Blocks.LIME_STAINED_GLASS,
            Blocks.GREEN_STAINED_GLASS,
            Blocks.BLACK_STAINED_GLASS,
            Blocks.BROWN_STAINED_GLASS,
            Blocks.WHITE_STAINED_GLASS,
            Blocks.ORANGE_STAINED_GLASS,
            Blocks.PURPLE_STAINED_GLASS,
            Blocks.YELLOW_STAINED_GLASS,
            Blocks.MAGENTA_STAINED_GLASS,
            Blocks.LIGHT_BLUE_STAINED_GLASS
    );

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        CreativeModeTabEvents.modifyOutputEvent(CUSTOM_ITEM_GROUP_KEY).register(output -> {
            VANILLA_BLOCKS.forEach(block -> output.accept(block));
            ModBlock.all().forEach(block -> output.accept(block));
            ModItem.all().forEach(item -> output.accept(item));
        });
    }
}
