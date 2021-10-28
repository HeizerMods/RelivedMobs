package relivedmobs.common.entity.passive;

import java.util.EnumSet;

import javax.annotation.Nullable;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PuffinEntity extends TameableEntity implements IAnimatable, IFlyingAnimal{

	AnimationFactory factory = new AnimationFactory(this);
	
	public PuffinEntity(EntityType<? extends TameableEntity> mob, World world) {
		super(mob, world);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.puffin.walk", true));
	    } else if(this.getRandom().nextInt(25) == 1){
	    	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.puffin.clean", true));
	    } else {
	    	return PlayState.STOP;
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<PuffinEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entityIn) {
		return null;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
    	this.goalSelector.addGoal(0, new SwimGoal(this));
    	this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    	this.goalSelector.addGoal(1, new LookAtGoal(this, AbstractFishEntity.class, 15.0F));
    	this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.295F));
    	this.goalSelector.addGoal(2, new RandomlyFlyingGoal());
    	this.goalSelector.addGoal(2, new SitGoal(this));
    	this.goalSelector.addGoal(2, new PuffinAvoidWater(this));
    	this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
    	this.goalSelector.addGoal(4, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    	
    	this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractFishEntity.class, true, false));
	}
	
	private class PuffinAvoidWater extends Goal{
		private PuffinEntity entity;
		
		public PuffinAvoidWater(PuffinEntity entity) {
			this.entity = entity;
		}
		
		@Override
		public boolean canUse() {
			if(this.entity.isInWater()) {
				return true;
			}
			return false;
		}
		
		@SuppressWarnings("deprecation")
		public void start() {
			if(this.entity.isInWater()) {
				for(int i = this.entity.blockPosition().getY(); i < 250; i++) {
					BlockPos blockState = new BlockPos(this.entity.blockPosition().getX(), i, this.entity.blockPosition().getZ());
					if( this.entity.level.getBlockState(blockState.above(4)).isAir() ) {
						this.entity.moveTo(blockState.getX(), blockState.getY() + 4, blockState.getZ());
						break;
					}
				}
			}
		}
	}
	private class RandomlyFlyingGoal extends Goal {
	      public RandomlyFlyingGoal() {
	         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	      }

	      public boolean canUse() {
	         return PuffinEntity.this.navigation.isDone() && PuffinEntity.this.random.nextInt(10) == 0;
	      }

	      public boolean canContinueToUse() {
	         return PuffinEntity.this.navigation.isInProgress();
	      }

	      public void start() {
	         Vector3d vector3d = this.findPos();
	         if (vector3d != null) {
	            PuffinEntity.this.navigation.moveTo(PuffinEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
	         }

	      }

	      @Nullable
	      private Vector3d findPos() {
	         Vector3d vector3d = PuffinEntity.this.getViewVector(0.0F);

	         Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(PuffinEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
	         return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getLandPos(PuffinEntity.this, 8, 4);
	      }
	   }
}