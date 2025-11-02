package com.flarelabsmc.interactionary.client.render.item;

import com.google.gson.JsonElement;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class RenderOneHandedRangedWeapon extends RenderItemBase {
    public RenderOneHandedRangedWeapon(JsonElement element) {
        super(element);
    }

    @Override
    public void renderItemInHand(ItemStack stack, LivingEntityPatch<?> patch, InteractionHand hand, OpenMatrix4f[] poses, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        OpenMatrix4f matrixOff = this.getCorrectionMatrix(patch, InteractionHand.MAIN_HAND, poses);

        poseStack.pushPose();
        MathUtils.mulStack(poseStack, matrixOff);
        itemInHandRenderer.renderItem(patch.getOriginal(), stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
