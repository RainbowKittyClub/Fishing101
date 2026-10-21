package net.macck209.fishing101.loot;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.macck209.fishing101.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;

import static net.macck209.fishing101.Fishing101Initializer.id;

public final class Fishing101Loot {
    // Ordered before the default phase, so the dimension gate lands on vanilla's own fishing pool only.
    private static final Identifier VANILLA_GATE = id("vanilla_gate");

    private static final ResourceKey<LootTable> SQUID = vanilla("entities/squid");
    private static final ResourceKey<LootTable> GLOW_SQUID = vanilla("entities/glow_squid");
    private static final ResourceKey<LootTable> SHORT_GRASS = vanilla("blocks/short_grass");

    private Fishing101Loot() {
    }

    public static void register() {
        LootTableEvents.MODIFY.addPhaseOrdering(VANILLA_GATE, Event.DEFAULT_PHASE);
        LootTableEvents.MODIFY.register(VANILLA_GATE, (key, table, source, registries) -> {
            if (!source.isBuiltin() || !key.equals(BuiltInLootTables.FISHING)) {
                return;
            }
            var handled = registries.getOrThrow(ResourceKey.create(Registries.PREDICATE, id("fishing/handled_dimension")));
            table.modifyPools(pool -> pool.when(InvertedLootItemCondition.invert(handled)));
            table.pool(nested(registries, "gameplay/fishing"));
        });
        LootTableEvents.MODIFY.register((key, table, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }
            if (key.equals(SQUID) || key.equals(GLOW_SQUID)) {
                table.pool(nested(registries, "entities/tentacle"));
            } else if (key.equals(SHORT_GRASS)) {
                table.pool(nested(registries, "blocks/short_grass_rice"));
            } else if (key.equals(BuiltInLootTables.FARMER_GIFT)) {
                table.modifyPools(pool -> pool.add(LootItem.lootTableItem(ItemRegistry.MEAL_BOOK)));
            } else if (key.equals(BuiltInLootTables.FISHERMAN_GIFT)) {
                table.modifyPools(pool -> pool.add(LootItem.lootTableItem(ItemRegistry.FISH_BOOK)));
            }
        });
    }

    private static LootPool nested(HolderLookup.Provider registries, String path) {
        var table = registries.getOrThrow(ResourceKey.create(Registries.LOOT_TABLE, id(path)));
        return LootPool.lootPool().add(NestedLootTable.lootTableReference(table)).build();
    }

    private static ResourceKey<LootTable> vanilla(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace(path));
    }
}
