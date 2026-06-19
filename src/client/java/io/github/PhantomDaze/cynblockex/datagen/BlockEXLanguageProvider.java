package io.github.PhantomDaze.cynblockex.datagen;

import io.github.PhantomDaze.cynblockex.ModBlock;
import io.github.PhantomDaze.cynblockex.ModGroup;
import io.github.PhantomDaze.cynblockex.ModItem;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class BlockEXLanguageProvider extends FabricLanguageProvider {
    private final boolean chinese;

    public BlockEXLanguageProvider(FabricPackOutput output, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup, boolean chinese) {
        super(output, languageCode, registryLookup);
        this.chinese = chinese;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModGroup.CUSTOM_ITEM_GROUP_KEY, "C.Y.N.");
        translationBuilder.add("message.cynblockex.update_available", chinese
                ? "服务端BlockEX Mod不为最新，请通知服务器管理员及时更新"
                : "Server's BlockEX Mod is not lastest, remind server manager upgrade it, please");

        ModBlock.names().forEach(name -> {
            var block = ModBlock.get(name);
            translationBuilder.add(block.asItem(), translateBlock(name));
        });
        ModItem.names().forEach(name -> {
            var item = ModItem.get(name);
            translationBuilder.add(item, translateItem(name));
        });
    }

    private String translateBlock(String name) {
        if (chinese) {
            return switch (name) {
                case "red_obsidian" -> "红色黑曜石";
                case "glowing_obsidian" -> "发光黑曜石";
                case "old_glowing_obsidian" -> "旧发光黑曜石";
                case "redux_glowing_obsidian" -> "Redux发光黑曜石";
                case "reactor_0" -> "反应核0";
                case "reactor_1" -> "反应核1";
                case "reactor_2" -> "反应核2";
                default -> name;
            };
        }
        return switch (name) {
            case "red_obsidian" -> "Red Obsidian";
            case "glowing_obsidian" -> "Glowing Obsidian";
            case "old_glowing_obsidian" -> "Old Glowing Obsidian";
            case "redux_glowing_obsidian" -> "Redux Glowing Obsidian";
            case "reactor_0" -> "Reactor 0";
            case "reactor_1" -> "Reactor 1";
            case "reactor_2" -> "Reactor 2";
            default -> name;
        };
    }

    private String translateItem(String name) {
        return chinese ? switch (name) {
            case "cyn" -> "C.Y.N.";
            case "unknown_item" -> "不为人知的物品";
            default -> name;
        } : switch (name) {
            case "cyn" -> "C.Y.N.";
            case "unknown_item" -> "Unknown Item";
            default -> name;
        };
    }
}
