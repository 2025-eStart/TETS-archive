package com.example.impulsecoachapp.data

import java.time.LocalTime

data class Transaction(
    //로그인 시 생성되는 사용자 ID
    val userId: String, // 사용자 ID

    // 결제 푸시 알림으로부터 불러와 DB에 저장되는 데이터
    val transactionId: String, // 결제 내역 ID
    val time: LocalTime, // 결제 시간
    val description: String,   // 결제 내용 (예: "배달음식")
    val amount: Int, // 결제 금액
    val paymentMethod: String, // 결제 수단 (예: "카드")
    var isImpulseBuy: Boolean = false, // 충동소비 여부 (초기값: false), 화면에서 사용자가 바꿀 수 있음, 상담에서 바꿀 수 있음

    // 상담 관련 데이터
    // Todo: 챗봇 API 연결 후 summary 수정하기
    val counselingId: String? = null, // 상담 ID (초기값: null)
    val summary: String? = "소비 내용: 배달음식\n원인: 스트레스\n실천할 대체 행동: 10분 산책하기" // 상담 요약 정보 (API를 통해 받아올 데이터)
)