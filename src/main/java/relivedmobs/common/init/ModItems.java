package relivedmobs.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import relivedmobs.api.entity.RelivedMobsEntities;
import relivedmobs.common.item.ModSpawnEggItem;
import relivedmobs.core.RelivedMobsMod;

import static relivedmobs.api.item.RelivedMobsItems.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RelivedMobsMod.MODID);
    
    static {
    	bison_fur = ITEMS.register("bison_fur",
            () -> new Item(new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    	saw_fish_tooth = ITEMS.register("saw_fish_tooth",
                () -> new Item(new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    	idol_vase = ITEMS.register("idol_vase",
                () -> new Item(new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    	bison_idol = ITEMS.register("bison_idol",
                () -> new Item(new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    	bison_spawn_egg = ITEMS.register("bison_spawn_egg", 
    		() -> new ModSpawnEggItem(() -> RelivedMobsEntities.bison.get(), 0x261b02, 0xaba59a,
					new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    	bison_spawn_egg = ITEMS.register("anaconda_spawn_egg", 
        		() -> new ModSpawnEggItem(() -> RelivedMobsEntities.anaconda.get(), 0x57a340, 0xb1bdb3,
    					new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    }
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
