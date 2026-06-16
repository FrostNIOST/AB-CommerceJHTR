import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';
import { State } from 'app/shared/model/enumerations/state.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IInvoice {
  id?: string;
  pref?: string | null;
  invoiceNumber?: string;
  paymentStatus?: keyof typeof PaymentStatus;
  refTransaction?: string | null;
  state?: keyof typeof State;
  order?: IOrder;
}

export const defaultValue: Readonly<IInvoice> = {};
