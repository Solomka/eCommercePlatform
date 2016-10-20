package ukma.eCommerce.core.userModule.model.domain.vo;

import javax.validation.constraints.Min;

/**
 * Created by Максим on 10/19/2016.
 */
public final class CustomerID {

    @Min(1)
    private final long id;

    public CustomerID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerID that = (CustomerID) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "CustomerID{" +
                "id=" + id +
                '}';
    }
}
