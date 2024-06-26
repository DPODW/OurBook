# 📖 도서 판매 웹 쇼핑몰 OurBook 📖

![OurBook111](https://github.com/DPODW/OurBook/assets/110981825/4bed72c0-7351-4da1-93cf-c7285d82a1bc)


<br>

## 프로젝트 소개

- OurBook은 각종 도서를 판매하는 웹 쇼핑몰 입니다. 
- OurBook 에서 판매하는 책 뿐만 아니라, 개인이 책을 판매하는 것도 가능합니다.
- 쇼핑몰이 갖추어야할 기본적이면서도 핵심적인 기능들을 구현하였습니다.


<br>

## 개발 인원 🧑

<div align="center">

개인 프로젝트
| **문태진** | 
| :------: | 
| [<img src="https://avatars.githubusercontent.com/u/110981825?s=400&u=90f6ff0a633494f94916237dc912e9eedb225e34&v=4" height=150 width=150> <br/> @TaeJin Moon](https://github.com/DPODW) |

</div>

<br>

## 1. 개발 정보 🖥️

### 개발 환경
- Language : JAVA 
- Front-End : HTML/CSS , ThymeLeaf , AJAX , jQuery , JavaScript , BootStrap
- Back-End : SpringBoot , MyBatis
- DB : Maria DB
- IDE : Intelli J 
<br>


## 2. 프로젝트 구조 🔨
- 디렉토리 수준으로 구조를 설정하였습니다.
```
├─build
│  ├─classes
│  │  └─java
│  │      ├─main
│  │      │  └─com
│  │      │      └─ourbook
│  │      │          └─shop
│  │      │              ├─check
│  │      │              ├─config
│  │      │              │  ├─auth
│  │      │              │  ├─aws
│  │      │              │  │  └─S3FileUpload
│  │      │              │  ├─exception
│  │      │              │  └─security
│  │      │              ├─controller
│  │      │              │  ├─inquiryController
│  │      │              │  ├─marketController
│  │      │              │  ├─memberController
│  │      │              │  ├─paymentController
│  │      │              │  └─shopController
│  │      │              ├─dto
│  │      │              │  ├─book
│  │      │              │  ├─inquiry
│  │      │              │  ├─library
│  │      │              │  ├─market
│  │      │              │  ├─member
│  │      │              │  └─payment
│  │      │              ├─mapper
│  │      │              │  ├─inquiryMapper
│  │      │              │  ├─marketMapper
│  │      │              │  ├─memberMapper
│  │      │              │  ├─paymentMapper
│  │      │              │  └─shopMapper
│  │      │              └─service
│  │      │                  ├─additionService
│  │      │                  │  ├─emailService
│  │      │                  │  └─fileUploadService
│  │      │                  ├─inquiryService
│  │      │                  │  └─impl
│  │      │                  ├─marketService
│  │      │                  │  └─impl
│  │      │                  ├─memberService
│  │      │                  │  └─impl
│  │      │                  ├─paymentService
│  │      │                  │  └─impl
│  │      │                  └─shopService
│  │      │                      └─impl


├─resources
│  │  └─main
│  │      ├─com
│  │      │  └─ourbook
│  │      │      └─shop
│  │      │          └─mapper
│  │      │              ├─inquiryMapper
│  │      │              ├─marketMapper
│  │      │              ├─memberMapper
│  │      │              ├─paymentMapper
│  │      │              └─shopMapper
│  │      ├─static
│  │      │  ├─css
│  │      │  ├─img
│  │      │  │  ├─home
│  │      │  │  ├─inquiry
│  │      │  │  ├─market
│  │      │  │  ├─member
│  │      │  │  ├─ourBookShopList
│  │      │  │  └─payment
│  │      │  └─js
│  │      │      ├─accessCheck
│  │      │      ├─bookQuantity
│  │      │      ├─libraryLocation
│  │      │      └─payment
│  │      └─templates
│  │          ├─books
│  │          ├─error
│  │          ├─inquiry
│  │          ├─mail
│  │          ├─main
│  │          ├─market
│  │          ├─member
│  │          └─payment
```

<br>

## 3. 기능 설명 📄
- 기능 설명 전 추가 정보
  - 정보를 입력해야 하는 기능에는 모두 Java Bean Validation 검증이 기본적으로 적용되어있습니다.
  - 검증 실패시 출력되는 에러 텍스트는 error.properties 에 정의되어있습니다.
  - application.properties 는 보안상 문제로 공개되어있지 않습니다.

  <br>
## 회원 관리 🧑‍🔧
☆ **로그인 , 로그아웃 , 가입 , 수정 , 탈퇴 기능**

☆ **SNS 네이버 로그인 기능과 일반 로그인 기능**

☆ **ID , Email 중복 검증**

☆ **비회원 , 구매자 , 판매자 , 관리자 가입목적(권한) 에 따른 기능 이용 차별점 존재**
<br></br>

### [회원가입]
- 가입 정보는 DB 에 저장되며, 비밀번호는 PasswordEncoder 를 사용하여서 암호화 합니다.

- 아이디와 이메일은 중복을 허용하지 않습니다.
  - 중복 검사 메소드를 구현 후, AJAX 를 이용하여서 중복 검증 기능을(비동기) 구현하였습니다.
<br></br>

### [로그인]
- 로그인은 OAuth2 를 이용한 SNS 네이버 로그인과, Spring Security 를 이용한 일반 로그인이 있습니다.
  
- 네이버 로그인 정보는 세션에 담아서 사용하며, Spring Security 로그인 정보는 UserDetail 객체에 담아서 사용합니다.
  
- 네이버 로그인 회원은 별도의 회원가입 없이, 네이버 아이디로 OurBook 을 이용할 수 있습니다.
<br></br>

### [회원수정 및 탈퇴]
- 회원수정 기능은 네이버 로그인 회원은 불가능 하며, 일반 로그인 회원만이 수정 가능 합니다.
   - 네이버 회원 판별 메소드를 구현하여 검증을 하였습니다.
- 수정 가능한 정보는 [이름] , [비밀번호] , [이메일] 이며 아이디와 가입 목적은 수정 불가능 합니다.
- 탈퇴 시, 자동으로 로그아웃 되며 DB 에서 회원정보가 즉각 삭제됩니다.


<br></br>
### 📷[동작 영상 및 사진]📷
**1. 일반 회원가입 영상입니다.**
- 회원가입 -> 로그인 -> 회원 정보 확인 -> 회원 수정 -> 회원 탈퇴 순으로 진행됩니다.


https://github.com/DPODW/OurBook/assets/110981825/67ec02b1-e013-459c-a5a2-2d71c21226af


<br></br>
<br></br>
**2.네이버 회원 동작 영상입니다.**
- 로그인(네이버 회원 별도의 회원가입 X) -> 회원 정보 확인 -> 회원 수정 -> 회원 탈퇴 순으로 진행됩니다.


https://github.com/DPODW/OurBook/assets/110981825/0bdd6452-43cb-4c0b-bd43-851f7f4d85cb


<br></br>
<br></br>
**3.회원 정보 저장 형식**
- 비밀번호는 아래의 사진과 같이 암호화 되어서 저장됩니다.
- 네이버 로그인 회원은, 아이디와 비밀번호가 NaverMember 로 저장됩니다.
![회원 저장 형식](https://github.com/DPODW/OurBook/assets/110981825/2273d3c5-5f68-4c32-807b-34caebd696a4)



## 상품 (도서) 📚
☆ **장바구니 기능**

☆ **배송정보 기능**

(비회원은 상품 열람만 가능합니다. 장바구니 사용 불가)
<br></br>

### [장바구니]
- 수량 선택이 가능하며, 선택한 수량 만큼 장바구니에 담깁니다.
  
- JS 를 이용하여 담겨진 상품의 수량 가격 합계를 즉각 계산합니다.
  
- 장바구니에 있는 상품이 결제가 되면, 장바구니에서 삭제됩니다.
  <br></br>

### [배송 정보]
- 유지중인 세션 혹은 UserDetail 에서 주문자 이름을 가져옵니다.
  
- JS 를 이용하여서 배송정보 입력 값을 검증합니다.
  
- Daum 우편번호 API 를 이용하여서 정확한 배송 정보를 얻어옵니다.


<br></br>
### 📷[동작 영상]📷
**장바구니 기능 및 구매 정보 기능 동작 영상입니다.**
- 장바구니에 상품 담기 -> 구매 정보 입력 -> 결제 초기 화면 순으로 진행됩니다.
- 결제 진행 영상은 아래의 **결제** 부분에 업로드되어 있습니다.


https://github.com/DPODW/OurBook/assets/110981825/ba8a789c-f44b-44b1-bf6c-f86e12bc4e8d


<br></br>
<br></br>
## 결제 💸
☆ **금융사 API를 활용한 결제 기능**

☆ **구매 확정 메일 기능**

☆ **구매 내역 기능**

(비회원 이용 불가)

### [금융사 API를 활용한 결제 기능]
- Toss API 와 IamPort(KG 이니시스) API 를 이용하여서 결제를 구현하였습니다.
  - Toss API 는 토스페이를 기준으로, IamPort API 는 농협(NH페이) 를 기준으로 하였습니다.
  - 테스트 환경에서 결제를 구현하였으며, Toss API는 실제 결제가 이루어지지 않으며
  - IamPort api 또한 결제 당일에 결제 금액이 모두 환불 됩니다.

- API 에서 제공하는 주문번호와 별개로, OurBook 고유의 주문번호를 추가로 만들어서 결제 정보를 관리하였습니다.
  
- 결제 도중 에러가 발생하였는데 출금이 이루어졌다면 -> 즉각 환불이 되도록 구현하였습니다.
  
- 회원의 선택에 의한 환불 또한 가능합니다.
<br></br>

### [구매 확정 메일 기능]
- JavaMailSender 를 활용하여서 구매를 한 회원 이메일로 구매 확정 메일을 전송합니다.
  
- Spring boot 에서 제공하는 springtemplateEngine 을 이용하여서 메일 양식을 HTML 로 설정하였습니다.
<br></br>

### [구매 내역 기능]
- 결제 완료가 된 건에 대해서 구매 내역 조회가 가능합니다.
  
- 구매 내역에 있는 상품은 구매자 선택에 의해서 삭제할 수 없으며, 환불 시 삭제 됩니다.
<br></br>

### 📷[동작 영상 및 사진]📷
**1. Toss API 를 이용한 결제**
- Toss API 에서 토스페이를 이용한 결제입니다.
- 결제시 전화번호와 생년월일이 필요하며, 개인정보 보호 차원에서 편집으로 가려두었습니다.
- 결제 완료시 결제 성공 화면과, 결제 성공 메일이 발송됩니다.


https://github.com/DPODW/OurBook/assets/110981825/03cabc02-d123-4904-a825-b134b25c372d


<br></br>
- 결제가 최종적으로 이루어지면, Toss 어플 에서 성공 알람을 보냅니다.
<br></br>
![토스 결제 성공 ](https://github.com/DPODW/OurBook/assets/110981825/80206f50-4071-4acd-997b-04fdbfc59815)
<hr>
<br></br>
<br></br>

**2. IamPort API 를 이용한 결제**
- IamPort API(KG이니시스) 에서 NH페이를 이용한 결제입니다.
- 결제시 실제 농협 카드 정보가 필요하며, 개인정보 보호 차원에서 편집하였습니다.
- 결제 성공 이후의 동작은 Toss API 와 동일합니다.


https://github.com/DPODW/OurBook/assets/110981825/b9766144-0099-4626-a105-a71e1a17e6bc


<br></br>
- 결제가 최종적으로 이루어지면, 농협 어플에서 출금 알람을 보냅니다.
<br></br>
![NH 결제 성공](https://github.com/DPODW/OurBook/assets/110981825/e02d7a0a-b0ee-4f9f-ade9-cbfabec9eb3f)
<hr>

<br></br>
<br></br>
**3. 결제 확정 메일**
- 결제에 성공하면, 가입시 기입한 이메일 주소로 결제 확정 메일을 전송합니다.
<br></br>
![결제 확정 메일](https://github.com/DPODW/OurBook/assets/110981825/278cdb48-a5e0-4ec9-9a65-f0e631e53df4)
<hr>


**4. Toss API 를 이용한 환불**
- 즉각 환불이 이루어집니다.


https://github.com/DPODW/OurBook/assets/110981825/0421bdb4-21d7-42af-8f00-f8cfaff69017


<br></br>
- 환불에 성공하면, 토스 어플에서 결제 취소 알람을 보냅니다.
<br></br>
![토스 환불 성공](https://github.com/DPODW/OurBook/assets/110981825/0b35e645-283e-4308-8088-8020797d9251)
<hr>


**5. IamPort API 를 이용한 환불**
- 즉각 환불이 이루어집니다.


https://github.com/DPODW/OurBook/assets/110981825/bd3526c8-15a3-43bb-8884-0df0a883d1b9


<br></br>
- 환불에 성공하면, 농협 어플에서 결제 정정 알람을 보냅니다.
<br></br>
![NH 환불 성공](https://github.com/DPODW/OurBook/assets/110981825/3c90a334-15b7-452f-8191-764b9061f9bd)
<hr>

**5. 구매 내역**
- 결제 완료된 상품들은 구매내역에서 확인할 수 있습니다.
<br></br>
![구매 내역](https://github.com/DPODW/OurBook/assets/110981825/684a9a5d-632c-4335-a2cd-87fa1c980610)




<br></br>
<br></br>
## 도서시장 🛒
- 도서시장 이란 개인이 도서를 사고 팔수 있는 공간을 의미합니다.
- [판매자] 권한을 가진 회원만이 도서를 판매할수 있습니다.

☆ **판매글에 등록할 이미지 AWS S3 를 이용하여 관리**

☆ **구매 요청 기능**

☆ **살래요 기능**

☆ **구매 요청 내역 기능**

(비회원 판매글 조회만 가능. 나머지 기능 이용 불가)
<br></br>

### [AWS S3 를 이용한 이미지 관리]
- 수 많은 판매자가 올리는 이미지를 로컬 환경에 저장할 수 없기 때문에 AWS S3 에 저장합니다.
  
- 중복 이름을 피하기 위하여 업로드 파일명에 UUID 를 추가하였습니다.
  
- 삭제 기능이 구현되어있습니다.
<br></br>

### [구매 요청 기능]
- 구매자가 판매자가 올린 판매글을 보고, 해당 상품에 구매 요청을 하는 것을 의미합니다.
  
- 구매 요청 시 -> 판매자 이메일로 구매자의 이메일 주소와 추가 정보가 적혀진 메일이 발송 됩니다.
  
- 구매 요청 내역을 확인할 수 있습니다.
<br></br>

### [살래요 기능]
- 해당 판매 도서가 몇명의 회원에게 구매 요청을 받았는지 알수있는 기능입니다.
  
- 특정 도서에 구매 요청이 이루어지면 살래요가 늘어납니다.
<br></br>


### 📷[동작 영상 및 사진]📷

**1. 도서시장 판매글 업로드**
- 판매글을 업로드 -> 판매글 수정 -> 판매글 삭제 순으로 진행됩니다.


https://github.com/DPODW/OurBook/assets/110981825/5cbc8f38-4542-4b6b-9523-4a63fc01ae1d


<br></br>
<br></br>
**2. 구매 요청**
- 판매글 구매 요청 -> 구매 요청 내역 확인 순으로 진행됩니다.
- '살래요!' 가 늘어난 것을 확인할 수 있습니다.


https://github.com/DPODW/OurBook/assets/110981825/118010bf-8ac4-45ad-8256-9cf6f54a7a01


<br></br>
<br></br>
**3. 구매 요청 메일**
- 구매 요청 메일 형식입니다.
![구매 요청 메일](https://github.com/DPODW/OurBook/assets/110981825/67940eac-8095-44ca-90fb-cce656b9fc0a)
<br><br>
<br></br>

**4. AWS S3 저장 형식**
- AWS S3 버킷에 저장될때, UUID 와 함께 저장됩니다.
<br></br>
![AWS S3](https://github.com/DPODW/OurBook/assets/110981825/f5691105-fd94-47a2-9016-2e9612755a36)
<hr>
<br></br>
<br></br>




## 문의 ❓
☆ **문의글 권한**

☆ **답변 여부**

☆ **문의 내역 확인 가능**

(비회원 이용 불가)

### [문의글 권한]
- [관리자] 권한을 가진 회원만이 문의글에 답변이 가능합니다.
   - 관리자 권한 회원만 답변창을 볼 수 있습니다.

- 문의글 열람은 작성자와 관리자만 가능합니다.
  
- 관리자는 문의글 삭제는 가능하나, 수정은 하지 못합니다.
  
- 답변이 등록된 문의는, 작성자가 수정하지 못합니다.
<br></br>

### [답변 여부]
- 답변 여부는 문의 리스트의 [O X] 를 통해 확인 할 수 있습니다.
<br></br>

### 📷[동작 영상 및 사진]📷

**1. 문의글 게시**
- 문의글 작성 -> 문의글 수정 -> 문의 내역 조회 순으로 진행됩니다.


https://github.com/DPODW/OurBook/assets/110981825/dba3bca7-d8fe-475a-893b-bfdc2987f81b


<br></br>
<br></br>
**2. 문의글 답변**
- 문의글 답변 등록 -> 답변 확인 -> 답변 삭제 -> 문의글 삭제 (관리자 권한) 순으로 진행됩니다.


https://github.com/DPODW/OurBook/assets/110981825/329130c8-87d1-43ff-8886-fb01aa92ae05


<br></br>
<br></br>
**3. 문의자 답변 완료 시점**
- 관리자가 아닌 문의자가 답변 완료 문의글을 보는 시점입니다.
- 문의자는 답변창을 볼수 없습니다.
<br></br>
![문의 완료](https://github.com/DPODW/OurBook/assets/110981825/dfb80d1c-2720-413f-bb42-e05d84dcbfe2)
<br></br>
<hr>
<br></br>
<br></br>

## 부가 기능 ➕
☆ **도서검색 기능**

☆ **근처 도서관 검색 기능**
<br></br>

### [도서검색 기능]
- OurBook 이 판매하는 도서와, 도서시장에 판매되고 도서 중에서 검색합니다.
- 띄어쓰기를 무시합니다.
<br></br>

 ### [근처 도서관 검색 기능]
- 네이버 지도 API 와 네이버 지역 검색 API 를 활용하였습니다.
  
- 지역 검색 API 를 통해 얻은 주소 정보를 이용하여 지도 위치를 이동시킵니다.
  
- LocalStorage에 드롭다운 값을 저장하여 새로고침시에도 드롭다운 값이 유지되도록 합니다.


### 📷[동작 영상 및 사진]📷
**1. 도서 검색**
- 도서를 검색하고, 검색된 도서를 바로 조회합니다.


https://github.com/DPODW/OurBook/assets/110981825/5a551d33-d9a4-4cf1-b768-6a82a45852a7


<br></br>
<br></br>
**2. 도서관 검색**
- 위치를 설정하고, 해당 위치 근방의 도서관을 검색합니다.
- 지도로 도서관 위치를 확인할 수 있습니다.



https://github.com/DPODW/OurBook/assets/110981825/919b5d2e-d16e-4fbb-abd6-ff4d02b2be14



<br></br>
## 4. 많이 고민했던 부분 👾

개발 중, 많이 고민했던 이슈에 대해서 정리하였습니다

- [결제 실패 AJAX 동작 이슈](https://github.com/DPODW/OurBook/wiki/결제-실패-AJAX-동작-이슈)
  
- [application.properties 값 @Value Null 이슈](https://github.com/DPODW/OurBook/wiki/application.properties-값-끌어오기-Null-이슈)


