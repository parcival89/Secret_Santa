package com.sander.secret_santa.controller.persoon;

import com.sander.secret_santa.domain.persoon.Persoon;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PersoonControllerTest {

    @Test
    public void testGetPersonen_returnsAListOfPersonen() throws Exception {
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().build();

        assertThat(controller.getPersonen()).isExactlyInstanceOf(ArrayList.class);
    }

    @Test
    public void testAddPerson_givenNieuwePersoon_thenVoegtPersoonToeAanPersonen() throws Exception {
        Persoon persoon = PersoonBuilder.maakNieuwePersoon().build();
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().metPersoon(persoon).build();

        controller.addPerson(persoon);

        assertThat(controller.getPersonen()).containsAll(Arrays.asList(persoon));
    }

    @Test
    public void testAddPerson_givenDuplicatePersoon_thenNothing() throws Exception {
        Persoon persoon = PersoonBuilder.maakNieuwePersoon().build();
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().metPersoon(persoon).build();

        controller.addPerson(persoon);

        assertThat(controller.getPersonen()).containsAll(Arrays.asList(persoon));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddPerson_givenNull_thenThrowAnException() throws Exception {
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().build();

        controller.addPerson(null);
    }

    @Test(expected=IllegalArgumentException.class)
        public void testZoekSecretSantaVoor_givenNull_thenThrowAnException() throws Exception {
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().build();

        controller.geefRandomPersoonZonderOntvangerVoor(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testZoekSecretSantaVoor_givenPersoonMetOntvanger_thenThrowAnException() throws Exception {
        Persoon persoon = PersoonBuilder.maakNieuwePersoonMetOntvanger().build();
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().build();

        controller.geefRandomPersoonZonderOntvangerVoor(persoon);
    }

    @Test
    public void testWhenGeefOntvangerVoorElkePersoonGeeftIedereenEenOntvanger() {
        PersoonController controller = PersoonControllerBuilder.maakPersoonController().build();

        controller.addPerson(new Persoon("Sander"));
        controller.addPerson(new Persoon("Joren"));
        controller.addPerson(new Persoon("Annelien"));
        controller.addPerson(new Persoon("Annelies"));
        controller.addPerson(new Persoon("Casper"));
        controller.addPerson(new Persoon("Wout"));
        controller.addPerson(new Persoon("Trees"));
        controller.addPerson(new Persoon("Liezelot"));
        for(Persoon persoon: controller.getPersonen()) {
            Persoon temp = controller.geefRandomPersoonZonderOntvangerVoor(persoon);
            if(!persoon.equals(temp)){
                persoon.setOntvanger(temp);
            }
        }

        controller.getPersonen().stream().forEach(persoon -> {
            Assertions.assertThat(persoon.getOntvanger()).isNotNull();
        });
    }

}