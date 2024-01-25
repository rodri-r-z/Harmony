package dev.rodriigo.minecraft.util;

import dev.rodriigo.minecraft.BackendPlugin;
import dev.rodriigo.minecraft.command.DynamicCommandListener;
import dev.rodriigo.minecraft.command.RealCommandListener;
import dev.rodriigo.minecraft.command.UnregisteredCommandListener;
import dev.rodriigo.minecraft.internal.CommandPacketListener;
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

    public static void registerListener(UnregisteredCommandListener listener) {
        // Check if legacy
        if (BackendPlugin.getInstance().isLegacy()) {
            new CommandPacketListener(listener.getCommandName(), listener);
            return;
        }
        new CommandPacketListener(listener.getCommandName(), listener, true);
    }

    public static void registerListener(String commandName, CommandExecutor executor) {
        final PluginCommand command = instance.getCommand(commandName);
        // Check if command is null
        if (command == null) {
            return;
        }
        command.setExecutor(executor);
    }

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