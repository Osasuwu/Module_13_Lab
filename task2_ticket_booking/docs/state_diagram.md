# Диаграмма состояний: Система бронирования билетов

```mermaid
stateDiagram-v2
    [*] --> Created: createRequest()

    Created --> AwaitingPayment: sendToClient()
    
    AwaitingPayment --> Paid: processPayment()
    AwaitingPayment --> Cancelled: paymentTimeout()
    
    Paid --> Confirmed: verifyBooking()
    
    Confirmed --> [*]: Бронирование завершено
    
    Cancelled --> [*]: Заявка отменена

    note right of Created
        Заявка только что создана.
        Ожидает отправки клиенту.
    end note

    note right of AwaitingPayment
        Заявка отправлена клиенту.
        Ожидается оплата в установленный срок.
    end note

    note right of Paid
        Клиент произвел оплату.
        Ожидается подтверждение бронирования.
    end note

    note right of Confirmed
        Бронирование подтверждено системой.
        Финальное успешное состояние.
    end note

    note right of Cancelled
        Заявка отменена (истек срок оплаты).
        Финальное состояние.
    end note
```

## Описание состояний

| Состояние | Описание |
|-----------|----------|
| Created | Заявка только что создана |
| AwaitingPayment | Заявка отправлена клиенту для оплаты |
| Paid | Клиент завершил оплату |
| Confirmed | Система подтвердила успешное бронирование |
| Cancelled | Заявка была отменена |

## Правила переходов

| Из состояния | В состояние | Событие | Условие |
|--------------|-------------|---------|---------|
| [*] | Created | createRequest() | Создание новой заявки |
| Created | AwaitingPayment | sendToClient() | Отправка клиенту |
| AwaitingPayment | Paid | processPayment() | Клиент произвел оплату |
| AwaitingPayment | Cancelled | paymentTimeout() | Истек срок оплаты |
| Paid | Confirmed | verifyBooking() | Проверка бронирования успешна |
| Confirmed | [*] | - | Финальное состояние |
| Cancelled | [*] | - | Финальное состояние |
