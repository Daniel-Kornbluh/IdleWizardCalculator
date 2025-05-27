package PersonalProjects.IdleWizardV3;

public class HeritageManaHelper {
    private int remainingMemories;

    private double initialProfit = 1;

    private double rekindledPrideOneMultiplier = 1.0;
    private double forlornNobilityTwoMultiplier = 1.0;
    private double forlornPurposeThreeMultiplier = 1.0;
    private double forlornGreatnessFourMultiplier = 1.0;

    private int nextRekindledPrideCost = 500;
    private int nextForlornNobilityCost = 500;
    private int nextForlornPurposeCost = 1000;
    private int nextForlornGreatnessCost = 15000;

    public int pridePurchased = 0;
    public int nobilityPurchased = 0;
    private int purposePurchased = 0;
    private int greatnessPurchased = 0;

    HeritageManaHelper(int remainingMemories) {
        this.remainingMemories = remainingMemories;
    }

    public double calculatePurchaseEfficiency(NextPurchaseEnum purchaseTypeToCheck) {
        double currentMultiplier = calculateCurrentMultiplier();

        return switch (purchaseTypeToCheck) {
            case REKINDLED_PRIDE -> {
                double multiplierAfterPridePurchase = (initialProfit * calculateNextRekindledPrideMultiplier() * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterPridePurchase = (multiplierAfterPridePurchase - currentMultiplier);
                double pridePurchaseEfficiency = (totalIncreaseAfterPridePurchase / nextRekindledPrideCost);
                yield pridePurchaseEfficiency;
            }
            case FORLORN_NOBILITY -> {
                // Cannot purchase more than 100 nobility
                if (nobilityPurchased == 100) {
                    yield 0;
                }
                double multiplierAfterNobilityPurchase = (initialProfit * rekindledPrideOneMultiplier * calculateNextForlornNobilityMultiplier() * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterNobilityPurchase = (multiplierAfterNobilityPurchase - currentMultiplier);
                double nobilityPurchaseEfficiency = (totalIncreaseAfterNobilityPurchase / nextForlornNobilityCost);
                yield nobilityPurchaseEfficiency;
            }
            case FORLORN_PURPOSE -> {
                // Cannot purchase more than 80 purpose
                if (purposePurchased == 80) {
                    yield 0;
                }
                double multiplierAfterPurposePurchase = (initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * calculateNextForlornPurposeMultiplier() * forlornGreatnessFourMultiplier);
                double totalIncreaseAfterPurposePurchase = (multiplierAfterPurposePurchase - currentMultiplier);
                double purposePurchaseEfficiency = (totalIncreaseAfterPurposePurchase / nextForlornPurposeCost);
                yield purposePurchaseEfficiency;
            }
            case FORLORN_GREATNESS -> {
                // Cannot purchase more than 80 purpose
                if (greatnessPurchased == 50) {
                    yield 0;
                }
                double multiplierAfterGreatnessPurchase = (initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * calculateNextForlornGreatnessMultiplier());
                double totalIncreaseAfterGreatnessPurchase = (multiplierAfterGreatnessPurchase - currentMultiplier);
                double greatnessPurchaseEfficiency = (totalIncreaseAfterGreatnessPurchase / nextForlornGreatnessCost);
                yield greatnessPurchaseEfficiency;
            }
            case NONE -> 0;
        };
    }

    private double calculateCurrentMultiplier() {
        return initialProfit * rekindledPrideOneMultiplier * forlornNobilityTwoMultiplier * forlornPurposeThreeMultiplier * forlornGreatnessFourMultiplier;
    }
    int getRemainingMemories() {
        return remainingMemories;
    }

    // FORLORN PRIDE
    private double calculateNextRekindledPrideMultiplier() {
        return (rekindledPrideOneMultiplier + 0.5);
    }
    double getNextRekindledPrideCost() {
        return nextRekindledPrideCost;
    }
    void buyRekindledPride() {
        rekindledPrideOneMultiplier = calculateNextRekindledPrideMultiplier();
        remainingMemories -= getNextRekindledPrideCost();
        nextRekindledPrideCost += 100;
        pridePurchased++;
    }

    // FORLORN NOBILITY
    private double calculateNextForlornNobilityMultiplier() {
        return (forlornNobilityTwoMultiplier * 1.25);
    }
    double getNextForlornNobilityCost() {
        return nextForlornNobilityCost;
    }
    void buyForlornNobility() {
        forlornNobilityTwoMultiplier = calculateNextForlornNobilityMultiplier();
        remainingMemories -= getNextForlornNobilityCost();
        nextForlornNobilityCost += 100;
        nobilityPurchased++;
    }

    // FORLORN PURPOSE
    double calculateNextForlornPurposeMultiplier() {
        return (forlornPurposeThreeMultiplier * 1.5);
    }
    double getNextForlornPurposeCost() {
        return nextForlornPurposeCost;
    }
    void buyForlornPurpose() {
        forlornPurposeThreeMultiplier = calculateNextForlornPurposeMultiplier();
        remainingMemories -= getNextForlornPurposeCost();
        nextForlornPurposeCost += 1200;
        purposePurchased++;
    }

    // FORLORN GREATNESS
    double calculateNextForlornGreatnessMultiplier() {
        return (forlornGreatnessFourMultiplier * 2);
    }
    double getNextForlornGreatnessCost() {
        return nextForlornGreatnessCost;
    }
    void buyForlornGreatness() {
        forlornGreatnessFourMultiplier = calculateNextForlornGreatnessMultiplier();
        remainingMemories -= getNextForlornGreatnessCost();
        nextForlornGreatnessCost += 6000;
        greatnessPurchased++;
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


}
