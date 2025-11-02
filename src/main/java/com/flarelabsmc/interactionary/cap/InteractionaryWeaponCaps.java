package com.flarelabsmc.interactionary.cap;

import com.flarelabsmc.interactionary.Interactionary;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = Interactionary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InteractionaryWeaponCaps {
    public static final Function<Item, CapabilityItem.Builder> BOW = (item) -> OneHandedBowCapability.builder()
            .zoomInType(CapabilityItem.ZoomInType.USE_TICK)
            .addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
            .addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK)
            .addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
            .addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
            .constructor(OneHandedBowCapability::new);

    @SubscribeEvent
    public static void registerCaps(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.parse("interactionary:one_handed_bow"), BOW);
        Interactionary.LOGGER.info("Registered Interactionary weapon capability presets");
    }

    public static void init() {}
}
