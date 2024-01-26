package net.brydget.harmony.packet;

import com.comphenix.protocol.events.PacketEvent;

public interface ServerPacketHandleInterface {

    void whenServerHandleAbilities(PacketEvent ignored);
    void whenServerHandleAdvancements(PacketEvent ignored);
    void whenServerHandleAnimation(PacketEvent ignored);
    void whenServerHandleAttachEntity(PacketEvent ignored);
    void whenServerHandleAutoRecipe(PacketEvent ignored);
    void whenServerHandleBlockAction(PacketEvent ignored);
    void whenServerHandleBlockBreakAnimation(PacketEvent ignored);
    void whenServerHandleBlockChange(PacketEvent ignored);
    void whenServerHandleBlockChangedAck(PacketEvent ignored);
    void whenServerHandleBoss(PacketEvent ignored);
    void whenServerHandleBundle(PacketEvent ignored);
    void whenServerHandleCamera(PacketEvent ignored);
    void whenServerHandleChat(PacketEvent ignored);
    void whenServerHandleChunksBiomes(PacketEvent ignored);
    void whenServerHandleClearTitles(PacketEvent ignored);
    void whenServerHandleCloseWindow(PacketEvent ignored);
    void whenServerHandleCollect(PacketEvent ignored);
    void whenServerHandleCommands(PacketEvent ignored);
    void whenServerHandleCustomChatCompletions(PacketEvent ignored);
    void whenServerHandleCustomPayload(PacketEvent ignored);
    void whenServerHandleDamageEvent(PacketEvent ignored);
    void whenServerHandleDeleteChatMessage(PacketEvent ignored);
    void whenServerHandleDisguisedChat(PacketEvent ignored);
    void whenServerHandleEntityDestroy(PacketEvent ignored);
    void whenServerHandleEntityEffect(PacketEvent ignored);
    void whenServerHandleEntityEquipment(PacketEvent ignored);
    void whenServerHandleEntityHeadRotation(PacketEvent ignored);
    void whenServerHandleEntityLook(PacketEvent ignored);
    void whenServerHandleEntityMetadata(PacketEvent ignored);
    void whenServerHandleEntitySound(PacketEvent ignored);
    void whenServerHandleEntityStatus(PacketEvent ignored);
    void whenServerHandleEntityTeleport(PacketEvent ignored);
    void whenServerHandleEntityVelocity(PacketEvent ignored);
    void whenServerHandleExperience(PacketEvent ignored);
    void whenServerHandleExplosion(PacketEvent ignored);
    void whenServerHandleGameStateChange(PacketEvent ignored);
    void whenServerHandleHeldItemSlot(PacketEvent ignored);
    void whenServerHandleHurtAnimation(PacketEvent ignored);
    void whenServerHandleInitializeBorder(PacketEvent ignored);
    void whenServerHandleKeepAlive(PacketEvent ignored);
    void whenServerHandleKickDisconnect(PacketEvent ignored);
    void whenServerHandleLightUpdate(PacketEvent ignored);
    void whenServerHandleLogin(PacketEvent ignored);
    void whenServerHandleLookAt(PacketEvent ignored);
    void whenServerHandleMap(PacketEvent ignored);
    void whenServerHandleMapChunk(PacketEvent ignored);
    void whenServerHandleMount(PacketEvent ignored);
    void whenServerHandleMultiBlockChange(PacketEvent ignored);
    void whenServerHandleNamedEntitySpawn(PacketEvent ignored);
    void whenServerHandleNamedSoundEffect(PacketEvent ignored);
    void whenServerHandleNbtQuery(PacketEvent ignored);
    void whenServerHandleOpenBook(PacketEvent ignored);
    void whenServerHandleOpenSignEditor(PacketEvent ignored);
    void whenServerHandleOpenWindow(PacketEvent ignored);
    void whenServerHandleOpenWindowHorse(PacketEvent ignored);
    void whenServerHandleOpenWindowMerchant(PacketEvent ignored);
    void whenServerHandlePing(PacketEvent ignored);
    void whenServerHandlePlayerCombatEnd(PacketEvent ignored);
    void whenServerHandlePlayerCombatEnter(PacketEvent ignored);
    void whenServerHandlePlayerCombatKill(PacketEvent ignored);
    void whenServerHandlePlayerInfo(PacketEvent ignored);
    void whenServerHandlePlayerInfoRemove(PacketEvent ignored);
    void whenServerHandlePlayerListHeaderFooter(PacketEvent ignored);
    void whenServerHandlePosition(PacketEvent ignored);
    void whenServerHandleRecipeUpdate(PacketEvent ignored);
    void whenServerHandleRecipes(PacketEvent ignored);
    void whenServerHandleRelEntityMove(PacketEvent ignored);
    void whenServerHandleRelEntityMoveLook(PacketEvent ignored);
    void whenServerHandleRemoveEntityEffect(PacketEvent ignored);
    void whenServerHandleResourcePackSend(PacketEvent ignored);
    void whenServerHandleRespawn(PacketEvent ignored);
    void whenServerHandleScoreboardDisplayObjective(PacketEvent ignored);
    void whenServerHandleScoreboardObjective(PacketEvent ignored);
    void whenServerHandleScoreboardScore(PacketEvent ignored);
    void whenServerHandleScoreboardTeam(PacketEvent ignored);
    void whenServerHandleSelectAdvancementTab(PacketEvent ignored);
    void whenServerHandleServerData(PacketEvent ignored);
    void whenServerHandleServerDifficulty(PacketEvent ignored);
    void whenServerHandleSetActionBarText(PacketEvent ignored);
    void whenServerHandleSetBorderCenter(PacketEvent ignored);
    void whenServerHandleSetBorderLerpSize(PacketEvent ignored);
    void whenServerHandleSetBorderSize(PacketEvent ignored);
    void whenServerHandleSetBorderWarningDelay(PacketEvent ignored);
    void whenServerHandleSetBorderWarningDistance(PacketEvent ignored);
    void whenServerHandleSetCooldown(PacketEvent ignored);
    void whenServerHandleSetSlot(PacketEvent ignored);
    void whenServerHandleSetSubtitleText(PacketEvent ignored);
    void whenServerHandleSetTitleText(PacketEvent ignored);
    void whenServerHandleSetTitlesAnimation(PacketEvent ignored);
    void whenServerHandleSpawnEntity(PacketEvent ignored);
    void whenServerHandleSpawnEntityExperienceOrb(PacketEvent ignored);
    void whenServerHandleSpawnPosition(PacketEvent ignored);
    void whenServerHandleStatistic(PacketEvent ignored);
    void whenServerHandleStopSound(PacketEvent ignored);
    void whenServerHandleSystemChat(PacketEvent ignored);
    void whenServerHandleTabComplete(PacketEvent ignored);
    void whenServerHandleTags(PacketEvent ignored);
    void whenServerHandleTileEntityData(PacketEvent ignored);
    void whenServerHandleUnloadChunk(PacketEvent ignored);
    void whenServerHandleUpdateAttributes(PacketEvent ignored);
    void whenServerHandleUpdateEnabledFeatures(PacketEvent ignored);
    void whenServerHandleUpdateHealth(PacketEvent ignored);
    void whenServerHandleUpdateSimulationDistance(PacketEvent ignored);
    void whenServerHandleUpdateTime(PacketEvent ignored);
    void whenServerHandleVehicleMove(PacketEvent ignored);
    void whenServerHandleViewCentre(PacketEvent ignored);
    void whenServerHandleViewDistance(PacketEvent ignored);
    void whenServerHandleWindowData(PacketEvent ignored);
    void whenServerHandleWindowItems(PacketEvent ignored);
    void whenServerHandleWorldEvent(PacketEvent ignored);
    void whenServerHandleWorldParticles(PacketEvent ignored);

    void ABILITIES(PacketEvent ignored);
    void ADVANCEMENTS(PacketEvent ignored);
    void ANIMATION(PacketEvent ignored);
    void ATTACH_ENTITY(PacketEvent ignored);
    void AUTO_RECIPE(PacketEvent ignored);
    void BLOCK_ACTION(PacketEvent ignored);
    void BLOCK_BREAK_ANIMATION(PacketEvent ignored);
    void BLOCK_CHANGE(PacketEvent ignored);
    void BLOCK_CHANGED_ACK(PacketEvent ignored);
    void BOSS(PacketEvent ignored);
    void BUNDLE(PacketEvent ignored);
    void CAMERA(PacketEvent ignored);
    void CHAT(PacketEvent ignored);
    void CHUNKS_BIOMES(PacketEvent ignored);
    void CLEAR_TITLES(PacketEvent ignored);
    void CLOSE_WINDOW(PacketEvent ignored);
    void COLLECT(PacketEvent ignored);
    void COMMANDS(PacketEvent ignored);
    void CUSTOM_CHAT_COMPLETIONS(PacketEvent ignored);
    void CUSTOM_PAYLOAD(PacketEvent ignored);
    void DAMAGE_EVENT(PacketEvent ignored);
    void DELETE_CHAT_MESSAGE(PacketEvent ignored);
    void DISGUISED_CHAT(PacketEvent ignored);
    void ENTITY_DESTROY(PacketEvent ignored);
    void ENTITY_EFFECT(PacketEvent ignored);
    void ENTITY_EQUIPMENT(PacketEvent ignored);
    void ENTITY_HEAD_ROTATION(PacketEvent ignored);
    void ENTITY_LOOK(PacketEvent ignored);
    void ENTITY_METADATA(PacketEvent ignored);
    void ENTITY_SOUND(PacketEvent ignored);
    void ENTITY_STATUS(PacketEvent ignored);
    void ENTITY_TELEPORT(PacketEvent ignored);
    void ENTITY_VELOCITY(PacketEvent ignored);
    void EXPERIENCE(PacketEvent ignored);
    void EXPLOSION(PacketEvent ignored);
    void GAME_STATE_CHANGE(PacketEvent ignored);
    void HELD_ITEM_SLOT(PacketEvent ignored);
    void HURT_ANIMATION(PacketEvent ignored);
    void INITIALIZE_BORDER(PacketEvent ignored);
    void KEEP_ALIVE(PacketEvent ignored);
    void KICK_DISCONNECT(PacketEvent ignored);
    void LIGHT_UPDATE(PacketEvent ignored);
    void LOGIN(PacketEvent ignored);
    void LOOK_AT(PacketEvent ignored);
    void MAP(PacketEvent ignored);
    void MAP_CHUNK(PacketEvent ignored);
    void MOUNT(PacketEvent ignored);
    void MULTI_BLOCK_CHANGE(PacketEvent ignored);
    void NAMED_ENTITY_SPAWN(PacketEvent ignored);
    void NAMED_SOUND_EFFECT(PacketEvent ignored);
    void NBT_QUERY(PacketEvent ignored);
    void OPEN_BOOK(PacketEvent ignored);
    void OPEN_SIGN_EDITOR(PacketEvent ignored);
    void OPEN_WINDOW(PacketEvent ignored);
    void OPEN_WINDOW_HORSE(PacketEvent ignored);
    void OPEN_WINDOW_MERCHANT(PacketEvent ignored);
    void PING(PacketEvent ignored);
    void PLAYER_COMBAT_END(PacketEvent ignored);
    void PLAYER_COMBAT_ENTER(PacketEvent ignored);
    void PLAYER_COMBAT_KILL(PacketEvent ignored);
    void PLAYER_INFO(PacketEvent ignored);
    void PLAYER_INFO_REMOVE(PacketEvent ignored);
    void PLAYER_LIST_HEADER_FOOTER(PacketEvent ignored);
    void POSITION(PacketEvent ignored);
    void RECIPE_UPDATE(PacketEvent ignored);
    void RECIPES(PacketEvent ignored);
    void REL_ENTITY_MOVE(PacketEvent ignored);
    void REL_ENTITY_MOVE_LOOK(PacketEvent ignored);
    void REMOVE_ENTITY_EFFECT(PacketEvent ignored);
    void RESOURCE_PACK_SEND(PacketEvent ignored);
    void RESPAWN(PacketEvent ignored);
    void SCOREBOARD_DISPLAY_OBJECTIVE(PacketEvent ignored);
    void SCOREBOARD_OBJECTIVE(PacketEvent ignored);
    void SCOREBOARD_SCORE(PacketEvent ignored);
    void SCOREBOARD_TEAM(PacketEvent ignored);
    void SELECT_ADVANCEMENT_TAB(PacketEvent ignored);
    void SERVER_DATA(PacketEvent ignored);
    void SERVER_DIFFICULTY(PacketEvent ignored);
    void SET_ACTION_BAR_TEXT(PacketEvent ignored);
    void SET_BORDER_CENTER(PacketEvent ignored);
    void SET_BORDER_LERP_SIZE(PacketEvent ignored);
    void SET_BORDER_SIZE(PacketEvent ignored);
    void SET_BORDER_WARNING_DELAY(PacketEvent ignored);
    void SET_BORDER_WARNING_DISTANCE(PacketEvent ignored);
    void SET_COOLDOWN(PacketEvent ignored);
    void SET_SLOT(PacketEvent ignored);
    void SET_SUBTITLE_TEXT(PacketEvent ignored);
    void SET_TITLE_TEXT(PacketEvent ignored);
    void SET_TITLES_ANIMATION(PacketEvent ignored);
    void SPAWN_ENTITY(PacketEvent ignored);
    void SPAWN_ENTITY_EXPERIENCE_ORB(PacketEvent ignored);
    void SPAWN_POSITION(PacketEvent ignored);
    void STATISTIC(PacketEvent ignored);
    void STOP_SOUND(PacketEvent ignored);
    void SYSTEM_CHAT(PacketEvent ignored);
    void TAB_COMPLETE(PacketEvent ignored);
    void TAGS(PacketEvent ignored);
    void TILE_ENTITY_DATA(PacketEvent ignored);
    void UNLOAD_CHUNK(PacketEvent ignored);
    void UPDATE_ATTRIBUTES(PacketEvent ignored);
    void UPDATE_ENABLED_FEATURES(PacketEvent ignored);
    void UPDATE_HEALTH(PacketEvent ignored);
    void UPDATE_SIMULATION_DISTANCE(PacketEvent ignored);
    void UPDATE_TIME(PacketEvent ignored);
    void VEHICLE_MOVE(PacketEvent ignored);
    void VIEW_CENTRE(PacketEvent ignored);
    void VIEW_DISTANCE(PacketEvent ignored);
    void WINDOW_DATA(PacketEvent ignored);
    void WINDOW_ITEMS(PacketEvent ignored);
    void WORLD_EVENT(PacketEvent ignored);
    void WORLD_PARTICLES(PacketEvent ignored);

}
