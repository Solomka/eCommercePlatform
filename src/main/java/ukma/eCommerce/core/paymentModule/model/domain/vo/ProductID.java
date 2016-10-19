package ukma.eCommerce.core.paymentModule.model.domain.vo;

import javax.validation.constraints.Min;

/**
 * Created by Максим on 10/19/2016.
 */
public final class ProductID {

    @Min(1)
    private final long id;

    public ProductID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductID productID = (ProductID) o;

        return id == productID.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ProductID{" +
                "id=" + id +
                '}';
    }
}
