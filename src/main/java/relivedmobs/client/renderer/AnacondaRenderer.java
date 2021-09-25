package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.AnacondaModel;
import relivedmobs.common.entity.monster.AnacondaEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AnacondaRenderer extends GeoEntityRenderer<AnacondaEntity>{
	public AnacondaRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new AnacondaModel());
		this.shadowRadius = 0.7F;
	}
}
