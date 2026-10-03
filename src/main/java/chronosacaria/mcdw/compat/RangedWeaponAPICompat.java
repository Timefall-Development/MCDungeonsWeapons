/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.compat;

import chronosacaria.mcdw.enums.*;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;

import java.util.HashMap;

public class RangedWeaponAPICompat {
    /** Ticks a vanilla bow pull takes, the baseline {@link RangedConfig#pull_time_bonus()} (in seconds) is added to. */
    private static final float BASELINE_PULL_TIME_TICKS = 20F;
    /** Speed of an arrow shot from a fully drawn vanilla bow. */
    private static final float BOW_BASELINE_VELOCITY = 3.0F;
    /** Speed of a bolt shot from a vanilla crossbow. */
    private static final float CROSSBOW_BASELINE_VELOCITY = 3.15F;

    public static void init() {
        var items = new HashMap<IRangedWeaponID, Item>();
        items.putAll(BowsID.getItemsEnum());
        items.putAll(ShortbowsID.getItemsEnum());
        items.putAll(LongbowsID.getItemsEnum());
        items.putAll(CrossbowsID.getItemsEnum());

        for (var entry: items.entrySet()) {
            var id = entry.getKey();
            if (!id.getIsEnabled()) {
                continue;
            }
            var isCrossbow = id instanceof CrossbowsID;
            var item = entry.getValue();
            var damage = id.getWeaponItemStats().projectileDamage;
            var speed = id.getWeaponItemStats().drawSpeed;
            float standardPullTime = isCrossbow ? 25F : 20F;
            var pullTime = isCrossbow // Speed seems to have inverse effects on crossbows compared to bows
                    ? speed
                    : standardPullTime * (20.0 / (float)speed);
            var velocity = (id.getWeaponItemStats().range / 15.0f) * 3.0;

            // Ranged Weapon API 2.x takes the pull time as a bonus in seconds over the vanilla 1 second
            // draw, and the velocity as a bonus over the weapon type's baseline speed.
            // Velocities below the baseline mean "no custom velocity", as the attribute cannot go negative.
            var pullTimeBonus = ((int) pullTime - BASELINE_PULL_TIME_TICKS) / BASELINE_PULL_TIME_TICKS;
            var baselineVelocity = isCrossbow ? CROSSBOW_BASELINE_VELOCITY : BOW_BASELINE_VELOCITY;
            var velocityBonus = Math.max(0F, (float) velocity - baselineVelocity);
            ((CustomRangedWeapon)item).setRangedWeaponConfig(new RangedConfig((float) damage, pullTimeBonus, velocityBonus));
        }
    }
}
