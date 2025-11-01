package samy.nekomatweaks

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.VerticallyAttachableBlockItem
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.slf4j.LoggerFactory

object NekomaTweaks : ModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks")

    override fun onInitialize() {
        initializeEdibleGlisterinMelonSlice()
        initializeUnderwaterTorch()

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world! I'm making a mod")
    }

    fun initializeEdibleGlisterinMelonSlice() {
        val foodComponent = FoodComponent.Builder().nutrition(4).saturationModifier(0.8f).build()

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(Items.GLISTERING_MELON_SLICE) { builder ->
                builder
                    .add(DataComponentTypes.FOOD, foodComponent)
                    .add(DataComponentTypes.CONSUMABLE, ConsumableComponents.FOOD)
            }
        }
    }

    fun initializeUnderwaterTorch() {
        // Create block settings for standing torch
        val torchSettings = AbstractBlock.Settings.create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("nekoma-tweaks", "underwater_torch")))
            .noCollision()
            .breakInstantly()
            .luminance { 14 }
            .sounds(BlockSoundGroup.WOOD)
            .pistonBehavior(PistonBehavior.DESTROY)

        // Register the standing torch block
        val underwaterTorch = WaterloggableTorchBlock(
            ParticleTypes.FLAME,
            torchSettings
        )

        val underwaterTorchBlock = Registry.register(
            Registries.BLOCK,
            Identifier.of("nekoma-tweaks", "underwater_torch"),
            underwaterTorch
        )

        // Create block settings for wall torch
        val wallTorchSettings = AbstractBlock.Settings.create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("nekoma-tweaks", "underwater_wall_torch")))
            .noCollision()
            .breakInstantly()
            .luminance { 14 }
            .sounds(BlockSoundGroup.WOOD)
            .pistonBehavior(PistonBehavior.DESTROY)

        // Register the wall torch block
        val underwaterWallTorch = WaterloggableWallTorchBlock(
            ParticleTypes.FLAME,
            wallTorchSettings
        )

        val underwaterWallTorchBlock = Registry.register(
            Registries.BLOCK,
            Identifier.of("nekoma-tweaks", "underwater_wall_torch"),
            underwaterWallTorch
        )

        // Register the item that places both torch variants
        val itemSettings = Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("nekoma-tweaks", "underwater_torch")))

        Registry.register(
            Registries.ITEM,
            Identifier.of("nekoma-tweaks", "underwater_torch"),
            VerticallyAttachableBlockItem(
                underwaterTorchBlock,
                underwaterWallTorchBlock,
                Direction.DOWN,
                itemSettings
            )
        )

        logger.info("Underwater torch registered successfully")
    }
}
