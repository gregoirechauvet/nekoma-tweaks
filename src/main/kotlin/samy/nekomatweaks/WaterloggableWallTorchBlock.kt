package samy.nekomatweaks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.WallTorchBlock
import net.minecraft.block.Waterloggable
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.SimpleParticleType
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction

class WaterloggableWallTorchBlock(particle: SimpleParticleType, settings: Settings) : WallTorchBlock(particle, settings), Waterloggable {

    init {
        defaultState = stateManager.defaultState
            .with(FACING, Direction.NORTH)
            .with(Properties.WATERLOGGED, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(Properties.WATERLOGGED)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        val state = super.getPlacementState(ctx)
        return state?.with(Properties.WATERLOGGED, fluidState.fluid === Fluids.WATER)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) {
            Fluids.WATER.getStill(false)
        } else {
            super.getFluidState(state)
        }
    }
}
