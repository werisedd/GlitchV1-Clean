package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class MixinHandRenderer {
    private static long swingStart = 0;
    private static boolean isSwinging = false;

    @Inject(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At("HEAD"))
    private void onRenderItem(float tickDelta, net.minecraft.client.util.math.MatrixStack matrices,
                              net.minecraft.client.render.VertexConsumerProvider.Immediate vertexConsumers,
                              net.minecraft.client.network.ClientPlayerEntity player, int light, CallbackInfo ci) {
        if (!GlitchV1.config.smoothSwing) return;
        if (player.handSwingTicks == 0) return;
        float progress = (float)(System.currentTimeMillis() - swingStart) / 250f;
        if (progress > 1f) progress = 1f;
        float swing = (float)Math.sin(progress * Math.PI) * 0.8f;
        matrices.translate(0, -swing * 0.3f, swing * 0.5f);
    }

    @Inject(method = "swingHand", at = @At("HEAD"))
    private void onSwing(Hand hand, CallbackInfo ci) {
        if (!GlitchV1.config.smoothSwing) return;
        swingStart = System.currentTimeMillis();
    }
}
