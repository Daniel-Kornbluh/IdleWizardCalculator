package PersonalProjects.IdleWizardV3;

import java.util.ArrayList;
import java.util.Collections;

import static PersonalProjects.IdleWizardV3.NextPurchaseEnum.*;

public class MemoryCalculator {
    private int memoriesToSpend = 0;
    private final HeritageManaHelper calc;

    private double nextPrideValue = 0;
    private double nextNobilityValue = 0;
    private double nextPurposeValue = 0;
    private double nextGreatnessValue = 0;
    private double nextMajestyValue = 0;

    private boolean canAffordPride = true;
    private boolean canAffordNobility = true;
    private boolean canAffordPurpose = true;
    private boolean canAffordGreatness = true;
    private boolean canAffordMajesty = true;

    public MemoryCalculator(int memoriesToSpend) {
        this.memoriesToSpend = memoriesToSpend;
        this.calc = new HeritageManaHelper(memoriesToSpend);
    }

    private void setCanAffordPride(boolean canAffordPride) {
        this.canAffordPride = canAffordPride;
    }
    private void setCanAffordNobility(boolean canAffordNobility) {
        this.canAffordNobility = canAffordNobility;
    }
    private void setCanAffordPurpose(boolean canAffordPurpose) {
        this.canAffordPurpose = canAffordPurpose;
    }
    private void setCanAffordGreatness(boolean canAffordGreatness) {
        this.canAffordGreatness = canAffordGreatness;
    }
    private void setCanAffordMajesty(boolean canAffordMajesty) {
        this.canAffordMajesty = canAffordMajesty;
    }

    public void getIdealHeritageManaPurchases2() {
        while (canAffordPride || canAffordNobility || canAffordPurpose || canAffordGreatness || canAffordMajesty) {
            //b Update which purchases can still be bought
            determineAvailablePurchases();

            //b Set values for all upgrades
            nextPrideValue = calcPurchaseValue(canAffordPride, REKINDLED_PRIDE);
            nextNobilityValue = calcPurchaseValue(canAffordNobility, FORLORN_NOBILITY);
            nextPurposeValue = calcPurchaseValue(canAffordPurpose, FORLORN_PURPOSE);
            nextGreatnessValue = calcPurchaseValue(canAffordGreatness, FORLORN_GREATNESS);
            nextMajestyValue = calcPurchaseValue(canAffordMajesty, FORLORN_MAJESTY);

            //b Calculate the highest value upgrade
            ArrayList<Double> valueList = new ArrayList<>();
            valueList.add(nextPrideValue);
            valueList.add(nextNobilityValue);
            valueList.add(nextPurposeValue);
            valueList.add(nextGreatnessValue);
            valueList.add(nextMajestyValue);
            double maxValueUpgrade = Collections.max(valueList);

            //b Purchase the best upgrade
            if (nextPrideValue == maxValueUpgrade) {
                calc.buyUpgrade(REKINDLED_PRIDE);
            }
            else if (nextNobilityValue == maxValueUpgrade) {
                calc.buyUpgrade(FORLORN_NOBILITY);
            }
            else if (nextPurposeValue == maxValueUpgrade) {
                calc.buyUpgrade(FORLORN_PURPOSE);
            }
            else if (nextGreatnessValue == maxValueUpgrade) {
                calc.buyUpgrade(FORLORN_GREATNESS);
            }
            else if (nextMajestyValue == maxValueUpgrade) {
                calc.buyUpgrade(FORLORN_MAJESTY);
            }

        }

        //b Print the full report for which purchases to buy
        calc.printValues();
    }

    private void determineAvailablePurchases() {
        if (canAffordPride) {
            setCanAffordPride(calc.getRemainingMemories() >= calc.getNextUpgradeCost(REKINDLED_PRIDE));
        }
        if (canAffordNobility) {
            setCanAffordNobility(calc.getRemainingMemories() >= calc.getNextUpgradeCost(FORLORN_NOBILITY));
        }
        if (canAffordPurpose) {
            setCanAffordPurpose(calc.getRemainingMemories() >= calc.getNextUpgradeCost(FORLORN_PURPOSE));
        }
        if (canAffordGreatness) {
            setCanAffordGreatness(calc.getRemainingMemories() >= calc.getNextUpgradeCost(FORLORN_GREATNESS));
        }
        if (canAffordMajesty) {
            setCanAffordMajesty(calc.getRemainingMemories() >= calc.getNextUpgradeCost(FORLORN_MAJESTY));
        }
    }

    private double calcPurchaseValue(boolean canAffordPurchase, NextPurchaseEnum purchaseType) {
        if (!canAffordPurchase) {
            return 0;
        }
        double nextPurchaseValue = calc.calculatePurchaseEfficiency(purchaseType);
        return nextPurchaseValue;
    }

}

