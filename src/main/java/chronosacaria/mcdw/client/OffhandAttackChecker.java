/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.client;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;

@Environment(EnvType.CLIENT)
public class OffhandAttackChecker {
    public static void checkForOffhandAttack() {
        if (CompatibilityFlags.noOffhandConflicts) {
            MinecraftClient mc = MinecraftClient.getInstance();
            PlayerEntity player = mc.player;
            if (mc.world != null
                    && mc.currentScreen == null
                    && !mc.isPaused()
                    && player != null
                    && !player.isBlocking()) {

                if (mc.interactionManager != null && mc.getNetworkHandler() != null) {
                    mc.getNetworkHandler().sendPacket(mc.crosshairTarget instanceof EntityHitResult entityHitResult
                            ? OffhandAttackPacket.offhandAttackPacket(entityHitResult.getEntity())
                            : OffhandAttackPacket.offhandMissPacket());
                }
            }
        }
    }
}
