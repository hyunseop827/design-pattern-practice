package ex1.after;

// 전략 패턴 사용
// Strategy
interface Payment {
    default void pay(int amount) {
        System.out.println("지원하지 않는 결제 방식입니다.");
    }
}

// Concrete Strategy
class NaverPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- 네이버페이 결제 시작 ---");
        System.out.println("네이버 로그인 세션 확인...");
        System.out.println("포인트 및 카드 복합 결제 시도...");
        System.out.println(amount + "원 결제 승인 요청");
        System.out.println("결제 완료!");
    }
}

class KakaoPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- 카카오페이 결제 시작 ---");
        System.out.println("카카오톡 앱 연동 중...");
        System.out.println(amount + "원 결제 승인 요청");
        System.out.println("결제 완료!");
    }
}

class TossPayment implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("--- 토스 결제 시작 ---");
        System.out.println("토스 간편결제 API 호출...");
        System.out.println(amount + "원 결제 승인 요청");
        System.out.println("결제 완료!");
    }
}

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