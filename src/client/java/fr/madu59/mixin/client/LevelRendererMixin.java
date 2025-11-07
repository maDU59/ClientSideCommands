package fr.madu59.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fr.madu59.Events.FrameEvent.FrameEnd;
import net.minecraft.client.gui.render.GuiRenderer;

@Mixin(GuiRenderer.class)
public abstract class LevelRendererMixin {
    @Inject(method = "render", at = @At("RETURN"))
    private void afterRender(CallbackInfo ci) {
        FrameEnd.EVENT.invoker().onFrameEnd();
	}
}