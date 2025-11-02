package com.flarelabsmc.interactionary.util;

import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;

public class OpenMatrixUtil {
    public static Vec3f getEulerAnglesXYZ(OpenMatrix4f m) {
        float x = (float) Math.atan2(-m.m21, m.m22);
        float y = (float) Math.atan2(-m.m20, Math.sqrt(1.0f - m.m20 * m.m20));
        float z = (float) Math.atan2(-m.m10, m.m00);
        return new Vec3f((float) Math.toDegrees(x), (float) Math.toDegrees(y), (float) Math.toDegrees(z));
    }
}
