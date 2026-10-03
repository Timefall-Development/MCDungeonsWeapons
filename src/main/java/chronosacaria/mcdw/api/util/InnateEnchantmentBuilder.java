/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import net.minecraft.enchantment.Enchantment;

import java.util.LinkedHashMap;
import java.util.Map;

public class InnateEnchantmentBuilder {
    private LinkedHashMap<Enchantment, Integer> enchantments = new LinkedHashMap<>();

    public InnateEnchantmentBuilder addInnateEnchantment(Object enchantment, int level) {
        if (enchantment instanceof EnchantmentsID enchantmentID) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(enchantmentID).mcdw$getIsEnabled()) {
                enchantments.put(EnchantsRegistry.enchantments.get(enchantmentID), level);
            }
        } else if (enchantment instanceof Enchantment vanillaEnchantment) {
            enchantments.put(vanillaEnchantment, level);
        }
        return this;
    }

    public InnateEnchantmentBuilder addEmptyEnchantment() {
        return addInnateEnchantment(Map.of(), 0);
    }

    public Map<Enchantment, Integer> build() {
        return enchantments;
    }
}