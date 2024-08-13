<div align="center">
<br/>
<h1>SML: 노인 지원 커뮤니티 서비스</h1>

![logo2](https://github.com/user-attachments/assets/ced9f4b5-cdf1-40de-b20b-a0582fe8e445)

</div>

>우리나라는 평균 수명이 83세를 넘어서는 장수 사회로 접어들었고, 노인 인구가 전체 인구의 14% 를 넘어 전 세계에서 가장 빠르게 고령사회로 진입하고 있습니다. 더불어 독거 노인들은 사회적 연결이 부족하고 일상 생활에서의 어려움을 겪는 경우가 많아 독거 노인의 문제가 점점 심각해지고 있습니다. 이러한 문제를 해결하기 위해 사회적 연결망을 활용한 노인 지원 시스템을 제안합니다.

<br/>
<div align="center">
<p align=center>
  <a href="https://www.notion.so/ohgiraffers/SML-Spring-in-My-Life-ebe55b3bf01041f2b67151d1fbd0bce7">팀 노션</a>
  &nbsp; | &nbsp; 
    <a href="https://zinc-mistake-ddb.notion.site/d194358e314c4e3797d81a177a55d5a1">기획서</a>
  &nbsp; | &nbsp;
<a href="https://github.com/orgs/SML-SpringInMyLife/projects/3/views/1?layout=table&groupedBy%5BcolumnId%5D=Milestone">백로그</a>  <br />   
  <a href="https://www.figma.com/design/bImVvvY8jcm9rkCwx3d3PI/SML?node-id=0-1&t=N23ODbW6Pr7160dP-1">figma</a> 
 &nbsp; | &nbsp;
  <a href="https://www.notion.so/ohgiraffers/TroubleShooting-b98fc6019508484f990ba1a5f1232fb1">트러블 슈팅</a>

</p>

배포 링크 <br />
**https://**

</div>

# 📑 목차


### [1. 프로젝트 소개](#-프로젝트-소개)

### [2. 주요 기능 설명](#-주요-기능-설명)

### [3. 기술 스택](#-기술-스택)

### [4. 시스템 아키텍처](#-시스템-아키텍처)

### [5. 기술적 도전](#-기술적-도전)

### [6. 팀원 소개](#-팀원-소개)
- [김노영(커뮤니티)](#-커뮤니티-및-수강신청-기능)
- [배은아(관리자)](#-관리자-기능)
- [손찬미(공지사항)](#-공지사항-기능)
- [이나래(위치찾기)](#-위치-검색-기능)
- [최혜진(로그인)](#-로그인-및-마이페이지-기능)

### [7. 팀원 회고](#-팀원-회고)

<br />

# 🌟 프로젝트 소개

## **SML**을 만들게 된 계기

저희 팀은 **사회적 연결망을 통해 독거 노인을 지원하는 서비스**를 만들고 싶었습니다. 

대한민국은 고령사회에 진입하면서 독거 노인의 문제가 점점 심각해지고 있습니다. 독거 노인들은 사회적 연결이 부족하여 일상생활에서 많은 어려움을 겪고 있으며, 이러한 문제를 해결할 수 있는 시스템의 필요성이 대두되었습니다.

그래서 **노인 사용자에게 친숙하고 직관적인 사용자 경험을 제공하는 서비스**, **SML: Spring in My Life**를 만들게 되었습니다. 이 프로젝트는 독거 노인들이 사회와 연결되고, 다양한 생활 정보 및 교육 지원을 받을 수 있도록 도와주는 것을 목표로 하고 있습니다.

<br />



# 🛠️ 주요 기능 설명

wiki에서 더 많은 기능을 살펴볼 수 있습니다.

[🔗 SML 프로젝트 소개 바로가기](https://github.com/SML-SpringInMyLife/SML/wiki)

<br />

## 🔐 로그인 및 마이페이지 기능

- **BCrypt를 통한 비밀번호 암호화 및 세션 관리**: 사용자가 입력한 비밀번호를 BCrypt로 암호화하여 저장, 로그인 시 암호화된 비밀번호와 비교하여 검증합니다. 로그인 성공 시 세션을 생성하여 로그인 상태를 유지하고, 비밀번호는 세션에 저장되지 않아 사용자의 민감한 정보가 보호됩니다.

- **이메일 인증 및 회원정보 수정**: 회원가입 시, 이메일로 인증번호를 전송하여 사용자가 올바른 이메일을 입력했는지 확인합니다. 사용자는 마이페이지에서 자신의 회원 정보를 수정할 수 있습니다.

- **포인트 관리 및 출석체크 기능**: 사용자는 특정 기간 동안 적립된 포인트를 조회하고, 출석체크를 통해 매일 출석 기록을 갱신할 수 있습니다. 출석 기록은 데이터베이스에 저장되며 이후 사용자 확인 및 분석에 활용됩니다.

<br/>


## 📢 공지사항 기능

- **공지사항 목록 조회**: 사용자는 공지사항 목록 페이지로 이동하여 등록된 모든 공지사항을 확인할 수 있습니다. 목록이 비어 있는 경우, '공지사항이 없습니다'라는 메시지가 표시됩니다.

- **공지사항 상세 조회 및 수정**: 사용자는 특정 공지사항을 선택하여 상세 정보를 확인할 수 있으며, 해당 공지사항을 수정할 수 있습니다. 수정된 내용은 데이터베이스에 반영되며, 이후 공지사항 목록으로 리다이렉트됩니다.

- **공지사항 등록**: 사용자는 새로운 공지사항을 등록할 수 있습니다. 등록된 공지사항은 공지사항 목록에 추가되며, 사용자에게 성공 메시지가 표시됩니다.

<br/>

## 📚 커뮤니티 및 수강신청 기능

- **게시판 기능과 게시글 상호작용**: 사용자는 커뮤니티 게시판에서 게시글을 작성, 수정, 삭제할 수 있습니다. 작성된 게시글은 목록에 추가되고 데이터베이스에 반영됩니다. 각 게시글에는 조회수와 좋아요 기능이 포함되어 있어 사용자가 편리하게 자신의 의견을 표현할 수 있습니다.

- **댓글 기능**: 게시글에는 댓글을 작성할 수 있고 이를 통해 사용자 간의 소통이 이루어집니다. 작성된 댓글은 게시글 아래에 표시되며, 댓글 작성자는 댓글을 수정하거나 삭제할 수 있습니다.

- **수강신청 및 강사 관리**: 사용자는 수강신청 목록을 확인하고 새로운 수업을 등록할 수 있습니다. 관리자는 강사를 등록하고 관리할 수 있습니다. 강사의 정보와 수업 등록 정보는 수정 및 삭제할 수 있으며, 관리자는 강사의 상세 정보를 조회하고 수정할 수 있습니다.

<br/>


## 🔧 관리자 기능

- **회원 관리 및 통계**: 관리자 페이지에서 모든 회원 목록 조회 및 검색을 할 수 있습니다. 특정 회원을 찾아 회원의 상태를 업데이트하거나 관리 권환을 부여/철회할 수 있습니다. 연령대별 회원 분포를 월별로 조회할 수 있는 통계를 관리자 메인 페이지에 차트로 시각화하여 제공합니다.

- **SMS 발송 및 관리**: 관리자는 특정 회원에게 SMS를 발송하고, 발송된 문자 내용과 관련 설정을 API를 통해 처리합니다. 발송 결과를 실시간으로 확인할 수 있습니다.

- **실시간 채팅 관리 및 기록**: WebSocket을 사용하여 사용자와 실시간으로 채팅을 제공하며, 채팅 메시지는 JSON 형식으로 처리됩니다. 채팅 세션 종료 시 채팅 내용이 데이터베이스에 저장되어 추후 대화 기록을 조회할 수 있습니다.


<br/>



## 🗺️ 위치 검색 기능

- **지도 검색 및 카테고리별 필터링**: 사용자는 Kakao Maps API를 활용하여 현재 위치를 기준으로 병원, 공공기관, 약국, 지하철 등 다양한 카테고리의 장소를 검색할 수 있습니다. 검색된 장소는 지도에 마커로 표시되며 검색 결과는 리스트 형태로 보여집니다.

- **검색 키워드 저장 및 최근 검색어 표시**: 사용자가 입력한 검색 키워드가 데이터베이스에 저장됩니다. 검색창을 누르면 데이터베이스에 저장된 검색어가 아래에 표시됩니다. 사용자가 자주 찾는 장소를 빠르게 검색할 수 있도록 도와줍니다.

- **지도 사용법 안내**: 사용자는 '지도 사용법' 버튼을 통해 안내 페이지로 이동하여 지도 기능의 사용 방법을 자세히 알아볼 수 있습니다. 이 페이지는 사용자가 지도 기능을 보다 효과적으로 활용할 수 있도록 돕습니다.


<br />


## ⚙️ 기술 스택

<table>
    <thead>
        <tr>
            <th>분류</th>
            <th>기술 스택</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <p>공통</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/spring-6DB33F?logo=spring&logoColor=white">
                <img src="https://img.shields.io/badge/eclipse-2C2255?style=flat-square&logo=eclipse&logoColor=white">
                <img src="https://img.shields.io/badge/maven-C71A36?logo=apache-maven&logoColor=white">
                <img src="https://img.shields.io/badge/mybatis-DC382D?logo=mybatis&logoColor=white"/>
            </td>
        </tr>
        <tr>
            <td>
                <p>프론트엔드</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/html5-E34F26?logo=html5&logoColor=white">
                <img src="https://img.shields.io/badge/javascript-F7DF1E?logo=javascript&logoColor=black">
            </td>
        </tr>
        <tr>
            <td>
                <p>백엔드</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">
                <img src="https://img.shields.io/badge/oracle-F80000?logo=oracle&logoColor=white">
                <img src="https://img.shields.io/badge/apache tomcat-F8DC75?logo=apache-tomcat&logoColor=black">
                <img src="https://img.shields.io/badge/HikariCP-007396?logo=HikariCP&logoColor=white">
                <img src="https://img.shields.io/badge/BCrypt-003B57?logo=spring-security&logoColor=white">
                <img src="https://img.shields.io/badge/Spring Security-6DB33F?logo=Spring-Security&logoColor=white">
                <img src="https://img.shields.io/badge/Jackson-00ADD8?logo=jackson&logoColor=white">
                <img src="https://img.shields.io/badge/JavaMail-003B57?logo=spring&logoColor=white">
                <img src="https://img.shields.io/badge/WebSocket-010101?logo=java&logoColor=white">
            </td>
        </tr>
        <tr>
            <td>
                <p>생산성</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/Lombok-2C8EBB?logo=Lombok&logoColor=ffffff">
            </td>
        </tr>
        <tr>
            <td>
                <p>배포</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/Docker-2496ED?&logo=Docker&logoColor=white">
                <img src="https://img.shields.io/badge/GitHub Actions-2088FF?logo=github-actions&logoColor=white">
            </td>
        </tr>
        <tr>
            <td>
                <p>협업</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/Notion-000000?logo=Notion&logoColor=white">
                <img src="https://img.shields.io/badge/Figma-F24E1E?logo=Figma&logoColor=ffffff">
                <img src="https://img.shields.io/badge/github-181717?logo=github&logoColor=ffffff">
            </td>
        </tr>
        <tr>
            <td>
                <p>테스트</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/JUnit-25A162?logo=JUnit5&logoColor=white">
            </td>
        </tr>
    </tbody>
</table>



<br />

## 🏛️ 시스템 아키텍처

<이미지>

```
📦SML
 ┣ 📂src/main/java
 ┃ ┣ 📂com.sml.controller
 ┃ ┣ 📂com.sml.interceptor
 ┃ ┣ 📂com.sml.mapper
 ┃ ┣ 📂com.sml.model
 ┃ ┗ 📂com.sml.service
 ┣ 📂src/main/resources
 ┃ ┣ 📂com.sml.mapper
 ┃ ┣ 📜apikey.properties
 ┃ ┗ 📜log4jdbc.log4j2.
 ┣ 📂src/test/java
 ┃ ┣ 📂com.sml.mapper
 ┃ ┣ 📂com.sml.persistence
 ┃ ┗ 📂com.sml.service
 WEB
 ┣ 📂src/main/webapp
 ┃ ┣ 📂resources
 ┃ ┃ ┣ 📂css
 ┃ ┃ ┣ 📂images
 ┃ ┃ ┣ 📂js
 ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┗ 📜common.js
 ┃ ┣ 📂WEB-INF
 ┃ ┃ ┣ 📂spring
 ┃ ┃ ┃ ┣ 📂appServlet
 ┃ ┃ ┃ ┃ ┣ 📜security-context.xml
 ┃ ┃ ┃ ┃ ┣ 📜servlet-context.xml 
 ┃ ┃ ┃ ┗ 📜root-context.xml
 ┃ ┃ ┣ 📂views
 ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┣ 📂community
 ┃ ┃ ┃ ┣ 📂course
 ┃ ┃ ┃ ┣ 📂donation
 ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┣ 📂location
 ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┣ 📂notice
 ┃ ┃ ┃ ┣ 📂safty
 ┃ ┃ ┃ ┗ 📜home.jsp
 ┃ ┃ ┗ 📜web.xml
 ┗ 📜pom.xml
```
<br />

<br />

## 🔎 기술적 도전

### JUnit을 이용한 코드 테스트

- **서비스 레이어 테스트**: JUnit을 사용하여 서비스 계층의 비즈니스 로직을 단위별로 테스트하였고, 각 메서드의 예상 동작을 검증하여 오류를 보다 쉽게 찾고 해결할 수 있었습니다.
  
### 데이터베이스 최적화

- **HikariCP를 사용한 커넥션 풀링 최적화**: HikariCP를 도입하여 데이터베이스 커넥션 풀링을 최적화했습니다. HikariCP를 통해 데이터베이스 연결의 성능을 향상시키고, 애플리케이션의 전체적인 응답 속도를 개선하였습니다.

### 보안 강화

- **BCrypt를 사용한 비밀번호 암호화**: 사용자의 비밀번호를 안전하게 보호하기 위해 BCrypt를 사용하여 암호화를 구현했습니다. 비밀번호 해싱을 통해 데이터베이스 내의 비밀번호를 안전하게 저장 및 관리할 수 있습니다.

### 메일 기능 구현

- **JavaMailSender를 사용한 이메일 전송**: JavaMailSender를 사용하여 회원가입시 사용자의 이메일로 인증 코드를 보내는 기능을 구현했습니다. 이를 통해 사용자의 메일이 유효한지 검증할 수 있었습니다.

### 실시간 기능 구현

- **WebSocket을 사용한 실시간 통신**: WebSocket을 사용하여 클라이언트와 서버 간의 실시간 양방향 통신을 구현했습니다. 사용자간 실시간 채팅 서비스를 제공합니다.

### API 사용

- **RESTful API 설계 및 구현**: Kakao Maps API를 통해 사용자의 현재 위치 기반 장소 검색 기능을 구현하였으며, 검색 결과를 JSON 형식으로 반환하여 지도에 표시합니다. 이 API는 클라이언트 애플리케이션과 서버 간의 위치 데이터 교환을 처리하며, 사용자에게 편리한 위치 기반 서비스를 제공합니다.

- **API 문서화**: coolSMS 시크릿키를 사용하여 SMS 기능 API를 문서화하였습니다. 이를 통해 팀원들과의 협업을 용이하게 하고, API의 기능을 명확하게 전달할 수 있으며, API Key 보안을 강화하였습니다.

### 데이터 시각화

- **JavaScript와 Chart.js를 이용한 데이터 시각화**: 백엔드에서 전달된 JSON 데이터를 JavaScript와 Chart.js를 사용하여 웹 페이지에 시각화하였습니다. 월별 회원 통계를 관리자가 편리하게 확인할 수 있습니다.


<br />


## 🧡 팀원 소개

|                                     김노영                                     |                                     배은아                                      |                                    손찬미                                    |                                    이나래                                    |                                    최혜진                                    |
| :----------------------------------------------------------------------------: | :-----------------------------------------------------------------------------: | :--------------------------------------------------------------------------: | :--------------------------------------------------------------------------: | :--------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/5ace22c1-9ae7-40cd-b436-8fd40d57a589" width="120" /> | <img src="https://github.com/user-attachments/assets/881d6702-c1bd-4dc2-bbfe-0e65c33a639b" width="120" /> | <img src="https://github.com/user-attachments/assets/7e7a7679-436c-452c-b7dc-53bd3e28cd03" width="120"> | <img src="https://github.com/user-attachments/assets/2ec9715e-5b20-4c21-b06f-97e3a553aff0" width="120"> | <img src="https://github.com/user-attachments/assets/654638f5-66f0-4420-9e30-25b50ccae4ef" width="120"> |
|                                     **BE**                                     |                                     **BE**                                      |                                    **BE**                                    |                                    **BE**                                    |                                    **BE**                                     |
|                    [@kosm0ssy](https://github.com/kosm0ssy)                    |                    [@eunahpae](https://github.com/eunahpae)                     |                   [@sonchanmi](https://github.com/sonchanmi)                   |                [@infinite130](https://github.com/infinite130)                |                [@choihyejin11](https://github.com/choihyejin11)                |

<br />

## ✍🏻 팀원 회고

팀원 회고 보러가기 👉  [SML 위키 페이지](https://github.com/SML-SpringInMyLife/SML/wiki)
