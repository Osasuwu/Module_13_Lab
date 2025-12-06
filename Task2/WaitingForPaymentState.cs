using System;

namespace DesignPatternsLab13.Task2
{
    /// <summary>
    /// Состояние: WaitingForPayment - Заявка ожидает оплаты
    /// </summary>
    public class WaitingForPaymentState : IBookingRequestState
    {
        public void SendToClient(BookingRequestContext context)
        {
            Console.WriteLine("ℹ️ Заявка уже отправлена клиенту.");
        }

        public void MakePayment(BookingRequestContext context)
        {
            // Проверка срока оплаты
            if (context.IsPaymentExpired())
            {
                Console.WriteLine("❌ Срок оплаты истек! Заявка автоматически отменена.");
                context.AddToHistory("Срок оплаты истек - автоматическая отмена");
                context.SetState(new CancelledState());
                return;
            }

            Console.WriteLine($"✅ Оплата получена: {context.TicketPrice:C}");
            Console.WriteLine($"💳 Билет оплачен клиентом {context.ClientName}");
            context.AddToHistory($"Оплата получена: {context.TicketPrice:C}");
            context.SetState(new PaidState());
        }

        public void ConfirmBooking(BookingRequestContext context)
        {
            Console.WriteLine("❌ Невозможно подтвердить. Сначала необходимо получить оплату.");
        }

        public void CancelRequest(BookingRequestContext context)
        {
            Console.WriteLine($"❌ Заявка {context.RequestId} отменена.");
            Console.WriteLine("   Клиент не успел оплатить в установленный срок.");
            context.AddToHistory("Заявка отменена - клиент не оплатил");
            context.SetState(new CancelledState());
        }

        public string GetStateName()
        {
            return "Ожидает оплаты (WaitingForPayment)";
        }

        public void PrintAvailableActions()
        {
            Console.WriteLine("\n✨ Доступные действия:");
            Console.WriteLine("  → Произвести оплату (MakePayment)");
            Console.WriteLine("  → Отменить заявку (CancelRequest)");
        }
    }
}

