package relivedmobs.common.entity.monster;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.ai.goal.FindWaterGoal;
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


public class AnacondaEntity extends CreatureEntity implements IAnimatable {
	
	public AnacondaEntity(EntityType<? extends AnacondaEntity> mob, World world) {
		super(mob, world);
	}

	AnimationFactory factory = new AnimationFactory(this);

	public static MutableAttribute createAnacondaAttributes() {
		return MobEntity.createMobAttributes()
	                .add(Attributes.MAX_HEALTH, RelivedMobsConfig.anaconda_health.get())
	                .add(Attributes.MOVEMENT_SPEED, RelivedMobsConfig.anaconda_speed.get())
	                .add(Attributes.ATTACK_DAMAGE, RelivedMobsConfig.anaconda_attack_damage.get());
	}

	    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
	        if (event.isMoving()) {
	            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.anaconda.walk", true));
	        } else {
	            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.anaconda.idle", true));
	        }
	        return PlayState.CONTINUE;
	    }

	    @Override
	    public void registerControllers(AnimationData data) {
	        data.addAnimationController(new AnimationController<AnacondaEntity>(this, "controller", 0, this::predicate));
	    }

	    @Override
	    public AnimationFactory getFactory() {
	        return this.factory;
	    }
	    
	    @Override
	    protected void registerGoals() {
	        this.goalSelector.addGoal(1, new SwimGoal(this));
	        this.goalSelector.addGoal(1, new FindWaterGoal(this));
	        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.7D, 500));
	        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 0.3D, 500));
	        this.goalSelector.addGoal(9, new LookAtGoal(this, ChickenEntity.class, 8.0F));
	        this.goalSelector.addGoal(9, new LookAtGoal(this, RabbitEntity.class, 8.0F));
	        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 0.3D, false));
	        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));   
	        registerTargetGoals();
	    }
	    private void registerTargetGoals() {
	    	this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<ChickenEntity>(this, ChickenEntity.class,true));
	    	this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<RabbitEntity>(this, RabbitEntity.class,true));
	    	this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class,true));
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
}
