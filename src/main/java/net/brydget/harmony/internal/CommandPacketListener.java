package net.brydget.harmony.internal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import net.brydget.harmony.packet.StandardPacketListener;
import net.brydget.harmony.command.UnregisteredCommandListener;

public class CommandPacketListener extends StandardPacketListener {

    String commandName;
    String trimmedCommandName;
    UnregisteredCommandListener listener;

    public CommandPacketListener(String commandName, UnregisteredCommandListener listener) {
        super(
                // When this constructor is instanced, that means the server isn't legacy
                PacketType.Play.Client.CHAT_COMMAND
                        .isSupported() ? PacketType.Play.Client.CHAT_COMMAND : PacketType.Play.Client.CHAT
        );

        this.commandName = (PacketType.Play.Client.CHAT_COMMAND
                .isSupported() ? "" : "/") + commandName+" ";
        trimmedCommandName = commandName.trim();
        this.listener = listener;
    }


    @Override
    public void whenClientBoundFired(PacketEvent packetEvent) {
        // Check for Packet type
        final String message = packetEvent.getPacket().getStrings().read(0);

        if (message.startsWith(commandName) || message.equals(trimmedCommandName)) {
            final boolean result = listener.whenExecute(
                    message
                            .replaceFirst(commandName, "")
                            .trim()
                            .split(" "),
                    packetEvent.getPlayer(),
                    packetEvent
            );
            // If false, the message "Player executed command"
            // will not be sent to the console
            if (!result) packetEvent.setCancelled(true);
        }
    }

    @Override
    public void whenServerBoundFired(PacketEvent event) {
        whenClientBoundFired(event);
    }
}
