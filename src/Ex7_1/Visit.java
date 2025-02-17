package Ex7_1;

public class Visit {
    private Customer customer;
    private double serviceExpense;
    private double productExpense;

    public Visit(Customer customer) {
        this.customer = customer;
        this.serviceExpense = 0;
        this.productExpense = 0;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public void setServiceExpense(double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public double getServiceExpense() {
        return serviceExpense;
    }

    public void setProductExpense(double productExpense) {
        this.productExpense = productExpense;
    }

    public double getProductExpense() {
        return productExpense;
    }

    public double getTotalExpense() {
        double serviceDiscount = DiscountRate.getServiceDiscountRate(customer.getMemberType());
        double productDiscount = DiscountRate.getProductDiscountRate();

        double totalServiceCost = serviceExpense * (1 - serviceDiscount);
        double totalProductCost = productExpense * (1 - productDiscount);

        return totalServiceCost + totalProductCost;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "customer=" + customer +
                ", serviceExpense=" + serviceExpense +
                ", productExpense=" + productExpense +
                '}';
    }
}

