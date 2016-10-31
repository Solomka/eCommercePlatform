package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 10/26/2016.
 */
public final class ChargeID {

    @NotNull
    private final UUID id;

    /**
     * generate random GUID id
     */
    public ChargeID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargeID chargeID = (ChargeID) o;

        return id.equals(chargeID.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ChargeID{" +
                "id=" + id +
                '}';
    }
}
