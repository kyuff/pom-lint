package dk.kyuff.pomlint;

public abstract class DisableRule implements Rule {

    private boolean disabled;

    public Rule setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public boolean isDisabled() {
        return disabled;
    }
}
