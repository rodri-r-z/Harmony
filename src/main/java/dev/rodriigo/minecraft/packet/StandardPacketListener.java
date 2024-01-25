package dev.rodriigo.minecraft.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import dev.rodriigo.minecraft.BackendPlugin;
import dev.rodriigo.minecraft.util.RegisteredPacketListener;

public class StandardPacketListener extends PacketAdapter implements RegisteredPacketListener {

    public StandardPacketListener(PacketType... types) {
        super(
                BackendPlugin.getInstance(),
                types
        );
    }

    public StandardPacketListener(ListenerPriority priority, PacketType... types) {
        super(
                BackendPlugin.getInstance(),
                priority,
                types
        );
    }

    public StandardPacketListener(ListenerPriority priority, Iterable<PacketType> types, ListenerOptions... options) {
        super(
                BackendPlugin.getInstance(),
                priority,
                types,
                options
        );
    }

    public StandardPacketListener(ListenerPriority priority, Iterable<PacketType> types) {
        super(
                BackendPlugin.getInstance(),
                priority,
                types
        );
    }

    @Override
    public void onPacketReceiving(PacketEvent packet) {
        whenClientBoundFired(packet);
    }

    @Override
    public void onPacketSending(PacketEvent packet) {
        whenServerBoundFired(packet);
    }

    @Override
    public void whenClientBoundFired(PacketEvent packet) {

    }

    @Override
    public void whenServerBoundFired(PacketEvent packet) {

    }
}