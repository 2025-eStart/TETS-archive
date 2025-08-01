## 📘 BAS 기반 충동 소비 관리 RAG 문서

### 🔵 BAS 유형 설명 및 성향

- **Goal-Drive Persistence (GDP)**

  - 계획적, 자기통제 강함, 목표 중심적
  - 예: 목표를 세우면 끝까지 해내는 성향

- **Reward Interest (RI)**

  - 호기심 많고 새로운 자극 추구
  - 예: 새로운 것에 쉽게 흥미를 느낌

- **Reward Reactivity (RR)**

  - 감정적으로 보상에 크게 반응
  - 예: 좋은 일이 생기면 기분이 매우 좋아짐

- **Impulsivity (IMP)**

  - 충동적이고 즉흥적인 행동 성향
  - 예: 생각보다 행동이 먼저 나오는 경향

---

### 🎲 BAS 유형별 대체행동 추천

#### 🎯 GDP (계획 추진형)

- 소비 목표 이미지 보기: 목표 상기
- 나만의 소비 규칙 리마인드: 자기통제 강화
- 미래의 나에게 쪽지 쓰기: 장기적 목표 인식
- 소비 안 했다고 칭찬 받기: 자기효능감 증대
- 앱 속 캐릭터 성장시키기: 목표 성취 감각 제공

#### 🧠 RI (보상 탐색형)

- 위시리스트 저장하기: 구매 충동을 미루고 관리
- 유튜브 미니 영상 보기: 자극 추구 욕구 해소
- 뇌과자 콘텐츠 보기: 지출 없이 흥미 충족
- 퀴즈 앱 실행하기: 관심 전환 및 자극 제공
- 친구에게 신기한 콘텐츠 공유하기: 사회적 흥미 충족

#### 💓 RR (보상 반응형)

- 일기 쓰기: 감정 인식 및 표현
- 감정 기록 앱 열기: 감정의 즉각적 해소
- 간식 먹기: 즉각적 감각 보상 제공
- 소비 안 했다고 칭찬 받기: 긍정적 피드백
- 앱 속 캐릭터 보상 받기: 대체적 보상 제공

#### ⚡ IMP (충동 반응형)

- 3초 체크리스트 작성: 즉각적 행동 제어
- 1분 타이머 맞추기: 잠시 멈춤과 욕구 평가
- 스트레칭 또는 팔굽혀펴기: 신체 활동으로 주의 전환
- 냉장고 청소하기: 행동으로 충동을 분산
- 퀴즈 앱 실행하기: 정신적 몰입으로 충동 제어

---

### ✅ BAS 4요인 자가진단 설문 (10문항, 5점 척도)

응답: 전혀 아니다(1) – 매우 그렇다(5)

| 문항 번호 | 문항 내용                                 | 요인  |
| ----- | ------------------------------------- | --- |
| Q1    | 나는 목표를 세우면 끝까지 해내려고 노력하는 편이다.         | GDP |
| Q2    | 나는 일을 미루기보다는 미리 계획하고 실천하는 편이다.        | GDP |
| Q3    | 나는 새로운 물건이나 서비스에 쉽게 관심이 생긴다.          | RI  |
| Q4    | 나는 지루함을 참기 어렵고 자극적인 것을 찾는 편이다.        | RI  |
| Q5    | 나는 좋은 일이 생기면 하루 종일 기분이 좋아진다.          | RR  |
| Q6    | 나는 보상을 받을 때 감정적으로 크게 반응한다.            | RR  |
| Q7    | 나는 하고 싶은 일이 생기면 깊이 생각하지 않고 실행하는 편이다.  | IMP |
| Q8    | 나는 즉흥적으로 결정을 내리는 일이 많다.               | IMP |
| Q9    | 나는 쇼핑 중 갑자기 사고 싶은 물건이 생기면 곧바로 사는 편이다. | IMP |
| Q10   | 나는 보상을 기대할 때 매우 들뜨고 흥분된다.             | RR  |

---

### 🧮 Python 계산 함수

```python
def calculate_bas_profile(answers: dict):
    subscales = {
        'GDP': [1, 2],
        'RI': [3, 4],
        'RR': [5, 6, 10],
        'IMP': [7, 8, 9]
    }

    scores = {name: sum(answers[q] for q in qs) / len(qs) for name, qs in subscales.items()}
    dominant_type = max(scores, key=scores.get)

    return {**scores, "dominant_type": dominant_type}
```

---

### 🔎 추천 로직 (프롬프트 예시)

"사용자는 GDP 유형이며 충동구매를 느끼고 있습니다. 이를 극복할 수 있는 적절한 대체 행동을 3가지 추천해주세요."

이 문서의 정보를 기반으로 챗봇이 맞춤형 대체 행동을 제안할 수 있음.

