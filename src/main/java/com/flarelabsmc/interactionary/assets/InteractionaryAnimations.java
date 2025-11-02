package com.flarelabsmc.interactionary.assets;

import com.flarelabsmc.interactionary.Interactionary;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;

@Mod.EventBusSubscriber(modid = Interactionary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InteractionaryAnimations {
    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder("interactionary", InteractionaryAnimations::build);
    }

    public static void build(AnimationManager.AnimationBuilder builder) {

    }
}