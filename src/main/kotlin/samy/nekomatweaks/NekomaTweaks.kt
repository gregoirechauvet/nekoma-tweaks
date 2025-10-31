package samy.nekomatweaks

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.item.Items
import org.slf4j.LoggerFactory

object NekomaTweaks : ModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks")

    override fun onInitialize() {
        val edibleComponent = FoodComponent.Builder().nutrition(4).saturationModifier(0.8f).build()

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(Items.GLISTERING_MELON_SLICE) { builder ->
                builder
                    .add(DataComponentTypes.FOOD, edibleComponent)
                    .add(DataComponentTypes.CONSUMABLE, ConsumableComponents.FOOD)
            }
        }

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world! I'm making a mod")
    }
}