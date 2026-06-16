import { IAccountU } from 'app/shared/model/account-u.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface IAddress {
  id?: string;
  adress?: string;
  city?: string;
  department?: string | null;
  postalCode?: string | null;
  state?: keyof typeof State;
  account?: IAccountU;
}

export const defaultValue: Readonly<IAddress> = {};
