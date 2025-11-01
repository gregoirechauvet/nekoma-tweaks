package samy.nekomatweaks

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import samy.nekomatweaks.block.BluestoneWireBlock

object NekomaTweaks : ModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks")
    const val MOD_ID = "nekoma-tweaks"

    override fun onInitialize() {
        val edibleComponent = FoodComponent.Builder().nutrition(4).saturationModifier(0.8f).build()

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(Items.GLISTERING_MELON_SLICE) { builder ->
                builder
                    .add(DataComponentTypes.FOOD, edibleComponent)
                    .add(DataComponentTypes.CONSUMABLE, ConsumableComponents.FOOD)
            }
        }

        // Register bluestone wire block
        val BLUESTONE_WIRE = Registry.register(
            Registries.BLOCK,
            Identifier.of(MOD_ID, "bluestone_wire"),
            BluestoneWireBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_WIRE))
        )

        // Register bluestone dust item
        val BLUESTONE_DUST = Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "bluestone_dust"),
            BlockItem(BLUESTONE_WIRE, Item.Settings())
        )

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world! I'm making a mod with bluestone!")
    }
}