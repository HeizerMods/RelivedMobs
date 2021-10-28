package relivedmobs.common.util;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class BlockUtil {
	public static boolean isAir(Entity entity, BlockPos pos) {
		return entity.level.getBlockState(pos).getMaterial() == Material.AIR;
	}
}
