package ex1.before;

class PaymentProcessor {
    public void pay(String type, int amount) {
        if (type.equals("KAKAO_PAY")) {
            System.out.println("--- KakaoPay Payment Start ---");
            System.out.println("Connecting to KakaoTalk app...");
            System.out.println("Requesting payment approval for " + amount + " KRW");
            System.out.println("Payment complete!");
        } else if (type.equals("NAVER_PAY")) {
            System.out.println("--- NaverPay Payment Start ---");
            System.out.println("Checking Naver login session...");
            System.out.println("Attempting combined point and card payment...");
            System.out.println("Requesting payment approval for " + amount + " KRW");
            System.out.println("Payment complete!");
        } else if (type.equals("TOSS")) {
            System.out.println("--- Toss Payment Start ---");
            System.out.println("Calling Toss simple payment API...");
            System.out.println("Requesting payment approval for " + amount + " KRW");
            System.out.println("Payment complete!");
        } else {
            System.out.println("Unsupported payment method.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.pay("KAKAO_PAY", 10000);
        processor.pay("NAVER_PAY", 5000);
        processor.pay("TOSS", 3000);
    }
}
