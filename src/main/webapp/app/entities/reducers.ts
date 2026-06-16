import accountU from 'app/entities/account-u/account-u.reducer';
import address from 'app/entities/address/address.reducer';
import cart from 'app/entities/cart/cart.reducer';
import cartItem from 'app/entities/cart-item/cart-item.reducer';
import category from 'app/entities/category/category.reducer';
import documentType from 'app/entities/document-type/document-type.reducer';
import invoice from 'app/entities/invoice/invoice.reducer';
import order from 'app/entities/order/order.reducer';
import orderItem from 'app/entities/order-item/order-item.reducer';
import paymentMethod from 'app/entities/payment-method/payment-method.reducer';
import product from 'app/entities/product/product.reducer';
import subCategory from 'app/entities/sub-category/sub-category.reducer';
import wishlist from 'app/entities/wishlist/wishlist.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  documentType,
  address,
  accountU,
  category,
  subCategory,
  product,
  wishlist,
  cart,
  cartItem,
  order,
  orderItem,
  paymentMethod,
  invoice,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
