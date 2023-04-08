# PROJECT IT-Hermes Batch

:paperclip: [서비스 링크](https://it-hermes.store)  

:paperclip: [IT-Hermes 크롤링서버 레포](https://github.com/f-lab-edu/IT-Hermes-Crawling)  
:paperclip: [IT-Hermes 애플리케이션서버 레포](https://github.com/f-lab-edu/IT-Hermes-Server)  
:paperclip: [IT-Hermes 프론트 레포](https://github.com/f-lab-edu/IT-Hermes-Front)    
<br>

## :thought_balloon: IT 관련 컨텐츠 알림 서비스 - 배치 서버

```프로젝트 기간: 2023.01.04 ~```

> IT에 관심이 많은 유저에게 채용, 유투브, 뉴스 정보 등의 각종 IT 관련 컨텐츠를   
> 유저 맞춤형으로 자동 알림해주는 서비스

- <em><strong>10분 간격</strong></em>으로 크롤링 서버를 호출해서 최신 IT 관련 컨텐츠를 수집   
- <em><strong>20분 간격</strong></em>으로 애플리케이션 서버를 호출해서 유저 구독 정보 알림 제공  
- <em><strong>10분 간격</strong></em>으로 애플리케이션 서버를 호출해서 ElasticSearch와 RDB 동기화 진행
- 객체지향 원리와 디자인 패턴을 적용하여 코드 작성  
- 깃허브 액션으로 CI, 젠킨스로 CD 구축  
- RabbitMQ를 이용해서 최신 크롤링 컨텐츠 DB Insert 장애 대응  

<br>

## :page_facing_up: 기술 스택  

`spring boot 3.0`, `Java 17`  
`Mysql 8.0`,`h2`,`Spring Data JPA`  
`Jenkins`, `Github action`     
`Feign Client`  
`RabbitMQ`

## :thought_balloon: 전체 구조    
![68A30EE6-7C85-4FD7-B51C-AFB90683363D_1_105_c](https://user-images.githubusercontent.com/70764912/230696042-70781f1d-6f8f-46d4-9e45-86ad4fa57cb5.jpeg)





