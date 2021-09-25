package relivedmobs.init;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import relivedmobs.core.RelivedMobsMod;

import static relivedmobs.api.item.RelivedMobsItems.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RelivedMobsMod.MODID);
    
    static {
    	bison_fur = ITEMS.register("bison_fur",
            () -> new Item(new Item.Properties().tab(ModGroups.RELIVED_MOBS)));
    }
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
