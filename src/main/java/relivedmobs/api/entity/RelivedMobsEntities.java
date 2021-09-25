package relivedmobs.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import relivedmobs.common.entity.monster.AnacondaEntity;
import relivedmobs.common.entity.passive.BisonEntity;

public class RelivedMobsEntities {
	public static RegistryObject<EntityType<AnacondaEntity>> anaconda;
	public static RegistryObject<EntityType<Entity>> hyena;
	public static RegistryObject<EntityType<BisonEntity>> bison;
	public static RegistryObject<EntityType<Entity>> fallow_deer;
	public static RegistryObject<EntityType<Entity>> puffin;
	public static RegistryObject<EntityType<Entity>> sea_horse;
}
