import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AccountU from './account-u';
import Address from './address';
import Cart from './cart';
import CartItem from './cart-item';
import Category from './category';
import DocumentType from './document-type';
import Invoice from './invoice';
import Order from './order';
import OrderItem from './order-item';
import PaymentMethod from './payment-method';
import Product from './product';
import SubCategory from './sub-category';
import Wishlist from './wishlist';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="/document-type/*" element={<DocumentType />} />
        <Route path="/address/*" element={<Address />} />
        <Route path="/account-u/*" element={<AccountU />} />
        <Route path="/category/*" element={<Category />} />
        <Route path="/sub-category/*" element={<SubCategory />} />
        <Route path="/product/*" element={<Product />} />
        <Route path="/wishlist/*" element={<Wishlist />} />
        <Route path="/cart/*" element={<Cart />} />
        <Route path="/cart-item/*" element={<CartItem />} />
        <Route path="/order/*" element={<Order />} />
        <Route path="/order-item/*" element={<OrderItem />} />
        <Route path="/payment-method/*" element={<PaymentMethod />} />
        <Route path="/invoice/*" element={<Invoice />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
