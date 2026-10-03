/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IMeleeWeaponID extends IMcdwWeaponID {

    static IMeleeWeaponID[] values() {
        return IMcdwWeaponID.meleeValues();
    }

    HashMap<? extends IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    MeleeStats getWeaponItemStats();

    MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    boolean getIsEnabled();
    ToolMaterial getMaterial();
    int getDamage();
    float getAttackSpeed();
    String[] getRepairIngredient();
    MeleeStats getMeleeStats();

    class MeleeStats {
        boolean isEnabled = true;
        String material = "iron";
        int damage = 0;
        float attackSpeed = -3f;
        String[] repairIngredient = new String[]{};

        public MeleeStats meleeStats(boolean isEnabled, String material, int damage, float attackSpeed, String[] repairIngredient) {
            this.isEnabled = isEnabled;
            this.material = material;
            this.damage = damage;
            this.attackSpeed = attackSpeed;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}