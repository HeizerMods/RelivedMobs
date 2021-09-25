package relivedmobs.common.entity.passive;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FallowDeerEntity extends AnimalEntity implements IAnimatable{

	protected FallowDeerEntity(EntityType<? extends AnimalEntity> mob, World world) {
		super(mob, world);
	}

	@Override
	public void registerControllers(AnimationData data) {
		
	}

	@Override
	public AnimationFactory getFactory() {
		return null;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}

}
