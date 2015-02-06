package com.sander.secret_santa.domain.persoon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by SanderP on 20/01/2015.
 */
public class Persoon {
    private String naam;
    private Persoon ontvanger;

    public Persoon(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setOntvanger(Persoon ontvanger) {
        if (this.equals(ontvanger)) {
            throw new IllegalArgumentException("Ontvanger en koper zijn gelijk");
        }
        this.ontvanger = ontvanger;
    }

    public Persoon getOntvanger() {
        return ontvanger;
    }

    public boolean heeftOntvanger() {
        return this.ontvanger != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Persoon)) {
            return false;
        }

        Persoon persoon = (Persoon) obj;
        return this.naam.equals(persoon.naam);
    }

    @Override
    public int hashCode() {
        return 49 * this.naam.hashCode() * (this.ontvanger == null ? 0 : this.ontvanger.naam.hashCode());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(this.naam)
                .append(this.ontvanger == null ? "geen ontvanger" : this.ontvanger.naam)
                .toString();
    }
}
