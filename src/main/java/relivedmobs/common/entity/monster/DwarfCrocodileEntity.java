package relivedmobs.common.entity.monster;

import java.util.EnumSet;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import relivedmobs.common.util.BlockUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DwarfCrocodileEntity extends WaterMobEntity implements IAnimatable{

	AnimationFactory factory = new AnimationFactory(this);
	
	public DwarfCrocodileEntity(EntityType<? extends WaterMobEntity> mob, World world) {
		super(mob, world);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.2F, true));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new WalkingOnBeach(0.7F));
        this.goalSelector.addGoal(2, new SwimToBeach(0.7F));
        
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<AbstractFishEntity>(this, AbstractFishEntity.class,false));
	}
	
	@Override
	protected void handleAirSupply(int p_209207_1_) {
		
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

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving() && !this.isInWater()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.west_african_crocodile.walk", true));
	    } else if(this.isInWater() && this.isInWater()){
	    	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.west_african_crocodile.swim", true));
	    } 
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<DwarfCrocodileEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}
	
	private class SwimToBeach extends RandomSwimmingGoal{
		
		public SwimToBeach(double speed) {
			super(DwarfCrocodileEntity.this, speed, 350);
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override 
		public boolean canUse() {
			if(mob.isInWaterOrBubble() && mob.getRandom().nextInt(20) == 0 && !this.mob.isVehicle()) {
				 Vector3d vector3d = this.getPosition();
		         if (vector3d == null) {
		            return false;
		         } else {
		            this.wantedX = vector3d.x;
		            this.wantedY = vector3d.y;
		            this.wantedZ = vector3d.z;
		            this.forceTrigger = false;
		            return true;
		         }
			}
			return false;
		}
		
		public boolean canContinueToUse() {
			return !this.mob.getNavigation().isDone() && !this.mob.isVehicle();
		}
		
		@Override 
		public void start() {
			BlockPos pos = this.mob.blockPosition();
			boolean isOnSwamp = false;
			loop:
				for(int i = pos.getX() - 5; i > pos.getX(); i--) 
				{
					for(int j = pos.getZ() - 5; j > pos.getZ(); j--) 
					{
						for(int k = pos.getY(); k < pos.getY() + 5; k++) 
						{ 
							BlockPos blockPos = new BlockPos(i, k, j);
							BlockState blockState = this.mob.level.getBlockState(pos);
							
							if(blockState.getFluidState().isEmpty() && BlockUtil.isAir(mob, blockPos.above()) ) 
							{
								mob.moveTo(i, k+1, j);
								isOnSwamp = true;
								break loop;
							} else {
								isOnSwamp = false;
							}
						}
					}
				}
			if(!isOnSwamp) {
				this.mob.addEffect(new EffectInstance(Effects.POISON, 5));
				this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
			}
		}
		
		@Override
		public void tick() {
			super.tick();
			this.mob.addEffect(new EffectInstance(Effects.WEAKNESS, 5));
		}
	}
	
	private class WalkingOnBeach extends Goal{
		DwarfCrocodileEntity mob;
		
		public WalkingOnBeach(double speed) {
			this.mob = DwarfCrocodileEntity.this;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override 
		public boolean canUse() {
			if(!mob.isInWaterOrBubble() && mob.getRandom().nextInt(50) == 0) {
				return true;
			}
			return false;
		}
		
		@Override 
		public void start() {
			BlockPos pos = this.mob.blockPosition();
			loop:
				for(int i = pos.getX() - 3; i > pos.getX(); i--) 
				{
					for(int j = pos.getZ() - 3; j > pos.getZ(); j--) 
					{
						for(int k = pos.getY(); k < pos.getY() + 3; k++) 
						{
							BlockPos blockPos = new BlockPos(i, k, j);
							BlockState blockState = this.mob.level.getBlockState(pos);
							
							if(!blockState.getFluidState().isEmpty() && BlockUtil.isAir(mob, blockPos.above()) ) 
							{
								mob.moveTo(i, k+1, j);
								break loop;
							}
						}
					}
				}
		}
	}
}
