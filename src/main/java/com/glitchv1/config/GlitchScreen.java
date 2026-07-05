package com.glitchv1.config;

import com.glitchv1.GlitchV1;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import java.util.Random;

public class GlitchScreen extends Screen {
    private int tickCounter = 0;
    private final Random random = new Random();
    private int glitchTimer = 0;
    private boolean glitchActive = false;
    private int glitchOffsetX = 0;
    private int glitchOffsetY = 0;

    public GlitchScreen() {
        super(new LiteralText("GLITCH V1"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 55;
        int buttonWidth = 160;
        int buttonHeight = 20;

        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY, buttonWidth, buttonHeight, "Fullbright", GlitchV1.config.fullbright, btn -> {
            GlitchV1.config.fullbright = !GlitchV1.config.fullbright;
            ((GlitchButton)btn).toggled = GlitchV1.config.fullbright;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 26, buttonWidth, buttonHeight, "No Fire", GlitchV1.config.noFireOverlay, btn -> {
            GlitchV1.config.noFireOverlay = !GlitchV1.config.noFireOverlay;
            ((GlitchButton)btn).toggled = GlitchV1.config.noFireOverlay;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 52, buttonWidth, buttonHeight, "No Hurt Cam", GlitchV1.config.noHurtCam, btn -> {
            GlitchV1.config.noHurtCam = !GlitchV1.config.noHurtCam;
            ((GlitchButton)btn).toggled = GlitchV1.config.noHurtCam;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 78, buttonWidth, buttonHeight, "Custom Sky", GlitchV1.config.customSky, btn -> {
            GlitchV1.config.customSky = !GlitchV1.config.customSky;
            ((GlitchButton)btn).toggled = GlitchV1.config.customSky;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 104, buttonWidth, buttonHeight, "Hit Sounds", GlitchV1.config.hitSounds, btn -> {
            GlitchV1.config.hitSounds = !GlitchV1.config.hitSounds;
            ((GlitchButton)btn).toggled = GlitchV1.config.hitSounds;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 130, buttonWidth, buttonHeight, "Kill Sounds", GlitchV1.config.killSounds, btn -> {
            GlitchV1.config.killSounds = !GlitchV1.config.killSounds;
            ((GlitchButton)btn).toggled = GlitchV1.config.killSounds;
        }));
        
        this.addButton(new GlitchButton(centerX - buttonWidth / 2, startY + 156, buttonWidth, buttonHeight, "Clean HUD", GlitchV1.config.cleanHud, btn -> {
            GlitchV1.config.cleanHud = !GlitchV1.config.cleanHud;
            ((GlitchButton)btn).toggled = GlitchV1.config.cleanHud;
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Рисуем блюр-фон через градиент
        this.fillGradient(matrices, 0, 0, this.width, this.height, 0xBB0A0A1A, 0xBB050510);
        
        tickCounter++;
        glitchTimer++;
        
        if (glitchTimer > 30 + random.nextInt(20)) {
            glitchActive = true;
            glitchOffsetX = random.nextInt(6) - 3;
            glitchOffsetY = random.nextInt(3) - 1;
            glitchTimer = 0;
        } else if (glitchTimer > 4) {
            glitchActive = false;
        }

        // Заголовок GLITCH VISUALS
        String title = "GLITCH VISUALS";
        int titleColor = Color.HSBtoRGB((tickCounter % 100) / 100f, 0.8f, 1f) | 0xFF000000;
        
        if (glitchActive) {
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f - 2 + glitchOffsetX,
                10 + glitchOffsetY, 0xFFFF0055);
            this.textRenderer.drawWithShadow(matrices, title,
                this.width / 2f - this.textRenderer.getWidth(title) / 2f + 2 + glitchOffsetX,
                10 - glitchOffsetY, 0xFF0055FF);
        }
        
        this.textRenderer.drawWithShadow(matrices, title,
            this.width / 2f - this.textRenderer.getWidth(title) / 2f + glitchOffsetX,
            10 + glitchOffsetY, titleColor);
        
        // Версия
        String version = "v1.0 Pro";
        this.textRenderer.drawWithShadow(matrices, version,
            this.width / 2f - this.textRenderer.getWidth(version) / 2f,
            22, 0xFF888888);
        
        // Линия-разделитель
        drawHorizontalLine(matrices, 40, this.width - 40, 34, 0x33444466);
        
        // Рендер кнопок
        super.render(matrices, mouseX, mouseY, delta);
        
        // Футер
        String footer = "Dev: @werised";
        this.textRenderer.drawWithShadow(matrices, footer,
            this.width / 2f - this.textRenderer.getWidth(footer) / 2f,
            this.height - 14, 0xFF555555);
    }

    // Кастомный класс кнопки с закруглением
    public static class GlitchButton extends ButtonWidget {
        public boolean toggled;
        private String label;

        public GlitchButton(int x, int y, int width, int height, String text, boolean toggled, PressAction onPress) {
            super(x, y, width, height, new LiteralText(""), onPress);
            this.toggled = toggled;
            this.label = text;
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            if (!this.visible) return;
            
            // Рисуем закруглённый фон кнопки
            int bgColor = this.isHovered() ? 0x55333355 : 0x33222244;
            drawRoundedRect(matrices, this.x, this.y, this.x + this.width, this.y + this.height, 4, bgColor);
            
            // Текст кнопки
            String displayText = label + ": " + (toggled ? "§aON" : "§cOFF");
            int textColor = toggled ? 0xAAFFAA : 0xFFAAAA;
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, displayText,
                this.x + 8, this.y + (this.height - 8) / 2f, textColor);
        }
    }

    // Функция рисования закруглённого прямоугольника
    public static void drawRoundedRect(MatrixStack matrices, int left, int top, int right, int bottom, int radius, int color) {
        float a = (color >> 24 & 255) / 255.0F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
        
        for (int i = 0; i <= 90; i += 15) {
            double angle = Math.toRadians(i);
            buffer.vertex(right - radius + Math.cos(angle) * radius, bottom - radius + Math.sin(angle) * radius, 0).color(r, g, b, a).next();
        }
        for (int i = 90; i <= 180; i += 15) {
            double angle = Math.toRadians(i);
            buffer.vertex(left + radius + Math.cos(angle) * radius, bottom - radius + Math.sin(angle) * radius, 0).color(r, g, b, a).next();
        }
        for (int i = 180; i <= 270; i += 15) {
            double angle = Math.toRadians(i);
            buffer.vertex(left + radius + Math.cos(angle) * radius, top + radius + Math.sin(angle) * radius, 0).color(r, g, b, a).next();
        }
        for (int i = 270; i <= 360; i += 15) {
            double angle = Math.toRadians(i);
            buffer.vertex(right - radius + Math.cos(angle) * radius, top + radius + Math.sin(angle) * radius, 0).color(r, g, b, a).next();
        }
        
        tessellator.draw();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawHorizontalLine(MatrixStack matrices, int startX, int endX, int y, int color) {
        if (endX < startX) { int i = startX; startX = endX; endX = i; }
        fill(matrices, startX, y, endX + 1, y + 1, color);
    }
}
