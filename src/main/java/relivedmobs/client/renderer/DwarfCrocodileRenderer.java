package relivedmobs.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import relivedmobs.client.model.DwarfCrocodileModel;
import relivedmobs.common.entity.monster.DwarfCrocodileEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DwarfCrocodileRenderer extends GeoEntityRenderer<DwarfCrocodileEntity>{

	public DwarfCrocodileRenderer(EntityRendererManager renderManager) {
		super(renderManager, new DwarfCrocodileModel());
		this.shadowRadius = 0.7F;
	}

}
