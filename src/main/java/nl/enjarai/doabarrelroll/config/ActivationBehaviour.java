package nl.enjarai.doabarrelroll.config;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum ActivationBehaviour implements NameableEnum {
    VANILLA,
    TRIPLE_JUMP,
    HYBRID,
    HYBRID_TOGGLE;

    @Override
    public Component getDisplayName() {
        return Component.translatable("config.do_a_barrel_roll.controls.activation_behaviour." + this.name().toLowerCase());
    }
}
