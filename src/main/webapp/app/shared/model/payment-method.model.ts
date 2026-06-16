import { State } from 'app/shared/model/enumerations/state.model';

export interface IPaymentMethod {
  id?: string;
  namePaymentMethod?: string;
  cod?: string;
  description?: string | null;
  type?: string;
  apiKey?: string | null;
  endpoint?: string | null;
  state?: keyof typeof State;
}

export const defaultValue: Readonly<IPaymentMethod> = {};
