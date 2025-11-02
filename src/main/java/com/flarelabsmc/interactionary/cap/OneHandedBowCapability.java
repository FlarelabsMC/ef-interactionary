package com.flarelabsmc.interactionary.cap;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.UseAnim;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class OneHandedBowCapability extends OneHandedRangedWeaponCapability {
    protected OneHandedBowCapability(CapabilityItem.Builder builder) {
        super(builder);
    }

    @Override
    public LivingMotion getLivingMotion(LivingEntityPatch<?> patch, InteractionHand hand) {
        return patch.getOriginal().isUsingItem() && patch.getOriginal().getUseItem().getUseAnimation() == UseAnim.BOW ? LivingMotions.AIM : null;
    }
}
