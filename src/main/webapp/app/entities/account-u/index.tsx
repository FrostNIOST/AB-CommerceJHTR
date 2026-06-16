import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AccountU from './account-u';
import AccountUDeleteDialog from './account-u-delete-dialog';
import AccountUDetail from './account-u-detail';
import AccountUUpdate from './account-u-update';

const AccountURoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AccountU />} />
    <Route path="new" element={<AccountUUpdate />} />
    <Route path=":id">
      <Route index element={<AccountUDetail />} />
      <Route path="edit" element={<AccountUUpdate />} />
      <Route path="delete" element={<AccountUDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AccountURoutes;
