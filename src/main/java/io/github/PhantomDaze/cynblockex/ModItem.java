package io.github.PhantomDaze.cynblockex;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.List;


public class ModItem {
    public record ItemDefinition(String name) {}

    private static final List<ItemDefinition> DEFINITIONS = List.of(
            new ItemDefinition("cyn"),
            new ItemDefinition("unknown_item")
    );

    private static final ModRegistry<Item> ITEMS = new ModRegistry<>();

    static {
        registerAll();
    }

    private static void registerAll() {
        DEFINITIONS.forEach(definition -> ITEMS.put(definition.name(), register(definition.name())));
    }

    public static Item register(String name) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("cynblockex", name));
        Item item = new Item(new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static Item get(String name) {
        return ITEMS.get(name);
    }

    public static List<Item> all() {
        return ITEMS.all();
    }

    public static List<String> names() {
        return ITEMS.names();
    }

    public static void initialize() {
    }
}
