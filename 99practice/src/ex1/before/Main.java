package ex1.before;

class PaymentProcessor {
    public void pay(String type, int amount) {
        if (type.equals("KAKAO_PAY")) {
            System.out.println("--- 카카오페이 결제 시작 ---");
            System.out.println("카카오톡 앱 연동 중...");
            System.out.println(amount + "원 결제 승인 요청");
            System.out.println("결제 완료!");
        } else if (type.equals("NAVER_PAY")) {
            System.out.println("--- 네이버페이 결제 시작 ---");
            System.out.println("네이버 로그인 세션 확인...");
            System.out.println("포인트 및 카드 복합 결제 시도...");
            System.out.println(amount + "원 결제 승인 요청");
            System.out.println("결제 완료!");
        } else if (type.equals("TOSS")) {
            System.out.println("--- 토스 결제 시작 ---");
            System.out.println("토스 간편결제 API 호출...");
            System.out.println(amount + "원 결제 승인 요청");
            System.out.println("결제 완료!");
        } else {
            System.out.println("지원하지 않는 결제 방식입니다.");
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