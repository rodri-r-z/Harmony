package net.brydget.harmony.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;

import java.util.Arrays;

public abstract class StandardServerHandler implements ServerPacketHandleInterface {

    public StandardServerHandler() {
        Arrays.stream(this.getClass().getMethods())
                .filter(a -> a.getName().toUpperCase().equals(a.getName()))
                .forEach(a -> {
                    try {
                        final PacketType packetType = (PacketType) PacketType.Play.Server.class.getField(a.getName()).get(PacketType.class);
                        if (!packetType.isSupported()) return;

                        RegisteredPacketMode.getProtocolManager()
                                .addPacketListener(new StandardPacketListener(
                                        packetType
                                ) {
                                    @Override
                                    public void whenClientBoundFired(PacketEvent packet) {
                                        try {
                                            a.invoke(this, packet);
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }

                                    @Override
                                    public void whenServerBoundFired(PacketEvent packet) {
                                        try {
                                            a.invoke(this, packet);
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    @Override
    public void whenServerHandleAbilities(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleAdvancements(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleAnimation(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleAttachEntity(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleAutoRecipe(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBlockAction(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBlockBreakAnimation(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBlockChange(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBlockChangedAck(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBoss(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleBundle(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCamera(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleChat(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleChunksBiomes(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleClearTitles(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCloseWindow(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCollect(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCommands(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCustomChatCompletions(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleCustomPayload(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleDamageEvent(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleDeleteChatMessage(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleDisguisedChat(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityDestroy(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityEffect(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityEquipment(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityHeadRotation(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityLook(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityMetadata(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntitySound(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityStatus(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityTeleport(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleEntityVelocity(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleExperience(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleExplosion(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleGameStateChange(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleHeldItemSlot(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleHurtAnimation(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleInitializeBorder(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleKeepAlive(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleKickDisconnect(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleLightUpdate(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleLogin(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleLookAt(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleMap(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleMapChunk(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleMount(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleMultiBlockChange(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleNamedEntitySpawn(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleNamedSoundEffect(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleNbtQuery(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleOpenBook(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleOpenSignEditor(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleOpenWindow(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleOpenWindowHorse(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleOpenWindowMerchant(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePing(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerCombatEnd(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerCombatEnter(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerCombatKill(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerInfo(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerInfoRemove(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePlayerListHeaderFooter(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandlePosition(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRecipeUpdate(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRecipes(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRelEntityMove(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRelEntityMoveLook(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRemoveEntityEffect(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleResourcePackSend(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleRespawn(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleScoreboardDisplayObjective(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleScoreboardObjective(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleScoreboardScore(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleScoreboardTeam(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSelectAdvancementTab(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleServerData(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleServerDifficulty(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetActionBarText(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetBorderCenter(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetBorderLerpSize(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetBorderSize(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetBorderWarningDelay(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetBorderWarningDistance(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetCooldown(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetSlot(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetSubtitleText(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetTitleText(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSetTitlesAnimation(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSpawnEntity(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSpawnEntityExperienceOrb(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSpawnPosition(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleStatistic(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleStopSound(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleSystemChat(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleTabComplete(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleTags(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleTileEntityData(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUnloadChunk(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUpdateAttributes(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUpdateEnabledFeatures(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUpdateHealth(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUpdateSimulationDistance(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleUpdateTime(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleVehicleMove(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleViewCentre(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleViewDistance(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleWindowData(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleWindowItems(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleWorldEvent(PacketEvent ignored) {

    }

    @Override
    public void whenServerHandleWorldParticles(PacketEvent ignored) {

    }

    @Override public void ABILITIES(PacketEvent o){ whenServerHandleAbilities(o); }
    @Override public void ADVANCEMENTS(PacketEvent o){ whenServerHandleAdvancements(o); }
    @Override public void ANIMATION(PacketEvent o){ whenServerHandleAnimation(o); }
    @Override public void ATTACH_ENTITY(PacketEvent o){ whenServerHandleAttachEntity(o); }
    @Override public void AUTO_RECIPE(PacketEvent o){ whenServerHandleAutoRecipe(o); }
    @Override public void BLOCK_ACTION(PacketEvent o){ whenServerHandleBlockAction(o); }
    @Override public void BLOCK_BREAK_ANIMATION(PacketEvent o){ whenServerHandleBlockBreakAnimation(o); }
    @Override public void BLOCK_CHANGE(PacketEvent o){ whenServerHandleBlockChange(o); }
    @Override public void BLOCK_CHANGED_ACK(PacketEvent o){ whenServerHandleBlockChangedAck(o); }
    @Override public void BOSS(PacketEvent o){ whenServerHandleBoss(o); }
    @Override public void BUNDLE(PacketEvent o){ whenServerHandleBundle(o); }
    @Override public void CAMERA(PacketEvent o){ whenServerHandleCamera(o); }
    @Override public void CHAT(PacketEvent o){ whenServerHandleChat(o); }
    @Override public void CHUNKS_BIOMES(PacketEvent o){ whenServerHandleChunksBiomes(o); }
    @Override public void CLEAR_TITLES(PacketEvent o){ whenServerHandleClearTitles(o); }
    @Override public void CLOSE_WINDOW(PacketEvent o){ whenServerHandleCloseWindow(o); }
    @Override public void COLLECT(PacketEvent o){ whenServerHandleCollect(o); }
    @Override public void COMMANDS(PacketEvent o){ whenServerHandleCommands(o); }
    @Override public void CUSTOM_CHAT_COMPLETIONS(PacketEvent o){ whenServerHandleCustomChatCompletions(o); }
    @Override public void CUSTOM_PAYLOAD(PacketEvent o){ whenServerHandleCustomPayload(o); }
    @Override public void DAMAGE_EVENT(PacketEvent o){ whenServerHandleDamageEvent(o); }
    @Override public void DELETE_CHAT_MESSAGE(PacketEvent o){ whenServerHandleDeleteChatMessage(o); }
    @Override public void DISGUISED_CHAT(PacketEvent o){ whenServerHandleDisguisedChat(o); }
    @Override public void ENTITY_DESTROY(PacketEvent o){ whenServerHandleEntityDestroy(o); }
    @Override public void ENTITY_EFFECT(PacketEvent o){ whenServerHandleEntityEffect(o); }
    @Override public void ENTITY_EQUIPMENT(PacketEvent o){ whenServerHandleEntityEquipment(o); }
    @Override public void ENTITY_HEAD_ROTATION(PacketEvent o){ whenServerHandleEntityHeadRotation(o); }
    @Override public void ENTITY_LOOK(PacketEvent o){ whenServerHandleEntityLook(o); }
    @Override public void ENTITY_METADATA(PacketEvent o){ whenServerHandleEntityMetadata(o); }
    @Override public void ENTITY_SOUND(PacketEvent o){ whenServerHandleEntitySound(o); }
    @Override public void ENTITY_STATUS(PacketEvent o){ whenServerHandleEntityStatus(o); }
    @Override public void ENTITY_TELEPORT(PacketEvent o){ whenServerHandleEntityTeleport(o); }
    @Override public void ENTITY_VELOCITY(PacketEvent o){ whenServerHandleEntityVelocity(o); }
    @Override public void EXPERIENCE(PacketEvent o){ whenServerHandleExperience(o); }
    @Override public void EXPLOSION(PacketEvent o){ whenServerHandleExplosion(o); }
    @Override public void GAME_STATE_CHANGE(PacketEvent o){ whenServerHandleGameStateChange(o); }
    @Override public void HELD_ITEM_SLOT(PacketEvent o){ whenServerHandleHeldItemSlot(o); }
    @Override public void HURT_ANIMATION(PacketEvent o){ whenServerHandleHurtAnimation(o); }
    @Override public void INITIALIZE_BORDER(PacketEvent o){ whenServerHandleInitializeBorder(o); }
    @Override public void KEEP_ALIVE(PacketEvent o){ whenServerHandleKeepAlive(o); }
    @Override public void KICK_DISCONNECT(PacketEvent o){ whenServerHandleKickDisconnect(o); }
    @Override public void LIGHT_UPDATE(PacketEvent o){ whenServerHandleLightUpdate(o); }
    @Override public void LOGIN(PacketEvent o){ whenServerHandleLogin(o); }
    @Override public void LOOK_AT(PacketEvent o){ whenServerHandleLookAt(o); }
    @Override public void MAP(PacketEvent o){ whenServerHandleMap(o); }
    @Override public void MAP_CHUNK(PacketEvent o){ whenServerHandleMapChunk(o); }
    @Override public void MOUNT(PacketEvent o){ whenServerHandleMount(o); }
    @Override public void MULTI_BLOCK_CHANGE(PacketEvent o){ whenServerHandleMultiBlockChange(o); }
    @Override public void NAMED_ENTITY_SPAWN(PacketEvent o){ whenServerHandleNamedEntitySpawn(o); }
    @Override public void NAMED_SOUND_EFFECT(PacketEvent o){ whenServerHandleNamedSoundEffect(o); }
    @Override public void NBT_QUERY(PacketEvent o){ whenServerHandleNbtQuery(o); }
    @Override public void OPEN_BOOK(PacketEvent o){ whenServerHandleOpenBook(o); }
    @Override public void OPEN_SIGN_EDITOR(PacketEvent o){ whenServerHandleOpenSignEditor(o); }
    @Override public void OPEN_WINDOW(PacketEvent o){ whenServerHandleOpenWindow(o); }
    @Override public void OPEN_WINDOW_HORSE(PacketEvent o){ whenServerHandleOpenWindowHorse(o); }
    @Override public void OPEN_WINDOW_MERCHANT(PacketEvent o){ whenServerHandleOpenWindowMerchant(o); }
    @Override public void PING(PacketEvent o){ whenServerHandlePing(o); }
    @Override public void PLAYER_COMBAT_END(PacketEvent o){ whenServerHandlePlayerCombatEnd(o); }
    @Override public void PLAYER_COMBAT_ENTER(PacketEvent o){ whenServerHandlePlayerCombatEnter(o); }
    @Override public void PLAYER_COMBAT_KILL(PacketEvent o){ whenServerHandlePlayerCombatKill(o); }
    @Override public void PLAYER_INFO(PacketEvent o){ whenServerHandlePlayerInfo(o); }
    @Override public void PLAYER_INFO_REMOVE(PacketEvent o){ whenServerHandlePlayerInfoRemove(o); }
    @Override public void PLAYER_LIST_HEADER_FOOTER(PacketEvent o){ whenServerHandlePlayerListHeaderFooter(o); }
    @Override public void POSITION(PacketEvent o){ whenServerHandlePosition(o); }
    @Override public void RECIPE_UPDATE(PacketEvent o){ whenServerHandleRecipeUpdate(o); }
    @Override public void RECIPES(PacketEvent o){ whenServerHandleRecipes(o); }
    @Override public void REL_ENTITY_MOVE(PacketEvent o){ whenServerHandleRelEntityMove(o); }
    @Override public void REL_ENTITY_MOVE_LOOK(PacketEvent o){ whenServerHandleRelEntityMoveLook(o); }
    @Override public void REMOVE_ENTITY_EFFECT(PacketEvent o){ whenServerHandleRemoveEntityEffect(o); }
    @Override public void RESOURCE_PACK_SEND(PacketEvent o){ whenServerHandleResourcePackSend(o); }
    @Override public void RESPAWN(PacketEvent o){ whenServerHandleRespawn(o); }
    @Override public void SCOREBOARD_DISPLAY_OBJECTIVE(PacketEvent o){ whenServerHandleScoreboardDisplayObjective(o); }
    @Override public void SCOREBOARD_OBJECTIVE(PacketEvent o){ whenServerHandleScoreboardObjective(o); }
    @Override public void SCOREBOARD_SCORE(PacketEvent o){ whenServerHandleScoreboardScore(o); }
    @Override public void SCOREBOARD_TEAM(PacketEvent o){ whenServerHandleScoreboardTeam(o); }
    @Override public void SELECT_ADVANCEMENT_TAB(PacketEvent o){ whenServerHandleSelectAdvancementTab(o); }
    @Override public void SERVER_DATA(PacketEvent o){ whenServerHandleServerData(o); }
    @Override public void SERVER_DIFFICULTY(PacketEvent o){ whenServerHandleServerDifficulty(o); }
    @Override public void SET_ACTION_BAR_TEXT(PacketEvent o){ whenServerHandleSetActionBarText(o); }
    @Override public void SET_BORDER_CENTER(PacketEvent o){ whenServerHandleSetBorderCenter(o); }
    @Override public void SET_BORDER_LERP_SIZE(PacketEvent o){ whenServerHandleSetBorderLerpSize(o); }
    @Override public void SET_BORDER_SIZE(PacketEvent o){ whenServerHandleSetBorderSize(o); }
    @Override public void SET_BORDER_WARNING_DELAY(PacketEvent o){ whenServerHandleSetBorderWarningDelay(o); }
    @Override public void SET_BORDER_WARNING_DISTANCE(PacketEvent o){ whenServerHandleSetBorderWarningDistance(o); }
    @Override public void SET_COOLDOWN(PacketEvent o){ whenServerHandleSetCooldown(o); }
    @Override public void SET_SLOT(PacketEvent o){ whenServerHandleSetSlot(o); }
    @Override public void SET_SUBTITLE_TEXT(PacketEvent o){ whenServerHandleSetSubtitleText(o); }
    @Override public void SET_TITLE_TEXT(PacketEvent o){ whenServerHandleSetTitleText(o); }
    @Override public void SET_TITLES_ANIMATION(PacketEvent o){ whenServerHandleSetTitlesAnimation(o); }
    @Override public void SPAWN_ENTITY(PacketEvent o){ whenServerHandleSpawnEntity(o); }
    @Override public void SPAWN_ENTITY_EXPERIENCE_ORB(PacketEvent o){ whenServerHandleSpawnEntityExperienceOrb(o); }
    @Override public void SPAWN_POSITION(PacketEvent o){ whenServerHandleSpawnPosition(o); }
    @Override public void STATISTIC(PacketEvent o){ whenServerHandleStatistic(o); }
    @Override public void STOP_SOUND(PacketEvent o){ whenServerHandleStopSound(o); }
    @Override public void SYSTEM_CHAT(PacketEvent o){ whenServerHandleSystemChat(o); }
    @Override public void TAB_COMPLETE(PacketEvent o){ whenServerHandleTabComplete(o); }
    @Override public void TAGS(PacketEvent o){ whenServerHandleTags(o); }
    @Override public void TILE_ENTITY_DATA(PacketEvent o){ whenServerHandleTileEntityData(o); }
    @Override public void UNLOAD_CHUNK(PacketEvent o){ whenServerHandleUnloadChunk(o); }
    @Override public void UPDATE_ATTRIBUTES(PacketEvent o){ whenServerHandleUpdateAttributes(o); }
    @Override public void UPDATE_ENABLED_FEATURES(PacketEvent o){ whenServerHandleUpdateEnabledFeatures(o); }
    @Override public void UPDATE_HEALTH(PacketEvent o){ whenServerHandleUpdateHealth(o); }
    @Override public void UPDATE_SIMULATION_DISTANCE(PacketEvent o){ whenServerHandleUpdateSimulationDistance(o); }
    @Override public void UPDATE_TIME(PacketEvent o){ whenServerHandleUpdateTime(o); }
    @Override public void VEHICLE_MOVE(PacketEvent o){ whenServerHandleVehicleMove(o); }
    @Override public void VIEW_CENTRE(PacketEvent o){ whenServerHandleViewCentre(o); }
    @Override public void VIEW_DISTANCE(PacketEvent o){ whenServerHandleViewDistance(o); }
    @Override public void WINDOW_DATA(PacketEvent o){ whenServerHandleWindowData(o); }
    @Override public void WINDOW_ITEMS(PacketEvent o){ whenServerHandleWindowItems(o); }
    @Override public void WORLD_EVENT(PacketEvent o){ whenServerHandleWorldEvent(o); }
    @Override public void WORLD_PARTICLES(PacketEvent o){ whenServerHandleWorldParticles(o); }

}
