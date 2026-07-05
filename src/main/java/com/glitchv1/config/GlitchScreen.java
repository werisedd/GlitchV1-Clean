package com.glitchv1.config;

import com.glitchv1.GlitchV1;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;
import java.util.Random;

public class GlitchScreen extends Screen {
    private int tickCounter = 0;
    private final Random random = new Random();

    public GlitchScreen() {
        super(new LiteralText("GLITCH V1"));
    }

    @Override
    protected void init() {
        int x = 10;
        int y = 40;
        int w = 200;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Fullbright: " + onOff(GlitchV1.config.fullbright)), btn -> {
            GlitchV1.config.fullbright = !GlitchV1.config.fullbright;
            btn.setMessage(new LiteralText("Fullbright: " + onOff(GlitchV1.config.fullbright)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("No Fire: " + onOff(GlitchV1.config.noFireOverlay)), btn -> {
            GlitchV1.config.noFireOverlay = !GlitchV1.config.noFireOverlay;
            btn.setMessage(new LiteralText("No Fire: " + onOff(GlitchV1.config.noFireOverlay)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("No Hurt Cam: " + onOff(GlitchV1.config.noHurtCam)), btn -> {
            GlitchV1.config.noHurtCam = !GlitchV1.config.noHurtCam;
            btn.setMessage(new LiteralText("No Hurt Cam: " + onOff(GlitchV1.config.noHurtCam)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Custom Sky: " + onOff(GlitchV1.config.customSky)), btn -> {
            GlitchV1.config.customSky = !GlitchV1.config.customSky;
            btn.setMessage(new LiteralText("Custom Sky: " + onOff(GlitchV1.config.customSky)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Hit Sounds: " + onOff(GlitchV1.config.hitSounds)), btn -> {
            GlitchV1.config.hitSounds = !GlitchV1.config.hitSounds;
            btn.setMessage(new LiteralText("Hit Sounds: " + onOff(GlitchV1.config.hitSounds)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Kill Sounds: " + onOff(GlitchV1.config.killSounds)), btn -> {
            GlitchV1.config.killSounds = !GlitchV1.config.killSounds;
            btn.setMessage(new LiteralText("Kill Sounds: " + onOff(GlitchV1.config.killSounds)));
        })); y+=22;
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Clean HUD: " + onOff(GlitchV1.config.cleanHud)), btn -> {
            GlitchV1.config.cleanHud = !GlitchV1.config.cleanHud;
            btn.setMessage(new LiteralText("Clean HUD: " + onOff(GlitchV1.config.cleanHud)));
        }));
    }

    private String onOff(boolean val) { return val ? "§aON" : "§cOFF"; }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        tickCounter++;
        String title = "GLITCH V1";
        float wave = MathHelper.sin(tickCounter * 0.1f) * 3.0f;
        int titleColor = (tickCounter % 20 > 10) ? 0xFF00FF : 0x00FFFF;
        if (random.nextFloat() < 0.08f) {
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f + random.nextInt(8) - 4,
                15 + random.nextInt(4) - 2, titleColor);
        } else {
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f + wave, 15, titleColor);
        }
        String dev = "@werised";
        this.textRenderer.drawWithShadow(matrices, dev,
            this.width / 2f - this.textRenderer.getWidth(dev) / 2f, this.height - 15, 0xAAAAAA);
        super.render(matrices, mouseX, mouseY, delta);
    }
                }
