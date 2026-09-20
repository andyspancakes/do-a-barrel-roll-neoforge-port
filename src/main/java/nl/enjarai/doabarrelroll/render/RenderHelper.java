package nl.enjarai.doabarrelroll.render;


//? if <26.2 {
/*import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;*/
//? }
import java.util.function.BiConsumer;

import com.mojang.renderpearl.api.pipeline.BlendFunction;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;

public class RenderHelper {
    public static final RenderPipeline INVERTED = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.DEBUG_FILLED_SNIPPET)
            .withLocation("pipeline/crosshair")
            //? if <26.2 {
            /*.withColorTargetState(new ColorTargetState(new BlendFunction(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO)))*/
            //? } else {
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            //? }
            .build()
    );

    public static BiConsumer<Integer, Integer> blankPixel(GuiGraphicsExtractor drawContext) {
        return (x, y) -> {
            drawContext.fill(INVERTED, x, y, x + 1, y + 1, 0xffffffff);
        };
    }
}
