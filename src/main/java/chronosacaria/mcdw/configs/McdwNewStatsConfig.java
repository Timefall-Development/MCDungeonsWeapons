/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.Arrays;
import java.util.LinkedHashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    @Comment("Can use the following tags as repair ingredient -- minecraft:planks, minecraft:stone_crafting_materials. Other tags do not work.")
    public final LinkedHashMap<SwordsID, IMeleeWeaponID.MeleeStats> swordStats = new LinkedHashMap<>();
    public final LinkedHashMap<AxesID, IMeleeWeaponID.MeleeStats> axeStats = new LinkedHashMap<>();
    public final LinkedHashMap<DoubleAxesID, IMeleeWeaponID.MeleeStats> doubleAxeStats = new LinkedHashMap<>();
    public final LinkedHashMap<DaggersID, IMeleeWeaponID.MeleeStats> daggerStats = new LinkedHashMap<>();
    public final LinkedHashMap<SoulDaggersID, IMeleeWeaponID.MeleeStats> soulDaggerStats = new LinkedHashMap<>();
    public final LinkedHashMap<HammersID, IMeleeWeaponID.MeleeStats> hammerStats = new LinkedHashMap<>();
    public final LinkedHashMap<GauntletsID, IMeleeWeaponID.MeleeStats> gauntletStats = new LinkedHashMap<>();
    public final LinkedHashMap<SicklesID, IMeleeWeaponID.MeleeStats> sickleStats = new LinkedHashMap<>();
    public final LinkedHashMap<ScythesID, IMeleeWeaponID.MeleeStats> scytheStats = new LinkedHashMap<>();
    public final LinkedHashMap<PicksID, IMeleeWeaponID.MeleeStats> pickStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfGlaives = 1.0D;
    public final LinkedHashMap<GlaivesID, IMeleeWeaponID.MeleeStats> glaiveStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfSpears = 1.0D;
    public final LinkedHashMap<SpearsID, IMeleeWeaponID.MeleeStats> spearStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfStaves = 1.0D;
    public final LinkedHashMap<StavesID, IMeleeWeaponID.MeleeStats> staffStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.5")
    public final double extraAttackReachOfWhips = 1.5D;
    public final LinkedHashMap<WhipsID, IMeleeWeaponID.MeleeStats> whipStats = new LinkedHashMap<>();
    public final LinkedHashMap<BowsID, IRangedWeaponID.RangedStats> bowStats = new LinkedHashMap<>();
    public final LinkedHashMap<ShortbowsID, IRangedWeaponID.RangedStats> shortbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<LongbowsID, IRangedWeaponID.RangedStats> longbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<CrossbowsID, IRangedWeaponID.RangedStats> crossbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<ShieldsID, IShieldID.ShieldStats> shieldStats = new LinkedHashMap<>();

    public McdwNewStatsConfig() {

        for (SwordsID swordsID : SwordsID.values())
            swordStats.put(swordsID, swordsID.getMeleeStats());
        for (AxesID axesID : AxesID.values())
            axeStats.put(axesID, axesID.getMeleeStats());
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values())
            doubleAxeStats.put(doubleAxesID, doubleAxesID.getMeleeStats());
        for (DaggersID daggersID : DaggersID.values())
            daggerStats.put(daggersID, daggersID.getMeleeStats());
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values())
            soulDaggerStats.put(soulDaggersID, soulDaggersID.getMeleeStats());
        for (HammersID hammersID : HammersID.values())
            hammerStats.put(hammersID, hammersID.getMeleeStats());
        for (GauntletsID gauntletsID : GauntletsID.values())
            gauntletStats.put(gauntletsID, gauntletsID.getMeleeStats());
        for (SicklesID sicklesID : SicklesID.values())
            sickleStats.put(sicklesID, sicklesID.getMeleeStats());
        for (ScythesID scythesID : ScythesID.values())
            scytheStats.put(scythesID, scythesID.getMeleeStats());
        for (PicksID picksID : PicksID.values())
            pickStats.put(picksID, picksID.getMeleeStats());
        for (GlaivesID glaivesID : GlaivesID.values())
            glaiveStats.put(glaivesID, glaivesID.getMeleeStats());
        for (SpearsID spearsID : SpearsID.values())
            spearStats.put(spearsID, spearsID.getMeleeStats());
        for (StavesID stavesID : StavesID.values())
            staffStats.put(stavesID, stavesID.getMeleeStats());
        for (WhipsID whipsID : WhipsID.values())
            whipStats.put(whipsID, whipsID.getMeleeStats());
        for (BowsID bowsID : BowsID.values())
            bowStats.put(bowsID, bowsID.getRangedStats());
        for (ShortbowsID shortBowsID : ShortbowsID.values())
            shortbowStats.put(shortBowsID, shortBowsID.getRangedStats());
        for (LongbowsID longBowsID : LongbowsID.values())
            longbowStats.put(longBowsID, longBowsID.getRangedStats());
        for (CrossbowsID crossbowsID : CrossbowsID.values())
            crossbowStats.put(crossbowsID, crossbowsID.getRangedStats());
        for (ShieldsID shieldsID : ShieldsID.values())
            shieldStats.put(shieldsID, shieldsID.getShieldStats());

        // Stats Hash Assign
        Arrays.stream(IMeleeWeaponID.values()).forEach(IMeleeWeaponID::getMeleeStats);
        Arrays.stream(IRangedWeaponID.values()).forEach(IRangedWeaponID::getRangedStats);
        Arrays.stream(IShieldID.values()).forEach(IShieldID::getShieldStats);
    }


}
