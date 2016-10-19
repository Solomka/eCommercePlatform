package ukma.eCommerce.core.paymentModule.model.domain.bo;

import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductInfo;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * <p>
 * Value object that represents Product
 * </p>
 *
 * @author Solomka
 */

public final class Product {

    private final long id;
    private final SellerID seller;
    private ProductInfo productInfo;

    /**
     * builder that creates immutable instance of {@linkplain Product}
     *
     * @author Solomka
     */
    public static class Builder implements IBuilder<Product> {

        private long id;
        private ProductInfo productInfo;
        private SellerID seller;

        public Builder(@NotNull Product product) {

            Objects.requireNonNull(product, "product must not be null");

            setId(product.getId()).setProductInfo(product.getProductInfo()).
                    setSeller(product.getSeller());
        }

        public Builder() {
        }

        public long getId() {
            return id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public ProductInfo getProductInfo() {
            return productInfo;
        }

        public Builder setProductInfo(ProductInfo productInfo) {
            this.productInfo = productInfo;
            return this;
        }

        public SellerID getSeller() {
            return seller;
        }

        public Builder setSeller(SellerID seller) {
            this.seller = seller;
            return this;
        }

        @Override
        public Product build() {
            return new Product(this);
        }
    }

    private Product(@NotNull Builder builder) {

        Objects.requireNonNull(builder, "builder must no be null");

        if (builder.getId() < 1)
            throw new IllegalArgumentException("id < 1");

        this.id = builder.getId();
        this.productInfo = ValidationUtil.validate(builder.getProductInfo());
        this.seller = ValidationUtil.validate(builder.getSeller());
    }

    public long getId() {
        return id;
    }

    public SellerID getSeller() {
        return seller;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void updateInfo(ProductInfo productInfo) {
        this.productInfo = ValidationUtil.validate(productInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (!seller.equals(product.seller)) return false;
        return productInfo.equals(product.productInfo);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + seller.hashCode();
        result = 31 * result + productInfo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", seller=" + seller +
                ", productInfo=" + productInfo +
                '}';
    }
}
