package nl.enjarai.doabarrelroll;

import io.netty.buffer.Unpooled;
//? if fabric {
import nl.enjarai.cicada.api.util.ProperLogger;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
//?} else {
/*import nl.enjarai.doabarrelroll.util.ModPermissions;
*///?}
import nl.enjarai.doabarrelroll.net.ServerNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoABarrelRoll {
    public static final String MODID = "do_a_barrel_roll";
    public static final Logger LOGGER =
            //? if fabric {
            ProperLogger.getLogger(MODID);
            //?} else
            /*LoggerFactory.getLogger(MODID);*/

    public static final Identifier HANDSHAKE_CHANNEL = id("handshake");
    public static final Identifier SERVER_CONFIG_UPDATE_CHANNEL = id("server_config_update");
    public static final Identifier ROLL_CHANNEL = id("player_roll");

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public static void init() {
        ServerNetworking.init();
    }

    public static FriendlyByteBuf createBuf() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }

    public static boolean checkPermission(ServerGamePacketListenerImpl handler, String permission, int operatorLevel) {
        //? if fabric {
        return Permissions.check(handler.getPlayer().createCommandSourceStack(), permission, operatorLevel);
        //?} else
        /*return ModPermissions.resolve(handler.getPlayer(), permission, operatorLevel);*/
    }
}
