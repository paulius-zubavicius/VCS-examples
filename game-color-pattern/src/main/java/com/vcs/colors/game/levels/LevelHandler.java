package com.vcs.colors.game.levels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vcs.colors.game.pattern.ArNeraGeltonos;
import com.vcs.colors.game.pattern.ArNeraMelynos;
import com.vcs.colors.game.pattern.ArNeraRaudonos;
import com.vcs.colors.game.pattern.ArNeraZalios;
import com.vcs.colors.game.pattern.ArYraGeltona;
import com.vcs.colors.game.pattern.ArYraMelyna;
import com.vcs.colors.game.pattern.ArYraRaudona;
import com.vcs.colors.game.pattern.ArYraZalia;
import com.vcs.colors.game.pattern.ColorPattern;
import com.vcs.colors.game.pattern.KaimynesRaidesNesutampa;
import com.vcs.colors.game.pattern.KasAntraTokiaPati;
import com.vcs.colors.game.pattern.LyginisKiekis;
import com.vcs.colors.game.pattern.PirmaArbaAntraSpalvaSutampaSuPaskutine;
import com.vcs.colors.game.pattern.PirmaIrTreciaSpalvosNeRaudonos;
import com.vcs.colors.game.pattern.PirmairPriespaskutineNesutampa;
import com.vcs.colors.game.pattern.PrIrPabAsimetrija;
import com.vcs.colors.game.pattern.PrIrPabAsimetrijaIrKaimynesNesutampa;
import com.vcs.colors.game.pattern.PrIrPabNesutampaPglTaisykle;
import com.vcs.colors.game.pattern.PrIrPabNesutampaPglTaisyklePoDvi;
import com.vcs.colors.game.pattern.PrIrPabSimetrija;

public class LevelHandler {

	private Map<Level, List<ColorPattern>> levels = new HashMap<>();

	public LevelHandler() {

		levels.put(Level.LEVEL0, Arrays.asList(new KasAntraTokiaPati(), new PrIrPabSimetrija()));
		levels.put(Level.LEVEL1, Arrays.asList(new KaimynesRaidesNesutampa(), new LyginisKiekis()));
		levels.put(Level.LEVEL2, Arrays.asList(new PrIrPabAsimetrija()));
		levels.put(Level.LEVEL3,
				Arrays.asList(new ArYraGeltona(), new ArYraMelyna(), new ArYraRaudona(), new ArYraZalia()));
		levels.put(Level.LEVEL4, Arrays.asList(new PirmairPriespaskutineNesutampa()));
		levels.put(Level.LEVEL5,
				Arrays.asList(new ArNeraGeltonos(), new ArNeraMelynos(), new ArNeraRaudonos(), new ArNeraZalios()));
		levels.put(Level.LEVEL6, Arrays.asList(new PrIrPabAsimetrijaIrKaimynesNesutampa()));
		levels.put(Level.LEVEL7, Arrays.asList(new PirmaArbaAntraSpalvaSutampaSuPaskutine()));
		levels.put(Level.LEVEL8, Arrays.asList(new PirmaIrTreciaSpalvosNeRaudonos()));
		levels.put(Level.LEVEL9,
				Arrays.asList(new PrIrPabNesutampaPglTaisykle(), new PrIrPabNesutampaPglTaisyklePoDvi()));

	}

	public ColorPattern getRandomPatternImpl(Level level) {
		List<ColorPattern> patternsList = levels.get(level);
		int rndIndex = (new Random()).nextInt(patternsList.size());
		return patternsList.get(rndIndex);
	}

}
