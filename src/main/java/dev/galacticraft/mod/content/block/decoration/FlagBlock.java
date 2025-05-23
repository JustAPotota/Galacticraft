package dev.galacticraft.mod.content.block.decoration;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.galacticraft.mod.content.block.entity.decoration.FlagBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class FlagBlock extends AbstractBannerBlock {
    public static final MapCodec<FlagBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            propertiesCodec(), DyeColor.CODEC.fieldOf("color").forGetter(FlagBlock::getColor)
    ).apply(instance, FlagBlock::new));

    protected static final VoxelShape SHAPE = Shapes.or(
            Shapes.box(6, 0, 6, 10, 2, 10),
            Shapes.box(7, 2, 7, 9, 16, 9)
    );

    public FlagBlock(Properties properties, DyeColor color) {
        super(color, properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FlagBlockEntity(pos, state, this.getColor());
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NotNull MapCodec<FlagBlock> codec() {
        return CODEC;
    }
}
