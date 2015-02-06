package com.sander.secret_santa.controller.persoon;

import com.sander.secret_santa.domain.persoon.Persoon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by SanderP on 20/01/2015.
 */
public class PersoonController {
    private static final Logger LOG = LoggerFactory.getLogger(PersoonController.class);

    private List<Persoon> personen;

    public PersoonController() {
        LOG.info("Instantiating new PersoonController");
        this.personen = new ArrayList<>();
    }

    public List<Persoon> getPersonen() {
        LOG.debug("PersoonController.getPersonen() called, returning person List");
        return personen;
    }

    public void addPerson(Persoon persoon) {
        if (persoon == null) {
            throw new IllegalArgumentException("persoon is null");
        }

        if (!personen.contains(persoon)) {
            LOG.debug("add new persoon tot personen");
            personen.add(persoon);
        }
    }

    public Persoon geefRandomPersoonZonderOntvangerVoor(Persoon persoon) {
        if (persoon == null) {
            throw new IllegalArgumentException("persoon is null");
        }

        if (persoon.heeftOntvanger()) {
            throw new IllegalArgumentException(persoon + " heeft al een ontvanger.");
        }

        Set<Persoon> keuzes = new HashSet<Persoon>();
        Persoon kandidaatGelukkig = null;
        boolean blijfDoorgaan;
        do {
            blijfDoorgaan = false;

            voegKeuzeUniekToeAanLijst(keuzes, kandidaatGelukkig);
            if (keuzes.size() > personen.size()) {
                throw new IllegalArgumentException("Geen personen meer");
            }
            kandidaatGelukkig = kiesRandomPersoon();
            LOG.info("nieuwe kandidaat voor " + persoon + ": " + kandidaatGelukkig);
            blijfDoorgaan = isPersoonAlGekozen(kandidaatGelukkig) || isOntvangerVan(persoon, kandidaatGelukkig);
        } while (blijfDoorgaan);

        LOG.info("kandidaat gekozen voor " + persoon + ": " + kandidaatGelukkig);

        return kandidaatGelukkig;
    }

    private Persoon kiesRandomPersoon() {
        return personen.get(new Random().nextInt(personen.size()));
    }

    private boolean isPersoonAlGekozen(Persoon kandidaatGelukkig) {
        for (Persoon persoon : personen) {
            if (persoon.heeftOntvanger() && persoon.getOntvanger().equals(kandidaatGelukkig)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOntvangerVan(Persoon persoon, Persoon kandidaatGelukkig) {
        if (persoon.equals(kandidaatGelukkig)) {
            LOG.info("persoon en kandidaat zijn gelijk");
            return true;
        }
        return false;
    }

    private void voegKeuzeUniekToeAanLijst(Set<Persoon> keuzes, Persoon kandidaatGelukkig) {
        LOG.info("nieuwe keuze wordt toegevoegd (size was " + keuzes.size() + ")");
        keuzes.add(kandidaatGelukkig);
    }
}
