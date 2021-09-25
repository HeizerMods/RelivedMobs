package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.PuffinModel;
import relivedmobs.common.entity.passive.PuffinEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PuffinRenderer extends GeoEntityRenderer<PuffinEntity>{

	protected PuffinRenderer(EntityRendererManager renderManager) {
		super(renderManager, new PuffinModel());
	}

}
