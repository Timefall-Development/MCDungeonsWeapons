/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.api.util.EnchantmentRestriction;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.enums.SwordsID;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentRestrictionsRegistry {
    public static void register() {
        // Permit individual enchantments for specific items
        EnchantmentRestriction.permit(Enchantments.FIRE_ASPECT, itemStack -> itemStack.getItem() instanceof McdwAxe || itemStack.getItem() instanceof McdwDoubleAxe);
        EnchantmentRestriction.permit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(SwordsID.SWORD_MECHANIZED_SAWBLADE.getItem()));

        // Permit specific enchantment types for certain items
        EnchantmentRestriction.permitTarget((enchantment, itemStack) -> enchantment instanceof DamageEnchantment && itemStack.getItem() instanceof McdwSpear);

        // Prohibit individual enchantments for specific items
        EnchantmentRestriction.prohibit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(SwordsID.SWORD_BROKEN_SAWBLADE.getItem()));
    }
}