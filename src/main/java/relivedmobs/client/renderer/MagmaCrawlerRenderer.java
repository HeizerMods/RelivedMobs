package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.MagmaCrawlerModel;
import relivedmobs.common.entity.monster.MagmaCrawlerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MagmaCrawlerRenderer extends GeoEntityRenderer<MagmaCrawlerEntity>{

	public MagmaCrawlerRenderer(EntityRendererManager renderManager) {
		super(renderManager, new MagmaCrawlerModel());
		this.shadowRadius = 0.7F;
	}

}
