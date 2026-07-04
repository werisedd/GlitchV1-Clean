package com.glitchv1.mixin;

import com.glitchv1.GlitchV1;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class MixinClientWorld {
    
    @Inject(method = "getTimeOfDay", at = @At("RETURN"), cancellable = true)
    private void customTime(CallbackInfoReturnable<Long> cir) {
        if (GlitchV1.config.customTime) {
            cir.setReturnValue(GlitchV1.config.customTimeTicks);
        }
    }
}
