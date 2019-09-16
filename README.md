# Phoncus
AndroidApp은 창업 최초로 서비스의 MVP를 구현하고자 만든 안드로이드 앱의 소스코드입니다.
IntroActivity – HomeActivity – StrartActivity – ChoiceActivity – ResultActivity 로구성되며
각각은세부 Fragment와 연결되어있습니다.

ionicWebApp은 스마트폰 큐레이션 서비스를 ionic을 활용하여 하이브리드 웹앱으로 이식한 버전의 핵심소스입니다.
ios와 웹 개발의 멀티 플랫폼에 서비스를 제공하고자 안드로이드 앱을 ionic으로 바꿔 작성하였습니다.
Angular2 기반의 ionic 프레임워크를 활용하여 TypeScript로 작성되었습니다.

MysqlCurationProcedure는 Mysql의 stored procedure 기능을 활용하여 제작한 초기 스마트폰 추천서비스의 알고리즘 소스입니다
Curation_main – curation_score – curation_score + 세부사항 순으로 호출되어 작동합니다.
유저가 중요시 여기는 스마트폰의 기능비중과 민감도에 따라 DB에 저장된 각 스마트폰에 점수가 매겨지고, 가장 가까운값의 스마트폰이 추천되는 알고리즘입니다.

SpringWebServer는 Spring을 활용하여 ajax로 Api를 구현했던 웹서버 코드입니다
JSON 방식으로 패킷 전송을 주고받고 클라이언트에서 유저의 입력 값을 서버와 DB에서 처리하여 재전송합니다.
DBConn에서 Mysql과 연결을 주고 받습니다.

WebScraping_Electron은 창업간 통신3사의 요금제가 필요하여 제작하였던 프로그램 코딩소스입니다.
자바스크립트 Electron 프레임워크에 Angular를 자체적으로 접목하여 개발하였으며, 회사의 DB에 자동으로 수십가지의 스마트폰 요금제와 각 폰의 공시지원금을 
삽입하는 프로그램입니다.
KT-scrap.componets.ts는 KT의 공시지원금을, Skt-scrap.componet.ts는 SKT의 공시지원금을, Lg-scrap.component.ts는 LG U+의 공시지원금을
파싱하는 코드이며 http.ts에서 서버로 전송합니다.
