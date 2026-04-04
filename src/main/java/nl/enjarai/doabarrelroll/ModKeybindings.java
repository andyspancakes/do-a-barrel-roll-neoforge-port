package nl.enjarai.doabarrelroll;

import nl.enjarai.doabarrelroll.api.key.InputContext;
import nl.enjarai.doabarrelroll.config.LimitedModConfigServer;
import nl.enjarai.doabarrelroll.config.ModConfig;
import nl.enjarai.doabarrelroll.config.ModConfigScreen;
import nl.enjarai.doabarrelroll.net.ClientNetworking;
import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import java.util.List;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ModKeybindings {
    public static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(DoABarrelRoll.id("do_a_barrel_roll"));

    public static final KeyMapping TOGGLE_ENABLED = new KeyMapping(
            "key.do_a_barrel_roll.toggle_enabled",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            CATEGORY
    );
    public static final KeyMapping TOGGLE_THRUST = new KeyMapping(
            "key.do_a_barrel_roll.toggle_thrust",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
    public static final KeyMapping OPEN_CONFIG = new KeyMapping(
            "key.do_a_barrel_roll.open_config",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );

    public static final KeyMapping PITCH_UP = new KeyMapping(
            "key.do_a_barrel_roll.pitch_up",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
    public static final KeyMapping PITCH_DOWN = new KeyMapping(
            "key.do_a_barrel_roll.pitch_down",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
    public static final KeyMapping YAW_LEFT = new KeyMapping(
            "key.do_a_barrel_roll.yaw_left",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_A,
            CATEGORY
    );
    public static final KeyMapping YAW_RIGHT = new KeyMapping(
            "key.do_a_barrel_roll.yaw_right",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_D,
            CATEGORY
    );
    public static final KeyMapping ROLL_LEFT = new KeyMapping(
            "key.do_a_barrel_roll.roll_left",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
    public static final KeyMapping ROLL_RIGHT = new KeyMapping(
            "key.do_a_barrel_roll.roll_right",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
    public static final KeyMapping THRUST_FORWARD = new KeyMapping(
            "key.do_a_barrel_roll.thrust_forward",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_W,
            CATEGORY
    );
    public static final KeyMapping THRUST_BACKWARD = new KeyMapping(
            "key.do_a_barrel_roll.thrust_backward",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );

    public static final List<KeyMapping> ALL = List.of(
            TOGGLE_ENABLED,
            TOGGLE_THRUST,
            OPEN_CONFIG,
            PITCH_UP,
            PITCH_DOWN,
            YAW_LEFT,
            YAW_RIGHT,
            ROLL_LEFT,
            ROLL_RIGHT,
            THRUST_FORWARD,
            THRUST_BACKWARD
    );

    public static void clientTick(Minecraft client) {
        while (TOGGLE_ENABLED.consumeClick()) {
            if (!ClientNetworking.HANDSHAKE_CLIENT.getConfig().map(LimitedModConfigServer::forceEnabled).orElse(false)) {
                ModConfig.INSTANCE.setModEnabled(!ModConfig.INSTANCE.getModEnabled());
                ModConfig.INSTANCE.save();

                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.translatable(
                                    "key.do_a_barrel_roll." +
                                            (ModConfig.INSTANCE.getModEnabled() ? "toggle_enabled.enable" : "toggle_enabled.disable")
                            ),
                            true
                    );
                }
            } else {
                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.translatable("key.do_a_barrel_roll.toggle_enabled.disallowed"),
                            true
                    );
                }
            }
        }
        while (TOGGLE_THRUST.consumeClick()) {
            if (ClientNetworking.HANDSHAKE_CLIENT.getConfig().map(LimitedModConfigServer::allowThrusting).orElse(false)) {
                ModConfig.INSTANCE.setEnableThrust(!ModConfig.INSTANCE.getEnableThrust());
                ModConfig.INSTANCE.save();

                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.translatable(
                                    "key.do_a_barrel_roll." +
                                            (ModConfig.INSTANCE.getEnableThrust() ? "toggle_thrust.enable" : "toggle_thrust.disable")
                            ),
                            true
                    );
                }
            } else {
                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.translatable("key.do_a_barrel_roll.toggle_thrust.disallowed"),
                            true
                    );
                }
            }
        }
        while (OPEN_CONFIG.consumeClick()) {
            client.setScreen(ModConfigScreen.create(client.screen));
        }
    }
}
