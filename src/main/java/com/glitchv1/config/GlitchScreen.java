package com.glitchv1.config;

import com.glitchv1.GlitchV1;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;
import java.util.Random;

public class GlitchScreen extends Screen {
    private int tickCounter = 0;
    private final Random random = new Random();
    
    // Переменные для глитч-эффекта
    private int glitchTimer = 0;
    private boolean glitchActive = false;
    private int glitchOffsetX = 0;
    private int glitchOffsetY = 0;

    public GlitchScreen() {
        super(new LiteralText("GLITCH V1"));
    }

    @Override
    protected void init() {
        int x = 15;
        int y = 55;
        int w = 180;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Fullbright: " + onOff(GlitchV1.config.fullbright)), btn -> {
            GlitchV1.config.fullbright = !GlitchV1.config.fullbright;
            btn.setMessage(new LiteralText("Fullbright: " + onOff(GlitchV1.config.fullbright)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("No Fire: " + onOff(GlitchV1.config.noFireOverlay)), btn -> {
            GlitchV1.config.noFireOverlay = !GlitchV1.config.noFireOverlay;
            btn.setMessage(new LiteralText("No Fire: " + onOff(GlitchV1.config.noFireOverlay)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("No Hurt Cam: " + onOff(GlitchV1.config.noHurtCam)), btn -> {
            GlitchV1.config.noHurtCam = !GlitchV1.config.noHurtCam;
            btn.setMessage(new LiteralText("No Hurt Cam: " + onOff(GlitchV1.config.noHurtCam)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Custom Sky: " + onOff(GlitchV1.config.customSky)), btn -> {
            GlitchV1.config.customSky = !GlitchV1.config.customSky;
            btn.setMessage(new LiteralText("Custom Sky: " + onOff(GlitchV1.config.customSky)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Hit Sounds: " + onOff(GlitchV1.config.hitSounds)), btn -> {
            GlitchV1.config.hitSounds = !GlitchV1.config.hitSounds;
            btn.setMessage(new LiteralText("Hit Sounds: " + onOff(GlitchV1.config.hitSounds)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Kill Sounds: " + onOff(GlitchV1.config.killSounds)), btn -> {
            GlitchV1.config.killSounds = !GlitchV1.config.killSounds;
            btn.setMessage(new LiteralText("Kill Sounds: " + onOff(GlitchV1.config.killSounds)));
        })); y += 24;
        
        this.addButton(new ButtonWidget(x, y, w, 20, new LiteralText("Clean HUD: " + onOff(GlitchV1.config.cleanHud)), btn -> {
            GlitchV1.config.cleanHud = !GlitchV1.config.cleanHud;
            btn.setMessage(new LiteralText("Clean HUD: " + onOff(GlitchV1.config.cleanHud)));
        }));
    }

    private String onOff(boolean val) { return val ? "§aON" : "§cOFF"; }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Тёмный фон с градиентом
        this.fillGradient(matrices, 0, 0, this.width, this.height, 0xCC000011, 0xDD000033);
        
        tickCounter++;
        glitchTimer++;
        
        // Каждые 40-60 тиков — глитч
        if (glitchTimer > 40 + random.nextInt(20)) {
            glitchActive = true;
            glitchOffsetX = random.nextInt(8) - 4;
            glitchOffsetY = random.nextInt(4) - 2;
            glitchTimer = 0;
        } else if (glitchTimer > 5) {
            glitchActive = false;
        }
        
        // Заголовок
        String title = "GLITCH VISUALS";
        int titleColor = 0xAA44FF;
        
        if (glitchActive) {
            // Красный слой (сдвинут влево)
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f - 3 + glitchOffsetX,
                12 + glitchOffsetY, 0xFF0044);
            // Синий слой (сдвинут вправо)
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f + 3 + glitchOffsetX,
                12 - glitchOffsetY, 0x0044FF);
        }
        
        // Основной белый слой
        this.textRenderer.drawWithShadow(matrices, title,
            this.width / 2f - this.textRenderer.getWidth(title) / 2f + glitchOffsetX,
            12 + glitchOffsetY, titleColor);
        
        // Версия
        String version = "v1.0";
        this.textRenderer.drawWithShadow(matrices, version,
            this.width / 2f - this.textRenderer.getWidth(version) / 2f,
            24, 0x888888);
        
        // Разделитель
        String line = "=================================";
        this.textRenderer.drawWithShadow(matrices, line,
            this.width / 2f - this.textRenderer.getWidth(line) / 2f,
            36, 0x444444);
        
        // Брендинг снизу
        String brand = "Dev: @werised  |  GlitchV1 Pro";
        this.textRenderer.drawWithShadow(matrices, brand,
            this.width / 2f - this.textRenderer.getWidth(brand) / 2f,
            this.height - 15, 0x666666);
        
        super.render(matrices, mouseX, mouseY, delta);
    }
            }
