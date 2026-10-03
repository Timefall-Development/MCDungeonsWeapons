/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IRangedWeaponID extends IMcdwWeaponID {

    static IRangedWeaponID[] values() {
        return IMcdwWeaponID.rangedValues();
    }

    HashMap<? extends IRangedWeaponID, IRangedWeaponID.RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    IRangedWeaponID.RangedStats getWeaponItemStats();

    IRangedWeaponID.RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    boolean getIsEnabled();
    ToolMaterial getMaterial();
    @SuppressWarnings("unused")
    double getProjectileDamage();
    int getDrawSpeed();
    float getRange();
    String[] getRepairIngredient();

    RangedStats getRangedStats();

    class RangedStats {
        public boolean isEnabled = true;
        public String material = "iron";
        public double projectileDamage = 0.0;
        public int drawSpeed = 20;
        public float range = 16f;
        String[] repairIngredient = new String[]{};

        public RangedStats rangedStats(boolean isEnabled, String material, double projectileDamage, int drawSpeed, float range, String[] repairIngredient) {
            this.isEnabled = isEnabled;
            this.material = material;
            if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
                this.projectileDamage = projectileDamage;
            } else {
                this.projectileDamage = 0;
            }
            this.drawSpeed = drawSpeed;
            this.range = range;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}