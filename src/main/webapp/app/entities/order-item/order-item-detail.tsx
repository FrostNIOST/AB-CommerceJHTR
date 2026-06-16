import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-item.reducer';

export const OrderItemDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const orderItemEntity = useAppSelector(state => state.orderItem.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderItemDetailsHeading">Order Item</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderItemEntity.id}</dd>
          <dt>
            <span id="productName">Product Name</span>
          </dt>
          <dd>{orderItemEntity.productName}</dd>
          <dt>
            <span id="sellingPrice">Selling Price</span>
          </dt>
          <dd>{orderItemEntity.sellingPrice}</dd>
          <dt>
            <span id="quantity">Quantity</span>
          </dt>
          <dd>{orderItemEntity.quantity}</dd>
          <dt>
            <span id="subtotal">Subtotal</span>
          </dt>
          <dd>{orderItemEntity.subtotal}</dd>
          <dt>Product</dt>
          <dd>{orderItemEntity.product ? orderItemEntity.product.productName : ''}</dd>
          <dt>Order</dt>
          <dd>{orderItemEntity.order ? orderItemEntity.order.id : ''}</dd>
        </dl>
        <Button as={Link as any} to="/order-item" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/order-item/${orderItemEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderItemDetail;
