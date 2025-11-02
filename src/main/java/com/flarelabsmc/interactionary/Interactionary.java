package com.flarelabsmc.interactionary;

import com.flarelabsmc.interactionary.cap.InteractionaryWeaponCaps;
import com.flarelabsmc.interactionary.cap.OneHandedBowCapability;
import com.flarelabsmc.interactionary.client.render.item.InteractionaryItemRenderers;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mod.EventBusSubscriber(modid = Interactionary.MODID)
@Mod(Interactionary.MODID)
public class Interactionary {
    public static final String MODID = "interactionary";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Interactionary() {
        InteractionaryWeaponCaps.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InteractionaryItemRenderers::init);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        LOGGER.info(String.valueOf(EpicFightCapabilities.getItemStackCapability(event.player.getMainHandItem()) instanceof OneHandedBowCapability));
    }
}
