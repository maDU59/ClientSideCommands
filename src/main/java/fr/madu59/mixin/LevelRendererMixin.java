package fr.madu59.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.madu59.Commands.ScreenshotCommand;
import fr.madu59.Commands.UpdateWorldIconCommand;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public abstract class LevelRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void afterMain(float p_109094_, long p_109095_, boolean bl, CallbackInfo ci) {
        ScreenshotCommand.callMe();
        UpdateWorldIconCommand.callMe();
	}
}