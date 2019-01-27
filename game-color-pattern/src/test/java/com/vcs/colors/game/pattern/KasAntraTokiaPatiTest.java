//package com.vcs.colors.game.pattern;
//
//import com.vcs.colors.game.pattern.line.ColoredItem;
//import com.vcs.colors.game.pattern.line.ColoredLine;
//import org.junit.Before;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.testng.AssertJUnit.assertEquals;
//
//public class KasAntraTokiaPatiTest {
//
//    private KasAntraTokiaPati algoritmas;
//    private ColoredItem spalvos;
//
//    @Before
//    public void init() {
//        List<ColoredItem> neraGeltonos = new ArrayList<>();
//        neraGeltonos.add(ColoredItem.RED);
//        neraGeltonos.add(ColoredItem.BLUE);
//        neraGeltonos.add(ColoredItem.GREEN);
//        spalvos.equals(neraGeltonos);
//    }
//
//    @Test
//    public void kasAntraTokiaPatiSuccess(ColoredLine spalvos) {
//        List<ColoredItem> neraGeltonos = new ArrayList<>();
//        neraGeltonos.add(ColoredItem.RED);
//        neraGeltonos.add(ColoredItem.BLUE);
//        neraGeltonos.add(ColoredItem.GREEN);
//        spalvos.equals(neraGeltonos);
//        boolean output = algoritmas.isPatternMathed(spalvos);
//        assertEquals(true, output);
//
//
//    }
//
//    @Test
//    public void kasAntraTokiaPatiFail(ColoredLine spalvos) {
//        List<ColoredItem> yraGeltona = new ArrayList<>();
//
//        yraGeltona.add(ColoredItem.RED);
//        yraGeltona.add(ColoredItem.BLUE);
//        yraGeltona.add(ColoredItem.YELLOW);
//        yraGeltona.add(ColoredItem.GREEN);
//        spalvos.equals(yraGeltona);
//        boolean output = algoritmas.isPatternMathed(spalvos);
//        assertEquals(false, output);
//
//
//    }
//
//}