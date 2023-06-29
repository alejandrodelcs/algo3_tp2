package edu.fiuba.algo3.modelo.credit;

import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;

public class Credit {
    private int quantity;
    public Credit(int cant) {
        this.quantity = cant;
    }
    public Credit() {
        this.quantity = 0;
    }
    public void chargeCredits(Credit creditLoad) {
        quantity += creditLoad.getQuantity();
    }
    public int getQuantity() {
        return quantity;
    }
    public boolean equalTo(Credit credit) {
        return (quantity== credit.getQuantity());
    }
    public void subtractCredits(Credit credit) {
        if ((quantity - credit.getQuantity()) < 0) {
            throw new InsufficientCredits();
        }
        quantity -= credit.getQuantity();
    }
    public boolean areNegative() { //TODO: bad coding practice you must ask true and positive
        return quantity < 0;
    }
    public boolean arePositive() {
        return  quantity > 0;
    }
}
