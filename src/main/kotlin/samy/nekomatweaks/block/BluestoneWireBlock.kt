package samy.nekomatweaks.block

import net.minecraft.block.RedstoneWireBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random

class BluestoneWireBlock(settings: Settings) : RedstoneWireBlock(settings) {

    // Override the particle color to be blue instead of red
    override fun getWireColor(powerLevel: Int): Int {
        // Convert power level (0-15) to blue color
        // Full power = bright blue (0x0080FF), no power = dark blue (0x000030)
        val minBrightness = 0x30
        val maxBrightness = 0xFF
        val brightness = minBrightness + (maxBrightness - minBrightness) * powerLevel / 15

        // Return blue color (0x00RRGGBB format, where blue is the last two bytes)
        return (brightness shl 0) or (brightness / 2 shl 8) // Blue + slight green for visibility
    }
}
