package samy.nekomatweaks

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.RedstoneWireBlock
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object NekomaTweaksClient : ClientModInitializer {
	private val logger = LoggerFactory.getLogger("nekoma-tweaks-client")

	override fun onInitializeClient() {
		// Register block color provider for redstone cable
		val redstoneCableBlock = Registries.BLOCK.get(Identifier.of(NekomaTweaks.MOD_ID, "redstone_cable"))

		logger.info("Registering color provider for redstone cable")

		ColorProviderRegistry.BLOCK.register({ state, world, pos, tintIndex ->
			// Use the same color as regular redstone wire based on power level
			val powerLevel = state?.get(RedstoneWireBlock.POWER) ?: 0
			val color = RedstoneWireBlock.getWireColor(powerLevel)
			logger.info("Color provider called: power=$powerLevel, color=${color.toString(16)}, tintIndex=$tintIndex")
			color
		}, redstoneCableBlock)

		logger.info("Color provider registered successfully")
	}
}