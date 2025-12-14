package ex1.after;

// Strategy - defines the interface for payment algorithms
interface Payment {
    default void pay(int amount) {
        System.out.println("Unsupported payment method.");
    }
}

// ConcreteStrategy - NaverPay payment
class NaverPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- NaverPay Payment Start ---");
        System.out.println("Checking Naver login session...");
        System.out.println("Attempting combined point and card payment...");
        System.out.println("Requesting payment approval for " + amount + " KRW");
        System.out.println("Payment complete!");
    }
}

// ConcreteStrategy - KakaoPay payment
class KakaoPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- KakaoPay Payment Start ---");
        System.out.println("Connecting to KakaoTalk app...");
        System.out.println("Requesting payment approval for " + amount + " KRW");
        System.out.println("Payment complete!");
    }
}

// ConcreteStrategy - Toss payment
class TossPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- Toss Payment Start ---");
        System.out.println("Calling Toss simple payment API...");
        System.out.println("Requesting payment approval for " + amount + " KRW");
        System.out.println("Payment complete!");
    }
}

// Context - maintains a reference to a Strategy object
class PaymentProcessor {
    private Payment payment;

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void pay(int amount) {
        payment.pay(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        Payment kakao = new KakaoPayment();
        Payment toss = new TossPayment();
        Payment naver = new NaverPayment();

        processor.setPayment(kakao);
        processor.pay(10000);
        processor.setPayment(toss);
        processor.pay(3000);
        processor.setPayment(naver);
        processor.pay(5000);
    }
}