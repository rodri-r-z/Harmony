package dev.rodriigo.minecraft.packet.client;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class ClientBoundChatMessagePacket extends PacketContainer {

    public ClientBoundChatMessagePacket() {
        super();
    }

    public String getMessageJson() {
        return getChatComponents().read(0).getJson();
    }

}
