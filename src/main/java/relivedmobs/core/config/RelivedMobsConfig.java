package relivedmobs.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class RelivedMobsConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	// Spawn
	public static final BooleanValue spawn_anaconda;
	public static final BooleanValue spawn_hyena;
	public static final BooleanValue spawn_bison;
	public static final BooleanValue spawn_fallowdeer;
	public static final BooleanValue spawn_puffin;
	public static final BooleanValue spawn_seahorse;
	
	// Minimum and maximum spawn value
	
	public static final ConfigValue<Integer> anaconda_min_spawn_value;
	public static final ConfigValue<Integer> anaconda_max_spawn_value;
	public static final ConfigValue<Integer> bison_min_spawn_value;
	public static final ConfigValue<Integer> bison_max_spawn_value;
	
	// Health, speed and attack damage
	public static final ConfigValue<Double> anaconda_health;
	public static final ConfigValue<Double> anaconda_speed;
	public static final ConfigValue<Double> anaconda_attack_damage;
	
	public static final ConfigValue<Double> hyena_health;
	public static final ConfigValue<Double> hyena_speed;
	public static final ConfigValue<Double> hyena_attack_damage;
	
	public static final ConfigValue<Double> bison_health;
	public static final ConfigValue<Double> bison_speed;
	public static final ConfigValue<Double> bison_attack_damage;
	
	public static final ConfigValue<Double> fallow_deer_health;
	public static final ConfigValue<Double> fallow_deer_speed;
	public static final ConfigValue<Double> fallow_deer_attack_damage;
	
	public static final ConfigValue<Double> puffin_health;
	public static final ConfigValue<Double> puffin_speed;
	public static final ConfigValue<Double> puffin_attack_damage;
	
	public static final ConfigValue<Double> sea_horse_health;
	public static final ConfigValue<Double> sea_horse_speed;
	public static final ConfigValue<Double> sea_horse_attack_damage;
	
	static {
		BUILDER.push("RelivedMobsConfig");
		
		spawn_anaconda = BUILDER.comment("Will the anacondas spawn? Default value is true").define("Spawn anacondas", true);
		spawn_hyena = BUILDER.comment("Will the hyenas spawn? Default value is true").define("Spawn hyenas", true);
		spawn_bison = BUILDER.comment("Will the bisons spawn? Default value is true").define("Spawn bisons", true);
		spawn_fallowdeer = BUILDER.comment("Will the fallow deers spawn? Default value is true").define("Spawn fallow deers", true);
		spawn_puffin = BUILDER.comment("Will the puffins spawn? Default value is true").define("Spawn puffins", true);
		spawn_seahorse = BUILDER.comment("Will the sea horses spawn? Default value is true").define("Spawn sea horses", true);
		
		anaconda_min_spawn_value = register("Minimum spawn value for anaconda", "Minimum anaconda spawn value", 1);
		anaconda_max_spawn_value = register("Maximum spawn value for anaconda", "Maximum anaconda spawn value", 2);
		
		bison_min_spawn_value = register("Minimum spawn value for bison", "Minimum bison spawn value", 3);
		bison_max_spawn_value = register("Maximum spawn value for bison", "Maximum bison spawn value", 5);
		
		anaconda_health = register("", "Anaconda's health", 20.0D);
		anaconda_speed = register("", "Anaconda's speed", 0.7D);
		anaconda_attack_damage = register("", "Anaconda's attack damage", 3.0D);
		
		hyena_health = unimplemented_register("", "Hyena's health", 0.0D);
		hyena_speed = unimplemented_register("", "Hyena's speed", 0.0D);
		hyena_attack_damage = unimplemented_register("", "Hyena's attack damage", 0.0D);
		
		bison_health = register("", "Bison's health", 15.0D);
		bison_speed = register("", "Bison's speed", 0.4D);
		bison_attack_damage = register("", "Bison's attack damage", 2.5D);
		
		fallow_deer_health = unimplemented_register("", "Fallow deer's health", 0.0D);
		fallow_deer_speed = unimplemented_register("", "Fallow deer's speed", 0.0D);
		fallow_deer_attack_damage = unimplemented_register("", "Fallow deer's attack damage", 0.0D);
		
		puffin_health = unimplemented_register("", "Puffin's health", 0.0D);
		puffin_speed = unimplemented_register("", "Puffin's speed", 0.0D); 
		puffin_attack_damage = unimplemented_register("", "Puffin's attack damage", 0.0D);
		
		sea_horse_health = unimplemented_register("", "Sea horse's health", 0.0D);
		sea_horse_speed = unimplemented_register("", "Sea horse's speed", 0.0D);
		sea_horse_attack_damage = unimplemented_register("", "Sea horse's attack damage", 0.0D);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
	
	static <T>ConfigValue<T> unimplemented_register(String comment, String desc, T value) {
		return BUILDER.comment(comment + (comment.isEmpty()?"":'.') + "Default value is " + value.toString() + ". Not added yet." ).define(desc, value);
	}
	
	static <T>ConfigValue<T> register(String comment, String desc, T value) {
		return BUILDER.comment( comment + (comment.isEmpty()?"":'.') + "Default value is " + value.toString() + '.' ).define(desc, value);
	}
}