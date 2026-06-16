import { IProduct } from 'app/shared/model/product.model';

export interface ICartItem {
  id?: string;
  quantity?: number;
  product?: IProduct;
}

export const defaultValue: Readonly<ICartItem> = {};
