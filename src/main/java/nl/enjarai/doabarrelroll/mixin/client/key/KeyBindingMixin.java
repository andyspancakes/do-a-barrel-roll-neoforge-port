package nl.enjarai.doabarrelroll.mixin.client.key;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.InputConstants;
import nl.enjarai.doabarrelroll.api.key.InputContext;
import nl.enjarai.doabarrelroll.impl.key.InputContextImpl;
import nl.enjarai.doabarrelroll.util.key.ContextualKeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.KeyMapping;

@Mixin(KeyMapping.class)
public abstract class KeyBindingMixin implements ContextualKeyBinding {
    @Unique
    private final ArrayList<InputContext> contexts = new ArrayList<>();

    @Override
    public List<InputContext> doABarrelRoll$getContexts() {
        return contexts;
    }

    @Override
    public void doABarrelRoll$addToContext(InputContext context) {
        contexts.add(context);
    }

    private static KeyMapping getContextKeyBinding(InputConstants.Key key) {
        for (var context : InputContextImpl.getContexts()) {
            var binding = context.getKeyBinding(key);
            if (binding != null) {
                if (context.isActive()) {
                    return binding;
                } else {
                    binding.setDown(false);
                }
            }
        }

        return null;
    }

    //? if fabric {
    
    @WrapOperation(
            method = "click",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            require = 0 // We let all these mixins fail if they need to as a temporary workaround to be compatible with Connector.
    )
    private static Object doABarrelRoll$applyKeybindContext(Map<InputConstants.Key, KeyMapping> map, Object key, Operation<KeyMapping> original) {
        var binding = getContextKeyBinding((InputConstants.Key) key);
        if (binding != null) return binding;

        return original.call(map, key);
    }

    @WrapOperation(
            method = "set",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            require = 0
    )
    private static Object doABarrelRoll$applyKeybindContext2(Map<InputConstants.Key, KeyMapping> map, Object key, Operation<KeyMapping> original) {
        var binding = getContextKeyBinding((InputConstants.Key) key);
        var originalBinding = original.call(map, key);
        if (binding != null) {
            if (originalBinding != null) {
                originalBinding.setDown(false);
            }
            return binding;
        }

        return originalBinding;
    }

    @Inject(
            method = "resetMapping",
            at = @At("HEAD"),
            require = 0
    )
    private static void doABarrelRoll$updateContextualKeys(CallbackInfo ci) {
        for (var context : InputContextImpl.getContexts()) {
            context.updateKeysByCode();
        }
    }

    @WrapWithCondition(
            method = "resetMapping",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            require = 0
    )
    private static boolean doABarrelRoll$skipAddingContextualKeys(Map<InputConstants.Key, KeyMapping> map, Object key, Object keyBinding) {
        return !InputContextImpl.contextsContain((KeyMapping) keyBinding);
    }
    //?}
}
