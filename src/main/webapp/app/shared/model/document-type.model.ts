import { State } from 'app/shared/model/enumerations/state.model';

export interface IDocumentType {
  id?: string;
  cod?: string;
  documentType?: string;
  state?: keyof typeof State;
}

export const defaultValue: Readonly<IDocumentType> = {};
