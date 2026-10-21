package net.macck209.fishing101.items;

import eu.pb4.sgui.api.elements.BookElementBuilder;
import eu.pb4.sgui.api.gui.BookGui;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.macck209.fishing101.items.polymer.PolymerAutoItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class PolymerMealBookItem extends Item implements PolymerAutoItem {
    private static ItemStack book;

    public PolymerMealBookItem(Properties settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.WRITTEN_BOOK;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> tooltip, TooltipFlag type) {
        tooltip.accept(Component.translatable("item.fishing101.meal_book_subtitle").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (user instanceof ServerPlayer player) {
            new IndexGui(player, hand).open();
            user.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.SUCCESS;
    }

    // Built on first use: item components are not bound yet during onInitialize.
    private static ItemStack book() {
        if (book == null) {
            book = build();
        }
        return book;
    }

    private static ItemStack build(){
        var builder = new BookElementBuilder();

        builder.addPage(
                Component.translatable("books.fishing101.meal_book.intro.title").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.intro.text")
        );
        builder.addPage(
                Component.translatable("item.minecraft.bone_meal").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.bone_meal.text")
        );
        builder.addPage(
                Component.translatable("item.minecraft.slime_ball").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.fishing101.thick_goo")),
                Component.literal( "- ").append(Component.translatable("item.minecraft.green_dye"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.dough").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.wheat")),
                Component.literal( "- ").append(Component.translatable("item.minecraft.egg"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.noodles").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.noodles.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.rice").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.obtaining").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.rice.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.tentacle").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.obtaining").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.tentacle.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.cooked_tentacle").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.cooked_tentacle.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.chicken_broth").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.bowl")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.noodles")),
                Component.literal( "- ").append(Component.translatable("books.fishing101.meal_book.chickens.text"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.purple_pasta").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.bowl")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.noodles")),
                Component.literal( "- ").append(Component.translatable("item.minecraft.cooked_porkchop")),
                Component.literal( "- ").append(Component.translatable("item.minecraft.chorus_fruit"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.spaghetti_frutti_di_mare").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.bowl")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.noodles")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.cooked_tentacle")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.smoked_ordinary_shrimp"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.starfish_shashlik").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.stick")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.starfish"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.end_starfish_shashlik").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.minecraft.stick")),
                Component.literal( "- ").append(Component.translatable("item.fishing101.end_starfish"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.sushi").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.literal( "- ").append(Component.translatable("item.fishing101.rice")),
                Component.literal( "- ").append(Component.translatable("block.minecraft.kelp")),
                Component.literal( "- ").append(Component.translatable("item.minecraft.salmon"))
        );
        builder.addPage(
                Component.translatable("item.fishing101.thick_goo").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.thick_goo.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.charged_thunderfin").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.recipe").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.charged_thunderfin.text")
        );
        builder.addPage(
                Component.translatable("item.fishing101.crab_claw").withStyle(ChatFormatting.BOLD,ChatFormatting.GOLD),
                Component.translatable("books.fishing101.meal_book.obtaining").withStyle(ChatFormatting.UNDERLINE),
                Component.empty(),
                Component.translatable("books.fishing101.meal_book.crab_claw.text")
        );

        return builder.asStack();
    }

    public static final class IndexGui extends BookGui {
        private final ItemStack stack;
        private final InteractionHand hand;

        public IndexGui(ServerPlayer player, InteractionHand hand) {
            super(player, book());
            this.stack = player.getItemInHand(hand);
            this.hand = hand;
            this.setPage(Math.min(stack.getOrDefault(Fishing101Components.BOOK_PAGE, 0),
                    book().get(DataComponents.WRITTEN_BOOK_CONTENT).getPages(false).size()));
        }

        @Override
        public void onTakeBookButton() {
            this.close();
        }

        @Override
        public void setPage(int page) {
            super.setPage(page);
            this.player.connection.send(new ClientboundSoundPacket(
                    BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BOOK_PAGE_TURN), SoundSource.BLOCKS,
                    this.player.getX(), this.player.getY(), this.player.getZ(), 1f, 1, this.player.getRandom().nextLong()));

            if (this.book == book() && this.stack == this.player.getItemInHand(hand)) {
                this.stack.set(Fishing101Components.BOOK_PAGE, page);
            }
        }
    }
}
