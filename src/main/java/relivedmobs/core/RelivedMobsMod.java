package relivedmobs.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import relivedmobs.api.entity.RelivedMobsEntities;
import relivedmobs.client.renderer.AnacondaRenderer;
import relivedmobs.client.renderer.BisonRenderer;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.passive.BisonEntity;
import relivedmobs.common.init.ModEntities;
import relivedmobs.common.init.ModItems;
import relivedmobs.core.config.ConfigScreen;
import relivedmobs.core.config.RelivedMobsConfig;
import software.bernie.geckolib3.GeckoLib;

//The value here should match an entry in the META-INF/mods.toml file
@Mod(RelivedMobsMod.MODID)
public class RelivedMobsMod
{
	public static final Logger logger = LogManager.getLogger();
	public static final String MODID = "relivedmobs";

	public RelivedMobsMod() {
		GeckoLib.initialize();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::entityAttributeCreationEvent);
		ModEntities.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		ModLoadingContext.get().registerExtensionPoint(
                ExtensionPoint.CONFIGGUIFACTORY,
                () -> (mc, screen) -> new ConfigScreen(screen));
		
		ModLoadingContext.get().registerConfig(Type.COMMON, RelivedMobsConfig.SPEC, "relivedmobs-common.toml");
		
		MinecraftForge.EVENT_BUS.register(this);
		
		MinecraftForge.EVENT_BUS.addListener(this::onBiomesLoad);
	}
 
	private void preInit(final FMLCommonSetupEvent event)
	{
		logger.info("Relived Mobs is starting preinit");
	}

	private void clientSetup(final FMLClientSetupEvent event) 
	{
		logger.info("Relived Mobs setup in client...");
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.anaconda.get(),
				manager -> new AnacondaRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.bison.get(),
				manager -> new BisonRenderer(manager));
	}
	
	public void onBiomesLoad(BiomeLoadingEvent event) {
        System.out.println(event.getName());
        if( RelivedMobsConfig.spawn_anaconda.get().booleanValue() == true && (event.getName().toString().equals("minecraft:jungle") 
        		|| event.getName().toString().equals("minecraft:jungle_edge") 
                || event.getName().toString().equals("minecraft:modified_jungle")) ) 
        {
        	event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.anaconda.get(), 
        														RelivedMobsConfig.anaconda_min_spawn_value.get(), 
        														RelivedMobsConfig.anaconda_max_spawn_value.get(), 85));
        } else if( RelivedMobsConfig.spawn_bison.get().booleanValue() == true && (event.getName().toString().equals("minecraft:savanna") 
        		|| event.getName().toString().equals("minecraft:savanna_plateau") 
                || event.getName().toString().equals("minecraft:shattered_savanna")
                || event.getName().toString().equals("minecraft:shattered_savanna_plateau"))) 
        {
        	event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.bison.get(), 
        															RelivedMobsConfig.bison_min_spawn_value.get().intValue(), 
        															RelivedMobsConfig.bison_max_spawn_value.get().intValue(), 80));
        }
    }
 
	public void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		logger.info("RelivedMobs is starting entity attribute event");
			event.put(RelivedMobsEntities.anaconda.get(), AnacondaEntity.createAnacondaAttributes().build());
			event.put(RelivedMobsEntities.bison.get(), BisonEntity.createBisonAttributes().build());
	}
}

