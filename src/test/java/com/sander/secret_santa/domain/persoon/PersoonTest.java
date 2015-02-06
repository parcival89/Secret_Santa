package com.sander.secret_santa.domain.persoon;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersoonTest {

    public static final String ANNELIEN = "Annelien";

    @Test
    public void constructorTest_givenNaam_thenSetsNaam(){
        Persoon persoon = new Persoon(ANNELIEN);

        assertThat(persoon.getNaam()).isEqualTo(ANNELIEN);
    }

    @Test
    public void equalsTest_givenNull_returnsFalse(){
        assertThat(new Persoon("Sander").equals(null)).isFalse();
    }

    @Test
    public void equalsTest_givenSameObject_returnsTrue(){
        Persoon sander = new Persoon("Sander");
        assertThat(sander.equals(sander)).isTrue();
    }

    @Test
    public void equalsTest_givenObjectWithSameNaam_returnsTrue(){
        Persoon sander = new Persoon("Sander");
        assertThat(sander.equals(new Persoon("Sander"))).isTrue();
    }

    @Test
    public void equalsTest_givenObjectWithDifferentNaam_returnsFalse(){
        Persoon sander = new Persoon("Sander");
        assertThat(sander.equals(new Persoon("Slabander"))).isFalse();
    }
}