package com.flarelabsmc.interactionary.mixin;

import com.flarelabsmc.interactionary.util.ClientJointUtil;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    @Shadow private static void stringVertex(float pX, float pY, float pZ, VertexConsumer pConsumer, PoseStack.Pose pPose, float p_174124_, float p_174125_) {
        throw new RuntimeException();
    }

    @Redirect(
            method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/FishingHookRenderer;stringVertex(FFFLcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/mojang/blaze3d/vertex/PoseStack$Pose;FF)V"
            )
    )
    private void inty$fixLinePos(float pX, float pY, float pZ, VertexConsumer pConsumer, PoseStack.Pose pPose, float p_174124_, float p_174125_,
                                 @Local(argsOnly = true) FishingHook pEntity, @Local Player player, @Local(ordinal = 3) double d4,
                                 @Local(ordinal = 1, argsOnly = true) float partialTicks) {
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        InteractionHand hand = player.getMainHandItem().canPerformAction(ToolActions.FISHING_ROD_CAST) ?
                InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        Vec3 handPos;
        if (dispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
            LocalPlayerPatch patch = ClientEngine.getInstance().getPlayerPatch();
            handPos = ClientJointUtil.getItemPosition(
                    patch,
                    hand,
                    partialTicks,
                    ClientJointUtil.getFishingRodOffset()
            ).toDoubleVector();
        } else {
            LocalPlayerPatch patch = (LocalPlayerPatch) player.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY).orElse(null);
            handPos = ClientJointUtil.getItemPosition(
                    patch,
                    hand,
                    partialTicks,
                    ClientJointUtil.getFishingRodOffset()
            ).toDoubleVector();
        }
        double hookX = Mth.lerp(partialTicks, pEntity.xo, pEntity.getX());
        double hookY = Mth.lerp(partialTicks, pEntity.yo + 0.25, pEntity.getY() + 0.25);
        double hookZ = Mth.lerp(partialTicks, pEntity.zo, pEntity.getZ());
        float mx = (float)(handPos.x - hookX);
        float my = (float)(handPos.y - hookY);
        float mz = (float)(handPos.z - hookZ);
        stringVertex(mx, my, mz, pConsumer, pPose, p_174124_, p_174125_);
    }
}