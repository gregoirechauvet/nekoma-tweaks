package samy.nekomatweaks

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.RedstoneWireBlock
import net.minecraft.client.render.BlockRenderLayer
import org.slf4j.LoggerFactory

object NekomaTweaksClient : ClientModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks-client")

    override fun onInitializeClient() {
        BlockRenderLayerMap.putBlock(NekomaTweaks.REDSTONE_CABLE_BLOCK, BlockRenderLayer.CUTOUT_MIPPED)

        // Register color provider for redstone cable to handle power level tinting
        ColorProviderRegistry.BLOCK.register({ state, _, _, _ ->
            RedstoneWireBlock.getWireColor(state.get(RedstoneWireBlock.POWER))
        }, NekomaTweaks.REDSTONE_CABLE_BLOCK)
    }
}