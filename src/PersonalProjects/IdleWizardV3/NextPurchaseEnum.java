package PersonalProjects.IdleWizardV3;

public enum NextPurchaseEnum {
    NONE(0), REKINDLED_PRIDE(1), FORLORN_NOBILITY(2), FORLORN_PURPOSE(3), FORLORN_GREATNESS(4);

    private final int value;

    NextPurchaseEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
