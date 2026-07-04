package com.glitchv1.mixin;

import com.glitchv1.render.NoRenderHandler;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    
    @Inject(method = "render", at = @At("RETURN"))
    private void onRenderWorld(MatrixStack matrices, float tickDelta, long limitTime,
                               boolean renderBlockOutline, Camera camera,
                               GameRenderer gameRenderer, LightmapTextureManager lightmap,
                               Matrix4f projection, CallbackInfo ci) {
        NoRenderHandler.onWorldRender(matrices, camera);
    }
}
