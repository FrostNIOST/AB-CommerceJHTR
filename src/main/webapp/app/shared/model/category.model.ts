import { State } from 'app/shared/model/enumerations/state.model';

export interface ICategory {
  id?: string;
  categoryName?: string;
  description?: string | null;
  state?: keyof typeof State;
}

export const defaultValue: Readonly<ICategory> = {};
