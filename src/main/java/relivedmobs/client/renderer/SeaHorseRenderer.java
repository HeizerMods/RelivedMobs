package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.SeaHorseModel;
import relivedmobs.common.entity.passive.SeaHorseEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SeaHorseRenderer extends GeoEntityRenderer<SeaHorseEntity>{
	public SeaHorseRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new SeaHorseModel());
		this.shadowRadius = 0.7F;
	}
}
