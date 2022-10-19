package org.bleachhack.module.mods;

import net.minecraft.world.GameMode;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class ForceSurvival extends Module {

    public GameMode normalGameMode = GameMode.DEFAULT;
    public boolean listenToUpdates = true;
    public ForceSurvival() {
        super("ForceSurvival", KEY_UNBOUND, ModuleCategory.LIVEOVERFLOW, "Forces you to be in survival mode.");
    }

    @Override
    public void onEnable(boolean inWorld) {
        super.onEnable(inWorld);
        if (inWorld) {
            listenToUpdates = false;
            mc.interactionManager.setGameMode(GameMode.SURVIVAL);
            listenToUpdates = true;
        }
    }

    @Override
    public void onDisable(boolean inWorld) {
        super.onDisable(inWorld);
        if (inWorld) {
            listenToUpdates = false;
            mc.interactionManager.setGameMode(normalGameMode);
            listenToUpdates = true;
        }
    }
}
