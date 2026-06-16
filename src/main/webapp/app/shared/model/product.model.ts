import { State } from 'app/shared/model/enumerations/state.model';
import { ISubCategory } from 'app/shared/model/sub-category.model';

export interface IProduct {
  id?: string;
  productName?: string;
  description?: string | null;
  purchasePrice?: number;
  sellingPrice?: number;
  imgProductContentType?: string | null;
  imgProduct?: string | null;
  slug?: string;
  stock?: number;
  state?: keyof typeof State;
  subCategory?: ISubCategory;
}

export const defaultValue: Readonly<IProduct> = {};
