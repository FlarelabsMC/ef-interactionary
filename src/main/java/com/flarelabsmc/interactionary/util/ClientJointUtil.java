package com.flarelabsmc.interactionary.util;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;

@OnlyIn(Dist.CLIENT)
public class ClientJointUtil {
    public static Vec3f getItemPosition(LocalPlayerPatch patch, InteractionHand hand, float partialTicks, Vec3f offsetPos) {
        String jointName = hand == InteractionHand.MAIN_HAND ? "Tool_R" : "Tool_L";
        return getJointPosition(patch, jointName, partialTicks, offsetPos);
    }

    public static Vec3f getItemEulerRotation(LocalPlayerPatch patch, InteractionHand hand, float partialTicks) {
        String jointName = hand == InteractionHand.MAIN_HAND ? "Tool_R" : "Tool_L";
        return getJointEulerRotation(patch, jointName, partialTicks);
    }

    public static Vec3f getJointPosition(LocalPlayerPatch patch, String jointName, float partialTicks, Vec3f offsetPos) {
        OpenMatrix4f m = getJointMatrix(patch, jointName, partialTicks);
        Vec3f jointBasePos = new Vec3f(m.m30, m.m31, m.m32);
        Vec3f rotatedOffset = new Vec3f();
        rotatedOffset.x = m.m00 * offsetPos.x + m.m10 * offsetPos.y + m.m20 * offsetPos.z;
        rotatedOffset.y = m.m01 * offsetPos.x + m.m11 * offsetPos.y + m.m21 * offsetPos.z;
        rotatedOffset.z = m.m02 * offsetPos.x + m.m12 * offsetPos.y + m.m22 * offsetPos.z;
        return new Vec3f(
                jointBasePos.x + rotatedOffset.x + Mth.lerp(partialTicks, patch.getOriginal().xo, patch.getOriginal().getX()),
                jointBasePos.y + rotatedOffset.y + Mth.lerp(partialTicks, patch.getOriginal().yo, patch.getOriginal().getY()),
                jointBasePos.z + rotatedOffset.z + Mth.lerp(partialTicks, patch.getOriginal().zo, patch.getOriginal().getZ())
        );
    }

    public static Vec3f getJointEulerRotation(LocalPlayerPatch patch, String jointName, float partialTicks) {
        OpenMatrix4f m = getJointMatrix(patch, jointName, partialTicks);
        return OpenMatrixUtil.getEulerAnglesXYZ(m);
    }

    public static OpenMatrix4f getJointMatrix(LocalPlayerPatch patch, String jointName, float partialTicks) {
        OpenMatrix4f m = patch.getArmature().getBoundTransformFor(patch.getAnimator().getPose(partialTicks), patch.getArmature().searchJointByName(jointName));
        OpenMatrix4f transform = new OpenMatrix4f()
                .rotate(-((float) Math.toRadians((Mth.lerp(partialTicks, patch.getOriginal().yBodyRotO + 180.0f, patch.getOriginal().yBodyRot + 180.0f)))), new Vec3f(0f, 1f, 0f));
        OpenMatrix4f.mul(transform, m, m);
        return m;
    }

    public static Vec3f getFishingRodOffset() {
        return new Vec3f(-0.05, 0.1, -0.8);
    }
}
