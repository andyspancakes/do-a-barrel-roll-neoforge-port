package nl.enjarai.doabarrelroll;

//? if fabric {
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class DoABarrelRollInitializer implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DoABarrelRollClient.init();
    }

    @Override
    public void onInitialize() {
        DoABarrelRoll.init();
    }
}
//?} else {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import nl.enjarai.doabarrelroll.net.ClientNetworking;
import nl.enjarai.doabarrelroll.net.ServerNetworking;

@Mod(DoABarrelRoll.MODID)
public class DoABarrelRollInitializer {
    public DoABarrelRollInitializer(IEventBus modBus) {
        DoABarrelRoll.init();

        // Register all network payloads on the mod bus
        modBus.addListener((RegisterPayloadHandlersEvent event) -> {
            var registrar = event.registrar(DoABarrelRoll.MODID);
            ServerNetworking.registerPayloads(registrar);
            if (FMLLoader.getDist().isClient()) {
                ClientNetworking.registerClientPayloads(registrar);
            }
        });

        if (FMLLoader.getDist().isClient()) {
            DoABarrelRollClient.init();

            // Register keybindings on the mod bus
            modBus.addListener((RegisterKeyMappingsEvent event) ->
                    ModKeybindings.ALL.forEach(event::register));
        }
    }
}
*///?}
