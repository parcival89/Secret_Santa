import com.sander.secret_santa.controller.persoon.PersoonController;
import com.sander.secret_santa.controller.persoon.PersoonControllerBuilder;
import com.sander.secret_santa.domain.persoon.Persoon;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SanderP on 20/01/2015.
 */
public class SecretSanta {
    private static PersoonController controller;

    public static void main(String[] args) {
        controller = PersoonControllerBuilder.maakPersoonController().build();

        controller.addPerson(new Persoon("Sander"));
        controller.addPerson(new Persoon("Joren"));
        controller.addPerson(new Persoon("Annelien"));
        controller.addPerson(new Persoon("Annelies"));
        controller.addPerson(new Persoon("Casper"));
        controller.addPerson(new Persoon("Wout"));
        controller.addPerson(new Persoon("Trees"));
        controller.addPerson(new Persoon("Liezelot"));
        for (Persoon persoon : controller.getPersonen()) {
            zoekSecretSantaVoor(persoon);
        }

        System.out.print("Overview van alle personen in de lijst:");
        for (Persoon persoon : controller.getPersonen()) {
            System.out.println(persoon);
        }
    }

    public static void zoekSecretSantaVoor(Persoon persoon) {
        persoon.setOntvanger(controller.geefRandomPersoonZonderOntvangerVoor(persoon));
    }
}
