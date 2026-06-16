import { IDocumentType } from 'app/shared/model/document-type.model';
import { IUser } from 'app/shared/model/user.model';

export interface IAccountU {
  id?: string;
  documentNumber?: string;
  firstName?: string;
  secondName?: string | null;
  firstLastName?: string;
  secondLastName?: string | null;
  user?: IUser;
  documentType?: IDocumentType;
}

export const defaultValue: Readonly<IAccountU> = {};
