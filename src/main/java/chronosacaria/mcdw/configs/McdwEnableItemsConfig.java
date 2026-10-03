/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.ItemsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {
    public final LinkedHashMap<ItemsID, Boolean> ITEMS_ENABLED = new LinkedHashMap<>();

    public McdwEnableItemsConfig() {
        for (ItemsID itemsID : ItemsID.values())
            ITEMS_ENABLED.put(itemsID, true);
    }
}
