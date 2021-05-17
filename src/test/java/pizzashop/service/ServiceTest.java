package pizzashop.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.IMenuRepo;
import pizzashop.repository.IRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class ServiceTest {
    //Arrange

    private IMenuRepo menuRepo= mock(IMenuRepo.class);
    private IRepository payRepo= mock(IRepository.class);
    private Service service =new Service(menuRepo,payRepo);




    @Test

    void testEntity(){
        Payment testPayment= new Payment(1,PaymentType.Card,30);
        testPayment.setAmount(50);
        assert(testPayment.getAmount()==50);
    }
    @Test

    void testReadMenuData(){
        IMenuRepo menuRepo=new MenuRepository();
        IRepository payRepo= new PaymentRepository();
        Service service = new Service(menuRepo,payRepo);
        var menuData=service.getMenuData();
        assert(menuData.size()==9);

    }


    @Test
    void testGetMenuData(){

        List<MenuDataModel> menuList = new ArrayList<>();
        menuList.add(new MenuDataModel("pizza",2,3.0));
        menuList.add(new MenuDataModel("pizzaMare",2,10.0));
        menuList.add(new MenuDataModel("pizzaMoca",2,0.0));
        Mockito.when(menuRepo.getMenu()).thenReturn(menuList);
        assert(service.getMenuData()==menuList);

    }



    @Test
    void testSetPayment(){
        List<Payment> paymentList= new ArrayList<>();
        paymentList.add(new Payment(1,PaymentType.Card,20));
        paymentList.add(new Payment(1,PaymentType.Card,50));
        paymentList.add(new Payment(2,PaymentType.Card,80));
        Mockito.when(payRepo.getAll()).thenReturn(paymentList);
        var payments=service.getPayments();
        payments.get(0).setAmount(100);


    }
    @Test
    void testGetAll(){
        List<Payment> paymentList= new ArrayList<>();
        paymentList.add(new Payment(1,PaymentType.Card,20));
        paymentList.add(new Payment(1,PaymentType.Card,50));
        paymentList.add(new Payment(2,PaymentType.Card,80));
        Mockito.when(payRepo.getAll()).thenReturn(paymentList);
        var a=service.getPayments();
        assert(service.getPayments()==paymentList);

    }

    //valid
    @Test
    void testValidData(){
        //ARRANGE
        var paymentType = PaymentType.Card;
        //ACT
        var totalAmount = service.getTotalAmount(paymentType);
        //ASSERT
        //assert(totalAmount==243.25);

    }

    @Test
    void testInvalidData(){
        //ARRANGE
        var paymentType = PaymentType.Card;
        //ACT
        payRepo.setPayments();
        var totalAmount = service.getTotalAmount(paymentType);
        //ASSERT
        assert(totalAmount==0);
    }

    @Test
    void testEmptyListOfPayments(){
        //ARRANGE
        var paymentType = PaymentType.Card;
        //ACT
        payRepo.setEmpty();
        var totalAmount = service.getTotalAmount(paymentType);
        //ASSERT
        assert(totalAmount==0);
    }

    @Test

    void testPaymentTypeInexistent(){
        //ARRANGE
        var paymentType = PaymentType.Card;
        //ACT
        payRepo.deleteCard();
        var totalAmount = service.getTotalAmount(paymentType);
        //ASSERT
        assert(totalAmount==0);
    }

}