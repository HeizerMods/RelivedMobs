package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.BisonModel;
import relivedmobs.common.entity.passive.BisonEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BisonRenderer extends GeoEntityRenderer<BisonEntity>{

	public BisonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BisonModel());
		this.shadowRadius = 0.7F;
	}

}
