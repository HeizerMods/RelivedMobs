package relivedmobs.common.entity.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EnderDartEntity extends AbstractArrowEntity implements IAnimatable{

	AnimationFactory factory = new AnimationFactory(this);
	
	public EnderDartEntity(EntityType<? extends EnderDartEntity> mob, World world) {
		super(mob, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ender_dart.fly", true));
	    } else {
	    	return PlayState.STOP;
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<EnderDartEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}

	@Override
	protected ItemStack getPickupItem() {
		return null;
	}
}