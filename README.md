## 이화여자대학교 2025 spring capstone design project – [TETS]

본 레포지토리는 2025년 spring 이화여자대학교 컴퓨터공학과 캡스톤 디자인 프로젝트로 진행한 **[TETS]**의 소스코드와 자료를 정리한 공간입니다.

## 📌 프로젝트 개요

- 만들고자 하는 것: LLM fine tuning을 통해 RST 모델 기반 맞춤형 충동소비 코칭을 제공하는 모바일 앱

## 🗂️ 레포지토리 구성
<pre>
├── frontend-android/              # Jetpack Compose 기반 Android 앱
│   ├── app/                       # Main app 모듈
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/com/example/impulsecoachapp/
│   │   │   │   │   ├── ui/        # Compose UI 관련
│   │   │   │   │   ├── model/     # 데이터 모델
│   │   │   │   │   ├── viewmodel/ # 뷰모델
│   │   │   │   │   ├── sound/     # 사운드 관련 클래스
│   │   │   │   │   ├── navigation/# 내비게이션 관리
│   │   │   │   └── resources/
│   │   └── build.gradle.kts
│   └── build.gradle.kts (Project)
│
├── backend-api/                  # FastAPI 백엔드 (구조 변경 가능)
│   ├── app/
│   │   ├── main.py               # FastAPI 진입점
│   │   ├── api/                  # API 라우터
│   │   ├── models/               # Pydantic 모델
│   │   ├── db/                   # DB 연결 및 모델
│   │   └── services/             # 비즈니스 로직
│   └── requirements.txt
│
├── firebase/                     # Firebase 관련 파일 (Firestore 규칙, 인증 등) (구조 변경 가능)
│   ├── firestore.rules
│   ├── firestore.indexes.json
│   └── firebase.json
│
├── GroundRule.md
└── README.md 
</pre>
