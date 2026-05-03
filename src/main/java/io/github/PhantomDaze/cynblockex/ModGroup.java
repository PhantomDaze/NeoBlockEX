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


public class ModGroup {
    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath("cynblockex", "item_group"));
    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItem.CYN))
            .title(Component.translatable("itemGroup.cynblockex"))
            .build();
    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        CreativeModeTabEvents.modifyOutputEvent(CUSTOM_ITEM_GROUP_KEY).register(output -> {
            output.accept(Blocks.GLASS);
            output.accept(Blocks.TINTED_GLASS);
            output.accept(Blocks.RED_STAINED_GLASS);
            output.accept(Blocks.PINK_STAINED_GLASS);
            output.accept(Blocks.BLUE_STAINED_GLASS);
            output.accept(Blocks.CYAN_STAINED_GLASS);
            output.accept(Blocks.GRAY_STAINED_GLASS);
            output.accept(Blocks.LIME_STAINED_GLASS);
            output.accept(Blocks.GREEN_STAINED_GLASS);
            output.accept(Blocks.BLACK_STAINED_GLASS);
            output.accept(Blocks.BROWN_STAINED_GLASS);
            output.accept(Blocks.WHITE_STAINED_GLASS);
            output.accept(Blocks.ORANGE_STAINED_GLASS);
            output.accept(Blocks.PURPLE_STAINED_GLASS);
            output.accept(Blocks.YELLOW_STAINED_GLASS);
            output.accept(Blocks.MAGENTA_STAINED_GLASS);
            output.accept(Blocks.LIGHT_BLUE_STAINED_GLASS);
            output.accept(ModBlock.REDUX_GLOWING_OBSIDIAN);
            output.accept(ModBlock.OLD_GLOWING_OBSIDIAN);
            output.accept(ModBlock.GLOWING_OBSIDIAN);
            output.accept(ModBlock.RED_OBSIDIAN);
            output.accept(ModBlock.REACTOR_0);
            output.accept(ModBlock.REACTOR_1);
            output.accept(ModBlock.REACTOR_2);
            output.accept(ModItem.UNKNOWN_ITEM);
            });
    }
}
