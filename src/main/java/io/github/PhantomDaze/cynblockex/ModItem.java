package io.github.PhantomDaze.cynblockex;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


public class ModItem {
    public static Item register(String name, @NotNull Function<Item.Properties, Item> itemFactory, Item.@NotNull Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("cynblockex", name));
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
    public static final Item CYN = register("cyn", Item::new, new Item.Properties());
    public static final Item UNKNOWN_ITEM = register("unknown_item", Item::new, new Item.Properties());
    public static final Item I1 = register("i1", Item::new, new Item.Properties());
    public static final Item I2 = register("i2", Item::new, new Item.Properties());
    public static void initialize() {}
}
