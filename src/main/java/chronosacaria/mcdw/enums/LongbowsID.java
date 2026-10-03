/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.bases.McdwLongbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum LongbowsID implements IRangedWeaponID, IInnateEnchantment {
    BOW_GUARDIAN_BOW(true, ToolMaterials.DIAMOND, RangedAttackHelper.mcdw$getBowDrawSpeed(2.0f), RangedAttackHelper.mcdw$getBowDrawSpeed(2.00f), 19f, "minecraft:diamond"),
    BOW_LONGBOW(     true, ToolMaterials.IRON,    RangedAttackHelper.mcdw$getBowDrawSpeed(2.0f), RangedAttackHelper.mcdw$getBowDrawSpeed(1.65f), 17f, "minecraft:planks"),
    BOW_RED_SNAKE(   true, ToolMaterials.DIAMOND, RangedAttackHelper.mcdw$getBowDrawSpeed(2.0f), RangedAttackHelper.mcdw$getBowDrawSpeed(2.00f), 18f, "minecraft:diamond");
// Draw speed = 9
    public final boolean isEnabled;
    public final ToolMaterial material;
    public final double projectileDamage;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    LongbowsID(boolean isEnabled, ToolMaterial material, double projectileDamage, int drawSpeed, float range, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
            this.projectileDamage = projectileDamage;
            this.drawSpeed = drawSpeed;
        } else {
            this.projectileDamage = 0;
            this.drawSpeed = 0;
        }
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<LongbowsID, McdwLongbow> getItemsEnum() {
        return ItemsRegistry.LONGBOW_ITEMS;
    }

    public static HashMap<LongbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.LONGBOW_SPAWN_RATES;
    }

    public static HashMap<LongbowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.longbowStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.longbowStats.get(this).isEnabled;
    }

    @Override
    public McdwLongbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<LongbowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longbowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longbowStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public double getProjectileDamage() {
        if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
            return projectileDamage;
        } else {
            return 0;
        }
    }

    @Override
    public int getDrawSpeed() {
        return drawSpeed;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public RangedStats getRangedStats() {
        return new IRangedWeaponID.RangedStats().rangedStats(isEnabled, CleanlinessHelper.materialToString(material), projectileDamage, drawSpeed, range, repairIngredient);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case BOW_LONGBOW -> Map.of();
            case BOW_GUARDIAN_BOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(2, Enchantments.POWER);
            case BOW_RED_SNAKE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.POWER, EnchantmentsID.FUSE_SHOT);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public McdwLongbow makeWeapon() {
        McdwLongbow mcdwLongbow = new McdwLongbow(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);
        getItemsEnum().put(this, mcdwLongbow);
        return mcdwLongbow;
    }
}