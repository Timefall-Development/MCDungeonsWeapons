/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.statuseffects.AccelerateStatusEffect;
import chronosacaria.mcdw.statuseffects.DynamoStatusEffect;
import chronosacaria.mcdw.statuseffects.PainCycleStatusEffect;
import chronosacaria.mcdw.statuseffects.ShadowFormStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectsRegistry {
    public static StatusEffect ACCELERATE;
    public static StatusEffect DYNAMO;
    public static StatusEffect PAIN_CYCLE;
    public static StatusEffect SHADOW_FORM;

    public static void register() {
        ACCELERATE = new AccelerateStatusEffect(StatusEffectCategory.BENEFICIAL, 0x036edc, "accelerate");
        DYNAMO = new DynamoStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffbf00, "dynamo");
        PAIN_CYCLE = new PainCycleStatusEffect(StatusEffectCategory.NEUTRAL, 0x640004, "pain_cycle");
        SHADOW_FORM = new ShadowFormStatusEffect(StatusEffectCategory.BENEFICIAL, 0x40023e, "shadow_form");
    }
}
