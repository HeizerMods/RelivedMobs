package relivedmobs.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MagmaCrawlerEntity extends MobEntity implements IAnimatable{

	AnimationFactory factory = new AnimationFactory(this);
	
	public MagmaCrawlerEntity(EntityType<? extends MobEntity> mob, World world) {
		super(mob, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.isInWater()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magma_crawler.water", true));
	    } else {
	    	return PlayState.STOP;
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<MagmaCrawlerEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}
}