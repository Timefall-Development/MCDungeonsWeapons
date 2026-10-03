/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ScythesID implements IMeleeWeaponID, IInnateEnchantment {
    SCYTHE_FROST_SCYTHE(true, ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SCYTHE_JAILORS_SCYTHE(true, ToolMaterials.IRON,4, -2.9f, "minecraft:iron_ingot"),
    SCYTHE_SKULL_SCYTHE(true, ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SCYTHE_SOUL_SCYTHE(true, ToolMaterials.DIAMOND,3, -2.9f, "minecraft:diamond");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    @SuppressWarnings("SameParameterValue")
    ScythesID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<ScythesID, McdwScythe> getItemsEnum() {
        return ItemsRegistry.SCYTHE_ITEMS;
    }

    public static HashMap<ScythesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SCYTHE_SPAWN_RATES;
    }

    public static HashMap<ScythesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.scytheStats.get(this).isEnabled;
    }

    @Override
    public McdwScythe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ScythesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats.get(this);
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
            case SCYTHE_FROST_SCYTHE, SCYTHE_SKULL_SCYTHE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.FREEZING), 1);
            case SCYTHE_JAILORS_SCYTHE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.CHAINS), 1);
            case SCYTHE_SOUL_SCYTHE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.SOUL_DEVOURER), 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwScythe makeWeapon() {
        McdwScythe mcdwScythe = new McdwScythe(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwScythe);
        return mcdwScythe;
    }
}
