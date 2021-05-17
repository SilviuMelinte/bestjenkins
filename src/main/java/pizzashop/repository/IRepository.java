package pizzashop.repository;

import pizzashop.model.Payment;

import java.util.List;

public interface IRepository {

     void setEmpty();
     List<Payment> getAll();
     void deleteCard();
     void setPayments();
     void add(Payment payment);
     void writeAll();
}
