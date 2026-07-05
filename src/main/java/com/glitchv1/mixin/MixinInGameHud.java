package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private void noFireOverlay(MatrixStack ms, CallbackInfo ci) {
        if (GlitchV1.config.noFireOverlay) ci.cancel();
    }
    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(MatrixStack ms, float d, CallbackInfo ci) {
        if (!GlitchV1.config.cleanHud || MinecraftClient.getInstance().player == null) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        String fps = mc.fpsDebugString.split(" ")[0] + " FPS";
        String coords = String.format("XYZ: %.0f %.0f %.0f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
        String brand = "GlitchV1 @werised";
        DrawableHelper.drawStringWithShadow(ms, mc.textRenderer, brand, 4, 4, 0xFF00FF);
        DrawableHelper.drawStringWithShadow(ms, mc.textRenderer, fps, 4, 16, 0x00FF00);
        DrawableHelper.drawStringWithShadow(ms, mc.textRenderer, coords, 4, 28, 0xFFFFFF);
    }
}
