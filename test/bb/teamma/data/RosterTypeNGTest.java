/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.data;

import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.teamma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class RosterTypeNGTest {

    private static LRB lrb;

    public RosterTypeNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB(LRB.E_Version.BB2016);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAssistant_cost method, of class RosterType.
     */
    @Test
    public void testGetAssistant_cost() {
        System.out.println("getAssistant_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getAssistant_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setAssistant_cost method, of class RosterType.
     */
    @Test
    public void testSetAssistant_cost() {
        System.out.println("setAssistant_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getAssistant_cost();
                instance.setAssistant_cost(1000);
                int result = instance.getAssistant_cost();
                Assert.assertEquals(result, 1000);
                instance.setAssistant_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getFan_factor_cost method, of class RosterType.
     */
    @Test
    public void testGetFan_factor_cost() {
        System.out.println("getFan_factor_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getFan_factor_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setFan_factor_cost method, of class RosterType.
     */
    @Test
    public void testSetFan_factor_cost() {
        System.out.println("setFan_factor_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getFan_factor_cost();
                instance.setFan_factor_cost(1000);
                int result = instance.getFan_factor_cost();
                Assert.assertEquals(result, 1000);
                instance.setFan_factor_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getCheerleader_cost method, of class RosterType.
     */
    @Test
    public void testGetCheerleader_cost() {
        System.out.println("getCheerleader_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getCheerleader_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setCheerleader_cost method, of class RosterType.
     */
    @Test
    public void testSetCheerleader_cost() {
        System.out.println("setCheerleader_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getCheerleader_cost();
                instance.setCheerleader_cost(1000);
                int result = instance.getCheerleader_cost();
                Assert.assertEquals(result, 1000);
                instance.setCheerleader_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getApothecary_cost method, of class RosterType.
     */
    @Test
    public void testGetApothecary_cost() {
        System.out.println("getApothecary_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getApothecary_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setApothecary_cost method, of class RosterType.
     */
    @Test
    public void testSetApothecary_cost() {
        System.out.println("setApothecary_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getApothecary_cost();
                instance.setApothecary_cost(1000);
                int result = instance.getApothecary_cost();
                Assert.assertEquals(result, 1000);
                instance.setApothecary_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getStarPlayer method, of class RosterType.
     */
    @Test
    public void testGetStarPlayer() {
        System.out.println("getStarPlayer");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getAvailableStarplayerCount();
                if (nb == 0) {
                    fail("Number of starplayer is null for " + instance.getName());
                }
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0; j < instance.getAvailableStarplayerCount(); j++) {
                    StarPlayer pt = instance.getAvailableStarplayer(j);
                    list.add(pt.getName());
                }

                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    StarPlayer pt = instance.getStarPlayer(key, false);
                    Assert.assertNotNull(pt);
                    Assert.assertEquals(pt, instance.getAvailableStarplayer(j));
                }

                list.clear();

                for (int j = 0; j < instance.getAvailableStarplayerCount(); j++) {
                    StarPlayer pt = instance.getAvailableStarplayer(j);
                    list.add(Translate.translate(pt.getName()));
                }

                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    StarPlayer pt = instance.getStarPlayer(key, true);
                    Assert.assertNotNull(pt);
                    Assert.assertEquals(pt, instance.getAvailableStarplayer(j));
                }

            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getName method, of class RosterType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                String result = instance.getName();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null name found");
            }
        }
    }

    /**
     * Test of setName method, of class RosterType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                String save = instance.getName();
                instance.setName("Toto");
                String value = instance.getName();
                Assert.assertEquals(value, "Toto");
                instance.setName(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getReroll_cost method, of class RosterType.
     */
    @Test
    public void testGetReroll_cost() {
        System.out.println("getReroll_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getReroll_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setReroll_cost method, of class RosterType.
     */
    @Test
    public void testSetReroll_cost() {
        System.out.println("setReroll_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getReroll_cost();
                instance.setReroll_cost(1000);
                int result = instance.getReroll_cost();
                Assert.assertEquals(result, 1000);
                instance.setReroll_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of isApothecary method, of class RosterType.
     */
    @Test
    public void testIsApothecary() {
        System.out.println("isApothecary");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                boolean value = instance.isApothecary();
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of setApothecary method, of class RosterType.
     */
    @Test
    public void testSetApothecary() {
        System.out.println("setApothecary");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                boolean save = instance.isApothecary();
                instance.setApothecary(true);
                boolean value = instance.isApothecary();
                Assert.assertTrue(value);
                instance.setApothecary(false);
                value = instance.isApothecary();
                Assert.assertFalse(value);
                instance.setApothecary(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

  

    /**
     * Test of getImage method, of class RosterType.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
//            String expResult = "";
                String result = instance.getImage();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null name found");
            }
        }
    }

    /**
     * Test of setImage method, of class RosterType.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                String save = instance.getImage();
                instance.setImage("Toto");
                String value = instance.getImage();
                Assert.assertEquals(value, "Toto");
                instance.setImage(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of addPlayerType method, of class RosterType.
     */
    @Test
    public void testAddPlayerType() {
        System.out.println("addPlayerType");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getPlayerTypeCount();
                if (nb == 0) {
                    fail("Number availabe player is null for " + instance.getName());
                }
                ArrayList<PlayerType> list = new ArrayList<>();
                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    list.add(instance.getPlayerType(j));
                }
                instance.clearPlayerType();

                for (int j = 0; j < list.size(); j++) {
                    int idx = instance.getPlayerTypeCount();
                    instance.addPlayerType(list.get(j));
                    PlayerType tmp = instance.getPlayerType(idx);
                    assertEquals(tmp, list.get(j));
                    assertEquals(idx + 1, instance.getPlayerTypeCount());
                }
            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of clearPlayerType method, of class RosterType.
     */
    @Test
    public void testClearPlayerType() {
        System.out.println("clearPlayerType");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getPlayerTypeCount();
                if (nb == 0) {
                    fail("Number of player is null for " + instance.getName());
                }
                ArrayList<PlayerType> list = new ArrayList<>();
                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    list.add(instance.getPlayerType(j));
                }
                instance.clearPlayerType();
                assertEquals(instance.getPlayerTypeCount(), 0);
                for (int j = 0; j < list.size(); j++) {
                    instance.addPlayerType(list.get(j));
                }
            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getPlayerTypeCount method, of class RosterType.
     */
    @Test
    public void testGetPlayerTypeCount() {
        System.out.println("getPlayerTypeCount");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getPlayerTypeCount();
                if (nb == 0) {
                    fail("Number of player is null for " + instance.getName());
                }
            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getPlayerType method, of class RosterType.
     */
    @Test
    public void testGetPlayerType_int() {
        System.out.println("getPlayerType");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getPlayerTypeCount();
                if (nb == 0) {
                    fail("Number of player is null for " + instance.getName());
                }
                ArrayList<PlayerType> list = new ArrayList<>();
                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);
                    list.add(pt);
                }

            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getAvailableStarplayer method, of class RosterType.
     */
    @Test
    public void testGetAvailableStarplayer() {
        System.out.println("getAvailableStarplayer");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getAvailableStarplayerCount();
                if (nb == 0) {
                    fail("Number availabe star player is null for " + instance.getName());
                }
                ArrayList<StarPlayer> list = new ArrayList<>();
                for (int j = 0; j < instance.getAvailableStarplayerCount(); j++) {
                    StarPlayer typ = instance.getAvailableStarplayer(j);
                    Assert.assertNotNull(typ);
                    list.add(typ);
                }

            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getAvailableStarplayerCount method, of class RosterType.
     */
    @Test
    public void testGetAvailableStarplayerCount() {
        System.out.println("getAvailableStarplayerCount");
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getAvailableStarplayerCount();
                if (nb == 0) {
                    fail("Number availabe star player is null for " + instance.getName());
                }

            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of addAvailableStarPlayer method, of class RosterType.
     */
    @Test
    public void testAddAvailableStarPlayer() {
        System.out.println("addAvailableStarPlayer");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getAvailableStarplayerCount();
                if (nb == 0) {
                    fail("Number availabe star player is null for " + instance.getName());
                }
                ArrayList<StarPlayer> list = new ArrayList<>();
                for (int j = 0; j < instance.getAvailableStarplayerCount(); j++) {
                    list.add(instance.getAvailableStarplayer(j));
                }
                instance.clearAvailableStarPlayerType();

                for (int j = 0; j < list.size(); j++) {
                    int idx = instance.getAvailableStarplayerCount();
                    instance.addAvailableStarPlayer(list.get(j));
                    StarPlayer tmp = instance.getAvailableStarplayer(idx);
                    assertEquals(tmp, list.get(j));
                    assertEquals(idx + 1, instance.getAvailableStarplayerCount());
                }
            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getPlayerType method, of class RosterType.
     */
    @Test
    public void testGetPlayerType_String_boolean() {
        System.out.println("getPlayerType");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getPlayerTypeCount();
                if (nb == 0) {
                    fail("Number of player is null for " + instance.getName());
                }
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    list.add(pt.getPosition());
                }

                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    PlayerType pt = instance.getPlayerType(key, false);
                    Assert.assertNotNull(pt);
                    Assert.assertEquals(pt, instance.getPlayerType(j));
                }

                list.clear();

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    list.add(Translate.translate(pt.getPosition()));
                }

                for (int j = 0; j < list.size(); j++) {
                    String key = list.get(j);
                    PlayerType pt = instance.getPlayerType(key, true);
                    Assert.assertNotNull(pt);
                    Assert.assertEquals(pt, instance.getPlayerType(j));
                }

            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of clearAvailableStarPlayerType method, of class RosterType.
     */
    @Test
    public void testClearAvailableStarPlayerType() {
        System.out.println("clearAvailableStarPlayerType");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {
                int nb = instance.getAvailableStarplayerCount();
                if (nb == 0) {
                    fail("Number availabe star player is null for " + instance.getName());
                }
                ArrayList<StarPlayer> list = new ArrayList<>();
                for (int j = 0; j < instance.getAvailableStarplayerCount(); j++) {
                    list.add(instance.getAvailableStarplayer(j));
                }
                instance.clearAvailableStarPlayerType();
                assertEquals(instance.getAvailableStarplayerCount(), 0);
                for (int j = 0; j < list.size(); j++) {
                    instance.addAvailableStarPlayer(list.get(j));
                }
            } else {
                fail("Null Roster type");
            }
        }
    }

    /**
     * Test of getVersion method, of class RosterType.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType instance = lrb.getRosterType(0);
        LRB.E_Version expResult = LRB.E_Version.BB2016;
        LRB.E_Version result = instance.getVersion();
        assertEquals(result, expResult);
    }

    /**
     * Test of setVersion method, of class RosterType.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        LRB.E_Version v = LRB.E_Version.BB2016;
        RosterType instance = new RosterType("test");
        instance.setVersion(v);
        LRB.E_Version result = instance.getVersion();
        assertEquals(result, v);
    }

    /**
     * Test of getMaxBigGuys method, of class RosterType.
     */
    @Test
    public void testGetMaxBigGuys() {
        System.out.println("getMaxBigGuys");
        RosterType instance = new RosterType("Test");
        int expResult = 1;
        int result = instance.getMaxBigGuys();
        assertEquals(result, expResult);
        
        instance.setMaxBigGuys(6);
        // TODO review the generated test code and remove the default call to fail.
        result = instance.getMaxBigGuys();
        assertEquals(result, 6);
    }

    /**
     * Test of setMaxBigGuys method, of class RosterType.
     */
    @Test
    public void testSetMaxBigGuys() {
        System.out.println("setMaxBigGuys");
        int maxBigGuys = 0;
        RosterType instance = new RosterType("Test");
        int expResult = 1;
        int result = instance.getMaxBigGuys();
        assertEquals(result, expResult);
        
        instance.setMaxBigGuys(6);
        // TODO review the generated test code and remove the default call to fail.
        result = instance.getMaxBigGuys();
        assertEquals(result, 6);
    }

    /**
     * Test of clearInducementType method, of class RosterType.
     */
    @Test
    public void testClearInducementType() {
        System.out.println("clearInducementType");
        InducementType it = new InducementType();
        RosterType instance = new RosterType("test");
        instance.addInducementType(it);
        
        assertEquals(instance.getInducementTypeSize(), 1);
        instance.clearInducementType();
        assertEquals(instance.getInducementTypeSize(), 0);
    }

    /**
     * Test of addInducementType method, of class RosterType.
     */
    @Test
    public void testAddInducementType() {
        System.out.println("addInducementType");
        InducementType it = new InducementType();
        RosterType instance = new RosterType("test");
        instance.addInducementType(it);
        
        assertEquals(instance.getInducementTypeSize(), 1);
    }

    /**
     * Test of getInducementTypeSize method, of class RosterType.
     */
    @Test
    public void testGetInducementTypeSize() {
        System.out.println("getInducementTypeSize");
        InducementType it = new InducementType();
        RosterType instance = new RosterType("test");
        instance.addInducementType(it);
        
        
        assertEquals(instance.getInducementTypeSize(), 1);
    }

    /**
     * Test of getInducementType method, of class RosterType.
     */
    @Test
    public void testGetInducementType_int() {
        System.out.println("getInducementType");
       InducementType it = new InducementType();
        RosterType instance = new RosterType("test");
        instance.addInducementType(it);
        
        assertEquals(instance.getInducementTypeSize(), 1);
        InducementType exp=instance.getInducementType(0);
        assertEquals(it, exp);
    }

    /**
     * Test of getInducementType method, of class RosterType.
     */
    @Test
    public void testGetInducementType_String() {
        System.out.println("getInducementType");
        String s = "";
        InducementType it = new InducementType();
        it.setName("test");
        RosterType instance = new RosterType("test");
        instance.addInducementType(it);        
        
        assertEquals(instance.getInducementTypeSize(), 1);
        
        InducementType exp=instance.getInducementType("test");
        assertEquals(exp.getName(), "test");
    }

}
