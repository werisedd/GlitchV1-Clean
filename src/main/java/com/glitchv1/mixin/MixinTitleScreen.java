package com.glitchv1.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;
import java.util.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {
    private int ticks = 0;
    private final Random rand = new Random();
    protected MixinTitleScreen() { super(new LiteralText("")); }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(MatrixStack m, int mx, int my, float d, CallbackInfo ci) {
        this.renderBackground(m);
        ticks++;
        String line1 = "GLITCH V1";
        String line2 = "by @werised";
        float wave1 = MathHelper.sin(ticks * 0.05f) * 5.0f;
        float wave2 = MathHelper.cos(ticks * 0.05f) * 5.0f;
        int color1 = (ticks % 30 > 15) ? 0xFF00FF : 0x00FFFF;
        
        // Строка 1 с глитч-эффектом
        if (rand.nextFloat() < 0.03f) {
            this.textRenderer.drawWithShadow(m, line1,
                this.width / 2f - this.textRenderer.getWidth(line1) / 2f + rand.nextInt(12) - 6,
                this.height / 2f - 20 + rand.nextInt(8) - 4, color1);
        } else {
            this.textRenderer.drawWithShadow(m, line1,
                this.width / 2f - this.textRenderer.getWidth(line1) / 2f + wave1,
                this.height / 2f - 20, color1);
        }
        
        // Строка 2 с разработчиком
        this.textRenderer.drawWithShadow(m, line2,
            this.width / 2f - this.textRenderer.getWidth(line2) / 2f + wave2,
            this.height / 2f, 0xFFFFFF);
        
        super.render(m, mx, my, d);
        ci.cancel();
    }
}
