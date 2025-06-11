## 이화여자대학교 2025 spring capstone design project – [TETS]

본 레포지토리는 2025년 spring 이화여자대학교 컴퓨터공학과 캡스톤 디자인 프로젝트로 진행한 **[TETS]**의 소스코드와 자료를 정리한 공간입니다.

## 📌 프로젝트 개요

- 만들고자 하는 것: 일기와 소비 데이터를 바탕으로, LLM과 시계열 분석을 통해 RST 모델 기반 맞춤형 충동소비 코칭을 제공하는 모바일 앱

## 🗂️ 레포지토리 구성
├── backend/ # FastAPI 
    ├── app/
    
├── frontend/ # Android 모바일 앱

├── dataset/ # 테스트용 데이터셋 및 샘플 입력

├── models/ # AI/ML 모델 관련 코드 및 결과

├── docs/ # 설계 문서 및 발표 자료

└── README.md # 레포지토리 소개 파일
'''
TETS/
├── backend/  # FastAPI 백엔드 API 서버
│   ├── app/
│   │   ├── main.py            # FastAPI 서버 진입점
│   │   ├── routes/            # API 라우팅 (예: /score, /parse)
│   │   ├── services/          # 비즈니스 로직 (점수 계산, 알림 파싱 등)
│   │   ├── models/            # Pydantic 모델 정의
│   │   └── config.py          # 사용자 설정 및 시간대 기준 등
│   └── requirements.txt
│
├── frontend/  # Android 모바일 앱 (Java/Kotlin or Flutter)
│   ├── app/
│   │   ├── ui/                # 화면 구성
│   │   ├── api/               # 백엔드 연동
│   │   └── utils/             # 알림 수신, 시간대 감지 등
│   └── build.gradle (또는 pubspec.yaml)
│
├── dataset/  # 샘플 데이터 및 테스트용 데이터셋
│   ├── sample_notifications.csv
│   └── category_mapping.json
│
├── models/  # 머신러닝 모델 코드 및 저장 파일
│   ├── rule_based/            # rule 로직 (scoring.py 등)
│   ├── ml/                    # ML 모델 학습/추론 코드
│   └── trained_models/        # pkl, joblib 등 저장된 모델
│
├── docs/  # 발표자료, API 명세, 설계 문서
│   ├── architecture.md
│   ├── impulse_flowchart.png
│   └── API_SPEC.md
│
└── README.md           
'''


<pre> impulse-detector/ ├── backend/ # FastAPI 백엔드 API 서버 │ ├── app/ │ │ ├── main.py # FastAPI 엔트리포인트 │ │ ├── routes/ # API 라우팅 (예: /score, /parse) │ │ ├── services/ # 점수 계산, 알림 파싱 등 비즈니스 로직 │ │ ├── models/ # Pydantic 모델 정의 │ │ └── config.py # 사용자 설정 및 시간대 기준 등 │ └── requirements.txt │ ├── frontend/ # Android 앱 (Java/Kotlin or Flutter) │ ├── app/ │ │ ├── ui/ # 화면 구성 │ │ ├── api/ # 백엔드 연동 │ │ └── utils/ # 알림 수신, 시간대 감지 등 │ └── build.gradle (또는 pubspec.yaml) │ ├── dataset/ # 샘플 데이터 및 테스트셋 │ ├── sample_notifications.csv │ └── category_mapping.json │ ├── models/ # 머신러닝 모델 코드 및 저장 파일 │ ├── rule_based/ # Rule 로직 (예: scoring.py) │ ├── ml/ # ML 모델 학습/추론 코드 │ └── trained_models/ # 저장된 모델 (joblib, pkl 등) │ ├── docs/ # 발표자료, API 명세, 설계 문서 │ ├── architecture.md │ ├── impulse_flowchart.png │ └── API_SPEC.md │ └── README.md # 프로젝트 소개 및 실행법 </pre>
