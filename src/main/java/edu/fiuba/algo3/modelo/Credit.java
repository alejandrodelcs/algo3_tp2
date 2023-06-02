package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.InsufficientCredits;

public class Credit {
    private int quantity;
    public Credit(int cant) {
        this.quantity = cant;
    }

    public Credit() {
        this.quantity = 0;
    }

    public void chargedCredits(Credit creditLoad) {
        quantity+= creditLoad.getQuantity();//TODO: See if the getter can be removed
    }
    public int getQuantity() {
        return quantity;
    }

    public boolean equalTo(Credit credit) {
        return (quantity == credit.getQuantity());
    }

    public void subtractCredits(Credit credit) {
        if ((quantity - credit.getQuantity()) <= 0) {
            throw new InsufficientCredits();
        }
        quantity -= credit.getQuantity();
    }
}
