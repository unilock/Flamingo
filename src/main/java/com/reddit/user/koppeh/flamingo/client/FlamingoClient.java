package com.reddit.user.koppeh.flamingo.client;

import com.reddit.user.koppeh.flamingo.Flamingo;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class FlamingoClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockEntityRendererFactories.register(Flamingo.FLAMINGO_BLOCK_ENTITY, FlamingoBlockEntityRenderer::new);
		BuiltinItemRendererRegistry.INSTANCE.register(Flamingo.FLAMINGO_BLOCK, FlamingoBlockEntityRenderer::renderItem);
		EntityModelLayerRegistry.registerModelLayer(FlamingoBlockEntityRenderer.flamingoLayer, FlamingoModel::getModel);
	}
}
