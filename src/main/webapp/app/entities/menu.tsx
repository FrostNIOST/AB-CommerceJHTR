import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/document-type">
        Document Type
      </MenuItem>
      <MenuItem icon="asterisk" to="/address">
        Address
      </MenuItem>
      <MenuItem icon="asterisk" to="/account-u">
        Account U
      </MenuItem>
      <MenuItem icon="asterisk" to="/category">
        Category
      </MenuItem>
      <MenuItem icon="asterisk" to="/sub-category">
        Sub Category
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        Product
      </MenuItem>
      <MenuItem icon="asterisk" to="/wishlist">
        Wishlist
      </MenuItem>
      <MenuItem icon="asterisk" to="/cart">
        Cart
      </MenuItem>
      <MenuItem icon="asterisk" to="/cart-item">
        Cart Item
      </MenuItem>
      <MenuItem icon="asterisk" to="/order">
        Order
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-item">
        Order Item
      </MenuItem>
      <MenuItem icon="asterisk" to="/payment-method">
        Payment Method
      </MenuItem>
      <MenuItem icon="asterisk" to="/invoice">
        Invoice
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
