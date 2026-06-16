import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { Link, useParams } from 'react-router';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order.reducer';

export const OrderDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const orderEntity = useAppSelector(state => state.order.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDetailsHeading">Order</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderEntity.id}</dd>
          <dt>
            <span id="orderNumber">Order Number</span>
          </dt>
          <dd>{orderEntity.orderNumber}</dd>
          <dt>
            <span id="subtotal">Subtotal</span>
          </dt>
          <dd>{orderEntity.subtotal}</dd>
          <dt>
            <span id="subtotalIva">Subtotal Iva</span>
          </dt>
          <dd>{orderEntity.subtotalIva}</dd>
          <dt>
            <span id="shippingCost">Shipping Cost</span>
          </dt>
          <dd>{orderEntity.shippingCost}</dd>
          <dt>
            <span id="total">Total</span>
          </dt>
          <dd>{orderEntity.total}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{orderEntity.status}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{orderEntity.state}</dd>
          <dt>Payment Method</dt>
          <dd>{orderEntity.paymentMethod ? orderEntity.paymentMethod.namePaymentMethod : ''}</dd>
          <dt>Shipping Address</dt>
          <dd>{orderEntity.shippingAddress ? orderEntity.shippingAddress.adress : ''}</dd>
          <dt>Account</dt>
          <dd>{orderEntity.account ? orderEntity.account.id : ''}</dd>
        </dl>
        <Button as={Link as any} to="/order" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/order/${orderEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderDetail;
