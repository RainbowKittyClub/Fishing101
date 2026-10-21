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

public class PolymerFishBookItem extends Item implements PolymerAutoItem {
    private static ItemStack book;

    public PolymerFishBookItem(Properties settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.WRITTEN_BOOK;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> tooltip, TooltipFlag type) {
        tooltip.accept(Component.translatable("item.fishing101.fish_book_subtitle").withStyle(ChatFormatting.GRAY));
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
                Component.translatable("books.fishing101.fish_book.date.intro"),
                Component.empty(),
                Component.translatable("books.fishing101.fish_book.intro")
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.carp"),
                Component.empty(),
                Component.translatable("books.fishing101.ordinary_carp").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.cypriniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.freshwater"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.carp"),
                Component.empty(),
                Component.translatable("books.fishing101.deformed_carp").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.cypriniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.freshwater"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.luminous_carp"),
                Component.empty(),
                Component.translatable("books.fishing101.luminous_carp").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.cypriniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.freshwater"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.nocturnal")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.luminous"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.luminous_carp"),
                Component.empty(),
                Component.translatable("books.fishing101.ordinary_catfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.siluriformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.every_water"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.muddy_catfish"),
                Component.empty(),
                Component.translatable("books.fishing101.muddy_catfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.siluriformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.swamp"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.tropical_catfish"),
                Component.empty(),
                Component.translatable("books.fishing101.tropical_catfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.siluriformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.tropical"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.divine_catfish"),
                Component.empty(),
                Component.translatable("item.fishing101.divine_catfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.siluriformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.every_water")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.ascension"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.mackerel"),
                Component.empty(),
                Component.translatable("books.fishing101.ordinary_mackerel").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.perciformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.mackerel"),
                Component.empty(),
                Component.translatable("books.fishing101.luminous_mackerel").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.perciformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.nocturnal")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.luminous"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.mangrove_jack"),
                Component.empty(),
                Component.translatable("books.fishing101.mangrove_jack").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.perciformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.mangrove"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.shrimp"),
                Component.empty(),
                Component.translatable("books.fishing101.ordinary_shrimp").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.decapoda"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.thermophilic"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.shrimp"),
                Component.empty(),
                Component.translatable("books.fishing101.luminous_shrimp").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.decapoda"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.thermophilic"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.nocturnal")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.luminous"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.jellyfish"),
                Component.empty(),
                Component.translatable("item.fishing101.jellyfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.semaeostomeae"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.allergy"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.jellyfish"),
                Component.empty(),
                Component.translatable("item.fishing101.divine_jellyfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.semaeostomeae"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.ascension"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.anglerfish"),
                Component.empty(),
                Component.translatable("books.fishing101.anglerfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.lophiiformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.darkness")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.nausea"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.anglerfish"),
                Component.empty(),
                Component.translatable("books.fishing101.eel").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.anguilliformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.darkness_and_pale_garden")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.allergy"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.koi"),
                Component.empty(),
                Component.translatable("item.fishing101.red_koi").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.cypriniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.cherry"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.koi"),
                Component.empty(),
                Component.translatable("item.fishing101.yellow_koi").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.cypriniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.cherry"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.thunderfin"),
                Component.empty(),
                Component.translatable("item.fishing101.thunderfin").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.fulmeniformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.electric")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.charging"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.starfish"),
                Component.empty(),
                Component.translatable("item.fishing101.starfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.paxillosida"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.saltwater"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.beach"))
                        .append(Component.translatable("books.fishing101.fish_book.distribution.thermophilic")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.shashlik"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.ice_cod"),
                Component.empty(),
                Component.translatable("item.fishing101.ice_cod").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.gadiformes"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.glaciers")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.cold"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.solarfish"),
                Component.empty(),
                Component.translatable("books.fishing101.solarfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.unknown"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.high")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.funny"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.pandafish"),
                Component.empty(),
                Component.translatable("books.fishing101.pandafish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.unknown"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.bamboo")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.funny"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.witchfish"),
                Component.empty(),
                Component.translatable("books.fishing101.witchfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.unknown"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.mushroom")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.funny"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.flowerfish"),
                Component.empty(),
                Component.translatable("books.fishing101.flowerfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.unknown"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.flower")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.funny"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.end_jellyfish"),
                Component.empty(),
                Component.translatable("item.fishing101.end_jellyfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.semaeostomeae"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.unknown_1")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.allergy"))
        );
        builder.addPage(
                Component.translatable("books.fishing101.fish_book.date.end_jellyfish"),
                Component.empty(),
                Component.translatable("item.fishing101.end_starfish").withStyle(ChatFormatting.BOLD),
                Component.translatable("books.fishing101.fish_book.order.paxillosida"),
                Component.translatable("books.fishing101.fish_book.distribution")
                        .append(Component.translatable("books.fishing101.fish_book.distribution.unknown_2")),
                Component.translatable("books.fishing101.fish_book.special")
                        .append(Component.translatable("books.fishing101.fish_book.special.inedible"))
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
