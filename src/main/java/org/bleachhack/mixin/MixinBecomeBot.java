package org.bleachhack.mixin;

import com.mojang.logging.LogUtils;
import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.BecomeBot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.class)
public abstract class MixinBecomeBot {
    static private final int PRECISION = 100;

    private static float removePrecisionFloat(float value) {
        return (float)removePrecisionDouble(value);
    }

    private static double removePrecisionDouble(double value) {
        return (double) (long)(value * PRECISION) / PRECISION;
    }

    private static boolean moduleEnabled() {
        return ModuleManager.getModule(BecomeBot.class).isEnabled();
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static double modifyX(double value)
    {
        if (moduleEnabled()) {
            double retval = removePrecisionDouble(value);

            if (((long)retval * 1000) % 10 != 0) {
                LogUtils.getLogger().warn("X was imprecise");
            }

            return retval;
        } else {
            return value;
        }
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private static double modifyZ(double value)
    {
        if (moduleEnabled()) {
            double retval = removePrecisionDouble(value);

            if (((long)retval * 1000) % 10 != 0) {
                LogUtils.getLogger().warn("Z was imprecise");
            }

            return retval;
        } else {
            return value;
        }
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static float modifyYaw(float value)
    {
        if (moduleEnabled()) {
            float retval = removePrecisionFloat(value);

            if (((long) retval * 1000) % 10 != 0) {
                LogUtils.getLogger().warn("Yaw was imprecise");
            }

            return retval;
        }
        else {
            return value;
        }
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static float modifyPitch(float value)
    {
        if (moduleEnabled()) {
            float retval = removePrecisionFloat(value);

            if (((long) retval * 1000) % 10 != 0) {
                LogUtils.getLogger().warn("Pitch was imprecise");
            }

            return retval;
        }
        else {
            return value;
        }
    }
}
