package relivedmobs.common.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import relivedmobs.common.util.MathUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SawFishEntity extends WaterMobEntity implements IAnimatable{
	
	AnimationFactory factory = new AnimationFactory(this);
	
	public SawFishEntity(EntityType<? extends SawFishEntity> mob, World world) {
		super(mob, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.saw_fish.water", true));
	    } else if(this.getTarget() != null){
	    	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.saw_fish.attack", true));
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<SawFishEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SawFishAttack());
		this.goalSelector.addGoal(0, new SawFishUniqueAttack());
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.3D, false));
		this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.2D, 210));
		this.goalSelector.addGoal(3, new FindWaterGoal(this));
	}
	
	public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if ( entity != null && !(entity instanceof PlayerEntity) ) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }
    
    public boolean tryAttack(Entity target) {
        boolean bl = target.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (bl) {
            this.doEnchantDamageEffects(this, target);
        }

        return bl;
    }
	
    private class SawFishUniqueAttack extends Goal {
    	private Integer thisTick = 0;
    	private Integer randomInterval = 0;
    	private Boolean canUse = false;
    	
    	public SawFishUniqueAttack() {
    		randomInterval = SawFishEntity.this.getRandom().ints(MathUtil.inTicks(1), MathUtil.inTicks(3)).findFirst().getAsInt();
		}
    	
    	@Override
    	public void tick() {
    		super.tick();
    		if(thisTick % 20 == 0) {
    			thisTick = 0;
    		}
    		thisTick++;
    		randomInterval--;
    	}
    	
		@Override
		public boolean canUse() {
			if(lastHurtByPlayer != null 
					&& (Math.abs(position().x() - getTarget().position().x()) >= 4 
					&&  Math.abs(position().x() - getTarget().position().x()) <= 8)
					|| (Math.abs(position().z() - getTarget().position().z()) >= 4
					&&  Math.abs(position().z() - getTarget().position().z()) >= 8) ) {
				return true;
			}
			return false;
		}
		
		@Override
		public boolean canContinueToUse() {
			if(lastHurtByPlayer.isInWaterOrBubble() && canUse) {
				return true;
			}
			return false;
		}
		
		@Override
		public void start() {
			super.start();
			setSpeed(getSpeed() + 0.333F + (float)Math.random() * getSpeed());
			setTarget(lastHurtByPlayer);
			randomInterval = SawFishEntity.this.getRandom().ints(MathUtil.inTicks(1), MathUtil.inTicks(3)).findFirst().getAsInt();
		}
		
		@Override
		public void stop() {
			setSpeed(1.2F);
			setTarget(null);
		}
    }
    
	private class SawFishAttack extends Goal {
		public SawFishAttack() {
		}
		
		@Override
		public void start() {
			setTarget(lastHurtByPlayer);
		}
		
		@Override 
		public void stop() {
			SawFishEntity.this.setTarget(null);
		}
		
		@Override
		public boolean canUse() {
			return (lastHurtByPlayer != null)?true:false;
		}
		
		@Override
		public boolean canContinueToUse() {
			if(lastHurtByPlayer.isInWaterOrBubble()) {
				return true;
			}
			return false;
		}
	}
}