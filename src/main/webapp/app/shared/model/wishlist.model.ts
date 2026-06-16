import dayjs from 'dayjs';

import { IAccountU } from 'app/shared/model/account-u.model';
import { State } from 'app/shared/model/enumerations/state.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IWishlist {
  id?: string;
  state?: keyof typeof State;
  updateAt?: dayjs.Dayjs;
  account?: IAccountU;
  productses?: IProduct[] | null;
}

export const defaultValue: Readonly<IWishlist> = {};
