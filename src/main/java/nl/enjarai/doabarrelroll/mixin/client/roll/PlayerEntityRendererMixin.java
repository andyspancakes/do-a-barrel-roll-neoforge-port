package nl.enjarai.doabarrelroll.mixin.client.roll;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import nl.enjarai.doabarrelroll.api.RollRenderState;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AvatarRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @ModifyArg(
            method = "setupRotations(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionfc;)V",
                    ordinal = 1
            ),
            index = 0
    )
    private Quaternionfc doABarrelRoll$modifyRoll(Quaternionfc original, @Local(argsOnly = true) AvatarRenderState state) {
        var rollState = (RollRenderState) state;

        if (rollState.doABarrelRoll$isRolling()) {
            var roll = rollState.doABarrelRoll$getRoll();
            return Axis.YP.rotationDegrees(roll);
        }

        return original;
    }
}
