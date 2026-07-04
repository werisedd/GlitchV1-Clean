package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {
    
    protected MixinTitleScreen() {
        super(new LiteralText(""));
    }
    
    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (GlitchV1.config.devBranding) {
            String text = "GlitchV1 by @werised";
            this.textRenderer.drawWithShadow(matrices, text, 
                this.width - this.textRenderer.getWidth(text) - 5, 
                this.height - 15, 0xFF00FF);
        }
    }
}
