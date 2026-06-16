import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Cart from './cart';
import CartDeleteDialog from './cart-delete-dialog';
import CartDetail from './cart-detail';
import CartUpdate from './cart-update';

const CartRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Cart />} />
    <Route path="new" element={<CartUpdate />} />
    <Route path=":id">
      <Route index element={<CartDetail />} />
      <Route path="edit" element={<CartUpdate />} />
      <Route path="delete" element={<CartDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CartRoutes;
