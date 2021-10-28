package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.SawFishModel;
import relivedmobs.common.entity.passive.SawFishEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SawFishRenderer extends GeoEntityRenderer<SawFishEntity>{

	public SawFishRenderer(EntityRendererManager renderManager) {
		super(renderManager, new SawFishModel());
		this.shadowRadius = 0.7F;
	}

}