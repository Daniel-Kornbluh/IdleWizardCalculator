package PersonalProjects.IdleWizardV3;

public enum NextPurchaseEnum {
    NONE(0, 0, 0),
    REKINDLED_PRIDE(1, 500, 100),
    FORLORN_NOBILITY(2, 500, 100),
    FORLORN_PURPOSE(3, 1000, 1200),
    FORLORN_GREATNESS(4, 15000, 6000);

    private final int value;
    private final int initialCost;
    private final int costIncreasePerUpgrade;

    NextPurchaseEnum(int value, int initialCost, int costIncreasePerUpgrade) {
        this.value = value;
        this.initialCost = initialCost;
        this.costIncreasePerUpgrade = costIncreasePerUpgrade;
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
}
