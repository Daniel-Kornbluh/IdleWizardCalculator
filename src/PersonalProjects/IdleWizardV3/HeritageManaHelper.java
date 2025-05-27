package PersonalProjects.IdleWizardV3;

import static PersonalProjects.IdleWizardV3.NextPurchaseEnum.*;

public class HeritageManaHelper {
    private int remainingMemories;

    private double initialProfit = 1;

    private double rekindledPrideOneMultiplier = 1.0;
    private double forlornNobilityTwoMultiplier = 1.0;
    private double forlornPurposeThreeMultiplier = 1.0;
    private double forlornGreatnessFourMultiplier = 1.0;

    private int nextRekindledPrideCost = REKINDLED_PRIDE.getInitialCost();
    private int nextForlornNobilityCost = FORLORN_NOBILITY.getInitialCost();
    private int nextForlornPurposeCost = FORLORN_PURPOSE.getInitialCost();
    private int nextForlornGreatnessCost = FORLORN_GREATNESS.getInitialCost();

    private int pridePurchased = 0;
    private int nobilityPurchased = 0;
    private int purposePurchased = 0;
    private int greatnessPurchased = 0;

    HeritageManaHelper(int remainingMemories) {
        this.remainingMemories = remainingMemories;
    }

    public double calculatePurchaseEfficiency(NextPurchaseEnum purchaseTypeToCheck) {
        double currentMultiplier = calculateCurrentMultiplier();

        return switch (purchaseTypeToCheck) {
            case REKINDLED_PRIDE -> {
                double multiplierAfterPridePurchase = (initialProfit * calculateNextUpgradeMultiplier(REKINDLED_PRIDE) * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterPridePurchase = (multiplierAfterPridePurchase - currentMultiplier);
                double pridePurchaseEfficiency = (totalIncreaseAfterPridePurchase / nextRekindledPrideCost);
                yield pridePurchaseEfficiency;
            }
            case FORLORN_NOBILITY -> {
                // Cannot purchase more than 100 nobility
                if (nobilityPurchased == FORLORN_NOBILITY.getMaxPurchases()) {
                    yield 0;
                }
                double multiplierAfterNobilityPurchase = (initialProfit * rekindledPrideOneMultiplier * calculateNextUpgradeMultiplier(FORLORN_NOBILITY) * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterNobilityPurchase = (multiplierAfterNobilityPurchase - currentMultiplier);
                double nobilityPurchaseEfficiency = (totalIncreaseAfterNobilityPurchase / nextForlornNobilityCost);
                yield nobilityPurchaseEfficiency;
            }
            case FORLORN_PURPOSE -> {
                // Cannot purchase more than 80 purpose
                if (purposePurchased == FORLORN_PURPOSE.getMaxPurchases()) {
                    yield 0;
                }
                double multiplierAfterPurposePurchase = (initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * calculateNextUpgradeMultiplier(FORLORN_PURPOSE) * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterPurposePurchase = (multiplierAfterPurposePurchase - currentMultiplier);
                double purposePurchaseEfficiency = (totalIncreaseAfterPurposePurchase / nextForlornPurposeCost);
                yield purposePurchaseEfficiency;
            }
            case FORLORN_GREATNESS -> {
                // Cannot purchase more than 50 greatness
                if (greatnessPurchased == FORLORN_GREATNESS.getMaxPurchases()) {
                    yield 0;
                }
                double multiplierAfterGreatnessPurchase = (initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * calculateNextUpgradeMultiplier(FORLORN_GREATNESS));
                double totalIncreaseAfterGreatnessPurchase = (multiplierAfterGreatnessPurchase - currentMultiplier);
                double greatnessPurchaseEfficiency = (totalIncreaseAfterGreatnessPurchase / nextForlornGreatnessCost);
                yield greatnessPurchaseEfficiency;
            }
        };
    }

    private double calculateCurrentMultiplier() {
        return initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier;
    }

    int getRemainingMemories() {
        return remainingMemories;
    }

    void printValues() {
        System.out.println("Purchased Rekindled Pride = " + pridePurchased + " Times");
        System.out.println("Purchased Forlorn Nobility = " + nobilityPurchased + " Times");
        System.out.println("Purchased Forlorn Purpose = " + purposePurchased + " Times");
        System.out.println("Purchased Forlorn Greatness = " + greatnessPurchased + " Times");

        System.out.println();

        System.out.println("Final Stats for Rekindled Pride = " + rekindledPrideOneMultiplier);
        System.out.println("Final Stats for Forlorn Nobility = " + forlornNobilityTwoMultiplier);
        System.out.println("Final Stats for Forlorn Purpose = " + forlornPurposeThreeMultiplier);
        System.out.println("Final Stats for Forlorn Greatness = " + forlornGreatnessFourMultiplier);

        System.out.println();

        System.out.println("Original Mana Per Second = " + initialProfit);
        System.out.println("Final Mana Per Second = " + calculateCurrentMultiplier());
    }


    public void buyUpgrade(NextPurchaseEnum upgradeToPurchase) {
        int nextUpgradeCostIncrease = upgradeToPurchase.getCostIncreasePerUpgrade();
        double nextUpgradeMultiplier = calculateNextUpgradeMultiplier(upgradeToPurchase);
        double costOfUpgrade = calculateNextUpgradeMultiplier(upgradeToPurchase);


        switch (upgradeToPurchase) {
            case REKINDLED_PRIDE -> {
                rekindledPrideOneMultiplier = nextUpgradeMultiplier;
                remainingMemories -= costOfUpgrade;
                nextRekindledPrideCost += nextUpgradeCostIncrease;
                pridePurchased++;
            }
            case FORLORN_NOBILITY -> {
                forlornNobilityTwoMultiplier = nextUpgradeMultiplier;
                remainingMemories -= costOfUpgrade;
                nextForlornNobilityCost += nextUpgradeCostIncrease;
                nobilityPurchased++;
            }
            case FORLORN_PURPOSE -> {
                forlornPurposeThreeMultiplier = nextUpgradeMultiplier;
                remainingMemories -= costOfUpgrade;
                nextForlornPurposeCost += nextUpgradeCostIncrease;
                purposePurchased++;
            }
            case FORLORN_GREATNESS -> {
                forlornGreatnessFourMultiplier = nextUpgradeMultiplier;
                remainingMemories -= costOfUpgrade;
                nextForlornGreatnessCost += nextUpgradeCostIncrease;
                greatnessPurchased++;
            }
        }
    }

    private double calculateNextUpgradeMultiplier(NextPurchaseEnum nextUpgrade) {
        return switch (nextUpgrade) {
            case REKINDLED_PRIDE -> (rekindledPrideOneMultiplier + 0.5);
            case FORLORN_NOBILITY -> (forlornNobilityTwoMultiplier * 1.25);
            case FORLORN_PURPOSE -> (forlornPurposeThreeMultiplier * 1.5);
            case FORLORN_GREATNESS -> (forlornGreatnessFourMultiplier * 2);
        };
    }

    double getNextUpgradeCost(NextPurchaseEnum nextUpgrade) {
        return switch (nextUpgrade) {
            case REKINDLED_PRIDE -> nextRekindledPrideCost;
            case FORLORN_NOBILITY -> nextForlornNobilityCost;
            case FORLORN_PURPOSE -> nextForlornPurposeCost;
            case FORLORN_GREATNESS -> nextForlornGreatnessCost;

        };
    }
}
