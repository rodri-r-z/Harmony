package net.brydget.harmony.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class StandardClientHandleListener implements ClientPacketHandleInterface {

    public StandardClientHandleListener() {
        Arrays.stream(this.getClass().getMethods())
                .filter(a -> a.getName().toUpperCase().equals(a.getName()))
                .forEach(a -> {
                    try {
                        final PacketType packetType = (PacketType) PacketType.Play.Client.class.getField(a.getName()).get(PacketType.class);
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
    public void whenClientHandleAbilities(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleAdvancements(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleArmAnimation(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleAutoRecipe(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleBEdit(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleBeacon(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleBlockDig(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleBlockPlace(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleBoatMove(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleChat(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleChatAck(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleChatCommand(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleChatSessionUpdate(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleClientCommand(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleCloseWindow(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleCustomPayload(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleDifficultyChange(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleDifficultyLock(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleEnchantItem(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleEntityAction(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleEntityNbtQuery(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleGround(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleHeldItemSlot(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleItemName(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleJigsawGenerate(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleKeepAlive(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleLook(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandlePickItem(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandlePong(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandlePosition(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandlePositionLook(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleRecipeDisplayed(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleRecipeSettings(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleResourcePackStatus(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSetCommandBlock(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSetCommandMinecart(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSetCreativeSlot(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSetJigsaw(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSettings(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSpectate(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleSteerVehicle(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleStruct(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleTabComplete(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleTeleportAccept(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleTileNbtQuery(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleTrSel(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleUpdateSign(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleUseEntity(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleUseItem(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleVehicleMove(PacketEvent ignored) {

    }

    @Override
    public void whenClientHandleWindowClick(PacketEvent ignored) {

    }

    @Override public void ABILITIES(PacketEvent o) { whenClientHandleAbilities(o); }
    @Override public void ADVANCEMENTS(PacketEvent o) { whenClientHandleAdvancements(o); }
    @Override public void ARM_ANIMATION(PacketEvent o) { whenClientHandleArmAnimation(o); }
    @Override public void AUTO_RECIPE(PacketEvent o) { whenClientHandleAutoRecipe(o); }
    @Override public void B_EDIT(PacketEvent o) { whenClientHandleBEdit(o); }
    @Override public void BEACON(PacketEvent o) { whenClientHandleBeacon(o); }
    @Override public void BLOCK_DIG(PacketEvent o) { whenClientHandleBlockDig(o); }
    @Override public void BLOCK_PLACE(PacketEvent o) { whenClientHandleBlockPlace(o); }
    @Override public void BOAT_MOVE(PacketEvent o) { whenClientHandleBoatMove(o); }
    @Override public void CHAT(PacketEvent o) { whenClientHandleChat(o); }
    @Override public void CHAT_ACK(PacketEvent o) { whenClientHandleChatAck(o); }
    @Override public void CHAT_COMMAND(PacketEvent o) { whenClientHandleChatCommand(o); }
    @Override public void CHAT_SESSION_UPDATE(PacketEvent o) { whenClientHandleChatSessionUpdate(o); }
    @Override public void CLIENT_COMMAND(PacketEvent o) { whenClientHandleClientCommand(o); }
    @Override public void CLOSE_WINDOW(PacketEvent o) { whenClientHandleCloseWindow(o); }
    @Override public void CUSTOM_PAYLOAD(PacketEvent o) { whenClientHandleCustomPayload(o); }
    @Override public void DIFFICULTY_CHANGE(PacketEvent o) { whenClientHandleDifficultyChange(o); }
    @Override public void DIFFICULTY_LOCK(PacketEvent o) { whenClientHandleDifficultyLock(o); }
    @Override public void ENCHANT_ITEM(PacketEvent o) { whenClientHandleEnchantItem(o); }
    @Override public void ENTITY_ACTION(PacketEvent o) { whenClientHandleEntityAction(o); }
    @Override public void ENTITY_NBT_QUERY(PacketEvent o) { whenClientHandleEntityNbtQuery(o); }
    @Override public void GROUND(PacketEvent o) { whenClientHandleGround(o); }
    @Override public void HELD_ITEM_SLOT(PacketEvent o) { whenClientHandleHeldItemSlot(o); }
    @Override public void ITEM_NAME(PacketEvent o) { whenClientHandleItemName(o); }
    @Override public void JIGSAW_GENERATE(PacketEvent o) { whenClientHandleJigsawGenerate(o); }
    @Override public void KEEP_ALIVE(PacketEvent o) { whenClientHandleKeepAlive(o); }
    @Override public void LOOK(PacketEvent o) { whenClientHandleLook(o); }
    @Override public void PICK_ITEM(PacketEvent o) { whenClientHandlePickItem(o); }
    @Override public void PONG(PacketEvent o) { whenClientHandlePong(o); }
    @Override public void POSITION(PacketEvent o) { whenClientHandlePosition(o); }
    @Override public void POSITION_LOOK(PacketEvent o) { whenClientHandlePositionLook(o); }
    @Override public void RECIPE_DISPLAYED(PacketEvent o) { whenClientHandleRecipeDisplayed(o); }
    @Override public void RECIPE_SETTINGS(PacketEvent o) { whenClientHandleRecipeSettings(o); }
    @Override public void RESOURCE_PACK_STATUS(PacketEvent o) { whenClientHandleResourcePackStatus(o); }
    @Override public void SET_COMMAND_BLOCK(PacketEvent o) { whenClientHandleSetCommandBlock(o); }
    @Override public void SET_COMMAND_MINECART(PacketEvent o) { whenClientHandleSetCommandMinecart(o); }
    @Override public void SET_CREATIVE_SLOT(PacketEvent o) { whenClientHandleSetCreativeSlot(o); }
    @Override public void SET_JIGSAW(PacketEvent o) { whenClientHandleSetJigsaw(o); }
    @Override public void SETTINGS(PacketEvent o) { whenClientHandleSettings(o); }
    @Override public void SPECTATE(PacketEvent o) { whenClientHandleSpectate(o); }
    @Override public void STEER_VEHICLE(PacketEvent o) { whenClientHandleSteerVehicle(o); }
    @Override public void STRUCT(PacketEvent o) { whenClientHandleStruct(o); }
    @Override public void TAB_COMPLETE(PacketEvent o) { whenClientHandleTabComplete(o); }
    @Override public void TELEPORT_ACCEPT(PacketEvent o) { whenClientHandleTeleportAccept(o); }
    @Override public void TILE_NBT_QUERY(PacketEvent o) { whenClientHandleTileNbtQuery(o); }
    @Override public void TR_SEL(PacketEvent o) { whenClientHandleTrSel(o); }
    @Override public void UPDATE_SIGN(PacketEvent o) { whenClientHandleUpdateSign(o); }
    @Override public void USE_ENTITY(PacketEvent o) { whenClientHandleUseEntity(o); }
    @Override public void USE_ITEM(PacketEvent o) { whenClientHandleUseItem(o); }
    @Override public void VEHICLE_MOVE(PacketEvent o) { whenClientHandleVehicleMove(o); }
    @Override public void WINDOW_CLICK(PacketEvent o) { whenClientHandleWindowClick(o); }
    }
