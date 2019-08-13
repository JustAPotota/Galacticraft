package com.hrznstudio.galacticraft.util;

import com.hrznstudio.galacticraft.api.wire.WireConnectionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public interface WireConnectable {

    default WireConnectionType canWireConnect(IWorld world, Direction opposite, BlockPos connectionSourcePos, BlockPos connectionTargetPos) {
        return WireConnectionType.NONE;
    }

}