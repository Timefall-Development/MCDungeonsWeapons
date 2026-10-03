/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
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

public enum DaggersID implements IMeleeWeaponID, IInnateEnchantment {
    DAGGER_BACKSTABBER(true, ToolMaterials.DIAMOND,1, -1.7f, "minecraft:diamond"),
    DAGGER_CHILL_GALE_KNIFE(true, ToolMaterials.DIAMOND,2, -2.2f, "minecraft:diamond"),
    DAGGER_DAGGER(true, ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_FANGS_OF_FROST(true, ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_MOON(true, ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_RESOLUTE_TEMPEST_KNIFE(true, ToolMaterials.IRON,2, -2.2f, "minecraft:iron_ingot"),
    DAGGER_SHEAR_DAGGER(true, ToolMaterials.IRON,0, -1.5f, "minecraft:iron_ingot"),
    DAGGER_SWIFT_STRIKER(true, ToolMaterials.NETHERITE,1, -1.7f, "minecraft:netherite_scrap"),
    DAGGER_TEMPEST_KNIFE(true, ToolMaterials.IRON,2, -2.2f, "minecraft:iron_ingot"),
    DAGGER_THE_BEGINNING(true, ToolMaterials.NETHERITE,1, -1.8f, "minecraft:netherite_scrap"),
    DAGGER_THE_END(true, ToolMaterials.NETHERITE,1, -1.8f, "minecraft:netherite_scrap"),
    DAGGER_VOID_TOUCHED_BLADE(true, ToolMaterials.DIAMOND,1, -1.8f, "minecraft:diamond");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;


    DaggersID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsRegistry.DAGGER_ITEMS;
    }

    public static HashMap<DaggersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.DAGGER_SPAWN_RATES;
    }

    public static HashMap<DaggersID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.daggerStats.get(this).isEnabled;
    }

    @Override
    public McdwDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<DaggersID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats.get(this);
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
            case DAGGER_BACKSTABBER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.AMBUSH);
            case DAGGER_CHILL_GALE_KNIFE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.RUSHDOWN, EnchantmentsID.FREEZING);
            case DAGGER_FANGS_OF_FROST -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.FREEZING);
            case DAGGER_DAGGER -> Map.of();
            case DAGGER_TEMPEST_KNIFE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.RUSHDOWN);
            case DAGGER_MOON -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.ENIGMA_RESONATOR);
            case DAGGER_RESOLUTE_TEMPEST_KNIFE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.COMMITTED, EnchantmentsID.RUSHDOWN);
            case DAGGER_SHEAR_DAGGER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.SWIRLING);
            case DAGGER_SWIFT_STRIKER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.AMBUSH, EnchantmentsID.RUSHDOWN);
            case DAGGER_THE_BEGINNING -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.LEECHING);
            case DAGGER_THE_END, DAGGER_VOID_TOUCHED_BLADE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.VOID_STRIKE);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwDagger makeWeapon() {
        McdwDagger mcdwDagger = new McdwDagger(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwDagger);
        return mcdwDagger;
    }
}
