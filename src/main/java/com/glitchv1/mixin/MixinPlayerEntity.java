package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onKill(CallbackInfo ci) {
        if (!GlitchV1.config.killSounds) return;
        PlayerEntity self = (PlayerEntity)(Object)this;
        self.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0.5f);
    }
}
