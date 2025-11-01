package samy.nekomatweaks

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap
import net.minecraft.client.render.BlockRenderLayer
import org.slf4j.LoggerFactory
import samy.nekomatweaks.block.RedstoneCableBlock

object NekomaTweaksClient : ClientModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks-client")

    override fun onInitializeClient() {
        BlockRenderLayerMap.putBlock(RedstoneCableBlock, BlockRenderLayer.CUTOUT);
    }
}