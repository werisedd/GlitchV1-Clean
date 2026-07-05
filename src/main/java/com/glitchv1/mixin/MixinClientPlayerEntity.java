package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(method = "attack", at = @At("HEAD"))
    private void onAttack(Entity target, CallbackInfo ci) {
        if (!GlitchV1.config.hitSounds) return;
        if (target instanceof LivingEntity) {
            ((ClientPlayerEntity)(Object)this).playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 0.8f, 1.2f);
        }
    }
}
