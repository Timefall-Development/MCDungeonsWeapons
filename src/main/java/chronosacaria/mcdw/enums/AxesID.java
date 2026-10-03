/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
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

public enum AxesID implements IMeleeWeaponID, IInnateEnchantment {
    AXE_ANCHOR(true, ToolMaterials.IRON, 8, -3.4f, "minecraft:iron_ingot"),
    AXE_AXE(true, ToolMaterials.IRON, 6, -3.1f, "minecraft:iron_ingot"),
    AXE_ENCRUSTED_ANCHOR(true, ToolMaterials.DIAMOND, 8, -3.4f, "minecraft:diamond"),
    AXE_FIREBRAND(true, ToolMaterials.DIAMOND, 4, -2.9f, "minecraft:diamond"),
    AXE_HIGHLAND(true, ToolMaterials.IRON, 4, -2.9f, "minecraft:iron_ingot");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    AxesID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<AxesID, McdwAxe> getItemsEnum() {
        return ItemsRegistry.AXE_ITEMS;
    }

    public static HashMap<AxesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.AXE_SPAWN_RATES;
    }

    public static HashMap<AxesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.axeStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.axeStats.get(this).isEnabled;
    }

    @Override
    public McdwAxe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<AxesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial(){
        return material;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public float getAttackSpeed(){
        return attackSpeed;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public MeleeStats getMeleeStats() {
        return new IMeleeWeaponID.MeleeStats().meleeStats(isEnabled, CleanlinessHelper.materialToString(material), damage, attackSpeed, repairIngredient);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case AXE_ANCHOR -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.GRAVITY);
            case AXE_AXE -> Map.of();
            case AXE_ENCRUSTED_ANCHOR -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.GRAVITY, EnchantmentsID.JUNGLE_POISON);
            case AXE_FIREBRAND -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.FIRE_ASPECT);
            case AXE_HIGHLAND -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.STUNNING);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwAxe makeWeapon() {
        McdwAxe mcdwAxe = new McdwAxe(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwAxe);
        return mcdwAxe;
    }
}
