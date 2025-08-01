package com.example.impulsecoachapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// 로그인 상태 추적 및 로그아웃
/*
- 모든 로그인 관련 상태는 UserViewModel에 집중하세요.
- 로그아웃 시 관련 상태 초기화도 여기서 처리하는 게 유지보수에 좋아요.
- Navigation과 ViewModel의 책임을 혼동하지 않는 구조가 이상적입니다.
* */
class UserViewModel : ViewModel() {
    var isLoggedIn by mutableStateOf(true)
        private set

    fun logout() {
        // FirebaseAuth.getInstance().signOut() 같은 로직; firebase 로그인 시 수정할 것
        isLoggedIn = false
    }
}
