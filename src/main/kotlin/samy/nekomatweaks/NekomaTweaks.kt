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
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import samy.nekomatweaks.block.RedstoneCableBlock
import samy.nekomatweaks.block.UnderwaterComparatorBlock

object NekomaTweaks : ModInitializer {
    private val logger = LoggerFactory.getLogger("nekoma-tweaks")
    const val MOD_ID = "nekoma-tweaks"

    lateinit var REDSTONE_CABLE_BLOCK: RedstoneCableBlock
    lateinit var UNDERWATER_COMPARATOR_BLOCK: UnderwaterComparatorBlock

    override fun onInitialize() {
        val edibleComponent = FoodComponent.Builder().nutrition(4).saturationModifier(0.8f).build()

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(Items.GLISTERING_MELON_SLICE) { builder ->
                builder
                    .add(DataComponentTypes.FOOD, edibleComponent)
                    .add(DataComponentTypes.CONSUMABLE, ConsumableComponents.FOOD)
            }
        }

        // Register redstone cable block
        REDSTONE_CABLE_BLOCK = Registry.register(
            Registries.BLOCK,
            Identifier.of(MOD_ID, "redstone_cable"),
            RedstoneCableBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_WIRE).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "redstone_cable")))),
        )

        // Register redstone cable item
        Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "redstone_cable"),
            BlockItem(REDSTONE_CABLE_BLOCK, Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "redstone_cable"))))
        )

        // Register underwater comparator block
        UNDERWATER_COMPARATOR_BLOCK = Registry.register(
            Registries.BLOCK,
            Identifier.of(MOD_ID, "underwater_comparator"),
            UnderwaterComparatorBlock(AbstractBlock.Settings.copy(Blocks.COMPARATOR).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "underwater_comparator")))),
        )

        // Register underwater comparator item
        Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "underwater_comparator"),
            BlockItem(UNDERWATER_COMPARATOR_BLOCK, Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "underwater_comparator"))))
        )

        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world! I'm making a mod with bluestone!")
    }
}