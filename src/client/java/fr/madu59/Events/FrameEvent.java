package fr.madu59.Events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class FrameEvent {
    public interface FrameEnd {
        void onFrameEnd();

        Event<FrameEnd> EVENT = EventFactory.createArrayBacked(FrameEnd.class,
            (callbacks) -> () -> {
                for (FrameEnd listener : callbacks) {
                    listener.onFrameEnd();
                }
            }
        );
    }
}
