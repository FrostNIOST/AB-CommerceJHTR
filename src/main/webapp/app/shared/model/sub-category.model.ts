import { ICategory } from 'app/shared/model/category.model';
import { State } from 'app/shared/model/enumerations/state.model';

export interface ISubCategory {
  id?: string;
  subCategoryName?: string;
  description?: string | null;
  state?: keyof typeof State;
  category?: ICategory;
}

export const defaultValue: Readonly<ISubCategory> = {};
