package samy.nekomatweaks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samy.nekomatweaks.block.RedstoneCableBlock;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", at = @At("HEAD"), cancellable = true)
    private static void allowRedstoneCableConnection(BlockState state, @Nullable Direction direction, CallbackInfoReturnable<Boolean> cir) {
        // Allow connecting to RedstoneCableBlock
        if (state.getBlock() instanceof RedstoneCableBlock) {
            cir.setReturnValue(true);
        }
    }
}
