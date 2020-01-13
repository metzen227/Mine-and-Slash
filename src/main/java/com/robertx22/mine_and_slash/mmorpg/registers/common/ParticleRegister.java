package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegister {

    public final static String eleId = Ref.MODID + ":ele_particle";

    @ObjectHolder(eleId)
    public static final BasicParticleType ele_particle = null;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<ParticleType<?>> event) {

        register(eleId, new BasicParticleType(false));

    }

    private static <T extends ParticleType<?>> T register(String name, T particleType) {
        particleType.setRegistryName(name);
        ForgeRegistries.PARTICLE_TYPES.register(particleType);
        return particleType;
    }
}
