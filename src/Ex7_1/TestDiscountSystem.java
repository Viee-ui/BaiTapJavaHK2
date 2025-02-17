package Ex7_1;

public class TestDiscountSystem {
    public static void main(String[] args) {

        Customer alice = new Customer("Alice");
        alice.setMember(true);
        alice.setMemberType("Premium");

        Customer bob = new Customer("Bob");
        bob.setMember(true);
        bob.setMemberType("Gold");

        Customer charlie = new Customer("Charlie");
        charlie.setMember(false); // Non-member


        Visit visitAlice = new Visit(alice);
        visitAlice.setServiceExpense(100); // $100 for services
        visitAlice.setProductExpense(50); // $50 for products
        System.out.println("Total expense for Alice: $" + visitAlice.getTotalExpense());

        Visit visitBob = new Visit(bob);
        visitBob.setServiceExpense(80); // $80 for services
        visitBob.setProductExpense(30); // $30 for products
        System.out.println("Total expense for Bob: $" + visitBob.getTotalExpense());

        Visit visitCharlie = new Visit(charlie);
        visitCharlie.setServiceExpense(60); // $60 for services
        visitCharlie.setProductExpense(20); // $20 for products
        System.out.println("Total expense for Charlie: $" + visitCharlie.getTotalExpense());
    }
}
