package fr.madu59;

import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import fr.madu59.Commands.CalcCommand;
import fr.madu59.Commands.ClearChatCommand;
import fr.madu59.Commands.CopyPositionCommand;
import fr.madu59.Commands.CopyRotationCommand;
import fr.madu59.Commands.CopyUUIDCommand;
import fr.madu59.Commands.DateCommand;
import fr.madu59.Commands.DebugCommand;
import fr.madu59.Commands.DebugRendererCommand;
import fr.madu59.Commands.EmojiCommand;
import fr.madu59.Commands.FOVCommand;
import fr.madu59.Commands.GammaCommand;
import fr.madu59.Commands.HUDScaleCommand;
import fr.madu59.Commands.LookAtCommand;
import fr.madu59.Commands.NetherCoordinatesCommand;
import fr.madu59.Commands.OverworldCoordinatesCommand;
import fr.madu59.Commands.PositionCommand;
import fr.madu59.Commands.QuitCommand;
import fr.madu59.Commands.ReloadChunksCommand;
import fr.madu59.Commands.ReloadRessourcePacksCommand;
import fr.madu59.Commands.RotationCommand;
import fr.madu59.Commands.ScreenshotCommand;
import fr.madu59.Commands.UUIDCommand;
import fr.madu59.Commands.UpdateWorldIconCommand;
import fr.madu59.Commands.WikiCommand;

@Mod.EventBusSubscriber(modid = ClientSideCommands.MOD_ID)
public class ClientCommandRegistration {
    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
		PositionCommand.register(event);
		RotationCommand.register(event);
		CopyPositionCommand.register(event);
		CopyRotationCommand.register(event);
		CopyUUIDCommand.register(event);
		EmojiCommand.register(event);
		CalcCommand.register(event);
		DebugRendererCommand.register(event);
		DebugCommand.register(event);
		FOVCommand.register(event);
		GammaCommand.register(event);
		UUIDCommand.register(event);
		LookAtCommand.register(event);
		NetherCoordinatesCommand.register(event);
		OverworldCoordinatesCommand.register(event);
		ClearChatCommand.register(event);
		QuitCommand.register(event);
		HUDScaleCommand.register(event);
		ReloadChunksCommand.register(event);
		ReloadRessourcePacksCommand.register(event);
		ScreenshotCommand.register(event);
		WikiCommand.register(event);
		UpdateWorldIconCommand.register(event);
		DateCommand.register(event);
	}
}