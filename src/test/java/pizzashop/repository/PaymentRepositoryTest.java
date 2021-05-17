package pizzashop.repository;

import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository repo = new PaymentRepository();
    @Test
    void add1() {
        //Arrange
        Payment payment = new Payment(1,PaymentType.Cash,200);

        int numberofPayments=repo.getAll().size();

        //Act
        repo.add(payment);

        //Assert
        assert(repo.getAll().size()==numberofPayments+1);


    }



    @Test
    void add4() {
        //Arrange
        Payment payment = new Payment(5,PaymentType.Card,-200);

        int numberofPayments=repo.getAll().size();

        //Act
        repo.add(payment);

        //Assert
        assert(repo.getAll().size()==numberofPayments);


    }
}