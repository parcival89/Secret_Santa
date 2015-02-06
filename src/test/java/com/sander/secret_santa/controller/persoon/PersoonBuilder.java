package com.sander.secret_santa.controller.persoon;

import com.sander.secret_santa.domain.persoon.Persoon;

/**
 * Created by SanderP on 22/01/2015.
 */
public class PersoonBuilder {
    private static final String DEFAULT_NAAM = "TEST";
    private static final Persoon DEFAULT_ONTVANGER = new Persoon("ontvanger");

    private String naam;
    private Persoon ontvanger;

    protected PersoonBuilder(){
        this.naam = DEFAULT_NAAM;
        this.ontvanger = null;
    }

    public static PersoonBuilder maakNieuwePersoon(){
        return new PersoonBuilder();
    }

    public static PersoonBuilder maakNieuwePersoonMetOntvanger(){
        return maakNieuwePersoon().metOntvanger(DEFAULT_ONTVANGER);
    }

    public PersoonBuilder metNaam(String naam){
        this.naam = naam;
        return this;
    }

    public PersoonBuilder metOntvanger(Persoon ontvanger) {
        this.ontvanger = ontvanger;
        return this;
    }

    public Persoon build(){
        Persoon persoon = new Persoon(naam);
        persoon.setOntvanger(this.ontvanger);
        return persoon;
    }

}
