package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.FallowDeerModel;
import relivedmobs.common.entity.passive.FallowDeerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FallowDeerRenderer extends GeoEntityRenderer<FallowDeerEntity>{

	protected FallowDeerRenderer(EntityRendererManager renderManager) {
		super(renderManager, new FallowDeerModel());
		this.shadowRadius = 0.7F;
	}

}
