package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.weapons.ItemHammer;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueHammer extends ItemHammer implements IUnique {

    public BaseUniqueHammer() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Hammer().Name();
    }
}