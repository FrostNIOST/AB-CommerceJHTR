import { IAccountU } from 'app/shared/model/account-u.model';
import { ICartItem } from 'app/shared/model/cart-item.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ICart {
  id?: string;
  state?: keyof typeof State;
  account?: IAccountU;
  itemses?: ICartItem[] | null;
}

export const defaultValue: Readonly<ICart> = {};
