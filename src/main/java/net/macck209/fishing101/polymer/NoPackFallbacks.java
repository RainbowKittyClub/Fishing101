package net.macck209.fishing101.polymer;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.equipment.trim.ArmorTrim;

import static net.macck209.fishing101.Fishing101Initializer.MOD_ID;

// Clients without the resource pack have no textures for this mod's equipment asset or trim
// patterns, so worn armour would render invisible or as the missing texture.
public final class NoPackFallbacks {
    private NoPackFallbacks() {
    }

    public static void register() {
        // Vanilla armour carrying one of our trims must go through Polymer's conversion too.
        PolymerItemUtils.CONTEXT_ITEM_CHECK.register((stack, context) ->
                lacksPack(context) && isOwnTrim(stack.get(DataComponents.TRIM)));

        PolymerItemUtils.ITEM_MODIFICATION_EVENT.register((original, client, context) -> {
            if (!lacksPack(context)) {
                return client;
            }
            if (isOwnTrim(client.get(DataComponents.TRIM))) {
                client.remove(DataComponents.TRIM);
            }
            Equippable equippable = client.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().map(NoPackFallbacks::isOwn).orElse(false)) {
                // Fall back to the client item's own look, e.g. iron boots for jelly boots.
                client.set(DataComponents.EQUIPPABLE, client.getItem().components().get(DataComponents.EQUIPPABLE));
            }
            return client;
        });
    }

    private static boolean lacksPack(PacketContext context) {
        return context.get(PacketContext.CONNECTION) != null && !PolymerResourcePackUtils.hasMainPack(context);
    }

    private static boolean isOwnTrim(ArmorTrim trim) {
        return trim != null && trim.pattern().unwrapKey().map(NoPackFallbacks::isOwn).orElse(false);
    }

    private static boolean isOwn(ResourceKey<?> key) {
        return key.identifier().getNamespace().equals(MOD_ID);
    }
}
