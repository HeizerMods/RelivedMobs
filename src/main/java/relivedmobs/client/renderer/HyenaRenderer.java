package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.HyenaModel;
import relivedmobs.common.entity.monster.HyenaEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HyenaRenderer extends GeoEntityRenderer<HyenaEntity>{

	protected HyenaRenderer(EntityRendererManager renderManager) {
		super(renderManager, new HyenaModel());
	}

}
