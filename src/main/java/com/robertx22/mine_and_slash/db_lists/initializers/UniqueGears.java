package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.unique_items.armor.BeastBloodChest;
import com.robertx22.mine_and_slash.database.unique_items.armor.InnerConfluxRobe;
import com.robertx22.mine_and_slash.database.unique_items.armor.JesterHat;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.BirthingMiracleNecklace;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.SkullOfSpiritsNecklace;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.ring.GreedsPersistenceRing;
import com.robertx22.mine_and_slash.database.unique_items.weapons.axe.ObsidianMightAxe;
import com.robertx22.mine_and_slash.database.unique_items.weapons.sword.WaterElementalSword;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class UniqueGears implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        new WaterElementalSword().addToSerializables();
        new BirthingMiracleNecklace().addToSerializables();
        new SkullOfSpiritsNecklace().addToSerializables();
        new GreedsPersistenceRing().addToSerializables();
        new JesterHat().addToSerializables();
        new ObsidianMightAxe().addToSerializables();
        new InnerConfluxRobe().addToSerializables();
        new BeastBloodChest().addToSerializables();

    }
}
