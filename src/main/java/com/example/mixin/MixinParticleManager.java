package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class MixinParticleManager {
    
    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", 
            at = @At("HEAD"), cancellable = true)
    private void blockParticles(ParticleEffect parameters, double x, double y, double z, 
                               double vx, double vy, double vz, CallbackInfo ci) {
        if (GlitchV1.config.noTotemParticles && 
            parameters.getType() == ParticleTypes.TOTEM_OF_UNDYING) {
            ci.cancel();
        }
        if (GlitchV1.config.noBreakParticles && 
            parameters.getType() == ParticleTypes.BLOCK) {
            ci.cancel();
        }
    }
}
