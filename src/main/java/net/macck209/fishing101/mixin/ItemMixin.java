package net.macck209.fishing101.mixin;

import net.macck209.fishing101.items.polymer.PolymerFishItem;
import net.macck209.fishing101.registries.ItemRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract Item asItem();

    @Inject(method = "finishUsingItem", at = @At("RETURN"))
    private void modifyFinishUsing(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (this.asItem() == Items.COD || this.asItem() == Items.SALMON || this.asItem() == Items.TROPICAL_FISH || this.asItem() == Items.PUFFERFISH
                || this.asItem() == Items.COOKED_COD || this.asItem() == Items.COOKED_SALMON) {
            if (world instanceof ServerLevel) {
                PolymerFishItem.dropAtUser(user, new ItemStack(ItemRegistry.FISH_BONES));
            }
        }
    }
}
