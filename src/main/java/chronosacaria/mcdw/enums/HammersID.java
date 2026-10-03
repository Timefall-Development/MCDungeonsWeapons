/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwHammer;
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

public enum HammersID implements IMeleeWeaponID, IInnateEnchantment {
    HAMMER_BONECLUB(true, ToolMaterials.IRON,7, -3.2f, "minecraft:bone_block"),
    HAMMER_BONE_CUDGEL(true, ToolMaterials.NETHERITE,7, -3.2f, "minecraft:netherite_scrap"),
    HAMMER_FLAIL(true, ToolMaterials.IRON,5, -2.8f, "minecraft:iron_ingot"),
    HAMMER_GRAVITY(true, ToolMaterials.DIAMOND,6, -3.2f, "minecraft:diamond"),
    HAMMER_GREAT_HAMMER(true, ToolMaterials.IRON,6, -3.2f, "minecraft:iron_ingot"),
    HAMMER_MACE(true, ToolMaterials.IRON,5, -2.8f, "minecraft:iron_ingot"),
    HAMMER_STORMLANDER(true, ToolMaterials.DIAMOND,7, -3.2f, "minecraft:diamond"),
    HAMMER_SUNS_GRACE(true, ToolMaterials.DIAMOND,4, -2.8f, "minecraft:diamond");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    HammersID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<HammersID, McdwHammer> getItemsEnum() {
        return ItemsRegistry.HAMMER_ITEMS;
    }

    public static HashMap<HammersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.HAMMER_SPAWN_RATES;
    }

    public static HashMap<HammersID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.hammerStats.get(this).isEnabled;
    }

    @Override
    public McdwHammer getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<HammersID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats.get(this);
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
            case HAMMER_BONECLUB -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK);
            case HAMMER_GREAT_HAMMER, HAMMER_MACE -> Map.of();
            case HAMMER_FLAIL -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.CHAINS);
            case HAMMER_BONE_CUDGEL -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK, EnchantmentsID.ILLAGERS_BANE);
            case HAMMER_GRAVITY -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.GRAVITY);
            case HAMMER_STORMLANDER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.THUNDERING);
            case HAMMER_SUNS_GRACE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.RADIANCE);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwHammer makeWeapon() {
        McdwHammer mcdwHammer = new McdwHammer(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwHammer);
        return mcdwHammer;
    }
}
