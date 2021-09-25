package relivedmobs.client.model;

import net.minecraft.util.ResourceLocation;
import relivedmobs.core.RelivedMobsMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelBase<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private String AnimationFileLocation;
	private String ModelFileLocation;
	private String TextureFileLocation;
	
	public ModelBase(String ModelFileLocation, String TextureFileLocation, String AnimationFileLocation) {
		this.AnimationFileLocation = AnimationFileLocation;
		this.ModelFileLocation = ModelFileLocation;
		this.TextureFileLocation = TextureFileLocation;
	}

	@Override
	public ResourceLocation getTextureLocation(T object) {
		return new ResourceLocation(RelivedMobsMod.MODID, this.TextureFileLocation);
	}
	
	@Override
	public ResourceLocation getModelLocation(T object) {
		return new ResourceLocation(RelivedMobsMod.MODID, this.ModelFileLocation);
	}
	
	@Override
	public ResourceLocation getAnimationFileLocation(T animatable) {
		return new ResourceLocation(RelivedMobsMod.MODID, this.AnimationFileLocation);
	}

}
