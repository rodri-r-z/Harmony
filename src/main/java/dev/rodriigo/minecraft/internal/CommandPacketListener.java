package dev.rodriigo.minecraft.internal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import dev.rodriigo.minecraft.packet.StandardPacketListener;
import dev.rodriigo.minecraft.command.UnregisteredCommandListener;

public class CommandPacketListener extends StandardPacketListener {

    String commandName;
    UnregisteredCommandListener listener;

    public CommandPacketListener(String commandName, UnregisteredCommandListener listener) {
        super(
                // We use CHAT instead of CHAT_COMMAND
                // Because CHAT_COMMAND isn't available on legacy versions
                PacketType.Play.Client.CHAT
        );

        this.commandName = "/"+commandName;
        this.listener = listener;
    }

    public CommandPacketListener(String commandName, UnregisteredCommandListener listener, boolean ignored) {
        super(
                // When this constructor is instanced, that means the server isn't legacy
                PacketType.Play.Client.CHAT_COMMAND
        );

        this.commandName = commandName;
        this.listener = listener;
    }


    @Override
    public void whenClientBoundFired(PacketEvent packetEvent) {
        // Check for Packet type
        final String message = packetEvent.getPacket().getStrings().read(0);

        if (message.startsWith(commandName)) {
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
}
