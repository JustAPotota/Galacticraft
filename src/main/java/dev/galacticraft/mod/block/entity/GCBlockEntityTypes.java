/*
 * Copyright (c) 2019-2022 Team Galacticraft
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

package dev.galacticraft.mod.block.entity;

import dev.galacticraft.mod.Constant;
import dev.galacticraft.mod.api.block.entity.WireBlockEntity;
import dev.galacticraft.mod.block.GCBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * @author <a href="https://github.com/TeamGalacticraft">TeamGalacticraft</a>
 */
public class GCBlockEntityTypes {
    // POWER GENERATION
    public static final BlockEntityType<CoalGeneratorBlockEntity> COAL_GENERATOR = FabricBlockEntityTypeBuilder.create(CoalGeneratorBlockEntity::new, GCBlocks.COAL_GENERATOR).build();
    public static final BlockEntityType<BasicSolarPanelBlockEntity> BASIC_SOLAR_PANEL = FabricBlockEntityTypeBuilder.create(BasicSolarPanelBlockEntity::new, GCBlocks.BASIC_SOLAR_PANEL).build();
    public static final BlockEntityType<AdvancedSolarPanelBlockEntity> ADVANCED_SOLAR_PANEL = FabricBlockEntityTypeBuilder.create(AdvancedSolarPanelBlockEntity::new, GCBlocks.ADVANCED_SOLAR_PANEL).build();

    // WIRES, PIPES, WALKWAYS
    public static final BlockEntityType<WireBlockEntity> WIRE_T1 = FabricBlockEntityTypeBuilder.create((pos, state) -> WireBlockEntity.createT1(GCBlockEntityTypes.WIRE_T1, pos, state), GCBlocks.ALUMINUM_WIRE, GCBlocks.SEALABLE_ALUMINUM_WIRE, GCBlocks.WIRE_WALKWAY).build();
    public static final BlockEntityType<WireBlockEntity> WIRE_T2 = FabricBlockEntityTypeBuilder.create((pos, state) -> WireBlockEntity.createT2(GCBlockEntityTypes.WIRE_T2, pos, state)).build();
    public static final BlockEntityType<GlassFluidPipeBlockEntity> GLASS_FLUID_PIPE = FabricBlockEntityTypeBuilder.create(GlassFluidPipeBlockEntity::new, GCBlocks.GLASS_FLUID_PIPE).build();
    public static final BlockEntityType<WalkwayBlockEntity> WALKWAY = FabricBlockEntityTypeBuilder.create(WalkwayBlockEntity::new, GCBlocks.WALKWAY).build();
    public static final BlockEntityType<WireWalkwayBlockEntity> WIRE_WALKWAY = FabricBlockEntityTypeBuilder.create(WireWalkwayBlockEntity::new, GCBlocks.WIRE_WALKWAY).build();
    public static final BlockEntityType<PipeWalkwayBlockEntity> PIPE_WALKWAY = FabricBlockEntityTypeBuilder.create(PipeWalkwayBlockEntity::new, GCBlocks.PIPE_WALKWAY).build();

    // MACHINES
    public static final BlockEntityType<CircuitFabricatorBlockEntity> CIRCUIT_FABRICATOR = FabricBlockEntityTypeBuilder.create(CircuitFabricatorBlockEntity::new, GCBlocks.CIRCUIT_FABRICATOR).build();
    public static final BlockEntityType<CompressorBlockEntity> COMPRESSOR = FabricBlockEntityTypeBuilder.create(CompressorBlockEntity::new, GCBlocks.COMPRESSOR).build();
    public static final BlockEntityType<ElectricCompressorBlockEntity> ELECTRIC_COMPRESSOR = FabricBlockEntityTypeBuilder.create(ElectricCompressorBlockEntity::new, GCBlocks.ELECTRIC_COMPRESSOR).build();
    public static final BlockEntityType<ElectricFurnaceBlockEntity> ELECTRIC_FURNACE = FabricBlockEntityTypeBuilder.create(ElectricFurnaceBlockEntity::new, GCBlocks.ELECTRIC_FURNACE).build();
    public static final BlockEntityType<ElectricArcFurnaceBlockEntity> ELECTRIC_ARC_FURNACE = FabricBlockEntityTypeBuilder.create(ElectricArcFurnaceBlockEntity::new, GCBlocks.ELECTRIC_ARC_FURNACE).build();
    public static final BlockEntityType<RefineryBlockEntity> REFINERY = FabricBlockEntityTypeBuilder.create(RefineryBlockEntity::new, GCBlocks.REFINERY).build();

    // OXYGEN MACHINES
    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR = FabricBlockEntityTypeBuilder.create(OxygenCollectorBlockEntity::new, GCBlocks.OXYGEN_COLLECTOR).build();
    public static final BlockEntityType<OxygenCompressorBlockEntity> OXYGEN_COMPRESSOR = FabricBlockEntityTypeBuilder.create(OxygenCompressorBlockEntity::new, GCBlocks.OXYGEN_COMPRESSOR).build();
    public static final BlockEntityType<OxygenDecompressorBlockEntity> OXYGEN_DECOMPRESSOR = FabricBlockEntityTypeBuilder.create(OxygenDecompressorBlockEntity::new, GCBlocks.OXYGEN_DECOMPRESSOR).build();
    public static final BlockEntityType<OxygenSealerBlockEntity> OXYGEN_SEALER = FabricBlockEntityTypeBuilder.create(OxygenSealerBlockEntity::new, GCBlocks.OXYGEN_SEALER).build();
    public static final BlockEntityType<BubbleDistributorBlockEntity> OXYGEN_BUBBLE_DISTRIBUTOR = FabricBlockEntityTypeBuilder.create(BubbleDistributorBlockEntity::new, GCBlocks.BUBBLE_DISTRIBUTOR).build();

    // RESOURCE STORAGE
    public static final BlockEntityType<EnergyStorageModuleBlockEntity> ENERGY_STORAGE_MODULE = FabricBlockEntityTypeBuilder.create(EnergyStorageModuleBlockEntity::new, GCBlocks.ENERGY_STORAGE_MODULE).build();
    public static final BlockEntityType<OxygenStorageModuleBlockEntity> OXYGEN_STORAGE_MODULE = FabricBlockEntityTypeBuilder.create(OxygenStorageModuleBlockEntity::new, GCBlocks.OXYGEN_STORAGE_MODULE).build();

    // ROCKETS

    // MISC
    public static final BlockEntityType<SolarPanelPartBlockEntity> SOLAR_PANEL_PART = FabricBlockEntityTypeBuilder.create(SolarPanelPartBlockEntity::new, GCBlocks.SOLAR_PANEL_PART).build();
    public static final BlockEntityType<AirlockControllerBlockEntity> AIRLOCK_CONTROLLER = FabricBlockEntityTypeBuilder.create(AirlockControllerBlockEntity::new, GCBlocks.AIR_LOCK_CONTROLLER).build();

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.COAL_GENERATOR), COAL_GENERATOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.BASIC_SOLAR_PANEL), BASIC_SOLAR_PANEL);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ADVANCED_SOLAR_PANEL), ADVANCED_SOLAR_PANEL);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ALUMINUM_WIRE), WIRE_T1);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.HEAVY_ALUMINUM_WIRE), WIRE_T2);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.GLASS_FLUID_PIPE), GLASS_FLUID_PIPE);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.WALKWAY), WALKWAY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.WIRE_WALKWAY), WIRE_WALKWAY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.PIPE_WALKWAY), PIPE_WALKWAY);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.CIRCUIT_FABRICATOR), CIRCUIT_FABRICATOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.COMPRESSOR), COMPRESSOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ELECTRIC_COMPRESSOR), ELECTRIC_COMPRESSOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ELECTRIC_FURNACE), ELECTRIC_FURNACE);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ELECTRIC_ARC_FURNACE), ELECTRIC_ARC_FURNACE);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.REFINERY), REFINERY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.ENERGY_STORAGE_MODULE), ENERGY_STORAGE_MODULE);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_COLLECTOR), OXYGEN_COLLECTOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_COMPRESSOR), OXYGEN_COMPRESSOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_DECOMPRESSOR), OXYGEN_DECOMPRESSOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_SEALER), OXYGEN_SEALER);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_BUBBLE_DISTRIBUTOR), OXYGEN_BUBBLE_DISTRIBUTOR);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.OXYGEN_STORAGE_MODULE), OXYGEN_STORAGE_MODULE);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Block.SOLAR_PANEL_PART), SOLAR_PANEL_PART);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Constant.id(Constant.Block.AIR_LOCK_CONTROLLER), AIRLOCK_CONTROLLER);
    }

    private static void register(String id, BlockEntityType<?> type) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, id), type);
    }
}