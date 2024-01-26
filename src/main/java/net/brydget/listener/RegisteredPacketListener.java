package net.brydget.listener;

import com.comphenix.protocol.events.PacketEvent;

public interface RegisteredPacketListener {

    void whenClientBoundFired(PacketEvent packet);

    void whenServerBoundFired(PacketEvent packet);
}
