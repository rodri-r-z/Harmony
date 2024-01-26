package net.brydget.harmony.discord;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ApiManager;

public class DiscordSRVBridge implements NormalizedDiscordBridge {

    // Utility class to connect Discord
    // With Minecraft using DiscordSRV or
    // JDA for Discord. This time is using DiscordSRV

    ApiManager apiManager;

    public DiscordSRVBridge() {
        apiManager = DiscordSRV.api;
    }


    @Override
    public void awaitReady() {
        // Ignore.
        // DiscordSRV is always ready
        // when reached this point
    }
}