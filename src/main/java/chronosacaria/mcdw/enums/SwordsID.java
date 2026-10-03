/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwSword;
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

public enum SwordsID implements IMeleeWeaponID, IInnateEnchantment {
    SWORD_BEESTINGER(true, ToolMaterials.IRON, 0, -1.1f, "minecraft:iron_ingot"),
    SWORD_BROADSWORD(true, ToolMaterials.IRON, 5, -3.0f, "minecraft:iron_ingot"),
    SWORD_BROKEN_SAWBLADE(true, ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_CLAYMORE(true, ToolMaterials.IRON, 7, -3.2f, "minecraft:iron_ingot"),
    SWORD_CORAL_BLADE(true, ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_CUTLASS(true, ToolMaterials.IRON,2, -2.3f, "minecraft:iron_ingot"),
    SWORD_DANCERS_SWORD(true, ToolMaterials.IRON,3, -2.0f, "minecraft:iron_ingot"),
    SWORD_DARK_KATANA(true, ToolMaterials.NETHERITE,4, -2.9f, "minecraft:netherite_scrap"),
    SWORD_DIAMOND_SWORD_VAR(true, ToolMaterials.DIAMOND,3, -2.4f, "minecraft:diamond"),
    SWORD_FREEZING_FOIL(true, ToolMaterials.IRON,1, -1.1f, "minecraft:iron_ingot"),
    SWORD_FROST_SLAYER(true, ToolMaterials.DIAMOND, 6, -3.2f, "minecraft:diamond"),
    SWORD_GREAT_AXEBLADE(true, ToolMaterials.IRON, 7, -3.2f, "minecraft:iron_ingot"),
    SWORD_HAWKBRAND(true, ToolMaterials.IRON,6, -2.9f, "minecraft:iron_ingot"),
    SWORD_HEARTSTEALER(true, ToolMaterials.DIAMOND, 6, -3.2f, "minecraft:diamond"),
    SWORD_IRON_SWORD_VAR(true, ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_KATANA(true, ToolMaterials.IRON,4, -2.9f, "minecraft:iron_ingot"),
    SWORD_MASTERS_KATANA(true, ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SWORD_MECHANIZED_SAWBLADE(true, ToolMaterials.DIAMOND,3, -2.4f, "minecraft:blaze_rod"),
    SWORD_NAMELESS_BLADE(true, ToolMaterials.IRON,4, -2.3f, "minecraft:iron_ingot"),
    SWORD_OBSIDIAN_CLAYMORE(true, ToolMaterials.NETHERITE, 6, -3.3f, "minecraft:netherite_scrap"),
    SWORD_RAPIER(true, ToolMaterials.IRON,0, -1.14f, "minecraft:iron_ingot"),
    SWORD_SINISTER(true, ToolMaterials.IRON,6, -2.9f, "minecraft:iron_ingot"),
    SWORD_SPONGE_STRIKER(true, ToolMaterials.DIAMOND,3, -2.4f, "minecraft:diamond"),
    SWORD_THE_STARLESS_NIGHT(true, ToolMaterials.NETHERITE, 6, -3.3f, "minecraft:netherite_scrap");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    SwordsID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<SwordsID, McdwSword> getItemsEnum() {
        return ItemsRegistry.SWORD_ITEMS;
    }

    public static HashMap<SwordsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SWORD_SPAWN_RATES;
    }

    public static HashMap<SwordsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.swordStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.swordStats.get(this).isEnabled;    }

    @Override
    public McdwSword getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<SwordsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats.get(this);
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
            case SWORD_BEESTINGER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.BUSY_BEE);
            case SWORD_BROKEN_SAWBLADE, SWORD_CORAL_BLADE, SWORD_CUTLASS, SWORD_DIAMOND_SWORD_VAR, SWORD_IRON_SWORD_VAR, SWORD_KATANA, SWORD_OBSIDIAN_CLAYMORE, SWORD_RAPIER -> Map.of();
            case SWORD_CLAYMORE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK);
            case SWORD_BROADSWORD -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK, EnchantmentsID.SWIRLING);
            case SWORD_DANCERS_SWORD -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.RAMPAGING);
            case SWORD_DARK_KATANA -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.SMITING);
            case SWORD_FREEZING_FOIL -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.FREEZING);
            case SWORD_FROST_SLAYER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK, EnchantmentsID.FREEZING);
            case SWORD_GREAT_AXEBLADE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK, EnchantmentsID.DYNAMO);
            case SWORD_HAWKBRAND, SWORD_MASTERS_KATANA, SWORD_SINISTER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.CRITICAL_HIT);
            case SWORD_HEARTSTEALER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.KNOCKBACK, EnchantmentsID.LEECHING);
            case SWORD_MECHANIZED_SAWBLADE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.FIRE_ASPECT);
            case SWORD_NAMELESS_BLADE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.WEAKENING);
            case SWORD_SPONGE_STRIKER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.ENIGMA_RESONATOR);
            case SWORD_THE_STARLESS_NIGHT -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.SHARED_PAIN);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwSword makeWeapon() {
        McdwSword mcdwSword = new McdwSword(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwSword);
        return mcdwSword;
    }
}
