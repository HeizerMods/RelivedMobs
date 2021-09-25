package relivedmobs.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import relivedmobs.api.item.RelivedMobsItems;

public class ModGroups {

    public static final ItemGroup RELIVED_MOBS = new ItemGroup("RelivedMobsTab") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(RelivedMobsItems.bison_fur.get());
		}
    };
}
