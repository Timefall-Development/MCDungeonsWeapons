/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwShield;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShieldsID implements IShieldID {
    SHIELD_ROYAL_GUARD(true, ToolMaterials.DIAMOND, "minecraft:iron_ingot", "minecraft:gold_ingot"),
    SHIELD_TOWER_GUARD(true, ToolMaterials.DIAMOND, "minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:copper_ingot"),
    SHIELD_VANGUARD(true, ToolMaterials.DIAMOND, "minecraft:planks", "minecraft:iron_ingot");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final String[] repairIngredient;

    @SuppressWarnings("SameParameterValue")
    ShieldsID(boolean isEnabled, ToolMaterial material, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<ShieldsID, McdwShield> getItemsEnum() {
        return ItemsRegistry.SHIELD_ITEMS;
    }

    public static HashMap<ShieldsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SHIELD_SPAWN_RATES;
    }

    public static HashMap<ShieldsID, ShieldStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shieldStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.shieldStats.get(this).isEnabled;    }

    @Override
    public McdwShield getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ShieldsID, ShieldStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shieldStats;
    }

    public ShieldStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public ShieldStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return getWeaponStats(mcdwNewStatsConfig).get(this);
    }

    @Override
    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public ShieldStats getShieldStats() {
        return new IShieldID.ShieldStats().shieldStats(isEnabled, CleanlinessHelper.materialToString(material), repairIngredient);
    }

    @Override
    public McdwShield makeWeapon() {
        McdwShield mcdwShield = new McdwShield(CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material), this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwShield);
        return mcdwShield;
    }
}
