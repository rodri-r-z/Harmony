package net.brydget.util;

import net.brydget.BackendPlugin;
import net.brydget.command.DynamicCommandListener;
import net.brydget.command.RealCommandListener;
import net.brydget.command.UnregisteredCommandListener;
import net.brydget.internal.CommandPacketListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;

import java.lang.reflect.Field;

public abstract class CommandUtil {
    static Field bukkitCommandMap;
    static BackendPlugin instance;
    static Server server;

    public static void _init() {
        instance = BackendPlugin.getInstance();
        server = instance.getServer();
    }

    static {
        try {
            bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Utility class to dynamically create commands
    // And listen for chat commands without registering them

    /**
     * Registers a listener for a command, taking into account whether it is a legacy command.
     *
     * @param  listener  the listener to be registered
     */
    public static void registerListener(UnregisteredCommandListener listener) {
        // Check if legacy
        if (BackendPlugin.getInstance().isLegacy()) {
            new CommandPacketListener(listener.getCommandName(), listener);
            return;
        }
        new CommandPacketListener(listener.getCommandName(), listener, true);
    }

    /**
     * Registers a listener for the given command name and executor.
     *
     * @param  commandName  the name of the command to register the listener for
     * @param  executor     the command executor to register
     */
    public static void registerListener(String commandName, CommandExecutor executor) {
        final PluginCommand command = instance.getCommand(commandName);
        // Check if command is null
        if (command == null) {
            return;
        }
        command.setExecutor(executor);
    }

    /**
     * Register a dynamic command listener.
     *
     * @param  listener   the dynamic command listener to register
     */
    public static void registerListener(DynamicCommandListener listener) {
        try {
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(listener.getCommandName(), listener);
            server.getClass().getMethod("syncCommands").invoke(server);

            // Register the listener
            registerListener(listener.getCommandName(), new RealCommandListener(listener));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}