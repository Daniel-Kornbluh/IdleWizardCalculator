package PersonalProjects.IdleWizardV3;

public enum NextPurchaseEnum {
    REKINDLED_PRIDE(1, 500, 100, -1),
    FORLORN_NOBILITY(2, 500, 100, 100),
    FORLORN_PURPOSE(3, 1000, 1200, 80),
    FORLORN_GREATNESS(4, 15000, 6000, 50);

    private final int value;
    private final int initialCost;
    private final int costIncreasePerUpgrade;
    private final int maxPurchases;

    NextPurchaseEnum(int value, int initialCost, int costIncreasePerUpgrade, int maxPurchases) {
        this.value = value;
        this.initialCost = initialCost;
        this.costIncreasePerUpgrade = costIncreasePerUpgrade;
        this.maxPurchases = maxPurchases;
    }

    public int getValue() {
        return value;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public int getCostIncreasePerUpgrade() {
        return costIncreasePerUpgrade;
    }

    public int getMaxPurchases() {
        return maxPurchases;
    }
}
