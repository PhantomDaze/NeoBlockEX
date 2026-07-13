package io.github.PhantomDaze.cynblockex;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModGroup {
    public static final Map<BlockType, ResourceKey<CreativeModeTab>> BLOCK_TABS = new EnumMap<>(BlockType.class);
    public static final ResourceKey<CreativeModeTab> ITEMS_TAB_KEY = key("items");
    public static final ResourceKey<CreativeModeTab> VANILLA_TAB_KEY = key("vanilla");

    private static final List<Block> VANILLA_BLOCKS = List.of(
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
        for (BlockType type : BlockType.values()) {
            List<Block> blocks = ModBlock.allOfType(type);
            if (blocks.isEmpty()) {
                continue;
            }
            ResourceKey<CreativeModeTab> tabKey = key(type.tabId());
            BLOCK_TABS.put(type, tabKey);
            Block icon = blocks.getFirst();
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabKey, FabricCreativeModeTab.builder()
                    .title(Component.translatable(type.tabTranslationKey()))
                    .icon(() -> new ItemStack(icon))
                    .displayItems((params, output) -> blocks.forEach(output::accept))
                    .build());
        }

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEMS_TAB_KEY, FabricCreativeModeTab.builder()
                .title(Component.translatable("itemGroup.cynblockex.items"))
                .icon(() -> new ItemStack(Objects.requireNonNull(ModItem.get("cyn"), "Missing item: cyn")))
                .displayItems((params, output) -> ModItem.all().forEach(output::accept))
                .build());

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, VANILLA_TAB_KEY, FabricCreativeModeTab.builder()
                .title(Component.translatable("itemGroup.cynblockex.vanilla"))
                .icon(() -> new ItemStack(Blocks.GLASS))
                .displayItems((params, output) -> VANILLA_BLOCKS.forEach(output::accept))
                .build());
    }

    private static ResourceKey<CreativeModeTab> key(String path) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(BlockEXMod.MOD_ID, path));
    }
}
