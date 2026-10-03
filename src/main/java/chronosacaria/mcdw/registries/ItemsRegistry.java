/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enums.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

import static chronosacaria.mcdw.Mcdw.CONFIG;
import static chronosacaria.mcdw.Mcdw.ID;

public class ItemsRegistry {

    public static final EnumMap<SwordsID, McdwSword> SWORD_ITEMS = new EnumMap<>(SwordsID.class);
    public static final EnumMap<AxesID, McdwAxe> AXE_ITEMS = new EnumMap<>(AxesID.class);
    public static final EnumMap<DoubleAxesID, McdwDoubleAxe> DOUBLE_AXE_ITEMS = new EnumMap<>(DoubleAxesID.class);
    public static final EnumMap<DaggersID, McdwDagger> DAGGER_ITEMS = new EnumMap<>(DaggersID.class);
    public static final EnumMap<SoulDaggersID, McdwSoulDagger> SOUL_DAGGER_ITEMS = new EnumMap<>(SoulDaggersID.class);
    public static final EnumMap<HammersID, McdwHammer> HAMMER_ITEMS = new EnumMap<>(HammersID.class);
    public static final EnumMap<GauntletsID, McdwGauntlet> GAUNTLET_ITEMS = new EnumMap<>(GauntletsID.class);
    public static final EnumMap<SicklesID, McdwSickle> SICKLE_ITEMS = new EnumMap<>(SicklesID.class);
    public static final EnumMap<ScythesID, McdwScythe> SCYTHE_ITEMS = new EnumMap<>(ScythesID.class);
    public static final EnumMap<PicksID, McdwPick> PICK_ITEMS = new EnumMap<>(PicksID.class);
    public static final EnumMap<GlaivesID, McdwGlaive> GLAIVE_ITEMS = new EnumMap<>(GlaivesID.class);
    public static final EnumMap<SpearsID, McdwSpear> SPEAR_ITEMS = new EnumMap<>(SpearsID.class);
    public static final EnumMap<StavesID, McdwStaff> STAFF_ITEMS = new EnumMap<>(StavesID.class);
    public static final EnumMap<WhipsID, McdwWhip> WHIP_ITEMS = new EnumMap<>(WhipsID.class);
    public static final EnumMap<BowsID, McdwBow> BOW_ITEMS = new EnumMap<>(BowsID.class);
    public static final EnumMap<ShortbowsID, McdwShortbow> SHORTBOW_ITEMS = new EnumMap<>(ShortbowsID.class);
    public static final EnumMap<LongbowsID, McdwLongbow> LONGBOW_ITEMS = new EnumMap<>(LongbowsID.class);
    public static final EnumMap<CrossbowsID, McdwCrossbow> CROSSBOW_ITEMS = new EnumMap<>(CrossbowsID.class);
    public static final EnumMap<ShieldsID, McdwShield> SHIELD_ITEMS = new EnumMap<>(ShieldsID.class);
    public static final EnumMap<ItemsID, Item> MCDW_ITEMS = new EnumMap<>(ItemsID.class);

    public static void register() {
        for (IMcdwWeaponID weaponID : IMcdwWeaponID.values()) {
            if (weaponID.getIsEnabled()) {
                Item weapon = weaponID.makeWeapon();
                registerItem(weaponID.toString().toLowerCase(Locale.ROOT), weapon);
            }
        }

        for (ItemsID itemID : ItemsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.ITEMS_ENABLED.get(itemID))
                continue;
            Item item = itemID.makeItem(new Item.Settings());
            registerItem(itemID.toString().toLowerCase(Locale.ROOT), item);
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(itemID.getItem()));
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registries.ITEM, ID(id), item);
    }

}
