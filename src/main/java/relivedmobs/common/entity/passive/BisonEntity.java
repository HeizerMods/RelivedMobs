package relivedmobs.common.entity.passive;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import relivedmobs.core.config.RelivedMobsConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BisonEntity extends CreatureEntity implements IAnimatable{

	public BisonEntity(EntityType<? extends CreatureEntity> mob, World world) {
		super(mob, world);
	}

	AnimationFactory factory = new AnimationFactory(this);

	public static MutableAttribute createBisonAttributes() {
		MutableAttribute attributes = CreatureEntity.createLivingAttributes()
	                .add(Attributes.MAX_HEALTH, RelivedMobsConfig.bison_health.get())
	                .add(Attributes.MOVEMENT_SPEED, RelivedMobsConfig.bison_speed.get())
	                .add(Attributes.ATTACK_SPEED, 1.25D);
		attributes.combine(CreatureEntity.createMobAttributes().add(Attributes.FOLLOW_RANGE, 4.0F));
		return attributes.add(Attributes.ATTACK_DAMAGE, RelivedMobsConfig.bison_attack_damage.get());
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
	        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bison.walk", true));
	    } else {
	    	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bison.idle_2", true));
	    }
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
	    data.addAnimationController(new AnimationController<BisonEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
	    return this.factory;
	}
	    
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
	    this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.3D, 500));
	    this.goalSelector.addGoal(3, new EatGrassGoal(this));
	    this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 0.3D, false));
	    this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(9, new LookRandomlyGoal(this));   
	    this.targetSelector.addGoal(2, new TargetWithoutShift(this, true));
	}

	public void tick() {
	    super.tick();
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

	public void handleStatus(byte status) {
		super.handleEntityEvent(status);
	}

	public int getLimitPerChunk() {
	    return 64;
	}
	    
	public Vector3d getLeashOffset() {
	    return new Vector3d(0.0D, (double)(0.6F * this.getStandingEyeHeight(this.getPose(), this.getDimensions(this.getPose()))), (double)(this.getBbWidth() * 0.4F));
	}
	
	private class TargetWithoutShift extends NearestAttackableTargetGoal<PlayerEntity> {

		public TargetWithoutShift(MobEntity mob, boolean mustSee) {
			super(mob, PlayerEntity.class, mustSee);
		}
		public boolean canUse() {
			if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
				return false;
			} else {
				this.findTarget();
				return this.target != null && !target.isShiftKeyDown();
			}
		}
	}
}
