/*
 * Copyright (c) 2019-2025 Team Galacticraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.mod.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.galacticraft.mod.client.render.entity.model.GCEntityModelLayer;
import dev.galacticraft.mod.content.block.entity.decoration.FlagBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class FlagBlockEntityRenderer implements BlockEntityRenderer<FlagBlockEntity> {
    protected final ModelPart flag;

    public FlagBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        ModelPart root = ctx.bakeLayer(GCEntityModelLayer.FLAG);
        this.flag = root.getChild("flag");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition flag = root.addOrReplaceChild("flag",
                CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 20, 40, 1),
                PartPose.rotation(0, 0, (float)Math.toRadians(90))
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void render(FlagBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        matrices.pushPose();
        matrices.translate(0.4375, 2.041666667, 0.479166667);
        matrices.scale((float) 2 /3, (float) 2 /3, (float) 2 /3);

        this.flag.setRotation((float) Math.toDegrees(entity.getFacingRadians()), 0, this.flag.zRot);
        renderPatterns(
                matrices, vertexConsumers, light, overlay, this.flag, ModelBakery.BANNER_BASE, entity.getBaseColor(), entity.getPatterns()
        );
        matrices.popPose();
    }

    public static void renderPatterns(
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
            int light,
            int overlay,
            ModelPart canvas,
            Material baseSprite,
            DyeColor color,
            BannerPatternLayers pattern
    ) {
        canvas.render(matrices, baseSprite.buffer(vertexConsumers, RenderType::entitySolid, false), light, overlay);
        renderPatternLayer(matrices, vertexConsumers, light, overlay, canvas, Sheets.BANNER_BASE, color);

        for (int i = 0; i < pattern.layers().size(); i++) {
            BannerPatternLayers.Layer layer = pattern.layers().get(i);
            Material material = Sheets.getBannerMaterial(layer.pattern());
            renderPatternLayer(matrices, vertexConsumers, light, overlay, canvas, material, layer.color());
        }
    }

    private static void renderPatternLayer(
            PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, ModelPart canvas, Material textureId, DyeColor color
    ) {
        int i = color.getTextureDiffuseColor();
        canvas.render(matrices, textureId.buffer(vertexConsumers, RenderType::entityNoOutline), light, overlay, i);
    }
}
