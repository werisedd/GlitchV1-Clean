package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
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
        if (GlitchV1.config.noFireOverlay) {
            ci.cancel();
        }
    }
}
