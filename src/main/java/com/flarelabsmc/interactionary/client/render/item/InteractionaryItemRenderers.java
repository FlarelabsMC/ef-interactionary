package com.flarelabsmc.interactionary.client.render.item;

import com.flarelabsmc.interactionary.Interactionary;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = Interactionary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InteractionaryItemRenderers {
    @SubscribeEvent
    public static void registerItemRenderers(PatchedRenderersEvent.RegisterItemRenderer event) {
        event.addItemRenderer(ResourceLocation.parse("interactionary:one_handed_ranged_weapon"), RenderOneHandedRangedWeapon::new);
    }

    public static void init() {}
}
