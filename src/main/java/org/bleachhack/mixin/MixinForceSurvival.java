package org.bleachhack.mixin;

import net.minecraft.world.GameMode;
import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.ForceSurvival;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(net.minecraft.client.network.ClientPlayerInteractionManager.class)
public abstract class MixinForceSurvival {
    @Shadow private GameMode gameMode;

    @Inject(method = "setGameMode(Lnet/minecraft/world/GameMode;)V", at = @At("HEAD"), cancellable = true)
    private void gameMode(GameMode _gameMode, CallbackInfo ci) {
        if (ModuleManager.getModule(ForceSurvival.class).listenToUpdates) {
            ModuleManager.getModule(ForceSurvival.class).normalGameMode = _gameMode;
        }
        if (ModuleManager.getModule(ForceSurvival.class).isEnabled()) {
            gameMode = GameMode.SURVIVAL;
            ci.cancel();
        }
    }
}
