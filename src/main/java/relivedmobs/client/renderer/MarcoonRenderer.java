package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.MarcoonModel;
import relivedmobs.common.entity.monster.MarcoonEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MarcoonRenderer extends GeoEntityRenderer<MarcoonEntity>{

	public MarcoonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new MarcoonModel());
		this.shadowRadius = 0.7F;
	}

}