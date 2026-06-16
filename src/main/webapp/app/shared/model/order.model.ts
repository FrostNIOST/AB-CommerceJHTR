import { IAccountU } from 'app/shared/model/account-u.model';
import { IAddress } from 'app/shared/model/address.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { State } from 'app/shared/model/enumerations/state.model';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';

export interface IOrder {
  id?: string;
  orderNumber?: string;
  subtotal?: number;
  subtotalIva?: number;
  shippingCost?: number;
  total?: number;
  status?: keyof typeof OrderStatus;
  state?: keyof typeof State;
  paymentMethod?: IPaymentMethod;
  shippingAddress?: IAddress;
  account?: IAccountU;
}

export const defaultValue: Readonly<IOrder> = {};
