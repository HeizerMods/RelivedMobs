package relivedmobs.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.passive.BisonEntity;
import relivedmobs.core.RelivedMobsMod;

import static relivedmobs.api.entity.RelivedMobsEntities.*;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RelivedMobsMod.MODID);
	
	static {
		anaconda = ENTITIES.register("anaconda", 
				() -> EntityType.Builder.of(AnacondaEntity::new, EntityClassification.MONSTER)
				.sized(5.5f, 0.25f)
				.setShouldReceiveVelocityUpdates(true)
				.build("anaconda"));	
		bison = ENTITIES.register("bison", 
				() -> EntityType.Builder.of(BisonEntity::new, EntityClassification.CREATURE)
				.sized(2.4375f, 1.1875f)
				.setShouldReceiveVelocityUpdates(true)
				.build("bison"));	
	}
	
	public static void register(IEventBus bus) {
		ENTITIES.register(bus);
	}
}