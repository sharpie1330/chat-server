### 초간단 채팅서버

#### 설명
- 인증이나 로그인 없이 회원가입만 진행하면 사용할 수 있도록 구현함
- STOMP 프로토콜 기반 웹 소켓 채팅서버
- 아래 테스트 도구들 동작 확인을 위해서만 단순하게 구현

#### 테스트 방법
아래 두 가지 방법으로 테스트 진행
- [apic - 오픈소스 request 테스트 도구](https://apic.app/online/#/tester)
- [Postman으로 STOMP 테스트](https://sharpie1330.notion.site/Stomp-eb66b509d62f4101bd65e618c1534741?pvs=4)

포스트맨으로 테스트 시 주의사항 : STOMP 프로토콜 준수, base64 인코딩은 4의 배수 길이로 맞춰지므로 배수가 아닌 경우 원하는 대로 동작하지 않을 수 있음