package com.glitchv1.config;

import com.glitchv1.GlitchV1;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class GlitchScreen extends Screen {
    
    public GlitchScreen() {
        super(new LiteralText("GlitchV1 Menu"));
    }
    
    @Override
    protected void init() {
        this.addButton(new ButtonWidget(10, 40, 150, 20, new LiteralText("Fullbright: " + (GlitchV1.config.fullbright ? "ON" : "OFF")), button -> {
            GlitchV1.config.fullbright = !GlitchV1.config.fullbright;
            button.setMessage(new LiteralText("Fullbright: " + (GlitchV1.config.fullbright ? "ON" : "OFF")));
        }));
        
        this.addButton(new ButtonWidget(10, 65, 150, 20, new LiteralText("No Fire: " + (GlitchV1.config.noFireOverlay ? "ON" : "OFF")), button -> {
            GlitchV1.config.noFireOverlay = !GlitchV1.config.noFireOverlay;
            button.setMessage(new LiteralText("No Fire: " + (GlitchV1.config.noFireOverlay ? "ON" : "OFF")));
        }));
        
        this.addButton(new ButtonWidget(10, 90, 150, 20, new LiteralText("No Hurt Cam: " + (GlitchV1.config.noHurtCam ? "ON" : "OFF")), button -> {
            GlitchV1.config.noHurtCam = !GlitchV1.config.noHurtCam;
            button.setMessage(new LiteralText("No Hurt Cam: " + (GlitchV1.config.noHurtCam ? "ON" : "OFF")));
        }));
        
        this.addButton(new ButtonWidget(10, 115, 150, 20, new LiteralText("No Totem: " + (GlitchV1.config.noTotemParticles ? "ON" : "OFF")), button -> {
            GlitchV1.config.noTotemParticles = !GlitchV1.config.noTotemParticles;
            button.setMessage(new LiteralText("No Totem: " + (GlitchV1.config.noTotemParticles ? "ON" : "OFF")));
        }));
        
        this.addButton(new ButtonWidget(10, 140, 150, 20, new LiteralText("No Break: " + (GlitchV1.config.noBreakParticles ? "ON" : "OFF")), button -> {
            GlitchV1.config.noBreakParticles = !GlitchV1.config.noBreakParticles;
            button.setMessage(new LiteralText("No Break: " + (GlitchV1.config.noBreakParticles ? "ON" : "OFF")));
        }));
    }
    
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, "GlitchV1 by @werised", this.width / 2, 15, 0xFF00FF);
        super.render(matrices, mouseX, mouseY, delta);
    }
                                              }
