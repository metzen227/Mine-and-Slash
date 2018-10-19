package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.bases.IRerollable;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.gearitem.Suffix;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.abstractclasses.AffixData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.WeightedUtils;

import net.minecraft.util.text.TextFormatting;

public class SuffixData extends AffixData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = 8802998468539898482L;

	public SuffixData() {

	}

	public SuffixData(GearItemData gear, String affixname, List<Integer> percents) {
		super();
		this.baseAffix = affixname;
		this.percents = percents;
		this.level = gear.level;
	}

	@Override
	public boolean IfRerollFully() {
		return this.setRerollFully;
	}

	@Override
	public boolean IfRerollNumbers() {

		return this.setRerollNumbers;
	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.setRerollFully = false;

		this.level = gear.level;

		List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSuffixes());
		Suffix suffix = (Suffix) WeightedUtils.WeightedRandom(list);

		baseAffix = suffix.Name();

		RerollNumbers(gear);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {
		this.setRerollNumbers = false;

		percents = new ArrayList<Integer>();

		for (StatMod mod : BaseAffix().StatMods()) {
			percents.add(StatGen.GenPercent());
		}

	}

	@Override
	public BaseAffix BaseAffix() {
		return Suffixes.All.get(baseAffix);
	}

	@Override
	public List<String> GetTooltipString() {

		BaseAffix affix = BaseAffix();

		List<String> list = new ArrayList<String>();

		list.add(TextFormatting.LIGHT_PURPLE + "Suffix: " + affix.Name());

		for (StatModData data : this.GetAllStats()) {

			list.add(data.GetTooltipString());
		}

		return list;

	}

}
