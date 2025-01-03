package dev.galacticraft.mod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.galacticraft.mod.content.item.GCItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RocketRecipe implements Recipe<RecipeInput> {
    private final String group;
    private final ItemStack result;

    private final Ingredient cone;
    private final Ingredient engine;
    private final Ingredient body;
    private final Ingredient fins;
    private final Ingredient boosters;

    private final int bodyHeight;

    public RocketRecipe(String group, ItemStack result, int bodyHeight, Ingredient body, Ingredient cone, Ingredient engine, Ingredient fins, Ingredient boosters) {
        this.group = group;
        this.result = result;

        this.bodyHeight = bodyHeight;
        this.body = body;
        this.cone = cone;
        this.engine = engine;
        this.fins = fins;
        this.boosters = boosters;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(this.cone);

        for (int i = 0; i < this.bodyHeight * 2; i++)
            ingredients.add(this.body);

        if (!this.boosters.isEmpty()) {
            ingredients.add(this.boosters);
            ingredients.add(this.boosters);
        }

        for (int i = 0; i < 4; i++)
            ingredients.add(this.fins);

        ingredients.add(this.engine);

        return ingredients;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(GCItems.ROCKET_WORKBENCH);
    }

    @Override
    public boolean matches(RecipeInput input, Level level) {
        NonNullList<Ingredient> ingredients = this.getIngredients();

        if (ingredients.size() != input.size())
            return false;

        for (int i = 0; i < ingredients.size(); i++) {
            if (!ingredients.get(i).test(input.getItem(i)))
                return false;
        }

        return true;
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput input, HolderLookup.Provider lookup) {
        return this.getResultItem(lookup).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 0;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registriesLookup) {
        return this.result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return GCRecipes.ROCKET_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return GCRecipes.ROCKET_TYPE;
    }

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    public static class Serializer implements RecipeSerializer<RocketRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<RocketRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result),
                Codec.INT.fieldOf("body_height").forGetter(r -> r.bodyHeight),
                Ingredient.CODEC.fieldOf("body").forGetter(r -> r.body),
                Ingredient.CODEC.fieldOf("cone").forGetter(r -> r.cone),
                Ingredient.CODEC.fieldOf("engine").forGetter(r -> r.engine),
                Ingredient.CODEC.fieldOf("fins").forGetter(r -> r.fins),
                Ingredient.CODEC.optionalFieldOf("boosters", Ingredient.EMPTY).forGetter(recipe -> recipe.boosters)
        ).apply(instance, RocketRecipe::new));

        // RocketRecipe has too many fields for StreamCodec.composite oops
        public static final StreamCodec<RegistryFriendlyByteBuf, RocketRecipe> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public @NotNull RocketRecipe decode(RegistryFriendlyByteBuf buf) {
                return new RocketRecipe(
                        ByteBufCodecs.STRING_UTF8.decode(buf),
                        ItemStack.STREAM_CODEC.decode(buf),
                        ByteBufCodecs.INT.decode(buf),
                        Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                        Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                        Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                        Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                        Ingredient.CONTENTS_STREAM_CODEC.decode(buf)
                );
            }

            @Override
            public void encode(RegistryFriendlyByteBuf buf, RocketRecipe r) {
                ByteBufCodecs.STRING_UTF8.encode(buf, r.group);
                ItemStack.STREAM_CODEC.encode(buf, r.result);
                ByteBufCodecs.INT.encode(buf, r.bodyHeight);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, r.body);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, r.cone);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, r.engine);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, r.fins);
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, r.boosters);
            }
        };

        @Override
        public @NotNull MapCodec<RocketRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, RocketRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}