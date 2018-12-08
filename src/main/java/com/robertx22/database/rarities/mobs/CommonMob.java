package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseCommon;

public class CommonMob extends BaseCommon implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 0.5F;
    }

    @Override
    public float DamageMultiplier() {
	return 0.4F;
    }

    @Override
    public float HealthMultiplier() {
	return 0.55F;
    }

    @Override
    public float LootMultiplier() {
	return 0.7F;
    }

    @Override
    public int MaxMobEffects() {
	return 0;
    }

    @Override
    public float ExpOnKill() {
	return 3;
    }

}