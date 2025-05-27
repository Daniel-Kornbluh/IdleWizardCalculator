package PersonalProjects.IdleWizardV3;

import java.util.ArrayList;
import java.util.Collections;

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

        int currentMemories = memoriesToSpend;

        HeritageManaHelper calc = new HeritageManaHelper(currentMemories);

        while (canAffordPride || canAffordNobility || canAffordPurpose || canAffordGreatness) {

            //b Pride
            canAffordPride = calc.getRemainingMemories() >= calc.getNextRekindledPrideCost();
            if (canAffordPride) {
                nextPrideValue = calc.calculatePurchaseEfficiency(NextPurchaseEnum.REKINDLED_PRIDE);
            }
            else {
                nextPrideValue = 0;
            }

            //b Nobility
            canAffordNobility = calc.getRemainingMemories() >= calc.getNextForlornNobilityCost();
            if (canAffordNobility) {
                nextNobilityValue = calc.calculatePurchaseEfficiency(NextPurchaseEnum.FORLORN_NOBILITY);
            }
            else {
                nextNobilityValue = 0;
            }

            //b Purpose
            canAffordPurpose = calc.getRemainingMemories() >= calc.getNextForlornPurposeCost();
            if (canAffordPurpose) {
                nextPurposeValue = calc.calculatePurchaseEfficiency(NextPurchaseEnum.FORLORN_PURPOSE);
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
                calc.buyRekindledPride();
            }
            else if (nextNobilityValue == valueList.get(valueList.size() - 1)) {
                calc.buyForlornNobility();
            }
            else if (nextPurposeValue == valueList.get(valueList.size() - 1)) {
                calc.buyForlornPurpose();
            }
            else if (nextGreatnessValue == valueList.get(valueList.size() - 1)) {
                calc.buyForlornGreatness();
            }

            calc.printValues();
        }
    }
}

