package ukma.eCommerce.core.paymentModule.repository;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.util.repository.filter.IFilter;
import ukma.eCommerce.util.repository.filter.sql.And;
import ukma.eCommerce.util.repository.filter.sql.FilterUtils;
import ukma.eCommerce.util.repository.filter.sql.Or;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Максим on 11/6/2016.
 */
public class ChargeId implements IFilter<String> {

    static CriteriaBuilder builder = null;// obtain instance somehow
    static CriteriaQuery<Charge> criteria = builder.createQuery( Charge.class );
    static Root<Charge> root = criteria.from( Charge.class );

    private long id;

    interface SomeFilter extends IFilter<Predicate> {
        // type to return to client; in this case we can change
        // <Predicate> to something else and client won't be affected!!!
    }

    public ChargeId(long id) {
        this.id = id;
    }

    @Override
    public String toFilter() {
        return String.format("id=%d", id);
    }

    static class ChargeIdHibernate implements SomeFilter {

        private CriteriaBuilder builder;
        private Root<Charge> root;
        private int id;

        public ChargeIdHibernate(Root<Charge> root, CriteriaBuilder builder, int id) {
            this.builder = builder;
            this.root = root;
            this.id = id;
        }

        @Override
        public Predicate toFilter() {
            return builder.equal( root.get( "id" ), id );
        }
    }

    static class AndHibernate implements SomeFilter {

        private CriteriaBuilder builder;
        private Root<Charge> root;
        SomeFilter first;
        SomeFilter second;

        public AndHibernate(CriteriaBuilder builder, Root<Charge> root, SomeFilter first, SomeFilter second) {
            this.builder = builder;
            this.root = root;
            this.first = first;
            this.second = second;
        }

        @Override
        public Predicate toFilter() {
            return builder.and(first.toFilter(), second.toFilter());
        }
    }

    public static void main(String [] args) {

        And and = new And(new ChargeId(20), new ChargeBefore(DateTime.now()));
        Or or = new Or(new ChargeId(30), and);

        String sql1 = "select * from `some_table` where " + or.toFilter();
        System.out.println(sql1);
        // or using utils
        String sql2 = "select * from `some_table` where " +
                FilterUtils.or(new ChargeId(30), FilterUtils.and(new ChargeId(20), new ChargeBefore(DateTime.now()))).toFilter();

        System.out.println(sql2);
        // do no run code below, it is just example
        createAndFilter(createIdFilter(30), createIdFilter(40));
    }

    static SomeFilter createIdFilter(int id) {
        // example in conjunction with hibernate
        return new ChargeIdHibernate(root, builder, id);
    }

    static SomeFilter createAndFilter(SomeFilter first, SomeFilter second) {
        // example in conjunction with hibernate
        return new AndHibernate(builder, root, first, second);
    }

}
