package com.flarelabsmc.interactionary.cap;

import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.RangedWeaponCapability;

public class OneHandedRangedWeaponCapability extends RangedWeaponCapability {
    protected OneHandedRangedWeaponCapability(CapabilityItem.Builder builder) {
        super(builder);
    }

    @Override
    public boolean canBePlacedOffhand() {
        return true;
    }
}
