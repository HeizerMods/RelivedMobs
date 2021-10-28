package relivedmobs.common.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import relivedmobs.common.entity.item.EnderDartEntity;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.monster.DwarfCrocodileEntity;
import relivedmobs.common.entity.monster.MagmaCrawlerEntity;
import relivedmobs.common.entity.monster.MarcoonEntity;
import relivedmobs.common.entity.passive.BisonEntity;
import relivedmobs.common.entity.passive.PuffinEntity;
import relivedmobs.common.entity.passive.SawFishEntity;
import relivedmobs.core.RelivedMobsMod;

import static relivedmobs.api.entity.RelivedMobsEntities.*;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RelivedMobsMod.MODID);
	
	static {
		anaconda = ENTITIES.register("anaconda", 
				() -> EntityType.Builder.of(AnacondaEntity::new, EntityClassification.MONSTER)
				.sized(1.5f, 0.25f)
				.setShouldReceiveVelocityUpdates(true)
				.build("anaconda"));	
		bison = ENTITIES.register("bison", 
				() -> EntityType.Builder.of(BisonEntity::new, EntityClassification.CREATURE)
				.sized(2.4375f, 1.1875f)
				.setShouldReceiveVelocityUpdates(true)
				.build("bison"));
		
		puffin = ENTITIES.register("puffin", 
				() -> EntityType.Builder.of(PuffinEntity::new, EntityClassification.CREATURE)
				.sized(0.25f, 0.875f)
				.setShouldReceiveVelocityUpdates(true)
				.build("puffin")); 
		
		saw_fish = ENTITIES.register("saw_fish", 
				() -> EntityType.Builder.of(SawFishEntity::new, EntityClassification.WATER_CREATURE)
				.sized(1.0f, 0.4375f)
				.setShouldReceiveVelocityUpdates(true)
				.build("saw_fish")); 
		
		dwarf_crocodile = ENTITIES.register("dwarf_crocodile", 
				() -> EntityType.Builder.of(DwarfCrocodileEntity::new, EntityClassification.MONSTER)
				.sized(1.25f, 0.625f)
				.setShouldReceiveVelocityUpdates(true)
				.build("dwarf_crocodile")); 
		
		// Nether
		
		magma_crawler = ENTITIES.register("magma_crawler", 
				() -> EntityType.Builder.of(MagmaCrawlerEntity::new, EntityClassification.MONSTER)
				.sized(0.6875f, 0.3125f)
				.setShouldReceiveVelocityUpdates(true)
				.build("magma_crawler")); 
		
		// End
		
		marcoon = ENTITIES.register("marcoon", 
				() -> EntityType.Builder.of(MarcoonEntity::new, EntityClassification.MONSTER)
				.sized(0.1f, 0.1f)
				.setShouldReceiveVelocityUpdates(true)
				.build("marcoon")); 
		
		// Misc
		
		ender_dart = ENTITIES.register("ender_dart", 
				() -> EntityType.Builder.of(EnderDartEntity::new, EntityClassification.MISC)
				.sized(0.1f, 0.1f)
				.setShouldReceiveVelocityUpdates(true)
				.build("ender_dart")); ; 
	}
	
	public static void register(IEventBus bus) {
		ENTITIES.register(bus);
	}
}