package com.alex.mysickwell.validation;


public abstract class Middleware {
    private Middleware next;

    /**
     * Builds chains of middleware objects.
     */
    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(String query);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(String query) {
        if (next == null) {
            return true;
        }
        return next.check(query);
    }
}
