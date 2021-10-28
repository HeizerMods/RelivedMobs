package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.EnderDartModel;
import relivedmobs.common.entity.item.EnderDartEntity;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class EnderDartRenderer extends GeoProjectilesRenderer<EnderDartEntity>{

	public EnderDartRenderer(EntityRendererManager renderManager) {
		super(renderManager, new EnderDartModel());
		this.shadowRadius = 0.7F;
	}

}
