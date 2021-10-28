package relivedmobs.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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
import relivedmobs.client.renderer.DwarfCrocodileRenderer;
import relivedmobs.client.renderer.MagmaCrawlerRenderer;
import relivedmobs.client.renderer.MarcoonRenderer;
import relivedmobs.client.renderer.PuffinRenderer;
import relivedmobs.client.renderer.SawFishRenderer;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.passive.BisonEntity;
import relivedmobs.common.init.ModEntities;
import relivedmobs.common.init.ModItems;
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
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.dwarf_crocodile.get(), 
				manager -> new DwarfCrocodileRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.magma_crawler.get(), 
				manager -> new MagmaCrawlerRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.marcoon.get(), 
				manager -> new MarcoonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.puffin.get(), 
				manager -> new PuffinRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(RelivedMobsEntities.saw_fish.get(), 
				manager -> new SawFishRenderer(manager));
	}
	
	public void onBiomesLoad(BiomeLoadingEvent event) {
        System.out.println(event.getName());
        if( event.getName().toString().equals("minecraft:jungle") 
        		|| event.getName().toString().equals("minecraft:jungle_edge") 
                || event.getName().toString().equals("minecraft:modified_jungle")) 
        {
        	event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.anaconda.get(), 1, 2, 85));
        } 
        else if( event.getName().toString().equals("minecraft:savanna") 
        		|| event.getName().toString().equals("minecraft:savanna_plateau") 
                || event.getName().toString().equals("minecraft:shattered_savanna")
                || event.getName().toString().equals("minecraft:shattered_savanna_plateau")) 
        {
        	event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.bison.get(), 3, 5, 80));
        } 
        else if( event.getName().toString().equals("minecraft:warm_ocean") 
        		|| event.getName().toString().equals("minecraft:deep_warm_ocean"))
        {
        	event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.saw_fish.get(), 3, 5, 80));
        } 
        else if( event.getName().toString().equals("minecraft:beach"))
        {
        	event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.puffin.get(), 3, 3, 90));
        } 
        else if( event.getName().toString().equals("minecraft:swamp")
        		|| event.getName().toString().equals("minecraft:swamp_hills") )
        {
        	event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(RelivedMobsEntities.dwarf_crocodile.get(), 1, 2, 80));
        }
    }
 
	public void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		logger.info("RelivedMobs is starting entity attribute event");
			event.put(RelivedMobsEntities.anaconda.get(), AnacondaEntity.createAnacondaAttributes().build());
			event.put(RelivedMobsEntities.bison.get(), BisonEntity.createBisonAttributes().build());
			event.put(RelivedMobsEntities.dwarf_crocodile.get(), MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 3.0F).build());
			event.put(RelivedMobsEntities.magma_crawler.get(), MobEntity.createMobAttributes().build());
			event.put(RelivedMobsEntities.marcoon.get(), MobEntity.createMobAttributes().build());
			event.put(RelivedMobsEntities.puffin.get(), AnimalEntity.createMobAttributes().build());
			event.put(RelivedMobsEntities.saw_fish.get(), CreatureEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 3.0F).build());
	}
}

