package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinSkyRenderer {
    @Inject(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V",
            at = @At("HEAD"), cancellable = true)
    private void customSky(MatrixStack matrices, Matrix4f proj, float tick, Camera cam, boolean bl, Runnable r, CallbackInfo ci) {
        if (!GlitchV1.config.customSky) return;
        float rr = GlitchV1.config.skyR, gg = GlitchV1.config.skyG, bb = GlitchV1.config.skyB;
        Tessellator t = Tessellator.getInstance();
        BufferBuilder b = t.getBuffer();
        RenderSystem.disableTexture();
        b.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
        b.vertex(-100, -100, -100).color(rr, gg, bb, 1).next();
        b.vertex(100, -100, -100).color(rr, gg, bb, 1).next();
        b.vertex(100, -100, 100).color(rr*2, gg*2, bb*2, 1).next();
        b.vertex(-100, -100, 100).color(rr*2, gg*2, bb*2, 1).next();
        t.draw();
        RenderSystem.enableTexture();
        ci.cancel();
    }
}
