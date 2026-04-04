package nl.enjarai.doabarrelroll.render;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import java.util.function.BiConsumer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;

public class RenderHelper {
    public static final RenderPipeline INVERTED = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.DEBUG_FILLED_SNIPPET)
                    .withLocation("pipeline/crosshair")
                    .withBlend(new BlendFunction(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO))
                    .build()
    );

    public static BiConsumer<Integer, Integer> blankPixel(GuiGraphics drawContext) {
        return (x, y) -> {
            drawContext.fill(INVERTED, x, y, x + 1, y + 1, 0xffffffff);
        };
    }
}
