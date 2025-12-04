# Диаграмма состояний: Система бронирования номера в гостинице

```mermaid
stateDiagram-v2
    [*] --> Idle: Запуск системы

    Idle --> RoomSelected: selectRoom()
    
    RoomSelected --> Idle: cancel()
    RoomSelected --> RoomSelected: changeRoom()
    RoomSelected --> BookingConfirmed: confirmBooking()
    
    BookingConfirmed --> BookingCancelled: cancel()
    BookingConfirmed --> Paid: pay()
    
    Paid --> [*]: Бронирование завершено
    
    BookingCancelled --> [*]: Бронирование отменено

    note right of Idle
        Начальное состояние.
        Система ожидает выбора номера.
    end note

    note right of RoomSelected
        Номер выбран, но не подтвержден.
        Можно изменить номер или отменить.
    end note

    note right of BookingConfirmed
        Бронирование подтверждено.
        Ожидается оплата.
    end note

    note right of Paid
        Бронирование оплачено.
        Номер закреплен за клиентом.
    end note

    note right of BookingCancelled
        Бронирование отменено.
        Финальное состояние.
    end note
```

## Описание состояний

| Состояние | Описание |
|-----------|----------|
| Idle | Начальное состояние. Система ожидает действия пользователя |
| RoomSelected | Пользователь выбрал номер, но не подтвердил бронирование |
| BookingConfirmed | Бронирование подтверждено, но не оплачено |
| Paid | Бронирование оплачено, номер закреплен за пользователем |
| BookingCancelled | Бронирование отменено |

## Переходы

| Из состояния | В состояние | Событие | Условие |
|--------------|-------------|---------|---------|
| Idle | RoomSelected | selectRoom() | Номер доступен |
| RoomSelected | Idle | cancel() | - |
| RoomSelected | RoomSelected | changeRoom() | Новый номер доступен |
| RoomSelected | BookingConfirmed | confirmBooking() | - |
| BookingConfirmed | BookingCancelled | cancel() | - |
| BookingConfirmed | Paid | pay() | Оплата успешна |
| Paid | [*] | - | Финальное состояние |
| BookingCancelled | [*] | - | Финальное состояние |
