/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.data;

import chronosacaria.mcdw.Mcdw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigItemEnabledCondition {

    public static void register() {
        // Register a resource condition for checking if multiple config values are all true
        ResourceConditions.register(new Identifier(Mcdw.MOD_ID, "config_enabled"), jsonObject -> {
            JsonArray values = JsonHelper.getArray(jsonObject, "values");
            List<Boolean> booleanList = new ArrayList<>();

            for (JsonElement element : values) {
                if (element.isJsonPrimitive()) {
                    String elementString = element.getAsString();
                    List<String> configClasses = Arrays.asList(elementString.split("\\."));
                    try {
                        if (configClasses.size() > 1) {
                            // Retrieve the config value and add it to the boolean list
                            booleanList.add(
                                    Mcdw.CONFIG
                                            .getClass().getField(configClasses.get(0))
                                            .get(Mcdw.CONFIG).getClass().getField(configClasses.get(1))
                                            .getBoolean(Mcdw.CONFIG.getClass().getField(configClasses.get(0))
                                                    .get(Mcdw.CONFIG)));
                        } else {
                            // Retrieve the config value and add it to the boolean list
                            booleanList.add(Mcdw.CONFIG.getClass().getField(elementString).getBoolean(Mcdw.CONFIG));
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            // Check if all config values are true
            return booleanList.stream().allMatch(Boolean::booleanValue);
        });

        // Register a resource condition for checking if an item is enabled
        ResourceConditions.register(new Identifier(Mcdw.MOD_ID, "item_enabled"), jsonObject -> {
            JsonArray values = JsonHelper.getArray(jsonObject, "values");

            for (JsonElement element : values) {
                if (element.isJsonPrimitive()) {
                    try {
                        // Check if the item exists in the item registry
                        return Registries.ITEM.get(new Identifier(element.getAsString())) != Items.AIR;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            // If no item is specified, default to true
            return true;
        });
    }
}