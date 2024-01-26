package net.brydget.harmony.packet;

import com.comphenix.protocol.events.PacketEvent;

public interface ClientPacketHandleInterface {

    void whenClientHandleAbilities(PacketEvent ignored);
    void whenClientHandleAdvancements(PacketEvent ignored);
    void whenClientHandleArmAnimation(PacketEvent ignored);
    void whenClientHandleAutoRecipe(PacketEvent ignored);
    void whenClientHandleBEdit(PacketEvent ignored);
    void whenClientHandleBeacon(PacketEvent ignored);
    void whenClientHandleBlockDig(PacketEvent ignored);
    void whenClientHandleBlockPlace(PacketEvent ignored);
    void whenClientHandleBoatMove(PacketEvent ignored);
    void whenClientHandleChat(PacketEvent ignored);
    void whenClientHandleChatAck(PacketEvent ignored);
    void whenClientHandleChatCommand(PacketEvent ignored);
    void whenClientHandleChatSessionUpdate(PacketEvent ignored);
    void whenClientHandleClientCommand(PacketEvent ignored);
    void whenClientHandleCloseWindow(PacketEvent ignored);
    void whenClientHandleCustomPayload(PacketEvent ignored);
    void whenClientHandleDifficultyChange(PacketEvent ignored);
    void whenClientHandleDifficultyLock(PacketEvent ignored);
    void whenClientHandleEnchantItem(PacketEvent ignored);
    void whenClientHandleEntityAction(PacketEvent ignored);
    void whenClientHandleEntityNbtQuery(PacketEvent ignored);
    void whenClientHandleGround(PacketEvent ignored);
    void whenClientHandleHeldItemSlot(PacketEvent ignored);
    void whenClientHandleItemName(PacketEvent ignored);
    void whenClientHandleJigsawGenerate(PacketEvent ignored);
    void whenClientHandleKeepAlive(PacketEvent ignored);
    void whenClientHandleLook(PacketEvent ignored);
    void whenClientHandlePickItem(PacketEvent ignored);
    void whenClientHandlePong(PacketEvent ignored);
    void whenClientHandlePosition(PacketEvent ignored);
    void whenClientHandlePositionLook(PacketEvent ignored);
    void whenClientHandleRecipeDisplayed(PacketEvent ignored);
    void whenClientHandleRecipeSettings(PacketEvent ignored);
    void whenClientHandleResourcePackStatus(PacketEvent ignored);
    void whenClientHandleSetCommandBlock(PacketEvent ignored);
    void whenClientHandleSetCommandMinecart(PacketEvent ignored);
    void whenClientHandleSetCreativeSlot(PacketEvent ignored);
    void whenClientHandleSetJigsaw(PacketEvent ignored);
    void whenClientHandleSettings(PacketEvent ignored);
    void whenClientHandleSpectate(PacketEvent ignored);
    void whenClientHandleSteerVehicle(PacketEvent ignored);
    void whenClientHandleStruct(PacketEvent ignored);
    void whenClientHandleTabComplete(PacketEvent ignored);
    void whenClientHandleTeleportAccept(PacketEvent ignored);
    void whenClientHandleTileNbtQuery(PacketEvent ignored);
    void whenClientHandleTrSel(PacketEvent ignored);
    void whenClientHandleUpdateSign(PacketEvent ignored);
    void whenClientHandleUseEntity(PacketEvent ignored);
    void whenClientHandleUseItem(PacketEvent ignored);
    void whenClientHandleVehicleMove(PacketEvent ignored);
    void whenClientHandleWindowClick(PacketEvent ignored);

    void ABILITIES(PacketEvent ignored);
    void ADVANCEMENTS(PacketEvent ignored);
    void ARM_ANIMATION(PacketEvent ignored);
    void AUTO_RECIPE(PacketEvent ignored);
    void B_EDIT(PacketEvent ignored);
    void BEACON(PacketEvent ignored);
    void BLOCK_DIG(PacketEvent ignored);
    void BLOCK_PLACE(PacketEvent ignored);
    void BOAT_MOVE(PacketEvent ignored);
    void CHAT(PacketEvent ignored);
    void CHAT_ACK(PacketEvent ignored);
    void CHAT_COMMAND(PacketEvent ignored);
    void CHAT_SESSION_UPDATE(PacketEvent ignored);
    void CLIENT_COMMAND(PacketEvent ignored);
    void CLOSE_WINDOW(PacketEvent ignored);
    void CUSTOM_PAYLOAD(PacketEvent ignored);
    void DIFFICULTY_CHANGE(PacketEvent ignored);
    void DIFFICULTY_LOCK(PacketEvent ignored);
    void ENCHANT_ITEM(PacketEvent ignored);
    void ENTITY_ACTION(PacketEvent ignored);
    void ENTITY_NBT_QUERY(PacketEvent ignored);
    void GROUND(PacketEvent ignored);
    void HELD_ITEM_SLOT(PacketEvent ignored);
    void ITEM_NAME(PacketEvent ignored);
    void JIGSAW_GENERATE(PacketEvent ignored);
    void KEEP_ALIVE(PacketEvent ignored);
    void LOOK(PacketEvent ignored);
    void PICK_ITEM(PacketEvent ignored);
    void PONG(PacketEvent ignored);
    void POSITION(PacketEvent ignored);
    void POSITION_LOOK(PacketEvent ignored);
    void RECIPE_DISPLAYED(PacketEvent ignored);
    void RECIPE_SETTINGS(PacketEvent ignored);
    void RESOURCE_PACK_STATUS(PacketEvent ignored);
    void SET_COMMAND_BLOCK(PacketEvent ignored);
    void SET_COMMAND_MINECART(PacketEvent ignored);
    void SET_CREATIVE_SLOT(PacketEvent ignored);
    void SET_JIGSAW(PacketEvent ignored);
    void SETTINGS(PacketEvent ignored);
    void SPECTATE(PacketEvent ignored);
    void STEER_VEHICLE(PacketEvent ignored);
    void STRUCT(PacketEvent ignored);
    void TAB_COMPLETE(PacketEvent ignored);
    void TELEPORT_ACCEPT(PacketEvent ignored);
    void TILE_NBT_QUERY(PacketEvent ignored);
    void TR_SEL(PacketEvent ignored);
    void UPDATE_SIGN(PacketEvent ignored);
    void USE_ENTITY(PacketEvent ignored);
    void USE_ITEM(PacketEvent ignored);
    void VEHICLE_MOVE(PacketEvent ignored);
    void WINDOW_CLICK(PacketEvent ignored);

}
