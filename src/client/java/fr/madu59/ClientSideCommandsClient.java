package fr.madu59;

import fr.madu59.Commands.CalcCommand;
import fr.madu59.Commands.ClearChatCommand;
import fr.madu59.Commands.CopyPositionCommand;
import fr.madu59.Commands.CopyRotationCommand;
import fr.madu59.Commands.CopyUUIDCommand;
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
import net.fabricmc.api.ClientModInitializer;

public class ClientSideCommandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		registerCommands();
	}

	public void registerCommands(){
		PositionCommand.register();
		RotationCommand.register();
		CopyPositionCommand.register();
		CopyRotationCommand.register();
		CopyUUIDCommand.register();
		EmojiCommand.register();
		CalcCommand.register();
		DebugRendererCommand.register();
		DebugCommand.register();
		FOVCommand.register();
		GammaCommand.register();
		UUIDCommand.register();
		LookAtCommand.register();
		NetherCoordinatesCommand.register();
		OverworldCoordinatesCommand.register();
		ClearChatCommand.register();
		QuitCommand.register();
		HUDScaleCommand.register();
		ReloadChunksCommand.register();
		ReloadRessourcePacksCommand.register();
		ScreenshotCommand.register();
		WikiCommand.register();
		UpdateWorldIconCommand.register();
	}
}