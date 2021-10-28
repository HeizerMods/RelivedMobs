package relivedmobs.common.init;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import relivedmobs.core.RelivedMobsMod;

public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, RelivedMobsMod.MODID);
	public static void register(IEventBus bus) {
		BIOMES.register(bus);
	}
}