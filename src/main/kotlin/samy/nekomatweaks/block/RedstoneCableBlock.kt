package samy.nekomatweaks.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.RedstoneWireBlock
import net.minecraft.block.Waterloggable
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView

class RedstoneCableBlock(settings: Settings) : RedstoneWireBlock(settings), Waterloggable {

    init {
        defaultState = defaultState.with(Properties.WATERLOGGED, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(Properties.WATERLOGGED)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return super.getPlacementState(ctx)?.with(Properties.WATERLOGGED, fluidState.fluid === Fluids.WATER)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) {
            Fluids.WATER.getStill(false)
        } else {
            super.getFluidState(state)
        }
    }
}
