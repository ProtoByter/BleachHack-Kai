package org.bleachhack.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.IgnoreBorders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket.class)
public abstract class MixinIgnoreBorders {
    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at=@At("HEAD"), cancellable = true)
    private void cancelApply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        if (ModuleManager.getModule(IgnoreBorders.class).isEnabled()) {
            ci.cancel();
        }
    }
}
