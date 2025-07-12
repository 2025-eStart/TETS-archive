package com.example.impulsecoachapp.ui.screens.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text as LegacyText
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yourapp.data.Transaction
import com.example.impulsecoachapp.ui.components.BottomTab
import com.example.impulsecoachapp.ui.components.ScreenScaffold
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ReportScreen(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
    viewModel: ReportViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    LaunchedEffect(uiState.selectedTransaction) {
        if (uiState.selectedTransaction != null) {
            coroutineScope.launch { modalSheetState.show() }
        } else {
            coroutineScope.launch { modalSheetState.hide() }
        }
    }

    LaunchedEffect(modalSheetState.isVisible) {
        if (!modalSheetState.isVisible) {
            viewModel.dismissTransactionDetails()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            TransactionDetailSheet(transaction = uiState.selectedTransaction)
        }
    ) {
        ScreenScaffold(
            selectedTab = selectedTab,
            onTabSelected = onTabSelected
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1C1B1F))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReportHeader(
                    date = uiState.selectedDate,
                    onCalendarClick = { viewModel.toggleCalendar(true) }
                )
                Spacer(modifier = Modifier.height(24.dp))
                DailySummaryCard()
                Spacer(modifier = Modifier.height(24.dp))
                TransactionList(
                    transactions = uiState.transactions,
                    onTransactionClick = { viewModel.showTransactionDetails(it) },
                    onImpulseCheckChanged = { transaction, isChecked ->
                        viewModel.onImpulseCheckChanged(transaction, isChecked)
                    }
                )

                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }

            if (uiState.showCalendar) {
                ReportCalendarDialog(
                    initialDate = uiState.selectedDate,
                    onDateSelected = { newDate ->
                        viewModel.toggleCalendar(false)
                        viewModel.loadTransactionsForDate(newDate)
                    },
                    onDismiss = { viewModel.toggleCalendar(false) }
                )
            }
        }
    }
}

@Composable
fun ReportHeader(date: LocalDate, onCalendarClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일")),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = onCalendarClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A4458))
        ) {
            Text("다른 날짜 보기", color = Color.White)
        }
    }
}

@Composable
fun DailySummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E0F4))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "오늘의 소비 한줄평",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "잘 참았어요!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun TransactionList(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit,
    onImpulseCheckChanged: (Transaction, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("시간", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                Text("내용", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(2f))
                Text("금액", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(2f), textAlign = TextAlign.End)
                Text("결제 방법", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(2f), textAlign = TextAlign.Center)
                Text("충동소비", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center)
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }

        if (transactions.isEmpty()) {
            item {
                Text(
                    text = "소비 내역이 없어요.",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 50.dp),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            items(transactions, key = { it.id }) { transaction ->
                TransactionListItem(
                    transaction = transaction,
                    onClick = { onTransactionClick(transaction) },
                    onCheckChanged = { isChecked -> onImpulseCheckChanged(transaction, isChecked) }
                )
            }
        }
    }
}

@Composable
fun TransactionListItem(
    transaction: Transaction,
    onClick: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(transaction.time.format(DateTimeFormatter.ofPattern("HH:mm")), color = Color.White, modifier = Modifier.weight(1.5f))
        Text(transaction.description, color = Color.White, modifier = Modifier.weight(2f))
        Text(
            text = "%,d원".format(transaction.amount),
            color = Color.White,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
        Text(transaction.paymentMethod, color = Color.White, modifier = Modifier.weight(2f), textAlign = TextAlign.Center)
        Box(modifier = Modifier.weight(1.5f), contentAlignment = Alignment.Center) {
            Checkbox(
                checked = transaction.isImpulseBuy,
                onCheckedChange = onCheckChanged
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportCalendarDialog(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    val selectedDateMillis = datePickerState.selectedDateMillis
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                selectedDateMillis?.let {
                    val selectedDate = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                    onDateSelected(selectedDate)
                }
            }) {
                Text("확인")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("취소")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun TransactionDetailSheet(transaction: Transaction?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 250.dp)
            .background(Color(0xFF36343B))
            .padding(24.dp)
    ) {
        if (transaction == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("상세 내역을 보려면 소비 내역을 선택해주세요.", color = Color.White)
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "상담 요약",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = transaction.summary ?: "상담 요약 정보가 없습니다.",
                    fontSize = 16.sp,
                    color = Color.White,
                    lineHeight = 24.sp
                )
            }
        }
    }
}
