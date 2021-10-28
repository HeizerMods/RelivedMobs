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

public class MarcoonEntity extends MobEntity implements IAnimatable{

	AnimationFactory factory = new AnimationFactory(this);
	
	public MarcoonEntity(EntityType<? extends MobEntity> mob, World world) {
		super(mob, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.marcoon.walk", true));
	    } else if(this.getTarget() != null){
	    	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.marcoon.shot", true));
	    } else {
	    	return PlayState.STOP;
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<MarcoonEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}
}