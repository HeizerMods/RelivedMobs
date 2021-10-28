package relivedmobs.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import relivedmobs.common.entity.item.EnderDartEntity;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.monster.DwarfCrocodileEntity;
import relivedmobs.common.entity.monster.MagmaCrawlerEntity;
import relivedmobs.common.entity.monster.MarcoonEntity;
import relivedmobs.common.entity.passive.BisonEntity;
import relivedmobs.common.entity.passive.PuffinEntity;
import relivedmobs.common.entity.passive.SawFishEntity;

public class RelivedMobsEntities {
	
	// Overworld
	
	public static RegistryObject<EntityType<AnacondaEntity>> anaconda; // OK
	public static RegistryObject<EntityType<Entity>> hyena; // hasn't got
	public static RegistryObject<EntityType<BisonEntity>> bison; // OK
	public static RegistryObject<EntityType<Entity>> fallow_deer; // hasn't got
	public static RegistryObject<EntityType<PuffinEntity>> puffin; // NO
	public static RegistryObject<EntityType<Entity>> sea_horse; // NO
	public static RegistryObject<EntityType<SawFishEntity>> saw_fish; // NO
	public static RegistryObject<EntityType<DwarfCrocodileEntity>> dwarf_crocodile; // NO
	
	// Nether
	
	public static RegistryObject<EntityType<MagmaCrawlerEntity>> magma_crawler; // NO
	
	// End
	
	public static RegistryObject<EntityType<MarcoonEntity>> marcoon; // NO
	
	// Misc
	
	public static RegistryObject<EntityType<EnderDartEntity>> ender_dart; // NO
}
