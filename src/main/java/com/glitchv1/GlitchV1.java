package com.glitchv1;

import com.glitchv1.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

public class GlitchV1 implements ModInitializer {
    public static ConfigManager config;

    @Override
    public void onInitialize() {
        config = new ConfigManager();
        System.out.println("[GlitchV1 Pro] by @werised loaded!");
    }
}
