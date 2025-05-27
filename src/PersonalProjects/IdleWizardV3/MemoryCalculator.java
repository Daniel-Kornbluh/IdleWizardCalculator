package PersonalProjects.IdleWizardV3;

import java.util.ArrayList;
import java.util.Collections;

import static PersonalProjects.IdleWizardV3.NextPurchaseEnum.*;

public class MemoryCalculator {

    public void getIdealHeritageManaPurchases2(int memoriesToSpend) {
        double nextPrideValue = 0;
        double nextNobilityValue = 0;
        double nextPurposeValue = 0;
        double nextGreatnessValue = 0;

        boolean canAffordPride = true;
        boolean canAffordNobility = true;
        boolean canAffordPurpose = true;
        boolean canAffordGreatness = true;

        HeritageManaHelper calc = new HeritageManaHelper(memoriesToSpend);

        while (canAffordPride || canAffordNobility || canAffordPurpose || canAffordGreatness) {

            //b Pride
            canAffordPride = calc.getRemainingMemories() >= calc.getNextRekindledPrideCost();
            if (canAffordPride) {
                nextPrideValue = calc.calculatePurchaseEfficiency(REKINDLED_PRIDE);
            }
            else {
                nextPrideValue = 0;
            }

            //b Nobility
            canAffordNobility = calc.getRemainingMemories() >= calc.getNextForlornNobilityCost();
            if (canAffordNobility) {
                nextNobilityValue = calc.calculatePurchaseEfficiency(FORLORN_NOBILITY);
            }
            else {
                nextNobilityValue = 0;
            }

            //b Purpose
            canAffordPurpose = calc.getRemainingMemories() >= calc.getNextForlornPurposeCost();
            if (canAffordPurpose) {
                nextPurposeValue = calc.calculatePurchaseEfficiency(FORLORN_PURPOSE);
            }
            else {
                nextPurposeValue = 0;
            }

            //b Greatness
            canAffordGreatness = calc.getRemainingMemories() >= calc.getNextForlornGreatnessCost();
            if (canAffordGreatness) {
                nextGreatnessValue = calc.calculatePurchaseEfficiency(NextPurchaseEnum.FORLORN_GREATNESS);
            }
            else {
                nextGreatnessValue = 0;
            }


            ArrayList<Double> valueList = new ArrayList<>();
            valueList.add(nextPrideValue);
            valueList.add(nextNobilityValue);
            valueList.add(nextPurposeValue);
            valueList.add(nextGreatnessValue);

            Collections.sort(valueList);

            if (nextPrideValue == valueList.get(valueList.size() - 1)) {
                calc.buyUpgrade(REKINDLED_PRIDE);
            }
            else if (nextNobilityValue == valueList.get(valueList.size() - 1)) {
                calc.buyUpgrade(FORLORN_NOBILITY);
            }
            else if (nextPurposeValue == valueList.get(valueList.size() - 1)) {
                calc.buyUpgrade(FORLORN_PURPOSE);
            }
            else if (nextGreatnessValue == valueList.get(valueList.size() - 1)) {
                calc.buyUpgrade(FORLORN_GREATNESS);
            }

            calc.printValues();
        }
    }
}

