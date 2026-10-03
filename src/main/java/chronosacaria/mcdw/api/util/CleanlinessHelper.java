/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.bases.McdwLongbow;
import chronosacaria.mcdw.bases.McdwShortbow;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public class CleanlinessHelper {
    @SuppressWarnings("deprecation")
    public static final Random random = Random.createThreadSafe();

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) < chance;
    }

    public static boolean canRepairCheck(String[] repairIngredient, ItemStack ingredient) {
        List<Item> potentialIngredients = new ArrayList<>(List.of());
        AtomicBoolean isWood = new AtomicBoolean(false);
        AtomicBoolean isStone = new AtomicBoolean(false);
        if (repairIngredient != null && repairIngredient.length > 0) {
            Arrays.stream(repairIngredient).toList().forEach(repIngredient -> {
                if (repIngredient != null) {
                    if (repIngredient.contentEquals("minecraft:planks"))
                        isWood.set(true);
                    else if (repIngredient.contentEquals("minecraft:stone_crafting_materials"))
                        isStone.set(true);
                    potentialIngredients.add(
                            Registries.ITEM.get(new Identifier(repIngredient)));
                }
            });
        }

        return potentialIngredients.contains(ingredient.getItem())
                || (isWood.get() && ingredient.isIn(ItemTags.PLANKS)
                || (isStone.get() && ingredient.isIn(ItemTags.STONE_CRAFTING_MATERIALS)));
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, float volume, float pitch) {
        playCenteredSound(center, soundEvent, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playCenteredSound (LivingEntity center, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        center.getWorld().playSound(null,
                center.getX(), center.getY(), center.getZ(),
                soundEvent, soundCategory,
                volume, pitch);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item) {
        mcdw$dropItem(le, item, 1);
    }

    public static void mcdw$dropItem(LivingEntity le, ItemStack itemStack) {
        ItemEntity it = new ItemEntity(
                le.getWorld(), le.getX(), le.getY(), le.getZ(),
                itemStack);
        le.getWorld().spawnEntity(it);
    }

    public static void mcdw$dropItem(LivingEntity le, Item item, int amount) {
        mcdw$dropItem(le, new ItemStack(item, amount));
    }

    public static void mcdw$tooltipHelper(ItemStack stack, List<Text> tooltip, int subStringIndex) {
        int i = 1;
        String str = stack.getItem().getTranslationKey().toLowerCase(Locale.ROOT).substring(subStringIndex);
        String translationKey = String.format("tooltip_info_item.mcdw.%s_", str);
        while (I18n.hasTranslation(translationKey + i)) {
            tooltip.add(Text.translatable(translationKey + i).formatted(Formatting.ITALIC));
            i++;
        }
        if (stack.getItem() instanceof IOffhandAttack) {
            if (CompatibilityFlags.noOffhandConflicts) {
                tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
                tooltip.add(Text.translatable("tooltip_note_item.mcdw.dualwield").formatted(Formatting.GREEN));
            }
        }
        if (stack.getItem() instanceof McdwShortbow) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_note_item.mcdw.shortbow").formatted(Formatting.GREEN));
        }
        if (stack.getItem() instanceof McdwLongbow) {
            tooltip.add(Text.translatable("tooltip_info_item.mcdw.gap").formatted(Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip_note_item.mcdw.longbow").formatted(Formatting.GREEN));
        }

    }

    public static void addPPEEnchantments(ItemStack itemStack, IMcdwEnchantedArrow ppe) {
        int chainReactionLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.CHAIN_REACTION), itemStack);
        if (chainReactionLevel > 0) {
            ppe.mcdw$setChainReactionLevel(chainReactionLevel);
        }
        int chargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.CHARGE), itemStack);
        if (chargeLevel > 0) {
            ppe.mcdw$setChargeLevel(chargeLevel);
        }
        int cobwebShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.COBWEB_SHOT), itemStack);
        if (cobwebShotLevel > 0) {
            ppe.mcdw$setCobwebShotLevel(cobwebShotLevel);
        }
        int dynamoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.DYNAMO), itemStack);
        if (dynamoLevel > 0) {
            ppe.mcdw$setDynamoLevel(dynamoLevel);
        }
        int enigmaResonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ENIGMA_RESONATOR), itemStack);
        if (enigmaResonatorLevel > 0) {
            ppe.mcdw$setEnigmaResonatorLevel(enigmaResonatorLevel);
        }
        int fuseShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.FUSE_SHOT), itemStack);
        if (fuseShotLevel > 0) {
            ppe.mcdw$setFuseShotLevel(fuseShotLevel);
        }
        int freezingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.FREEZING), itemStack);
        if (freezingLevel > 0) {
            ppe.mcdw$setFreezingLevel(freezingLevel);
        }
        int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.GRAVITY), itemStack);
        if (gravityLevel > 0) {
            ppe.mcdw$setGravityLevel(gravityLevel);
        }
        int growingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.GROWING), itemStack);
        if (growingLevel > 0) {
            ppe.mcdw$setGrowingLevel(growingLevel);
        }
        int levitationShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.LEVITATION_SHOT), itemStack);
        if (levitationShotLevel > 0) {
            ppe.mcdw$setLevitationShotLevel(levitationShotLevel);
        }
        int phantomsMarkLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.PHANTOMS_MARK), itemStack);
        if (phantomsMarkLevel > 0) {
            ppe.mcdw$setPhantomsMarkLevel(phantomsMarkLevel);
        }
        int poisonCloudLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.POISON_CLOUD), itemStack);
        if (poisonCloudLevel > 0) {
            ppe.mcdw$setPoisonCloudLevel(poisonCloudLevel);
        }
        int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.RADIANCE), itemStack);
        if (radianceLevel > 0) {
            ppe.mcdw$setRadianceLevel(radianceLevel);
        }
        int replenishLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.REPLENISH), itemStack);
        if (replenishLevel > 0) {
            ppe.mcdw$setReplenishLevel(replenishLevel);
        }
        int ricochetLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.RICOCHET), itemStack);
        if (ricochetLevel > 0) {
            ppe.mcdw$setRicochetLevel(ricochetLevel);
        }
        int shadowShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.SHADOW_SHOT), itemStack);
        if (shadowShotLevel > 0) {
            ppe.mcdw$setShadowShotLevel(shadowShotLevel);
        }
        int tempoTheftLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.TEMPO_THEFT), itemStack);
        if (tempoTheftLevel > 0) {
            ppe.mcdw$setTempoTheftLevel(tempoTheftLevel);
        }
        int thunderingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.THUNDERING), itemStack);
        if (thunderingLevel > 0) {
            ppe.mcdw$setThunderingLevel(thunderingLevel);
        }
        int voidShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.VOID_SHOT), itemStack);
        if (voidShotLevel > 0) {
            ppe.mcdw$setVoidShotLevel(voidShotLevel);
        }
        int wildRageLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.WILD_RAGE), itemStack);
        if (wildRageLevel > 0){
            ppe.mcdw$setWildRageLevel(wildRageLevel);
        }
    }

    public static Map<Enchantment, Integer> mcdw$checkInnateEnchantmentEnabled(int level, Object... enchantments) {
        LinkedHashMap<Enchantment, Integer> enchantmentIntegerLinkedHashMap = new LinkedHashMap<>();
        for (Object enchantment : enchantments) {
            if (enchantment instanceof EnchantmentsID id) {
                if (CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(id).mcdw$getIsEnabled()
                        && CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {
                    enchantmentIntegerLinkedHashMap.put(EnchantsRegistry.enchantments.get(id), level);
                }
            } else if (enchantment instanceof Enchantment vanillaEnchantment
                    && CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {
                enchantmentIntegerLinkedHashMap.put(vanillaEnchantment, level);
            }
        }
        return enchantmentIntegerLinkedHashMap;
    }

    public static String materialToString(ToolMaterial toolMaterial) {
        if (toolMaterial == ToolMaterials.WOOD)
            return "wood";
        else if (toolMaterial == ToolMaterials.STONE)
            return "stone";
        else if (toolMaterial == ToolMaterials.GOLD)
            return "gold";
        else if (toolMaterial == ToolMaterials.IRON)
            return "iron";
        else if (toolMaterial == ToolMaterials.DIAMOND)
            return "diamond";
        else if (toolMaterial == ToolMaterials.NETHERITE)
            return "netherite";
        else
            return "none";
    }

    public static ToolMaterial stringToMaterial(String material) {
        return switch (material) {
            case "wood" -> ToolMaterials.WOOD;
            case "stone" -> ToolMaterials.STONE;
            case "gold" -> ToolMaterials.GOLD;
            case "diamond" -> ToolMaterials.DIAMOND;
            case "netherite" -> ToolMaterials.NETHERITE;
            default -> ToolMaterials.IRON;
        };
    }
}
