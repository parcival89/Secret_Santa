package com.sander.secret_santa.controller.persoon;

import com.sander.secret_santa.domain.persoon.Persoon;

import java.util.ArrayList;
import java.util.List;

public class PersoonControllerBuilder {
    private List<Persoon> personen;

    protected PersoonControllerBuilder() {
        this.personen = new ArrayList<>();
    }

    public static PersoonControllerBuilder maakPersoonController() {
        return new PersoonControllerBuilder();
    }

    public PersoonControllerBuilder metPersoon(Persoon persoon){
        this.personen.add(persoon);
        return this;
    }

    public PersoonController build(){
        PersoonController controller = new PersoonController();
        for(Persoon persoon: personen){
            controller.addPerson(persoon);
        }

        return controller;
    }
}