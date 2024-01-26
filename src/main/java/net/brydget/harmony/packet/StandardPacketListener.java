package net.brydget.harmony.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.brydget.harmony.BackendPlugin;
import net.brydget.harmony.annotation.NeverNull;
import net.brydget.harmony.listener.RegisteredPacketListener;

public abstract class StandardPacketListener extends PacketAdapter implements RegisteredPacketListener {

    public StandardPacketListener(@NeverNull PacketType... types) {
        super(
                BackendPlugin.getInstance(),
                types
        );
    }

    public StandardPacketListener(PacketType type) {
        super(
                BackendPlugin.getInstance(),
                type
        );
    }

    public StandardPacketListener(ListenerPriority priority, @NeverNull PacketType... types) {
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
