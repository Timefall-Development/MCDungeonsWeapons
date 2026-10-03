/*
Timefall Development License 1.2
Copyright (c) 2020-2026. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.registries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEventsRegistry {
    public static final Identifier ECHO_SOUND = new Identifier("mcdw:echo_sound");
    public static final SoundEvent ECHO_SOUND_EVENT = SoundEvent.of(ECHO_SOUND);

    public static void register(){
        registerSound(ECHO_SOUND, ECHO_SOUND_EVENT);
    }

    @SuppressWarnings("SameParameterValue")
    protected static void registerSound(Identifier soundIdentifier, SoundEvent soundEvent) {
        Registry.register(Registries.SOUND_EVENT, soundIdentifier, soundEvent);

    }
}
