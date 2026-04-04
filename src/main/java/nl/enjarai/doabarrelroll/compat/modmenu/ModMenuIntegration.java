//? if fabric {
package nl.enjarai.doabarrelroll.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.util.Util;
import nl.enjarai.doabarrelroll.compat.Compat;
import nl.enjarai.doabarrelroll.compat.yacl.YACLImplementation;
import nl.enjarai.doabarrelroll.config.ModConfigScreen;

import java.net.URI;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModConfigScreen::create;
    }
}
//?}