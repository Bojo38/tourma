/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.languages.Translate;

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
        lrb = LRB.getLRB();
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
     * Test of getExtraRerollCost method, of class RosterType.
     */
    @Test
    public void testGetExtraRerollCost() {
        System.out.println("getExtraRerollCost");
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
                int result = instance.getExtraRerollCost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null ExtraRerollCost found");
            }
        }
    }

    /**
     * Test of setExtraRerollCost method, of class RosterType.
     */
    @Test
    public void testSetExtraRerollCost() {
        System.out.println("setExtraRerollCost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getExtraRerollCost();
                instance.setExtraRerollCost(1000);
                int result = instance.getExtraRerollCost();
                Assert.assertEquals(result, 1000);
                instance.setExtraRerollCost(save);
            } else {
                fail("Null ExtraRerollCost found");
            }
        }
    }

    /**
     * Test of getBabe_cost method, of class RosterType.
     */
    @Test
    public void testGetBabe_cost() {
        System.out.println("getBabe_cost");
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
                int result = instance.getBabe_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setBabe_cost method, of class RosterType.
     */
    @Test
    public void testSetBabe_cost() {
        System.out.println("setBabe_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getBabe_cost();
                instance.setBabe_cost(1000);
                int result = instance.getBabe_cost();
                Assert.assertEquals(result, 1000);
                instance.setBabe_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getWizard_cost method, of class RosterType.
     */
    @Test
    public void testGetWizard_cost() {
        System.out.println("getWizard_cost");
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
                int result = instance.getWizard_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setWizard_cost method, of class RosterType.
     */
    @Test
    public void testSetWizard_cost() {
        System.out.println("setWizard_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getWizard_cost();
                instance.setWizard_cost(1000);
                int result = instance.getWizard_cost();
                Assert.assertEquals(result, 1000);
                instance.setWizard_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getLocal_apo_cost method, of class RosterType.
     */
    @Test
    public void testGetLocal_apo_cost() {
        System.out.println("getLocal_apo_cost");
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
                int result = instance.getLocal_apo_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setLocal_apo_cost method, of class RosterType.
     */
    @Test
    public void testSetLocal_apo_cost() {
        System.out.println("setLocal_apo_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getLocal_apo_cost();
                instance.setLocal_apo_cost(1000);
                int result = instance.getLocal_apo_cost();
                Assert.assertEquals(result, 1000);
                instance.setLocal_apo_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getIgor_cost method, of class RosterType.
     */
    @Test
    public void testGetIgor_cost() {
        System.out.println("getIgor_cost");
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
                int result = instance.getIgor_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setIgor_cost method, of class RosterType.
     */
    @Test
    public void testSetIgor_cost() {
        System.out.println("setIgor_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getIgor_cost();
                instance.setIgor_cost(1000);
                int result = instance.getIgor_cost();
                Assert.assertEquals(result, 1000);
                instance.setIgor_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
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
                    StarPlayer pt = instance.getStarPlayer(key,false);
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
                    StarPlayer pt = instance.getStarPlayer(key,true);
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
     * Test of getBribe_cost method, of class RosterType.
     */
    @Test
    public void testGetBribe_cost() {
        System.out.println("getBribe_cost");
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
                int result = instance.getBribe_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setBribe_cost method, of class RosterType.
     */
    @Test
    public void testSetBribe_cost() {
        System.out.println("setBribe_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getBribe_cost();
                instance.setBribe_cost(1000);
                int result = instance.getBribe_cost();
                Assert.assertEquals(result, 1000);
                instance.setBribe_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of getChef_cost method, of class RosterType.
     */
    @Test
    public void testGetChef_cost() {
        System.out.println("getChef_cost");
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
                int result = instance.getChef_cost();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null Cost found");
            }
        }
    }

    /**
     * Test of setChef_cost method, of class RosterType.
     */
    @Test
    public void testSetChef_cost() {
        System.out.println("setChef_cost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                int save = instance.getChef_cost();
                instance.setChef_cost(1000);
                int result = instance.getChef_cost();
                Assert.assertEquals(result, 1000);
                instance.setChef_cost(save);
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of isIgor method, of class RosterType.
     */
    @Test
    public void testIsIgor() {
        System.out.println("isIgor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                boolean value = instance.isIgor();
            } else {
                fail("Null roster type found");
            }
        }
    }

    /**
     * Test of setIgor method, of class RosterType.
     */
    @Test
    public void testSetIgor() {
        System.out.println("setIgor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);

            if (instance != null) {
                boolean save = instance.isIgor();
                instance.setIgor(true);
                boolean value = instance.isIgor();
                Assert.assertTrue(value);
                instance.setIgor(false);
                value = instance.isIgor();
                Assert.assertFalse(value);
                instance.setIgor(save);
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
                    PlayerType pt=instance.getPlayerType(j);
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
                    StarPlayer typ=instance.getAvailableStarplayer(j);
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
                    PlayerType pt = instance.getPlayerType(key,false);
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
                    PlayerType pt = instance.getPlayerType(key,true);
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
        RosterType instance = null;
        LRB.E_Version expResult = null;
        LRB.E_Version result = instance.getVersion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVersion method, of class RosterType.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        LRB.E_Version v = null;
        RosterType instance = null;
        instance.setVersion(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChaos_wizard method, of class RosterType.
     */
    @Test
    public void testIsChaos_wizard() {
        System.out.println("isChaos_wizard");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isChaos_wizard();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChaos_wizard method, of class RosterType.
     */
    @Test
    public void testSetChaos_wizard() {
        System.out.println("setChaos_wizard");
        boolean _chaos_wizard = false;
        RosterType instance = null;
        instance.setChaos_wizard(_chaos_wizard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChaos_wizard_cost method, of class RosterType.
     */
    @Test
    public void testGetChaos_wizard_cost() {
        System.out.println("getChaos_wizard_cost");
        int expResult = 0;
        int result = RosterType.getChaos_wizard_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChaos_wizard_cost method, of class RosterType.
     */
    @Test
    public void testSetChaos_wizard_cost() {
        System.out.println("setChaos_wizard_cost");
        int _chaos_wizard_cost = 0;
        RosterType.setChaos_wizard_cost(_chaos_wizard_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHoratio_X_Schottenheim_cost method, of class RosterType.
     */
    @Test
    public void testGetHoratio_X_Schottenheim_cost() {
        System.out.println("getHoratio_X_Schottenheim_cost");
        int expResult = 0;
        int result = RosterType.getHoratio_X_Schottenheim_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHoratio_X_Schottenheim_cost method, of class RosterType.
     */
    @Test
    public void testSetHoratio_X_Schottenheim_cost() {
        System.out.println("setHoratio_X_Schottenheim_cost");
        int Horatio_X_Schottenheim_cost = 0;
        RosterType.setHoratio_X_Schottenheim_cost(Horatio_X_Schottenheim_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isKari_Coldstell method, of class RosterType.
     */
    @Test
    public void testIsKari_Coldstell() {
        System.out.println("isKari_Coldstell");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isKari_Coldstell();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKari_Coldstell method, of class RosterType.
     */
    @Test
    public void testSetKari_Coldstell() {
        System.out.println("setKari_Coldstell");
        boolean Kari_Coldstell = false;
        RosterType instance = null;
        instance.setKari_Coldstell(Kari_Coldstell);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKari_Coldstell_cost method, of class RosterType.
     */
    @Test
    public void testGetKari_Coldstell_cost() {
        System.out.println("getKari_Coldstell_cost");
        int expResult = 0;
        int result = RosterType.getKari_Coldstell_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKari_Coldstell_cost method, of class RosterType.
     */
    @Test
    public void testSetKari_Coldstell_cost() {
        System.out.println("setKari_Coldstell_cost");
        int Kari_Coldstell_cost = 0;
        RosterType.setKari_Coldstell_cost(Kari_Coldstell_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFink_Da_Fixer method, of class RosterType.
     */
    @Test
    public void testIsFink_Da_Fixer() {
        System.out.println("isFink_Da_Fixer");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isFink_Da_Fixer();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFink_Da_Fixer method, of class RosterType.
     */
    @Test
    public void testSetFink_Da_Fixer() {
        System.out.println("setFink_Da_Fixer");
        boolean Fink_Da_Fixer = false;
        RosterType instance = null;
        instance.setFink_Da_Fixer(Fink_Da_Fixer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFink_Da_Fixer_cost method, of class RosterType.
     */
    @Test
    public void testGetFink_Da_Fixer_cost() {
        System.out.println("getFink_Da_Fixer_cost");
        int expResult = 0;
        int result = RosterType.getFink_Da_Fixer_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFink_Da_Fixer_cost method, of class RosterType.
     */
    @Test
    public void testSetFink_Da_Fixer_cost() {
        System.out.println("setFink_Da_Fixer_cost");
        int Fink_Da_Fixer_cost = 0;
        RosterType.setFink_Da_Fixer_cost(Fink_Da_Fixer_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPapa_Skullbones method, of class RosterType.
     */
    @Test
    public void testIsPapa_Skullbones() {
        System.out.println("isPapa_Skullbones");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isPapa_Skullbones();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPapa_Skullbones method, of class RosterType.
     */
    @Test
    public void testSetPapa_Skullbones() {
        System.out.println("setPapa_Skullbones");
        boolean Papa_Skullbones = false;
        RosterType instance = null;
        instance.setPapa_Skullbones(Papa_Skullbones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPapa_Skullbones_cost method, of class RosterType.
     */
    @Test
    public void testGetPapa_Skullbones_cost() {
        System.out.println("getPapa_Skullbones_cost");
        int expResult = 0;
        int result = RosterType.getPapa_Skullbones_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPapa_Skullbones_cost method, of class RosterType.
     */
    @Test
    public void testSetPapa_Skullbones_cost() {
        System.out.println("setPapa_Skullbones_cost");
        int Papa_Skullbones_cost = 0;
        RosterType.setPapa_Skullbones_cost(Papa_Skullbones_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGalandril_Silverwater method, of class RosterType.
     */
    @Test
    public void testIsGalandril_Silverwater() {
        System.out.println("isGalandril_Silverwater");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isGalandril_Silverwater();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGalandril_Silverwater method, of class RosterType.
     */
    @Test
    public void testSetGalandril_Silverwater() {
        System.out.println("setGalandril_Silverwater");
        boolean Galandril_Silverwater = false;
        RosterType instance = null;
        instance.setGalandril_Silverwater(Galandril_Silverwater);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGalandril_Silverwater_cost method, of class RosterType.
     */
    @Test
    public void testGetGalandril_Silverwater_cost() {
        System.out.println("getGalandril_Silverwater_cost");
        int expResult = 0;
        int result = RosterType.getGalandril_Silverwater_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGalandril_Silverwater_cost method, of class RosterType.
     */
    @Test
    public void testSetGalandril_Silverwater_cost() {
        System.out.println("setGalandril_Silverwater_cost");
        int Galandril_Silverwater_cost = 0;
        RosterType.setGalandril_Silverwater_cost(Galandril_Silverwater_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isKrot_Shockwhisker method, of class RosterType.
     */
    @Test
    public void testIsKrot_Shockwhisker() {
        System.out.println("isKrot_Shockwhisker");
        RosterType instance = null;
        boolean expResult = false;
        boolean result = instance.isKrot_Shockwhisker();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKrot_Shockwhisker method, of class RosterType.
     */
    @Test
    public void testSetKrot_Shockwhisker() {
        System.out.println("setKrot_Shockwhisker");
        boolean Krot_Shockwhisker = false;
        RosterType instance = null;
        instance.setKrot_Shockwhisker(Krot_Shockwhisker);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKrot_Shockwhisker_cost method, of class RosterType.
     */
    @Test
    public void testGetKrot_Shockwhisker_cost() {
        System.out.println("getKrot_Shockwhisker_cost");
        int expResult = 0;
        int result = RosterType.getKrot_Shockwhisker_cost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKrot_Shockwhisker_cost method, of class RosterType.
     */
    @Test
    public void testSetKrot_Shockwhisker_cost() {
        System.out.println("setKrot_Shockwhisker_cost");
        int Krot_Shockwhisker_cost = 0;
        RosterType.setKrot_Shockwhisker_cost(Krot_Shockwhisker_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
