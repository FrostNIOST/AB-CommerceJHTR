import { IOrder } from 'app/shared/model/order.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IOrderItem {
  id?: string;
  productName?: string;
  sellingPrice?: number;
  quantity?: number;
  subtotal?: number;
  product?: IProduct;
  order?: IOrder;
}

export const defaultValue: Readonly<IOrderItem> = {};
