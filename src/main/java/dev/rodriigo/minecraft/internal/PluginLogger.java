package dev.rodriigo.minecraft.internal;

import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PluginLogger {

    static final BackendPlugin instance = BackendPlugin.getInstance();
    static final Logger baseLogger = instance.getLogger();
    static final CommandSender console = instance.getConsole();

    public void info(String message) {
        baseLogger.info(message);
    }

    public void info(Supplier<String> messages) {
        baseLogger.info(messages);
    }

    public void fine(String message) {
        baseLogger.fine(message);
    }

    public void fine(Supplier<String> messages) {
        baseLogger.fine(messages);
    }

    public void finer(String message) {
        baseLogger.finer(message);
    }

    public void finer(Supplier<String> messages) {
        baseLogger.finer(messages);
    }

    public void finest(String message) {
        baseLogger.finest(message);
    }

    public void finest(Supplier<String> messages) {
        baseLogger.finest(messages);
    }

    public void severe(String message) {
        baseLogger.severe(message);
    }

    public void warn(String message) {
        baseLogger.warning(message);
    }

    public void warn(Supplier<String> messages) {
        baseLogger.warning(messages);
    }

    public void warning(String message) {
        warn(message);
    }

    public void warning(Supplier<String> messages) {
        warn(messages);
    }

    public void error(String message) {
        severe(message);
    }

    public void addHandler(Handler handler) {
        baseLogger.addHandler(handler);
    }

    public Filter getFilter() {
        return baseLogger.getFilter();
    }

    public Level getLevel() {
        return baseLogger.getLevel();
    }

    public boolean isLoggable(Level level) {
        return baseLogger.isLoggable(level);
    }

    public Handler[] getHandlers() {
        return baseLogger.getHandlers();
    }

    public ResourceBundle getResourceBundle() {
        return baseLogger.getResourceBundle();
    }

    public String getName() {
        return baseLogger.getName();
    }

    public void sendColoredMessage(String message) {
        console.sendMessage(
                ChatColor.translateAlternateColorCodes('&', message)
        );
    }
}
